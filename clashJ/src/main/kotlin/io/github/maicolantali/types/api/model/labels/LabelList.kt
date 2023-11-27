package io.github.maicolantali.types.api.model.labels

import io.github.maicolantali.types.api.interfaces.ApiListResponse
import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Represents the *LabelList* model of the Clash of Clans API.
 *
 * @property items A list of [Label] objects representing the labels in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class LabelList(
    override val items: List<Label>,
    override val paging: Paging?,
) : ApiListResponse<Label>
