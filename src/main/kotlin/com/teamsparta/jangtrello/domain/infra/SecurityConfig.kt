package com.teamsparta.jangtrello.domain.infra

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity // http 기반 통신시 관련 보안 기능 켜기위해 설정
class SecurityConfig {
    @Bean
    fun filterChain(http:HttpSecurity):SecurityFilterChain{
        return http
            .httpBasic{it.disable()} // 사용하지 않는 필터 끄기
            .formLogin{it.disable()}
            .csrf{ it.disable()}
            .build()
    }
}