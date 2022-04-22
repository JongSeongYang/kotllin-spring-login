package com.example.kotlinTemplate.utils

import org.apache.commons.codec.binary.Hex
import org.springframework.stereotype.Component
import java.io.UnsupportedEncodingException
import java.security.MessageDigest

@Component
class HashUtils {
    fun computeHash(s: ByteArray?): ByteArray? {
        val messageDigest = "SHA-256"
        return try {
            val md = MessageDigest.getInstance(messageDigest)
            md.update(s)
            md.digest()
        } catch (e: Exception) {
            null
        }
    }

    fun toHash(value: String?): String? {
        if (null == value || value == "") return null
        var ret: String? = ""
        try {
            val bytes = value.toByteArray(charset("utf-8"))
            val encHashBytes = computeHash(bytes)
            ret = Hex.encodeHexString(encHashBytes)
        } catch (e: UnsupportedEncodingException) {
            println(e.toString())
        }
        return ret
    }

    fun toPasswordHash(password: String): String? {
        return toHash(password)
    }
}