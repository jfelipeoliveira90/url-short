package br.com.jfelipe.urlshort.entrypoints.rest.url

import io.micronaut.core.annotation.Introspected

@Introspected
data class UrlDTO(val fullAddress: String, val shortAddress: String?)