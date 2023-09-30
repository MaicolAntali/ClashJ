package com.clashj

import com.clashj.exception.ClashJException
import com.clashj.http.query.SearchClanQuery
import com.clashj.model.clan.component.ClanMemberRole
import com.clashj.model.clan.component.ClanType
import com.clashj.model.clan.component.WarState
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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

            val api = ClientBuilder("email", "pwd").engine(mockEngine).build()

            val clan = api.getClan("tag").await()

            assertThat(clan.tag).isEqualTo("#CVC0QUG0")
            assertThat(clan.location?.countryCode).isEqualTo("LS")
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

            val api = ClientBuilder("email", "pwd").engine(mockEngine).build()

            val clan = api.getClanCurrentWar("tag").await()

            assertThat(clan.attacksPerMember).isEqualTo(2)
            assertThat(clan.state).isEqualTo(WarState.IN_WAR)
            assertThat(clan.teamSize).isEqualTo(50)
            assertThat(clan.clan.members).hasSize(50)
            assertThat(clan.opponent.members).hasSize(50)
        }
    }

    @Test
    fun `searchClan should throw ClashJException`() {
        runBlocking {
            val mockEngine = MockEngine { _ ->
                respondBadRequest()
            }

            val api = ClientBuilder("email", "pwd").engine(mockEngine).build()

            val e = assertThrows<ClashJException> { api.searchClan(SearchClanQuery()).await() }
            assertThat(e).hasMessageContaining("Not able to handle this response")
        }
    }

    @Test
    fun `getPlayer should generate a Player obj`() {
        runBlocking {
            val mockEngine = MockEngine { _ ->
                respond(
                    File("src/test/resources/json/getPlayer.json").readText(),
                    headers = headers { append(HttpHeaders.ContentType, "application/json") }
                )
            }

            val api = ClientBuilder("email", "pwd").engine(mockEngine).build()

            val player = api.getPlayer("tag").await()

            assertThat(player.name).isEqualTo("Maicol :)")
            assertThat(player.clan?.name).isEqualTo("Stelle Cadenti")
            assertThat(player.heroes).hasSize(6)
            assertThat(player.warStars).isGreaterThan(1500)
            assertThat(player.role).isEqualTo(ClanMemberRole.CO_LEADER)
        }
    }
}
