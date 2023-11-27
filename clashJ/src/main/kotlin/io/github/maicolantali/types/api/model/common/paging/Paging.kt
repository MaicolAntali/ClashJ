package io.github.maicolantali.types.api.model.common.paging

/**
 * Represents the *Paging* model of the Clash of Clans API.
 *
 * @property cursors The cursors for pagination, containing references to the `before` and `after` elements in a list.
 * It provides navigation to the previous and next pages of results.
 */
data class Paging(
    val cursors: Cursors,
)
