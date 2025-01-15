package com.furre.dev.wolt_internship_furre_dev

import Geo
import com.furre.dev.wolt_internship_furre_dev.dataClasses.DeliveryFeeAndDistance
import com.furre.dev.wolt_internship_furre_dev.dataClasses.DeliveryOrder
import com.furre.dev.wolt_internship_furre_dev.dataClasses.DeliveryPrice
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/api/v1/delivery-order-price")
class DeliveryOrderPriceController {

    @PostMapping
    fun createDeliveryOrder(
        @RequestBody @Valid deliveryOrder: DeliveryOrder,
        bindingResult: BindingResult
    ): DeliveryPrice {

        if (bindingResult.hasErrors()) {
            //example of error message will be: "Please provide user_lon, user_lat, venue_slug"
            handleFieldErrors(bindingResult.fieldErrors);
        }

        val venue = Venue(
            deliveryOrder.venue_slug ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "venue_slug can not be null")
        )

        val userToVenueDistanceInMeters = Geo(
            deliveryOrder.user_lat ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "user_lat can not be null") ,
            deliveryOrder.user_lon ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "user_lon can not be null")
        ).haversine(Geo(venue.coordinates().lat, venue.coordinates().lon))

        val distanceRange = findRange(userToVenueDistanceInMeters, venue.deliveryPricing().distance_ranges)

        val priceCalculator = DeliveryFeeCalculator(
            userToVenueDistanceInMeters,
            venue.deliveryPricing().base_price,
            distanceRange.a,
            distanceRange.b
        )

        val deliveryFee = priceCalculator.calculateDeliveryFee()
        val surchargeFee = calculateSurcharge(
            venue.orderMinimumNoSurcharge(),
            deliveryOrder.cart_value ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "cart_value can not be null"))

        val deliveryFeeAndDistance = DeliveryFeeAndDistance(
            deliveryFee,
            userToVenueDistanceInMeters
        )

        val deliveryPrice = DeliveryPrice(
            deliveryOrder.cart_value.plus(deliveryFee).plus(surchargeFee),
            surchargeFee,
            deliveryOrder.cart_value,
            deliveryFeeAndDistance
        )

        return deliveryPrice
    }
}
