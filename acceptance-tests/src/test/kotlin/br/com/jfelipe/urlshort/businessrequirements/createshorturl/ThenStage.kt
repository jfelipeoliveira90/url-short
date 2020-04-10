package br.com.jfelipe.urlshort.businessrequirements.createshorturl

import br.com.jfelipe.urlshort.entrypoints.rest.url.UrlDTO
import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ScenarioState
import io.micronaut.http.HttpResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.assertAll

open class ThenStage : Stage<ThenStage>() {

    @ScenarioState(required = true)
    lateinit var response: HttpResponse<UrlDTO>

    open fun `return short url with 6 characters`(): ThenStage {
        assertAll(
            { assertNotNull(response.body()) },
            { assertNotNull(response.body()?.shortAddress) },
            { assertEquals(6, response.body()?.shortAddress?.length) }
        )
        return self()
    }
}
