package br.com.jfelipe.urlshort.core.usecase.createshorturl

import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateShortAddressUrlUseCaseTest {

    private lateinit var useCase: CreateShortAddressUrlUseCase
    private val insertUrl = mock<InsertUrl>()

    @BeforeAll
    fun setup() {
        useCase = CreateShortAddressUrlUseCase(insertUrl)
    }

    @Test
//    @DisplayName("Should create new a short url")
    fun `Should create new a short url`() {
        val short = useCase.createShortAddressUrl("https://loggi.com")
        Assertions.assertEquals(6, short.length)
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(
        strings = [
            "ftp://loggi.com",
            "http://localhost:8080"
        ]
    )
    fun `Should not create short url`(url: String) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            useCase.createShortAddressUrl(url)
        }
    }
}