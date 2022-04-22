package com.example.kotlinTemplate.exception


import com.example.kotlinTemplate.dto.ExceptionResponse
import com.example.kotlinTemplate.utils.Log
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    companion object: Log {}

    val exceptionResponse = ExceptionResponse()

    @ExceptionHandler(CustomResponseStatusException::class)
    fun handleException(e:CustomResponseStatusException): ResponseEntity<ExceptionResponse?> {
        logger().error(" Exception : {}", e.exceptionCode)
        return exceptionResponse.toResponseEntity(e.exceptionCode, e.detail)
    }
}