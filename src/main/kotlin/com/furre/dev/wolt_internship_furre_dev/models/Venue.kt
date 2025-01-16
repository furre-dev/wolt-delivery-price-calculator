package com.furre.dev.wolt_internship_furre_dev.models

import com.furre.dev.wolt_internship_furre_dev.utils.mathutils.Geo
import com.furre.dev.wolt_internship_furre_dev.models.delivery.DeliveryFeeAndDistance
import com.furre.dev.wolt_internship_furre_dev.models.delivery.DeliveryOrder
import com.furre.dev.wolt_internship_furre_dev.models.delivery.DeliveryPricing
import com.furre.dev.wolt_internship_furre_dev.models.venue.Coordinates
import com.furre.dev.wolt_internship_furre_dev.models.venue.VenueResponse
import com.furre.dev.wolt_internship_furre_dev.utils.apiutils.getVenueData
import com.furre.dev.wolt_internship_furre_dev.utils.calculateSurcharge
import com.furre.dev.wolt_internship_furre_dev.utils.deliveryFee
import com.furre.dev.wolt_internship_furre_dev.utils.findDeliveryDistanceRange

class Venue(
    newDeliveryOrderRequest: DeliveryOrder,
    venueResponse: VenueResponse = getVenueData(newDeliveryOrderRequest.venue_slug),
) {
    //These are set to private because we don't want access to these variables from the outside of this Class.
    //This is because we only have usage of these variables inside of this Class.
    private val coordinates: Coordinates = Coordinates(
        venueResponse.venue_raw.location.coordinates[1].toDouble(),
        venueResponse.venue_raw.location.coordinates[0].toDouble()
    )

    private val venueDeliveryPrices: DeliveryPricing =
        venueResponse.venue_raw.delivery_specs.delivery_pricing

    private val minimumCartValueForNoSurcharge: Int =
        venueResponse.venue_raw.delivery_specs.order_minimum_no_surcharge

    private val userToVenueDistanceInMeters = Geo(
        newDeliveryOrderRequest.user_lat,
        newDeliveryOrderRequest.user_lon
    ).haversine(Geo(coordinates.lat, coordinates.lon))

    private val matchingDeliveryDistanceRage = findDeliveryDistanceRange(
        userToVenueDistanceInMeters,
        venueDeliveryPrices.distance_ranges
    )

    //These are not private because we need access outside of this Class
    val deliveryFee = deliveryFee(
        userToVenueDistanceInMeters,
        venueDeliveryPrices.base_price,
        matchingDeliveryDistanceRage.a,
        matchingDeliveryDistanceRage.b
    )

    val surchargeFee = calculateSurcharge(
        minimumCartValueForNoSurcharge,
        newDeliveryOrderRequest.cart_value
    )

    val deliveryFeeAndDistance = DeliveryFeeAndDistance(
        deliveryFee,
        userToVenueDistanceInMeters
    )
}
