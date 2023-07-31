package com.clashj.model.clan

import com.clashj.model.common.Badge
import com.clashj.model.common.Label
import com.clashj.model.common.Language
import com.clashj.model.common.Location
import com.clashj.model.clan.component.ClanType
import com.clashj.model.league.SimpleLeague
import com.clashj.model.clan.component.ClanCapital
import com.clashj.model.clan.component.ClanMember
import com.clashj.model.clan.component.WarFrequency

/**
 * Represents detailed information about a clan in a game.
 *
 * @property warLeague The league of the clan in wars.
 * @property capitalLeague The league of the clan's capital.
 * @property memberList The list of clan members.
 * @property tag The tag of the clan.
 * @property warFrequency The frequency of wars for the clan.
 * @property clanLevel The level of the clan.
 * @property warWinStreak The current war win streak of the clan.
 * @property warWins The total number of war wins for the clan.
 * @property warTies The total number of war ties for the clan.
 * @property warLosses The total number of war losses for the clan.
 * @property clanPoints The points of the clan.
 * @property requiredTownhallLevel The required town hall level to join the clan.
 * @property chatLanguage The language used in the clan's chat.
 * @property isFamilyFriendly Indicates whether the clan is family-friendly.
 * @property requiredTrophies The required trophies to join the clan.
 * @property requiredBuilderBaseTrophies The required builder base trophies to join the clan.
 * @property requiredVersusTrophies The required versus trophies to join the clan.
 * @property isWarLogPublic Indicates whether the clan's war log is public.
 * @property clanBuilderBasePoints The builder base points of the clan.
 * @property clanVersusPoints The versus points of the clan.
 * @property clanCapitalPoints The capital points of the clan.
 * @property labels The list of labels associated with the clan.
 * @property name The name of the clan.
 * @property location The location of the clan.
 * @property type The type of the clan (Open, Invite Only, Closed).
 * @property members The total number of members in the clan.
 * @property description The description of the clan.
 * @property clanCapital The clan's capital information.
 * @property badgeUrls The URLs to the clan's badges (small, medium, large).
 */
data class Clan(
    val warLeague: SimpleLeague,
    val capitalLeague: SimpleLeague,
    val memberList: List<ClanMember>,
    val tag: String,
    val warFrequency: WarFrequency,
    val clanLevel: Int,
    val warWinStreak: Int,
    val warWins: Int,
    val warTies: Int,
    val warLosses: Int,
    val clanPoints: Int,
    val requiredTownhallLevel: Int,
    val chatLanguage: Language,
    val isFamilyFriendly: Boolean,
    val requiredTrophies: Int,
    val requiredBuilderBaseTrophies: Int,
    val requiredVersusTrophies: Int,
    val isWarLogPublic: Boolean,
    val clanBuilderBasePoints: Int,
    val clanVersusPoints: Int,
    val clanCapitalPoints: Int,
    val labels: List<Label>,
    val name: String,
    val location: Location,
    val type: ClanType,
    val members: Int,
    val description: String,
    val clanCapital: ClanCapital,
    val badgeUrls: Badge
)