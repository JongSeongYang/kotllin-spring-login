package com.example.kotlinTemplate.utils

import io.jsonwebtoken.*
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

@Component
class JwtTokenProvider {

    val EXP_TIME: Long = 1000L * 60 * 60
    val JWT_SECRET: String = "F12GViFbqBay6Cnx/fWIBAYFv/vGZWUmz7aiBCuP8IVKEO9J4oX5V1yfbgKCbVDw4lKdIRz7llprMlEVJ/0IY8nYKUpCORXeBPF3Vaodn/4A73yk44hMUw0PU8tbdZgVG7kVp4sFowkESxW1n0+yThawORZRmuPkxDXPttyvCMxo3guoMH1MsWElIxdC7tKvz1Nx6dHpVBiboZ0bm0rVlrwU8oP5GacXvrSqb58eYdaP20c5WNOqVfSTNIiITHhBJ8JnhG5LpmmU4o7R4uyNDyEzfoGQe5jN/c9pvW+BnjtoFY7/IpWlsIYXkE+MEh421GWzBnIl2qYoOJiY5kFy"
    val SIGNATURE_ALG: SignatureAlgorithm = SignatureAlgorithm.HS256

    // 토큰검증
    fun validation(claims: Claims) : Boolean {
        val exp: Date = claims.expiration
        return exp.after(Date())
    }

    fun validation(token: String) : Boolean {
        val claims: Claims = getAllClaims(token)
        val exp: Date = claims.expiration
        return exp.after(Date())
    }

    // 토큰에서 email 파싱
    fun parseEmail(token: String): String {
        val claims: Claims = getAllClaims(token)
        return claims["email"] as String
    }

    fun createRegisterToken(memberId: Int?, type: String?, email: String?): String? {
        val apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_SECRET)
        val signingKey: Key = SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALG.jcaName)
        val headerMap: MutableMap<String, Any> = HashMap()
        headerMap["typ"] = "JWT"
        headerMap["alg"] = "HS256"
        val claims: Claims = Jwts.claims();
        claims["id"] = memberId.toString()
        claims["type"] = type
        claims["email"] = email
        val builder = Jwts.builder()
                .setHeader(headerMap)
                .setClaims(claims)
                .setExpiration(Date(System.currentTimeMillis()+ EXP_TIME))
                .signWith(signingKey, SIGNATURE_ALG)
        return builder.compact()
    }

    fun getAllClaims(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(JWT_SECRET)
                .build()
                .parseClaimsJws(token)
                .body
    }

//    fun validateToken(token: String?): Boolean {
//        return try {
//            val claims = Jwts.parserBuilder().setSigningKey(JWT_SECRET)
//                    .build()
//                    .parseClaimsJws(token)
//            !claims.body.expiration.before(Date())
//        } catch (e: JwtException) {
//            false
//        } catch (e: IllegalArgumentException) {
//            false
//        }
//    }

}
