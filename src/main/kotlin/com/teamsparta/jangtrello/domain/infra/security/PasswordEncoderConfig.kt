package com.teamsparta.jangtrello.domain.infra.security

import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoderConfig { // PasswordEncoder 빈 등록
    @Bean
    fun passwordEncoder() : PasswordEncoder{
        return BCryptPasswordEncoder()
        // 비밀번호 해싱해줌. 단방향. 저장된 코드만 있고 그 코드가 무슨 값인지는 모름.
    }
}