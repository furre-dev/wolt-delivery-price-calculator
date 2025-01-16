package com.furre.dev.wolt_internship_furre_dev.models.delivery

data class DeliveryPrice(
    val total_price: Int,
    val small_order_surcharge: Int,
    val cart_value: Int,
    val delivery: DeliveryFeeAndDistance,
)
