package com.furre.dev.wolt_internship_furre_dev.models.delivery

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.furre.dev.wolt_internship_furre_dev.utils.mathutils.Geo
import jakarta.validation.constraints.*

/*
    I'm using @JsonProperty req=true because Kotlin automatically assigns the values for variables if not provided.
    This will bypass the @NotNull annotation.
    A solution could be to assign the types of the variables to nullable, but this will create unnecessary error
    handling for EVERY time we use a variable. I've searched for solutions where we only do one validation and make
    sure Kotlin understands that this variable cannot be null anymore, but this is the best alternative so far.
    Pls hit me up and teach me a more robust solution, I'm dying to know <3
 */

data class DeliveryOrder @JsonCreator constructor(
    @field:NotBlank
    @JsonProperty(value = "venue_slug", required = true)
    val venue_slug: String,

    @JsonProperty(value = "cart_value", required = true)
    val cart_value: Int,

    @field:DecimalMin(Geo.minLat.toString())
    @field:DecimalMax(Geo.maxLat.toString())
    @JsonProperty(value = "user_lat", required = true)
    val user_lat: Double,

    @field:DecimalMin(Geo.minLong.toString())
    @field:DecimalMax(Geo.maxLong.toString())
    @JsonProperty(value = "user_lon", required = true)
    val user_lon : Double,
)
