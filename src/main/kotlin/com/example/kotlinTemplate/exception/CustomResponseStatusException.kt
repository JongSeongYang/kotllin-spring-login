package com.example.kotlinTemplate.exception

import com.example.kotlinTemplate.enum.ExceptionCode

class CustomResponseStatusException( val exceptionCode: ExceptionCode,
                                     val detail: String) : RuntimeException() {
}