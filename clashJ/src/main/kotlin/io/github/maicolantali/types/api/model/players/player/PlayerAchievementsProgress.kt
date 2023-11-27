package io.github.maicolantali.types.api.model.players.player

import io.github.maicolantali.types.api.enums.Village

/**
 * Represents the *PlayerAchievementsProgress* model of the Clash of Clans API.
 * The PlayerAchievementsProgress data class stores information about the progress of a player's achievements in the game.
 *
 * @property stars The current number of stars earned for the achievement.
 * @property value The current value of the achievement progress.
 * @property name The name of the achievement.
 * @property target The target value that needs to be reached for completing the achievement.
 * @property info Additional information about the achievement.
 * @property completionInfo Information about the completion status of the achievement.
 * @property village The type of village associated with the achievement (Home Village or Builder Base).
 */
data class PlayerAchievementsProgress(
    val stars: Int,
    val value: Int,
    val name: String,
    val target: Int,
    val info: String,
    val completionInfo: String,
    val village: Village,
)
