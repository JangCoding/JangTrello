package com.teamsparta.jangtrello.domain.user.controller

import com.teamsparta.jangtrello.domain.user.dto.*
import com.teamsparta.jangtrello.domain.user.service.UserService
import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signUp")
    fun signUp(@RequestBody request: SignUpRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(userService.signUp(request))
    }
    @PostMapping("/logIn")
    fun logIn(@RequestBody request: LogInRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(userService.logIn(request))
    }
    @PutMapping("/updateProfile")
    fun updateUser(
        @RequestBody request: UpdateUserRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(userService.updateUser(userPrincipal, request))
    }
}