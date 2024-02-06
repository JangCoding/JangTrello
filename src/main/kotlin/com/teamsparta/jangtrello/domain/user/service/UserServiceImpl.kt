package com.teamsparta.jangtrello.domain.user.service

import com.teamsparta.jangtrello.domain.exception.InvalidCredentialsException
import com.teamsparta.jangtrello.domain.exception.ModelNotFoundException
import com.teamsparta.jangtrello.domain.user.dto.*
import com.teamsparta.jangtrello.domain.user.model.User
import com.teamsparta.jangtrello.domain.user.model.UserRole
import com.teamsparta.jangtrello.domain.user.repository.UserRepository
import com.teamsparta.jangtrello.infra.security.UserPrincipal
import com.teamsparta.jangtrello.infra.security.jwt.JwtPlugin
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder, // 비밀번호 암호화 저장
    private val jwtPlugin: JwtPlugin // 토큰 검증 ,  생성

) : UserService {

    // 유저 회원 가입
    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
            User(
                email = request.email,
                // TODO: 비밀번호 암호화
                password = passwordEncoder.encode( request.password) , // BCryptPasswordEncoder().encode()
                nickName = request.nickName,
                role = when(request.role.uppercase()){
                    "USER" -> UserRole.USER
                    "MANAGER" -> UserRole.MANAGER
                    else -> throw  IllegalStateException("Invalid Role")
                }
            )
        ).toResponse()
    }

    // 유저 로그인
    override fun logIn(request: LogInRequest): LoginResponse {
        // email, password 체크
        val user = userRepository.findByEmail(request.email)
            ?: throw ModelNotFoundException("User", "Email")

        if (!passwordEncoder.matches(request.password, user.password))
            throw InvalidCredentialsException("Password", request.password)

        return LoginResponse(
            accessToken =
            jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name,
                nickname = user.nickName
            )
        )
    }

    // 유저 리스트 조회 ( 페이지 적영, 역할 기준 )
    override fun getPagedUserList(pageable: Pageable, role: String?): Page<UserResponse> {
        val r =  when(role?.uppercase()){
            "USER" -> UserRole.USER
            "MANAGER" -> UserRole.MANAGER
            else -> null
        }
        return userRepository.findByPageableAndRole(pageable, r).map{ it.toResponse() }
    }

    override fun getUser(id:Long): DetailUserResponse {
        return userRepository.findByIdDetailed(id)
            ?.toResponse()
            ?:throw ModelNotFoundException("ID", id)
    }

    override fun getUsersByNickName(pageable: Pageable, nickName: String): Page<SimpleUserResponse> {
        return userRepository.findByNickName(pageable, nickName).map{ it.toResponse()}
    }

    // 유저 업데이트
    @Transactional
    override fun updateUser(userPrincipal: UserPrincipal, request: UpdateUserRequest): UserResponse {
        val user = userRepository.findByIdOrNull(userPrincipal.id)
            ?: throw ModelNotFoundException("User", userPrincipal.id)

        if (!passwordEncoder.matches(request.password, user.password))
            throw InvalidCredentialsException("Password", request.password)

        if (userRepository.existsByNickName(request.nickname)) {
            throw IllegalStateException("NickName is already in use")
        }
        user.nickName = request.nickname

        userRepository.save(user)

        return user.toResponse()
    }

    // 유저 삭제
}
