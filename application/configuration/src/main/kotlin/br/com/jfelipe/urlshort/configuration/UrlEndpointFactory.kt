package br.com.jfelipe.urlshort.configuration

import br.com.jfelipe.urlshort.core.usecase.createshorturl.CreateShortAddressUrlUseCase
import br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl.SearchFullAddressUrlUseCase
import br.com.jfelipe.urlshort.entrypoints.rest.url.UrlEndpoint
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class UrlEndpointFactory {

    @Singleton
    fun urlEndpoint(
        createShortAddressUrlUseCase: CreateShortAddressUrlUseCase,
        searchFullAddressUrlUseCase: SearchFullAddressUrlUseCase
    ) = UrlEndpoint(createShortAddressUrlUseCase, searchFullAddressUrlUseCase)
}