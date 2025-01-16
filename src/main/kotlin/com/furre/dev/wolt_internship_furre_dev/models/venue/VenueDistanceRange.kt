package com.furre.dev.wolt_internship_furre_dev.models.venue

import jakarta.validation.constraints.Null

data class VenueDistanceRange(
    val min: Int,
    val max: Int,
    val a: Int,
    val b: Int,
    val flag: Null,
)
