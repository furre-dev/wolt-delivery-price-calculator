package com.furre.dev.wolt_internship_furre_dev.utils

import com.furre.dev.wolt_internship_furre_dev.models.venue.VenueDistanceRange
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal
import java.math.RoundingMode

fun calculateSurcharge(orderMinimumNoSurcharge: Int, cartValue: Int): Int =
    if (cartValue > orderMinimumNoSurcharge) 0 else orderMinimumNoSurcharge - cartValue

fun findDeliveryDistanceRange(distance: Int, venueDeliveryDistanceRanges: List<VenueDistanceRange>): VenueDistanceRange {
    // This will take the distance, divide it by 1000 so we get the distance in kilometers.
    // Then it will only keep the first decimal. I want to avoid rounding for misleading information.

    val distanceInKm = BigDecimal(distance.toDouble() / 1000).setScale(1, RoundingMode.DOWN).toDouble()

    return venueDeliveryDistanceRanges.find { distance in it.min until it.max }
        ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "The specified distance of $distanceInKm km falls outside the delivery range. " +
                    "Please ensure your location is within the available delivery zones.")
}

fun feeMultipliedBasedOnDistance(b: Int, distance: Int): Int{
    val distanceMultiplier = b * distance / 10
    return distanceMultiplier
}

fun deliveryFee(
    distance: Int,
    basePrice: Int,
    a: Int,
    b: Int
): Int =
    basePrice + a + feeMultipliedBasedOnDistance(b, distance)