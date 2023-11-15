package io.github.maicolantali.model.label

import io.github.maicolantali.model.common.Paging

/**
 * Represents the *LabelList* model of the Clash of Clans API.
 *
 * @property items A list of [Label] objects representing the labels in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class LabelList(
    val items: List<Label>,
    val paging: Paging?,
)
