package com.teamsparta.jangtrello.infra.security

import com.example.courseregistration.domain.exception.dto.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.logging.log4j.message.ObjectArrayMessage
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
//커스텀 인증 진입 지점. 인증 예외가 발생했을 때 클라이언트에게 적절한 응답을 제공
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint{
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"

        val objectMapper = ObjectMapper()
        val jsonString = objectMapper.writeValueAsString(ErrorResponse("JWT Verification failed"))
        response.writer.write(jsonString)
    }
}