package com.clashj.model.label

import com.clashj.model.common.Paging

data class LabelList(
    val items: List<Label>,
    val paging: Paging?
)
