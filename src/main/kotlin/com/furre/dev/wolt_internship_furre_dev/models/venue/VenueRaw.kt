package com.furre.dev.wolt_internship_furre_dev.models.venue

import com.furre.dev.wolt_internship_furre_dev.models.delivery.DeliverySpecs

data class VenueRawStatic(
    val location: VenueLocation
)

data class VenueRawDynamic(
    val delivery_specs: DeliverySpecs
)

data class VenueRaw(
    val location: VenueLocation,
    val delivery_specs: DeliverySpecs
)

