package io.github.maicolantali.model.clan

import com.google.gson.annotations.SerializedName
import io.github.maicolantali.model.common.Badge

/**
 * Represents the *ClanWarLeagueGroup* model of the Clash of Clans API.
 *
 * @property tag The  tag of the clan war league group.
 * @property state The current state of the clan war league group.
 * @property season The season identifier of the clan war league group.
 * @property clans A list of [ClanWarLeagueClan] objects representing the clans in the group.
 * @property rounds A list of [ClanWarLeagueRound] objects representing the rounds of the clan war league group.
 */
data class ClanWarLeagueGroup(
    val tag: String,
    val state: ClanWarLeagueGroupState,
    val season: String,
    val clans: List<ClanWarLeagueClan>,
    val rounds: List<ClanWarLeagueRound>,
)

/**
 * Represents the state of a clan war league group in the Clash of Clans API.
 */
enum class ClanWarLeagueGroupState {
    /**
     * The group for clan war league is not found.
     */
    @SerializedName("groupNotFound")
    GROUP_NOT_FOUND,

    /**
     * The clan war league group is not currently in a war.
     */
    @SerializedName("notInWar")
    NOT_IN_WAR,

    /**
     * The clan war league group is in the preparation phase before the war starts.
     */
    @SerializedName("preparation")
    PREPARATION,

    /**
     * The clan war league group is currently in a war.
     */
    @SerializedName("war")
    WAR,

    /**
     * The clan war league group war has ended.
     */
    @SerializedName("ended")
    ENDED,
}

/**
 * Represents the *ClanWarLeagueClan* model of the Clash of Clans API.
 * The ClanWarLeagueClan data class stores information about a clan participating in the clan war league group.
 *
 * @property tag The tag of the clan.
 * @property clanLevel The level of the clan.
 * @property name The name of the clan.
 * @property members A list of [ClanWarLeagueClanMember] objects representing the members of the clan.
 * @property badgeUrls The badge URLs representing the clan's badge in different sizes.
 */
data class ClanWarLeagueClan(
    val tag: String,
    val clanLevel: Int,
    val name: String,
    val members: List<ClanWarLeagueClanMember>,
    val badgeUrls: Badge,
)

/**
 * Represents the *ClanWarLeagueClanMember* model of the Clash of Clans API.
 * The ClanWarLeagueClanMember data class stores information about a clan member
 * participating in the clan war league group.
 *
 * @property tag The tag of the clan member.
 * @property townHallLevel The town hall level of the clan member.
 * @property name The name of the clan member.
 */
data class ClanWarLeagueClanMember(
    val tag: String,
    val townHallLevel: Int,
    val name: String,
)

/**
 * Represents the *ClanWarLeagueRound* model of the Clash of Clans API.
 * The ClanWarLeagueRound data class stores information about a round in the clan war league group.
 *
 * @property warTags A list of unique tags representing the wars in the round.
 */
data class ClanWarLeagueRound(
    val warTags: List<String>,
)
