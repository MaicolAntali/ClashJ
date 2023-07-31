package com.clashj

import com.clashj.http.RequestHandler
import com.clashj.http.option.EngineOptions
import com.clashj.http.option.KeyOptions
import com.clashj.model.clan.component.ClanType
import com.clashj.model.clan.component.WarState
import com.clashj.util.Credential
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

class ClientTest {

    @Test
    fun `getClan should generate a Clan obj`() {
        runBlocking {
            val mockEngine = MockEngine { _ ->
                respond(
                    File("src/test/resources/json/getClan.json").readText(),
                    headers = headers { append(HttpHeaders.ContentType, "application/json") }
                )
            }

            val api = Client(
                RequestHandler(
                    Credential("mail", "pwd"),
                    KeyOptions("...", null, 1),
                    EngineOptions(),
                    mockEngine
                )
            )

            val clan = api.getClan("tag")

            assertThat(clan.tag).isEqualTo("#CVC0QUG0")
            assertThat(clan.location.countryCode).isEqualTo("LS")
            assertThat(clan.type).isEqualTo(ClanType.INVITE_ONLY)
            assertThat(clan.members).isEqualTo(50)
            assertThat(clan.memberList).hasSize(50)
        }

    }

    @Test
    fun `getCurrentWar should generate a ClanWar obj`() {
        runBlocking {
            val mockEngine = MockEngine { _ ->
                respond(
                    File("src/test/resources/json/getCurrentWar.json").readText(),
                    headers = headers { append(HttpHeaders.ContentType, "application/json") }
                )
            }

            val api = Client(
                RequestHandler(
                    Credential("mail", "pwd"),
                    KeyOptions("...", null, 1),
                    EngineOptions(),
                    mockEngine
                )
            )

            val clan = api.getClanCurrentWar("tag")

            assertThat(clan.attacksPerMember).isEqualTo(2)
            assertThat(clan.state).isEqualTo(WarState.IN_WAR)
            assertThat(clan.teamSize).isEqualTo(50)
            assertThat(clan.clan.members).hasSize(50)
            assertThat(clan.opponent.members).hasSize(50)
        }
    }
}
