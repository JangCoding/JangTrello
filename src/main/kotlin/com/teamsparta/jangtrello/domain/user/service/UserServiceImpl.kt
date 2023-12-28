package com.teamsparta.jangtrello.domain.user.service

import com.example.courseregistration.domain.exception.ModelNotFoundException
import com.teamsparta.jangtrello.domain.user.dto.LogInRequest
import com.teamsparta.jangtrello.domain.user.dto.SignUpRequest
import com.teamsparta.jangtrello.domain.user.dto.UpdateUserRequest
import com.teamsparta.jangtrello.domain.user.dto.UserResponse
import com.teamsparta.jangtrello.domain.user.model.User
import com.teamsparta.jangtrello.domain.user.model.toResponse
import com.teamsparta.jangtrello.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity

class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun signUp(request: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
            User(
                email = request.email,
                // TODO: 비밀번호 암호화
                password = request.password,
                name = request.name
            )
        ).toResponse()
    }

    override fun logIn(request: LogInRequest): UserResponse {
        TODO("Not yet implemented")
    }

    override fun updateUser(request: UpdateUserRequest, userId: Long): UserResponse {
        // TODO: 만약 userId에 해당하는 User가 없다면 throw ModelNotFoundException
        // TODO: DB에서 userId에 해당하는 User를 가져와서 updateUserProfileRequest로 업데이트 후 DB에 저장, 결과를 UserResponse로 변환 후 반환
        var user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }
        user.email = request.email
        user.password = request.pw

        return userRepository.save(user).toResponse()
    }
}