package br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl

interface GetUrl {
    fun getFullAddressUrl(shortAddressUrl: String): String?
}
