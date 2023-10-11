package com.clashj

import com.clashj.exception.ClashJException
import com.clashj.http.query.SearchClanQuery
import com.clashj.model.clan.Clan
import com.clashj.model.clan.ClanWar
import com.clashj.model.clan.component.ClanMemberRole
import com.clashj.model.clan.component.ClanType
import com.clashj.model.clan.component.WarState
import com.clashj.model.player.Player
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File


class ClientTest {

    @Nested
    inner class GetClan {

        private lateinit var clan: Clan

        @BeforeEach
        fun setUp() {
            runBlocking {
                val mockEngine = createMockEngineWithResponse("src/test/resources/json/getClan.json")
                val client = createApiWithMockEngine(mockEngine)
                clan = client.getClan("tag").await()
            }
        }

        @Test
        fun `should have the correct tag`() {
            assertThat(clan.tag).isEqualTo("#CVC0QUG0")
        }

        @Test
        fun `should have correct country code`() {
            assertThat(clan.location?.countryCode).isEqualTo("LS")
        }

        @Test
        fun `should have the correct type`() {
            assertThat(clan.type).isEqualTo(ClanType.INVITE_ONLY)
        }

        @Test
        fun `should have the 50 members`() {
            assertThat(clan.members).isEqualTo(50)
            assertThat(clan.memberList).hasSize(50)
        }
    }

    @Nested
    inner class GetCurrentWar {

        private lateinit var war: ClanWar

        @BeforeEach
        fun setUp() {
            runBlocking {
                val mockEngine = createMockEngineWithResponse("src/test/resources/json/getCurrentWar.json")
                val client = createApiWithMockEngine(mockEngine)
                war = client.getClanCurrentWar("tag").await()
            }
        }

        @Test
        fun `should have state equal to IN_WAR`() {
            assertThat(war.state).isEqualTo(WarState.IN_WAR)
        }

        @Test
        fun `should have team size equal to 50`() {
            assertThat(war.teamSize).isEqualTo(50)
        }

        @Test
        fun `should have the clan and opponent member list size equal to 50`() {
            assertThat(war.clan.members).hasSize(50)
            assertThat(war.opponent.members).hasSize(50)
        }

        @Test
        fun `getClanMembers() should be sorted and have a correct size`() {
            val members = war.getClanMembers()
            assertThat(members)
                .extracting("mapPosition", Int::class.java)
                .isSortedAccordingTo(Comparator.naturalOrder())

            assertThat(members).hasSize(war.teamSize)
        }

        @Test
        fun `getClanAttacks() should be sorted and have a correct size`() {
            val attacks = war.getClanAttacks()

            assertThat(attacks)
                .extracting("order", Int::class.java)
                .isSortedAccordingTo(Comparator.naturalOrder())

            assertThat(attacks).hasSize(war.clan.attacks)
        }
    }

    @Nested
    inner class GetPlayer {

        private lateinit var player: Player

        @BeforeEach
        fun setUp() {
            runBlocking {
                val mockEngine = createMockEngineWithResponse("src/test/resources/json/getPlayer.json")
                val client = createApiWithMockEngine(mockEngine)
                player = client.getPlayer("tag").await()
            }
        }

        @Test
        fun `should have name equal to Maicol`() {
            assertThat(player.name).isEqualTo("Maicol :)")
        }

        @Test
        fun `should have clan name equal to Stelle Cadenti`() {
            assertThat(player.clan?.name).isEqualTo("Stelle Cadenti")
        }

        @Test
        fun `should have a role equal to CO_LEADER`() {
            assertThat(player.role).isEqualTo(ClanMemberRole.CO_LEADER)
        }

        @Test
        fun `should have heroes size equal to 6`() {
            assertThat(player.heroes).hasSize(6)
        }
    }

    @Test
    fun `searchClan should throw ClashJException`() {
        runBlocking {
            val mockEngine = MockEngine { _ ->
                respondBadRequest()
            }

            val api = createApiWithMockEngine(mockEngine)

            val e = assertThrows<ClashJException> { api.searchClan(SearchClanQuery()).await() }
            assertThat(e).hasMessageContaining("Not able to handle this response")
        }
    }

    private fun createMockEngineWithResponse(responseFilePath: String): MockEngine {
        return MockEngine { _ ->
            respond(
                File(responseFilePath).readText(),
                headers = headers { append(HttpHeaders.ContentType, "application/json") }
            )
        }
    }

    private fun createApiWithMockEngine(mockEngine: MockEngine): Client {
        return ClientBuilder("email", "pwd")
            .engine(mockEngine)
            .build()
    }
}
