package br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.assertThrows
import org.junit.platform.commons.util.StringUtils.isNotBlank

@TestInstance(PER_CLASS)
class SearchFullAddressUrlUseCaseTest {

    private lateinit var useCase: SearchFullAddressUrlUseCase
    private lateinit var getUrl: GetUrl

    @BeforeAll
    fun setup() {
        getUrl = mock {
            on { getFullAddressUrl(any()) } doReturn listOf(
                "https://www.loggi.com/compartilhe/corp/86b35e3a1f2bf7b8f77c7ef30c3cd159",
                null
            )
        }

        useCase = SearchFullAddressUrlUseCase(getUrl)
    }

    @Test
    fun `Should return full url address`() {
        val url = useCase.searchFullAddressUrl("loggi")
        assertTrue(isNotBlank(url))
    }

    @Test
    fun `Should return exception for url not found`() {
        assertThrows<UrlNotFoundException> { useCase.searchFullAddressUrl("rappi") }
    }
}