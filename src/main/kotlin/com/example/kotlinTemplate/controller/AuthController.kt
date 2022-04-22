package com.example.kotlinTemplate.controller

import com.example.kotlinTemplate.dto.Auth
import com.example.kotlinTemplate.service.AuthService
import com.example.kotlinTemplate.utils.Log
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1/auth")
public class PostController (
        private val authService: AuthService
        ) {

    companion object : Log {}

    @ApiOperation(value = "signIn", notes = "signIn")
    @PostMapping(value = [""], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun signIn(request: HttpServletRequest,
               @RequestBody signInRequest : Auth.SignInRequest ): ResponseEntity<Auth.SignInResponse>? {
        return ResponseEntity.ok(authService.signIn(request,signInRequest))
    }
}