package com.teamsparta.jangtrello.domain.user.service

import com.teamsparta.jangtrello.domain.exception.InvalidCredentialsException
import com.teamsparta.jangtrello.domain.exception.ModelNotFoundException
import com.teamsparta.jangtrello.infra.security.jwt.JwtPlugin
import com.teamsparta.jangtrello.domain.user.dto.*
import com.teamsparta.jangtrello.domain.user.model.User
import com.teamsparta.jangtrello.domain.user.model.UserRole
import com.teamsparta.jangtrello.domain.user.model.toResponse
import com.teamsparta.jangtrello.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder, // 비밀번호 암호화 저장
    private val jwtPlugin: JwtPlugin // 토큰 검증 ,  생성

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
                nickName = request.nickName,
                role = when(request.role.uppercase()){
                    "USER" -> UserRole.USER
                    "MANAGER" -> UserRole.MANAGER
                    else -> throw  IllegalStateException("Invalid Role")
                }
            )
        ).toResponse()
    }

    override fun logIn(request: LogInRequest): LoginResponse {
        // email, password 체크
        val user = userRepository.findByEmail(request.email)
            ?: throw ModelNotFoundException("User", null)

        if (!passwordEncoder.matches(request.password, user.password))
            throw InvalidCredentialsException("Password", request.password)

        return LoginResponse(
            accessToken =
            jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name
            )
        )
    }


    override fun updateUser(request: UpdateUserRequest, userId: Long): UserResponse {
        // TODO: 만약 userId에 해당하는 User가 없다면 throw ModelNotFoundException
        // TODO: DB에서 userId에 해당하는 User를 가져와서 updateUserProfileRequest로 업데이트 후 DB에 저장, 결과를 UserResponse로 변환 후 반환
        var user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }
        user.email = request.email
        user.password = request.password

        return userRepository.save(user).toResponse()
    }
}