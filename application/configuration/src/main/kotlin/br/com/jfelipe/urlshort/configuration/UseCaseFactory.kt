package br.com.jfelipe.urlshort.configuration

import br.com.jfelipe.urlshort.core.usecase.createshorturl.CreateShortAddressUrlUseCase
import br.com.jfelipe.urlshort.core.usecase.createshorturl.InsertUrl
import br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl.GetUrl
import br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl.SearchFullAddressUrlUseCase
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class UseCaseFactory {

    @Singleton
    fun searchFullAddressUrlUseCase(getUrl: GetUrl) = SearchFullAddressUrlUseCase(getUrl)

    @Singleton
    fun createUrl(insertUrl: InsertUrl) = CreateShortAddressUrlUseCase(insertUrl)
}