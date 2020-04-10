package br.com.jfelipe.urlshort.businessrequirements.searchfulladdressurl

import com.tngtech.jgiven.annotation.ScenarioStage
import com.tngtech.jgiven.junit5.JGivenExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(JGivenExtension::class)
class SearchFullAddressUrlAcceptanceTest {

    @ScenarioStage
    lateinit var given: GivenStage

    @ScenarioStage
    lateinit var `when`: WhenStage

    @ScenarioStage
    lateinit var then: ThenStage

    @Test
    fun `Should return full address url`() {
        given.`a short address url`("t3st3")
        `when`.`call method get`()
        then.`return full address url`()
    }
}