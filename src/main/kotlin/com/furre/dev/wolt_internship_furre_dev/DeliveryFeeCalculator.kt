package com.furre.dev.wolt_internship_furre_dev

class DeliveryFeeCalculator(
    private val distance: Int,
    private val basePrice: Int,
    private val a: Int,
    private val b: Int
) {
    fun calculateDeliveryFee(): Int =
        0.plus(basePrice).plus(a).plus(feeMultipliedBasedOnDistance(b, distance));
}