package com.furre.dev.wolt_internship_furre_dev.services

import com.furre.dev.wolt_internship_furre_dev.models.delivery.DeliveryFeeAndDistance
import com.furre.dev.wolt_internship_furre_dev.models.delivery.DeliveryOrder
import com.furre.dev.wolt_internship_furre_dev.models.delivery.OrderPrice
import org.junit.jupiter.api.Assertions.*
import org.springframework.test.web.servlet.get
import kotlin.test.Test

class DeliveryServiceTest {

    private val deliveryService = DeliveryService()

    @Test
    fun `order price should be correct`() {
        val newDeliveryOrderRequest = DeliveryOrder(
            "home-assignment-venue-stockholm",
            10000,
            59.34829878585902,
            18.030581581937327
        )

        val expected = OrderPrice(
            10900,
            0,
            10000,
            DeliveryFeeAndDistance(900, 186)
        )

        val orderPrice: OrderPrice = deliveryService.calculateOrderPrice(newDeliveryOrderRequest)

        assertEquals(expected, orderPrice)
    }

    @Test
    fun `order should be free`() {
        val newDeliveryOrderRequest = DeliveryOrder(
            "home-assignment-venue-stockholm",
            0,
            59.34829878585902,
            18.030581581937327
        )

        val expected = OrderPrice(
            0,
            0,
            0,
            DeliveryFeeAndDistance(0, 186)
        )

        val orderPrice: OrderPrice = deliveryService.calculateOrderPrice(newDeliveryOrderRequest)

        assertEquals(expected, orderPrice)
    }

}