package com.furre.dev.wolt_internship_furre_dev.services

import com.furre.dev.wolt_internship_furre_dev.models.Venue
import com.furre.dev.wolt_internship_furre_dev.models.delivery.DeliveryFeeAndDistance
import com.furre.dev.wolt_internship_furre_dev.models.delivery.DeliveryOrder
import com.furre.dev.wolt_internship_furre_dev.models.delivery.OrderPrice
import org.springframework.stereotype.Service

@Service
class DeliveryService {

    fun calculateOrderPrice(newDeliveryOrderRequest: DeliveryOrder): OrderPrice {
        //1. Since we have the cart value and the location from our POST request body, we now try to fetch and handle
        //   the correct Venue information.
        val venue = Venue(
            newDeliveryOrderRequest
        )

        //2. If userHasFreeOrderVoucher is true, return a free of charge pricing for this delivery.
        if (venue.userHasFreeOrderVoucher) {
            return OrderPrice(
                0,
                0,
                0,
                DeliveryFeeAndDistance(
                    0,
                    venue.deliveryFeeAndDistance.distance
                )
            )
        }

        //3. This is the final step, where we combine the deliveryOrder information with our Venue and its calculations.
        val totalOrderPrice = OrderPrice(
            venue.totalPrice,
            venue.surchargeFee,
            newDeliveryOrderRequest.cart_value,
            venue.deliveryFeeAndDistance
        )

        //4. Now we return the deliveryPrice
        return totalOrderPrice
    }

}