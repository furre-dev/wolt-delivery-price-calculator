package com.furre.dev.wolt_internship_furre_dev.models.delivery

import com.furre.dev.wolt_internship_furre_dev.models.venue.VenueDistanceRange

data class DeliveryPricing(
    val base_price: Int,
    val distance_ranges: List<VenueDistanceRange>
)
