package br.com.jfelipe.urlshort.businessrequirements.createshorturl

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ScenarioState

open class GivenStage : Stage<GivenStage>() {

    @ScenarioState
    lateinit var url: String

    open fun `a full address url`(url: String): GivenStage {
        this.url = url
        return self()
    }
}
