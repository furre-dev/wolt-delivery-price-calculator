package com.furre.dev.wolt_internship_furre_dev.utils

import com.furre.dev.wolt_internship_furre_dev.dataClasses.VenueResponse
import com.furre.dev.wolt_internship_furre_dev.dataClasses.VenueRaw
import com.google.gson.Gson
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException

fun createApiUrl(venueSlug: String): String{
    val apiUrl: String = "https://consumer-api.development.dev.woltapi.com/home-assignment-api/v1/venues/${venueSlug}/"
    return apiUrl;
}

fun getVenueData(venueSlug: String?): VenueResponse {
    if(venueSlug == null) {
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide a venue_slug")
    }

    val gson = Gson();
    val apiUrl = createApiUrl(venueSlug);

    val staticApiUrl = "$apiUrl/static"
    val restTemplate = RestTemplate();
    val staticResult: String? = restTemplate.getForObject<String>(staticApiUrl, String::class.java);

    val dynamicApiUrl = "$apiUrl/dynamic";
    val dynamicResult: String? = restTemplate.getForObject<String>(dynamicApiUrl, String::class.java);

    val staticVenue = gson.fromJson(staticResult, VenueResponse::class.java);
    val dynamicVenue = gson.fromJson(dynamicResult, VenueResponse::class.java);

    val combinedVenue = VenueResponse(
        venue_raw = VenueRaw(
            location = staticVenue.venue_raw.location ?: dynamicVenue.venue_raw.location,
            delivery_specs = dynamicVenue.venue_raw.delivery_specs ?: staticVenue.venue_raw.delivery_specs
        )
    )

    return combinedVenue;
}
