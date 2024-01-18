package com.teamsparta.jangtrello.infra.security

import com.example.courseregistration.domain.exception.dto.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
//사용자가 특정 리소스 또는 기능에 대한 충분한 권한을 가지고 있지 않을 때 호출
class CustomAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException?
    ) {
        response.status = HttpServletResponse.SC_FORBIDDEN
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"

        val objectMapper = ObjectMapper()
        val jsonString = objectMapper.writeValueAsString(ErrorResponse("No permission to run API"))
        response.writer.write(jsonString)
    }
}