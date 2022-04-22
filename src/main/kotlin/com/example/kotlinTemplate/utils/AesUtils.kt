package com.example.kotlinTemplate.utils

import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.stereotype.Component
import org.springframework.util.Base64Utils
import org.springframework.util.ObjectUtils
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
class AesUtils {

    private val keyData = Base64Utils.decodeFromString("B4obYmCraUAnt1ben8tiCQ==")

    /**
     * AES256 으로 암호화 한다.
     *
     * @param str 암호화할 문자열
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */

    fun encrypt(str: String): String? {
        if (ObjectUtils.isEmpty(str)) {
            return ""
        }
        val IV = ByteArray(16)
        val secureKey: SecretKey = SecretKeySpec(keyData, "AES")
        val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
        c.init(Cipher.ENCRYPT_MODE, secureKey, IvParameterSpec(IV))
        val encrypted = c.doFinal(str.toByteArray(charset("UTF-8")))
        return String(Base64.encodeBase64(encrypted))
    }

    fun decrypt(str: String?): String? {
        if (ObjectUtils.isEmpty(str) || null == str) {
            return null
        }
        val IV = ByteArray(16)
        val secureKey: SecretKey = SecretKeySpec(keyData, "AES")
        val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
        c.init(Cipher.DECRYPT_MODE, secureKey, IvParameterSpec(IV))
        val decrypted = Base64Utils.decodeFromString(str)
        return String(c.doFinal(decrypted))
    }
}