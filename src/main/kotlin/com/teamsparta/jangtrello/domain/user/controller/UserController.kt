package com.teamsparta.jangtrello.domain.user.controller

import com.teamsparta.jangtrello.domain.user.dto.LogInRequest
import com.teamsparta.jangtrello.domain.user.dto.SignUpRequest
import com.teamsparta.jangtrello.domain.user.dto.UpdateUserRequest
import com.teamsparta.jangtrello.domain.user.dto.UserResponse
import com.teamsparta.jangtrello.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
    fun logIn(@RequestBody request: LogInRequest): ResponseEntity<UserResponse> {
        TODO()
    }
    @PutMapping("/users/{userId}/profile")
    fun updateUser(
        @RequestBody request: UpdateUserRequest,
        @PathVariable userId:Long
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(userService.updateUser(request, userId))
    }
}