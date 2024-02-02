package com.teamsparta.jangtrello.domain.card.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.service.CardService
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
import java.time.LocalDateTime


@SpringBootTest           // 쥬피터 ? 가 관리. Bean 자동 주입 못해서 적어줘야함
@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
class CardControllerTest @Autowired constructor (
    private val mockMvc : MockMvc,
    private val jwtPlugin: JwtPlugin,
) : DescribeSpec({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks() // mocking 된 걸 전부 제거
    }

    val cardService = mockk<CardService>()

    describe("GET /card/{id}"){
        context("존재하는 ID를 요청할 때"){
            it("200 status code를 응답한다"){
                var cardId = 1L

                every { cardService.getCard( any(), any() )} returns CardResponse(
                    id = cardId,
                    title = "Test_Title",
                    createdAt = LocalDateTime.now(),
                    email = "my@email",
                    nickName = "test_nick",
                    status = "false",
                    contents = "test_conntets",
                    comments = mutableListOf(),
                )

                val jwtToken = jwtPlugin.generateAccessToken(
                    subject = "1",
                    email = "tMail",
                    role = "USER",
                    nickname = "tnick",
                )
                val result = mockMvc.perform(
                    get("/card/$cardId")
                        .header("Authorization", "Bearer $jwtToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn()
                result.response.status shouldBe 200 // 상태값 200

                val responseDto = jacksonObjectMapper().readValue(
                    result.response.contentAsString,
                    CardResponse::class.java
                )

                responseDto.id shouldBe cardId
            }
        }
    }
})