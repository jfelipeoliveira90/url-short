package br.com.jfelipe.urlshort.businessrequirements.searchfulladdressurl

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ScenarioState

open class GivenStage : Stage<GivenStage>() {

    @ScenarioState
    lateinit var shortUrl: String

    open fun `a short address url`(shortUrl: String): GivenStage {
        this.shortUrl = shortUrl
        return self()
    }
}