package br.com.jfelipe.urlshort.entrypoints.rest.url

import br.com.jfelipe.urlshort.core.usecase.createshorturl.CreateShortAddressUrlUseCase
import br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl.SearchFullAddressUrlUseCase
import br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl.UrlNotFoundException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.created
import io.micronaut.http.HttpResponse.ok
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.hateoas.Link
import io.micronaut.http.hateoas.Link.SELF
import org.slf4j.LoggerFactory

@Controller("/urls")
class UrlEndpoint(
    private val createShortAddressUrlUseCase: CreateShortAddressUrlUseCase,
    private val searchFullAddressUrlUseCase: SearchFullAddressUrlUseCase
) {

    private val logger = LoggerFactory.getLogger(javaClass)

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

    @Error
    fun notFound(request: HttpRequest<*>, e: UrlNotFoundException): HttpResponse<JsonError> {
        val error = JsonError(e.message)
            .link(SELF, Link.of(request.uri))

        return HttpResponse.notFound<JsonError>()
            .body(error)
    }

    @Error
    fun internalServerError(request: HttpRequest<*>, e: Throwable): HttpResponse<JsonError> {
        val error = JsonError("Please try again")
            .link(SELF, Link.of(request.uri))

        logger.error(e.message, e)

        return HttpResponse.serverError(error)
    }
}