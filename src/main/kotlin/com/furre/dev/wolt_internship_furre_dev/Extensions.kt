package com.furre.dev.wolt_internship_furre_dev

import com.furre.dev.wolt_internship_furre_dev.dataClasses.DistanceRange
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.server.ResponseStatusException

fun createProvideErrorMessage(errors: List<String>): String{
    val fields: String = errors.joinToString(", ");
    return "Please provide ${fields}";
}

fun calculateSurcharge(orderMinimumNoSurcharge: Int, cartValue: Int): Int =
    if (cartValue > orderMinimumNoSurcharge) 0 else orderMinimumNoSurcharge - cartValue

fun handleFieldErrors(fieldErrors: List<FieldError>){
    val errors = fieldErrors.map { error ->
        error.field
    }
    val errorMessage = createProvideErrorMessage(errors);
    throw ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage)
}

fun findRange(distance: Int, distanceRanges: List<DistanceRange>): DistanceRange {
    return distanceRanges.find { distance in it.min until it.max }
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Could not match a distance_range for users distance to venue, delivery not possible")
}

fun feeMultipliedBasedOnDistance(b: Int, distance: Int): Int{
    val distanceMultiplier = b * distance / 10
    return distanceMultiplier
}