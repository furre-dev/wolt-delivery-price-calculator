package com.furre.dev.wolt_internship_furre_dev.utils.apiutils

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import kotlin.test.assertEquals


class ApiUtilsKtTest {

    @Test
    fun createApiUrlTest() {
        val apiUrl = createApiUrl("home-assignment-venue-stockholm")
        assertEquals(
            apiUrl,
            "https://consumer-api.development.dev.woltapi.com/home-assignment-api/v1/venues/home-assignment-venue-stockholm/"
        )
    }

    @Test
    fun `should respond with a INTERNAL_SERVER_ERROR`() {
        val actualException = assertThrows<ResponseStatusException> { getVenueData("this-venue-does-not-exist") }

        val expectedExceptionCode = HttpStatus.INTERNAL_SERVER_ERROR

        assertEquals(expectedExceptionCode, actualException.statusCode)
    }
}

