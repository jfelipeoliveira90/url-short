package br.com.jfelipe.urlshort.businessrequirements.searchfulladdressurl

import br.com.jfelipe.urlshort.entrypoints.rest.url.UrlDTO
import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ScenarioState
import io.micronaut.http.HttpResponse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.assertAll

open class ThenStage : Stage<ThenStage>() {

    @ScenarioState(required = true)
    lateinit var response: HttpResponse<UrlDTO>

    open fun `return full address url`(): ThenStage {
        assertAll(
            { assertNotNull(response.body()) },
            { assertNotNull(response.body()?.fullAddress) }
        )
        return self()
    }
}