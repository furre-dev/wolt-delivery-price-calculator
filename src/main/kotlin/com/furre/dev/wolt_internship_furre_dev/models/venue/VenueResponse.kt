package com.furre.dev.wolt_internship_furre_dev.models.venue

data class VenueResponseStatic(
    val venue_raw: VenueRawStatic
)

data class VenueResponseDynamic(
    val venue_raw: VenueRawDynamic
)

data class VenueResponse(
    val venue_raw: VenueRaw
)

