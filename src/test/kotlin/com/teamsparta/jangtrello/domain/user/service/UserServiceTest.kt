package com.teamsparta.jangtrello.domain.user.service

import com.teamsparta.jangtrello.domain.user.repository.UserRepository
import com.teamsparta.jangtrello.infra.security.jwt.JwtPlugin
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

    Given("유저의 닉네임으로 검색할 때"){
        When("존재하지 않는 닉네임을 입력하면"){
            Then("빈 페이지를 반환해야한다"){
                //val userId = 1L
                every{userRepository.findByNickName( any(), any() )} returns Page.empty()

                val result = userService.getUsersByNickName( Pageable.ofSize(3), "hoho" )

                //println("result = $result")
                result.size shouldBe 0

//                // 에러 발생 예측 시
//                shouldThrow<ModelNotFoundException> {
//                    userService.getUsersByNickName( Pageable.ofSize(3), "hoho" )
//                }
            }
        }

        When("존재하는 닉네임을 입력하면"){
            Then("결과에 맞게 출력해야한다"){
                //val userId = 1L
                every{userRepository.findByNickName( any(), any() )} returns Page.empty()
//                        PageRequest( ) <SimpleUser(
//                    email = "coding@jang.com",
//                    nickName = "codingjang",
//                )>

                val result = userService.getUsersByNickName( Pageable.ofSize(3), "user6" )

                //println("result = $result")
                result.size shouldBe 1

//                // 에러 발생 예측 시
//                shouldThrow<ModelNotFoundException> {
//                    userService.getUsersByNickName( Pageable.ofSize(3), "hoho" )
//                }
            }
        }
    }
})