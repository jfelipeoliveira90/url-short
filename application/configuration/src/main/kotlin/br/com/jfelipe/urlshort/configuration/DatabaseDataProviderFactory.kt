package br.com.jfelipe.urlshort.configuration

import br.com.jfelipe.urlshort.dataproviders.url.UrlRedisDataProvider
import io.lettuce.core.api.StatefulRedisConnection
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class DatabaseDataProviderFactory {

    @Singleton
    fun urlRedisDataProvider(connection: StatefulRedisConnection<String, String>) = UrlRedisDataProvider(connection)
}