package br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl

open class SearchFullAddressUrlUseCase(private val getUrl: GetUrl) {

    open fun searchFullAddressUrl(shortAddressUrl: String) = getUrl.getFullAddressUrl(shortAddressUrl)
        ?: throw UrlNotFoundException("Short URL $shortAddressUrl not found")
}