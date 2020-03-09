package br.com.jfelipe.urlshort.dataproviders.url

import br.com.jfelipe.urlshort.core.entity.Url
import br.com.jfelipe.urlshort.core.usecase.createshorturl.InsertUrl
import br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl.GetUrl
import io.lettuce.core.api.StatefulRedisConnection

class UrlRedisDataProvider(connection: StatefulRedisConnection<String, String>) : InsertUrl, GetUrl {

    private val syncCommands = connection.sync()

    override fun insertUrl(url: Url) {
        syncCommands.set(url.shortAddress, url.fullAddress)
    }

    override fun getFullAddressUrl(shortAddressUrl: String): String? = syncCommands.get(shortAddressUrl)
}