package br.com.jfelipe.urlshort

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .packages("br.com.jfelipe.urlshort")
            .mainClass(Application.javaClass)
            .start()
    }
}
