package com.example.kotlinTemplate.enum

import org.springframework.http.HttpStatus

enum class ExceptionCode (
        var code: Int,
        var message: String,
        var status: HttpStatus = HttpStatus.OK
        ){

    EMAIL_NOT_FOUND(10001,"이메일이 존재하지 않습니다."),
    SIGN_IN_FAIL(10002,"아이디와 비밀번호가 일치하지 않습니다."),
    WRONG_PWD_FIVE(10003,"비밀번호 입력이 5회 이상 실패했습니다.")
}