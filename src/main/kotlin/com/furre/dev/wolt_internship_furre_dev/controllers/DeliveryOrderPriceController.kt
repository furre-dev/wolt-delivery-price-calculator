package com.furre.dev.wolt_internship_furre_dev.controllers

import com.furre.dev.wolt_internship_furre_dev.models.delivery.DeliveryOrder
import com.furre.dev.wolt_internship_furre_dev.models.delivery.OrderPrice
import com.furre.dev.wolt_internship_furre_dev.services.DeliveryService
import com.furre.dev.wolt_internship_furre_dev.utils.createFieldErrorMessage
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

/*
*  This is the RestController where we handle different Spring Boot REST features like creating REST API endpoints
*  and adding different mappers, like @PostMapping (logic for a POST Request) or @GetMapping (logic for a GET request).
*/

@RestController
@RequestMapping("/api/v1/delivery-order-price")
class DeliveryOrderPriceController(private val deliveryService: DeliveryService) {

    //Enabling POST method to our endpoint
    @PostMapping
    fun createDeliveryOrder(
        @Valid @RequestBody newDeliveryOrderRequest: DeliveryOrder,
        bindingResult: BindingResult
    ): OrderPrice {

        //If the POST request body fields don't pass the validation, throw error with explanatory message.
        if (bindingResult.hasErrors()) {
            val errorMessages = createFieldErrorMessage(bindingResult.fieldErrors)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessages)
        }

        //If no errors, respond with the DeliveryPrice response returned from the calculateDeliveryPrice function.
        return deliveryService.calculateOrderPrice(newDeliveryOrderRequest)

    }
}

/*
  Example

    Input: {
      "cart_value": 10000,
      "user_lat": 59.34829878585902,
      "user_lon": 18.030581581937327,
      "venue_slug": "home-assignment-venue-stockholm"
    }

    Output: {
      "total_price": 10900,
      "small_order_surcharge": 4750,
      "cart_value": 5250,
      "delivery": {
        "fee": 900,
        "distance": 186
      }
    }
*/
