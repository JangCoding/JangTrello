package com.teamsparta.jangtrello.domain.user.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.teamsparta.jangtrello.domain.user.dto.DetailUserResponse
import com.teamsparta.jangtrello.domain.user.service.UserService
import com.teamsparta.jangtrello.infra.security.jwt.JwtPlugin
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get


@SpringBootTest
@AutoConfigureMockMvc // mockMvc 를 자동으로 연결해줌
@ExtendWith(MockKExtension::class)
class UserControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtPlugin: JwtPlugin,
) : DescribeSpec({
    extension(SpringExtension)

    // Container 기반으로 셋팅을 하기 때문에 다른 셋팅을 할 땐 비워주기
    afterContainer {
        clearAllMocks()
    }


    val userService = mockk<UserService>()

    describe("GET /Users/{id}"){
        context("존재하는 ID 를 요청할 때") {
            it("200 status code 응답") {
                val userId = 1L

                // any() 어떤 값을 넣든 every 모든 해당 서비스의 응답은 아래 값이 될 것
                every{userService.getUser( any() )} returns DetailUserResponse(
                    createdAt = null,
                    email = "coding@jang.com",
                    nickName = "codingjang",
                    role = "USER"
                )

                val jwtToken = jwtPlugin.generateAccessToken(
                    subject = "1",
                    email = "test@email.com",
                    role = "USER",
                    nickname = "testJang"
                )
                val result = mockMvc.perform(
                    //import mockMvc RequestBuilder
                    get("/Users/$userId")
                        .header("Authorization", "Bearer $jwtToken") // 시큐리티 구현했기 때문에 필요
                        .contentType(MediaType.APPLICATION_JSON) // 추가정보
                        .accept(MediaType.APPLICATION_JSON)  // 추가정보, 안해도 됨.
                    ).andReturn()

                result.response.status shouldBe 200 // 여기까진 성공

//                val responseDto = jacksonObjectMapper()   // Gson은 의존성 추가 필요 // Dto 에 @Seirializable 붙이는 방법도 있음
//                    .readValue(
//                        result.response.contentAsString,
//                        DetailUserResponse::class.java
//                    )
//
//                responseDto.nickName shouldBe "codingjang"
            }
        }
    }

})