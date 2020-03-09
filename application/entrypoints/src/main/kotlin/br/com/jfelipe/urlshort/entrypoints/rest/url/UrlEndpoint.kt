package br.com.jfelipe.urlshort.entrypoints.rest.url

import br.com.jfelipe.urlshort.core.usecase.createshorturl.CreateShortAddressUrlUseCase
import br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl.SearchFullAddressUrlUseCase
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.created
import io.micronaut.http.HttpResponse.ok
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/urls")
class UrlEndpoint(
    private val createShortAddressUrlUseCase: CreateShortAddressUrlUseCase,
    private val searchFullAddressUrlUseCase: SearchFullAddressUrlUseCase
) {

    @Post
    fun create(@Body urlDTO: UrlDTO): HttpResponse<UrlDTO> {
        val fullAddress = urlDTO.fullAddress
        val shortAddress = createShortAddressUrlUseCase.createShortAddressUrl(fullAddress)
        val dto = UrlDTO(fullAddress, shortAddress)

        return created(dto)
    }

    @Get("/{shortAddress}")
    fun get(shortAddress: String): HttpResponse<UrlDTO> {
        val fullAddress = searchFullAddressUrlUseCase.searchFullAddressUrl(shortAddress)
        val dto = UrlDTO(fullAddress, shortAddress)

        return ok(dto)
    }
}