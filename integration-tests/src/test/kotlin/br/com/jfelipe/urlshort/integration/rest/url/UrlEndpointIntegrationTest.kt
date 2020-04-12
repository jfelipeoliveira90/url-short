package br.com.jfelipe.urlshort.integration.rest.url

import br.com.jfelipe.urlshort.core.usecase.createshorturl.CreateShortAddressUrlUseCase
import br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl.SearchFullAddressUrlUseCase
import br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl.UrlNotFoundException
import br.com.jfelipe.urlshort.entrypoints.rest.url.UrlDTO
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doThrow
import com.nhaarman.mockito_kotlin.mock
import io.micronaut.http.HttpRequest.GET
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.mockito.Mockito.`when`
import javax.inject.Inject

@MicronautTest
class UrlEndpointIntegrationTest {

    @Inject
    @field:Client("/urls")
    private lateinit var client: HttpClient

    @Inject
    private lateinit var createShortAddressUrlUseCase: CreateShortAddressUrlUseCase

    @Inject
    private lateinit var searchFullAddressUrlUseCase: SearchFullAddressUrlUseCase

    @Test
    fun `Should return body with short url and status code 201`() {
        `when`(createShortAddressUrlUseCase.createShortAddressUrl(any()))
            .thenReturn("loggi")

        val response = client.toBlocking().exchange(
            POST("/", UrlDTO("https://loggi.com")),
            UrlDTO::class.java
        )

        assertAll(
            { assertEquals(201, response.status.code) },
            { assertNotNull(response.body()?.shortAddress) }
        )
    }

    @Test
    fun `Should return body with full address url and status code 200`() {
        `when`(searchFullAddressUrlUseCase.searchFullAddressUrl(any()))
            .thenReturn("https://loggi.com")

        val response = client.toBlocking().exchange(
            GET<String>("/loggi"),
            UrlDTO::class.java
        )

        assertAll(
            { assertEquals(200, response.status.code) },
            { assertNotNull(response.body()?.fullAddress) }
        )
    }

    @Test
    fun `Should return status code 400 for invalid arguments`() {
        `when`(createShortAddressUrlUseCase.createShortAddressUrl(any()))
            .doThrow(IllegalArgumentException())

        try {
            client.toBlocking().exchange(
                POST("/", UrlDTO("https://loggi.com")),
                UrlDTO::class.java
            )
        } catch (e: HttpClientResponseException) {
            assertEquals(400, e.status.code)
        }
    }

    @Test
    fun `Should return status code 404 for url not found`() {
        `when`(searchFullAddressUrlUseCase.searchFullAddressUrl(any()))
            .doThrow(UrlNotFoundException())

        try {
            client.toBlocking().exchange(
                GET<String>("/loggi"),
                UrlDTO::class.java
            )
        } catch (e: HttpClientResponseException) {
            assertEquals(404, e.status.code)
        }
    }

    @Test
    fun `Should return status code 500 for internal error`() {
        `when`(searchFullAddressUrlUseCase.searchFullAddressUrl(any()))
            .doThrow(RuntimeException())

        try {
            client.toBlocking().exchange(
                GET<String>("/loggi"),
                UrlDTO::class.java
            )
        } catch (e: HttpClientResponseException) {
            assertEquals(500, e.status.code)
        }
    }

    @MockBean(CreateShortAddressUrlUseCase::class)
    fun createShortAddressUrlUseCase() = mock<CreateShortAddressUrlUseCase>()

    @MockBean(SearchFullAddressUrlUseCase::class)
    fun searchFullAddressUrlUseCase() = mock<SearchFullAddressUrlUseCase>()
}