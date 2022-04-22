package com.example.kotlinTemplate.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.example.kotlinTemplate.enum.ExceptionCode
import com.example.kotlinTemplate.utils.Log
import com.example.kotlinTemplate.utils.TimeZoneDateSerializer

import org.springframework.http.ResponseEntity
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime


class ExceptionResponse(

        var code: Int? = null,
        var codeName: String? = null,
        var message: String? = null,
        @JsonSerialize(using = TimeZoneDateSerializer::class)
        val timestamp:LocalDateTime = LocalDateTime.now(ZoneOffset.UTC),
){
    companion object : Log {}

    fun toResponseEntity(exception: ExceptionCode, detail: String): ResponseEntity<ExceptionResponse?> {
        val exceptionResponse : ExceptionResponse = ExceptionResponse(
                codeName = exception.name,
                code = exception.code,
                message = exception.message + detail,
        )
        return ResponseEntity
                .status(exception.status)
                .body(exceptionResponse)
    }
}