package io.github.maicolantali.types.api.model.clans.clan

import io.github.maicolantali.types.api.model.clans.clancapital.ClanCapital
import io.github.maicolantali.types.api.model.clans.clanMember.ClanMember
import io.github.maicolantali.types.api.enums.ClanType
import io.github.maicolantali.types.api.enums.WarFrequency
import io.github.maicolantali.types.api.model.common.Badge
import io.github.maicolantali.types.api.model.common.Language
import io.github.maicolantali.types.api.model.labels.Label
import io.github.maicolantali.types.api.model.leagues.simpleleague.SimpleLeague
import io.github.maicolantali.types.api.model.locations.location.Location

/**
 * Represents the *Clan* model of the Clash of Clans API.
 * The Clan data class stores detailed information about a clan in the game.
 *
 * @property warLeague The league information for clan wars.
 * @property capitalLeague The league information for the clan's capital.
 * @property memberList A list of [ClanMember] objects representing the clan's members.
 * @property tag The tag of the clan.
 * @property warFrequency The frequency of clan wars.
 * @property clanLevel The level of the clan.
 * @property warWinStreak The current winning streak in clan wars.
 * @property warWins The total number of war wins.
 * @property warTies The total number of war ties.
 * @property warLosses The total number of war losses.
 * @property clanPoints The total points earned by the clan.
 * @property requiredTownhallLevel The minimum required town hall level to join the clan.
 * @property chatLanguage The language used for clan chat.
 * @property isFamilyFriendly A flag indicating if the clan is family-friendly.
 * @property requiredTrophies The minimum required trophies to join the clan.
 * @property requiredBuilderBaseTrophies The minimum required builder base trophies to join the clan.
 * @property requiredVersusTrophies The minimum required versus trophies to join the clan.
 * @property isWarLogPublic A flag indicating if the clan's war log is public.
 * @property clanBuilderBasePoints The builder base points earned by the clan.
 * @property clanVersusPoints The versus points earned by the clan.
 * @property clanCapitalPoints The points earned by the clan's capital.
 * @property labels A list of [Label] objects categorizing the clan.
 * @property name The name of the clan.
 * @property location The location of the clan.
 * @property type The type of the clan (open, invite-only, closed).
 * @property members The total number of clan members.
 * @property description The description of the clan.
 * @property clanCapital The information about the clan's capital.
 * @property badgeUrls The badge URLs representing the clan's badge in different sizes.
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
    val chatLanguage: Language?,
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
    val location: Location?,
    val type: ClanType,
    val members: Int,
    val description: String,
    val clanCapital: ClanCapital,
    val badgeUrls: Badge,
)
