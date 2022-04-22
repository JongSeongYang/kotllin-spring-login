package com.example.kotlinTemplate.dto

class CommonResponse {

    data class BooleanResponse(
            var result: Boolean = false,
            var message: String? = null
    )
}