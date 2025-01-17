package com.furre.dev.wolt_internship_furre_dev.utils.apiutils

import com.furre.dev.wolt_internship_furre_dev.models.delivery.DeliveryPricing
import com.furre.dev.wolt_internship_furre_dev.models.delivery.DeliverySpecs
import com.furre.dev.wolt_internship_furre_dev.models.venue.VenueDistanceRange
import com.furre.dev.wolt_internship_furre_dev.models.venue.VenueLocation
import com.furre.dev.wolt_internship_furre_dev.models.venue.VenueRaw
import com.furre.dev.wolt_internship_furre_dev.models.venue.VenueResponse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import kotlin.test.assertEquals


class ApiUtilsKtTest {

    @Test
    fun createApiUrlTest() {
        val apiUrl = createApiUrl("home-assignment-venue-stockholm")
        assertEquals(
            apiUrl,
            "https://consumer-api.development.dev.woltapi.com/home-assignment-api/v1/venues/home-assignment-venue-stockholm/"
        )
    }

    @Test
    fun `should fetch data for correct Venue based on slug`() {
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

        val venueData = getVenueData("home-assignment-venue-stockholm")
        assertEquals(
            venueData, VenueResponse(
                VenueRaw(
                    VenueLocation(listOf("18.0314984", "59.3466978")),
                    DeliverySpecs(10000, DeliveryPricing(900, distanceRanges))
                )
            )
        )
    }

    @Test
    fun `should respond with a INTERNAL_SERVER_ERROR`() {
        val actualException = assertThrows<ResponseStatusException> { getVenueData("this-venue-does-not-exist") }

        val expectedExceptionCode = HttpStatus.INTERNAL_SERVER_ERROR

        assertEquals(expectedExceptionCode, actualException.statusCode)
    }
}

