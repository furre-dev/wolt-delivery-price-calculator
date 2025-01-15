package com.furre.dev.wolt_internship_furre_dev

import com.furre.dev.wolt_internship_furre_dev.dataClasses.Coordinates
import com.furre.dev.wolt_internship_furre_dev.dataClasses.DeliveryPricing
import com.furre.dev.wolt_internship_furre_dev.dataClasses.VenueResponse
import com.furre.dev.wolt_internship_furre_dev.utils.getVenueData

class Venue(
    val venueSlug: String,
    val venueResponse: VenueResponse = getVenueData(venueSlug),
) {
    fun coordinates(): Coordinates =
        Coordinates(venueResponse.venue_raw.location.coordinates[1].toDouble(), venueResponse.venue_raw.location.coordinates[0].toDouble())


    fun deliveryPricing(): DeliveryPricing =
         venueResponse.venue_raw.delivery_specs.delivery_pricing

    fun orderMinimumNoSurcharge(): Int =
        venueResponse.venue_raw.delivery_specs.order_minimum_no_surcharge
}
