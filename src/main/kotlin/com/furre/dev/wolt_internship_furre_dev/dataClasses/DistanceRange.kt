package com.furre.dev.wolt_internship_furre_dev.dataClasses

import jakarta.validation.constraints.Null

data class DistanceRange(
    val min: Int,
    val max: Int,
    val a: Int,
    val b: Int,
    val flag: Null,
)
