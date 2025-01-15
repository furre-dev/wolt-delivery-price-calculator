package com.furre.dev.wolt_internship_furre_dev.dataClasses

data class DeliveryPricing(
    val base_price: Int,
    val distance_ranges: List<DistanceRange>
)
