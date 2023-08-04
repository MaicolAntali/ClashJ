package com.clashj.model.location

import com.clashj.model.common.Location
import com.clashj.model.common.Paging

data class LocationList(
    val items: List<Location>,
    val paging: Paging?
)
