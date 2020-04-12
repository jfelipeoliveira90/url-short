package br.com.jfelipe.urlshort.core.usecase.createshorturl

import br.com.jfelipe.urlshort.core.entity.Url
import org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString
import java.security.MessageDigest
import java.util.*

open class CreateShortAddressUrlUseCase(private val insertUrl: InsertUrl) {

    open fun createShortAddressUrl(fullAddress: String): String {
        val md = MessageDigest.getInstance("MD5")
        md.update(fullAddress.plus(Random().nextInt()).toByteArray())

        val shortAddress = encodeBase64URLSafeString(md.digest())
            .substring(0, 6)

        val url = Url(shortAddress, fullAddress)
        insertUrl.insertUrl(url)

        return url.shortAddress
    }
}