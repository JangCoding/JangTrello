package com.teamsparta.jangtrello.domain.user.service

import com.teamsparta.jangtrello.domain.exception.ModelNotFoundException
import com.teamsparta.jangtrello.domain.user.dto.DetailUserResponse
import com.teamsparta.jangtrello.domain.user.model.SimpleUser
import com.teamsparta.jangtrello.domain.user.repository.UserRepository
import com.teamsparta.jangtrello.infra.security.jwt.JwtPlugin
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
@ExtendWith(MockKExtension::class)
class UserServiceTest(
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin,
) : BehaviorSpec({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    val userRepository = mockk<UserRepository>()
    val userService = UserServiceImpl(userRepository, passwordEncoder, jwtPlugin)

    Given("~할 때"){
        When("~ 하면"){
            Then("~ 해야한다"){
                val userId = 1L
                every{userRepository.findByNickName( any(), any() )} returns Page.empty()

                val result = userService.getUsersByNickName( Pageable.ofSize(3), "hoho" )

                println("result = $result")
                result.size shouldBe 0

//                // 에러 발생 예측 시
//                shouldThrow<ModelNotFoundException> {
//                    userService.getUsersByNickName( Pageable.ofSize(3), "hoho" )
//                }
            }
        }

        When("~ 하면"){
            Then("~ 해야한다"){
                val userId = 1L
                every{userRepository.findByNickName( any(), any() )} returns Page.empty()
//                        PageRequest( ) <SimpleUser(
//                    email = "coding@jang.com",
//                    nickName = "codingjang",
//                )>

                val result = userService.getUsersByNickName( Pageable.ofSize(3), "hoho" )

                println("result = $result")
                result.size shouldBe 0

//                // 에러 발생 예측 시
//                shouldThrow<ModelNotFoundException> {
//                    userService.getUsersByNickName( Pageable.ofSize(3), "hoho" )
//                }
            }
        }
    }
})