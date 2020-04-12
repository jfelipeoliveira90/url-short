package br.com.jfelipe.urlshort.dataproviders.database.url

import br.com.jfelipe.urlshort.core.entity.Url
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class UrlRedisDataProviderTest {

    private val shortAddress = "0bkZ2V"
    private val fullAddress = "https://www.loggi.com/compartilhe/corp/86b35e3a1f2bf7b8f77c7ef30c3cd159"

    private val syncCommands = mock<RedisCommands<String, String>> {
        on { set(any(), any()) } doReturn shortAddress
        on { get(any()) } doReturn fullAddress
    }

    private val connection = mock<StatefulRedisConnection<String, String>> {
        on { sync() } doReturn syncCommands
    }

    private val dataProvider = UrlRedisDataProvider(connection)

    @Test
    fun `Should save a new url in database`() {
        dataProvider.insertUrl(Url(shortAddress, fullAddress))
        verify(syncCommands, times(1)).set(any(), any())
    }

    @Test
    fun `Should return full address url`() {
        val url = dataProvider.getFullAddressUrl(shortAddress)
        assertNotNull(url)
    }
}