package br.com.jfelipe.urlshort.businessrequirements.createshorturl

import com.tngtech.jgiven.annotation.ScenarioStage
import com.tngtech.jgiven.junit5.JGivenExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(JGivenExtension::class)
class CreateShortUrlAcceptanceTest {

    @ScenarioStage
    lateinit var given: GivenStage

    @ScenarioStage
    lateinit var `when`: WhenStage

    @ScenarioStage
    lateinit var then: ThenStage

    @ParameterizedTest
    @ValueSource(
        strings = [
            "http://foo.bar:8080",
            "https://www.google.com/search?q=loggi",
            "https://www.loggi.com/compartilhe/corp/86b35e3a1f2bf7b8f77c7ef30c3cd159"
        ]
    )
    fun `Should create new short url`(url: String) {
        given.`a full address url`(url)
        `when`.`call method post`()
        then.`return short url with 6 characters`()
    }
}