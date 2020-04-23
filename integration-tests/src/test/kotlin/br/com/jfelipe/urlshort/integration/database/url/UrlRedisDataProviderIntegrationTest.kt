package br.com.jfelipe.urlshort.integration.database.url

import br.com.jfelipe.urlshort.core.entity.Url
import br.com.jfelipe.urlshort.dataproviders.database.url.UrlRedisDataProvider
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import javax.inject.Inject

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class UrlRedisDataProviderIntegrationTest {

    @Inject
    private lateinit var database: UrlRedisDataProvider

    @Test
    @Order(1)
    fun `Should save a new url in database`() {
        database.insertUrl(Url("loggi", "https://loggi.com"))
    }

    @Test
    @Order(2)
    fun `Should return full address url`() {
        val url = database.getFullAddressUrl("loggi")
        Assertions.assertNotNull(url)
    }
}