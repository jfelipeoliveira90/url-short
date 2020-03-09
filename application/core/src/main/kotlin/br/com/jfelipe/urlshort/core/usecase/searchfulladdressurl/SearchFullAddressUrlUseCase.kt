package br.com.jfelipe.urlshort.core.usecase.searchfulladdressurl

class SearchFullAddressUrlUseCase(private val getUrl: GetUrl) {

    fun searchFullAddressUrl(shortAddressUrl: String) = getUrl.getFullAddressUrl(shortAddressUrl)
        ?: throw UrlNotFoundException("Short URL $shortAddressUrl not found.")
}