package com.example.kotlinTemplate.utils

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class UserAuthInterceptor(private val jwtTokenProvider: JwtTokenProvider) : HandlerInterceptor {
    private val AUTHORIZATION = "Authorization"

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        println(">>> UserAuthInterceptor.preHandle 호출")
        val token = extract(request, "Bearer")
        // 토큰이 없을 경우
        if (token == "") {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.")
        }
        if (!jwtTokenProvider.validation(token)) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.")
        }
        val allClaims = jwtTokenProvider.getAllClaims(token)
        request.setAttribute("memberId", allClaims["id"] as Int)
        request.setAttribute("type", allClaims["type"] as String)
        request.setAttribute("email", allClaims["email"] as String)
        return true
    }

    private fun extract(request: HttpServletRequest, type: String): String {
        val headers = request.getHeaders(AUTHORIZATION)
        while (headers.hasMoreElements()) {
            val value = headers.nextElement()
            if (value.toLowerCase().startsWith(type.toLowerCase())) {
                return value.substring(type.length).trim { it <= ' ' }
            }
        }
        return ""
    }
}