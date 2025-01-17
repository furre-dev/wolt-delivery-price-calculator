package com.furre.dev.wolt_internship_furre_dev.utils

import com.furre.dev.wolt_internship_furre_dev.models.delivery.DeliveryOrder
import org.springframework.validation.FieldError

fun createFieldErrorMessage(fieldErrors: List<FieldError>): String {
    val errors = fieldErrors.map { error ->
        error.field + " " + error.defaultMessage
    }
    val errorMessages: String = errors.joinToString(", ");
    return errorMessages
}
