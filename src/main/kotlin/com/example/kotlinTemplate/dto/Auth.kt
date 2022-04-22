package com.example.kotlinTemplate.dto

class Auth {

    data class SignInResponse(
            val result: Boolean = false,
            val token: String? = null,
            val message: String? = null,
    )

    data class SignInRequest(
            val email: String? = null,
            val password: String? = null,
            val deviceToken: String? = null,
    )
}