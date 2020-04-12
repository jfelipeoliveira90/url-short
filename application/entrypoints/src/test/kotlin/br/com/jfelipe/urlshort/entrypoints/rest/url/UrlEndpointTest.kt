package br.com.jfelipe.urlshort.entrypoints.rest.url

import br.com.jfelipe.urlshort.core.usecase.createshorturl.CreateShortAddressUrlUseCase
import br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl.SearchFullAddressUrlUseCase
import br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl.UrlNotFoundException
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import io.micronaut.http.HttpRequest.POST
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.net.URI

class UrlEndpointTest {

    private val shortAddress = "0bkZ2V"
    private val fullAddress = "https://www.loggi.com/compartilhe/corp/86b35e3a1f2bf7b8f77c7ef30c3cd159"

    private val createShortAddressUrlUseCase = mock<CreateShortAddressUrlUseCase> {
        on { createShortAddressUrl(any()) } doReturn shortAddress
    }

    private val searchFullAddressUrlUseCase = mock<SearchFullAddressUrlUseCase> {
        on { searchFullAddressUrl(any()) } doReturn fullAddress
    }

    private val urlEndpoint = UrlEndpoint(createShortAddressUrlUseCase, searchFullAddressUrlUseCase)

    @Test
    fun `Should create new short url`() {
        val response = urlEndpoint.create(UrlDTO(fullAddress, null))

        assertAll(
            { assertEquals(201, response.status.code) },
            { assertNotNull(response.body()) }
        )
    }

    @Test
    fun `Should return full address url`() {
        val response = urlEndpoint.get(shortAddress)

        assertAll(
            { assertEquals(200, response.status.code) },
            { assertNotNull(response.body()) }
        )
    }

    @Test
    fun `Should return not found`() {
        val notFound = urlEndpoint.notFound(
            POST(URI.create(""), UrlDTO("", "")),
            UrlNotFoundException()
        )

        assertAll(
            { assertEquals(404, notFound.status.code) },
            { assertNotNull(notFound.body()) }
        )
    }

    @Test
    fun `Should return internal server error`() {
        val serverError = urlEndpoint.internalServerError(
            POST(URI.create(""), UrlDTO("", "")),
            Exception()
        )

        assertAll(
            { assertEquals(500, serverError.status.code) },
            { assertNotNull(serverError.body()) }
        )
    }
}