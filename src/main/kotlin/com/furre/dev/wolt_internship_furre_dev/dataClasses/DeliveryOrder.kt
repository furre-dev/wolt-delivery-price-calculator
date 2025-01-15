package com.furre.dev.wolt_internship_furre_dev.dataClasses

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class DeliveryOrder(
    @field:NotNull
    val venue_slug: String?,

    @field:NotNull
    @field:Min(value = 0)
    val cart_value: Int?,

    // Fields are nullable, because if not nullable Kotlin will assign 0.0 value to variable of type Dobule.
    @field:NotNull
    val user_lat: Double?,

    @field:NotNull
    val user_lon : Double?,
)
