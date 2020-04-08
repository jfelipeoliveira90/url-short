package br.com.jfelipe.urlshort.core.usecase.createshorturl

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

@TestInstance(PER_CLASS)
class CreateShortAddressUrlUseCaseTest {

    private lateinit var insertUrl: InsertUrl
    private lateinit var useCase: CreateShortAddressUrlUseCase

    @BeforeAll
    fun setup() {
        insertUrl = mock {
            on { insertUrl(any()) } doAnswer {}
        }

        useCase = CreateShortAddressUrlUseCase(insertUrl)
    }

    @ParameterizedTest(name = "{index} - create short url for {0} ")
    @EmptySource
    @ValueSource(
        strings = [
            "https://www.google.com/search?q=loggi",
            "https://www.loggi.com/compartilhe/corp/86b35e3a1f2bf7b8f77c7ef30c3cd159"
        ]
    )
    fun `Should create a short url with 6 characters`(longUrl: String) {
        val short = useCase.createShortAddressUrl(longUrl)
        assertEquals(6, short.length)
    }
}