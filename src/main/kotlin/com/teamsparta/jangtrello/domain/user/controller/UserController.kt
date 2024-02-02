package com.teamsparta.jangtrello.domain.user.controller

import com.teamsparta.jangtrello.domain.user.dto.*
import com.teamsparta.jangtrello.domain.user.service.UserService
import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
    fun logIn(@RequestBody request: LogInRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(userService.logIn(request))
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/pagedUserList")
    fun getPagedUserList(
        // pathVarialble 로 동작
        @PageableDefault( // pageable 기본값 설정. id로 기본 정렬
            size = 3,
            sort = ["id"]
        ) pageable: Pageable,
        @RequestParam(value = "Role", required = false) role : String?,
    ) : ResponseEntity<Page<UserResponse>>
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getPagedUserList(pageable,role))
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