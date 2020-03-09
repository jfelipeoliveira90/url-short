package br.com.jfelipe.urlshort.core.usecase.createshorturl

import br.com.jfelipe.urlshort.core.entity.Url

interface InsertUrl {
    fun insertUrl(url: Url)
}
