package br.com.jfelipe.urlshort.core.usecase.createshorturl

import br.com.jfelipe.urlshort.core.entity.Url
import java.security.MessageDigest
import java.util.*

class CreateShortAddressUrlUseCase(private val insertUrl: InsertUrl) {

    fun createShortAddressUrl(fullAddress: String): String {
        val md = MessageDigest.getInstance("MD5")
        md.update(fullAddress.plus(Random().nextInt()).toByteArray())

        val shortAddress = Base64.getEncoder()
            .encodeToString(md.digest())
            .substring(0, 6)

        val url = Url(shortAddress, fullAddress)
        insertUrl.insertUrl(url)

        return url.shortAddress
    }
}