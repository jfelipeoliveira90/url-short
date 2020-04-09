package br.com.jfelipe.urlshort.core.entity

import org.apache.commons.codec.binary.Base64.isBase64
import org.apache.commons.validator.routines.UrlValidator

data class Url private constructor(val shortAddress: String, val fullAddress: String) {
    companion object {
        operator fun invoke(shortAddress: String, fullAddress: String): Url {
            val urlValidator = UrlValidator(arrayOf("http", "https"))

            require(isBase64(shortAddress)) { "Short url is not valid" }
            require(urlValidator.isValid(fullAddress)) { "Url is not valid" }

            return Url(shortAddress, fullAddress)
        }
    }
}