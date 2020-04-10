package br.com.jfelipe.urlshort.businessrequirements.createshorturl

import br.com.jfelipe.urlshort.core.usecase.createshorturl.CreateShortAddressUrlUseCase
import br.com.jfelipe.urlshort.core.usecase.createshorturl.InsertUrl
import br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl.GetUrl
import br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl.SearchFullAddressUrlUseCase
import br.com.jfelipe.urlshort.entrypoints.rest.url.UrlDTO
import br.com.jfelipe.urlshort.entrypoints.rest.url.UrlEndpoint
import com.nhaarman.mockito_kotlin.mock
import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ScenarioState
import io.micronaut.http.HttpResponse

open class WhenStage : Stage<WhenStage>() {

    @ScenarioState(required = true)
    lateinit var url: String

    @ScenarioState
    lateinit var response: HttpResponse<UrlDTO>

    private val insertUrl = mock<InsertUrl> {}
    private val getUrl = mock<GetUrl> {}
    private val createShortAddressUrlUseCase = CreateShortAddressUrlUseCase(insertUrl)
    private val searchFullAddressUrlUseCase = SearchFullAddressUrlUseCase(getUrl)
    private val endpoint = UrlEndpoint(createShortAddressUrlUseCase, searchFullAddressUrlUseCase)

    open fun `call method post`(): WhenStage {
        response = endpoint.create(UrlDTO(url, null))
        return self()
    }
}
