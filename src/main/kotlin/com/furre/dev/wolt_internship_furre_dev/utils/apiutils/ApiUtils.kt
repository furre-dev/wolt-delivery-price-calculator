package com.furre.dev.wolt_internship_furre_dev.utils.apiutils

import com.furre.dev.wolt_internship_furre_dev.models.venue.VenueRaw
import com.furre.dev.wolt_internship_furre_dev.models.venue.VenueResponse
import com.furre.dev.wolt_internship_furre_dev.models.venue.VenueResponseDynamic
import com.furre.dev.wolt_internship_furre_dev.models.venue.VenueResponseStatic
import com.google.gson.Gson
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException


fun createApiUrl(venueSlug: String): String {
    val apiUrl = "https://consumer-api.development.dev.woltapi.com/home-assignment-api/v1/venues/${venueSlug}/"
    return apiUrl;
}

fun getVenueData(venueSlug: String?): VenueResponse {
    if (venueSlug == null) {
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide a venue_slug")
    }

    val restTemplate = RestTemplate();
    val gson = Gson();
    val apiUrl = createApiUrl(venueSlug);

    try {
        val venueStaticApiResponse: String? = restTemplate.getForObject("$apiUrl/static", String::class.java);
        val venueDynamicApiResponse: String? = restTemplate.getForObject("$apiUrl/dynamic", String::class.java);

        val venueStaticData = gson.fromJson(venueStaticApiResponse, VenueResponseStatic::class.java);
        val venueDynamicData = gson.fromJson(venueDynamicApiResponse, VenueResponseDynamic::class.java);

        val combinedVenue = VenueResponse(
            VenueRaw(
                venueStaticData.venue_raw.location,
                venueDynamicData.venue_raw.delivery_specs
            )
        )

        return combinedVenue;
    } catch (e: Exception) {
        throw ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "An error occurred while fetching data for venue slug: '$venueSlug'. Please make sure the venue slug is valid, or try again later."
        )
    }
}
