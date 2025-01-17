package com.furre.dev.wolt_internship_furre_dev.utils

import com.furre.dev.wolt_internship_furre_dev.models.venue.VenueDistanceRange
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VenueExtensionsTests {

    @Test
    fun calculateSurchargeTest() {
        val surchargeFee = calculateSurcharge(200000, 149000)
        assertEquals(surchargeFee, 51000)
    }

    @Test
    fun findDeliveryDistanceRangeTest() {
        /*
       data from home-assignment-venue-stockholm
       "distance_ranges": [
           {"min":0,"max":500,"a":0,"b":0.0,"flag":null},
           {"min":500,"max":1000,"a":1000,"b":0.0,"flag":null},
           {"min":1000,"max":1500,"a":2000,"b":0.0,"flag":null},
           {"min":1500,"max":2000,"a":2000,"b":10.0,"flag":null}
       ]
       */

        val distanceRanges: List<VenueDistanceRange> = listOf(
            VenueDistanceRange(0, 500, 0, 0, null),
            VenueDistanceRange(500, 1000, 1000, 0, null),
            VenueDistanceRange(1000, 1500, 2000, 0, null),
            VenueDistanceRange(1500, 2000, 2000, 10, null),
            VenueDistanceRange(2000, 0, 0, 0, null)
        )

        val matchDistanceRange: VenueDistanceRange = findDeliveryDistanceRange(
            1240,
            distanceRanges
        )

        assertEquals(matchDistanceRange, distanceRanges[2])
    }


    @Test
    fun feeMultipliedBasedOnDistanceTest() {
        /*
         Exact example from instructions
         b: Multiplier to be used for calculating distance based component of the delivery fee.  The formula is
         b * distance / 10 and the result should be rounded to the nearest integer value.  For example, if the delivery
         distance is 1000 meters and the value of b is 2, we'd add 200 (2 * 1000 / 10) to the delivery fee.
       */
        val distanceFee = feeMultipliedBasedOnDistance(2, 1000)
        assertEquals(distanceFee, 200)
    }

    @Test
    fun deliveryFeeTest() {
        val totalDeliveryFee = deliveryFee(
            800,
            5000,
            1000,
            1
        )

        assertEquals(totalDeliveryFee, 6080)
    }
}