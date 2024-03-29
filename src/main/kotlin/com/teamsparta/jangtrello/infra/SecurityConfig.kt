package com.teamsparta.jangtrello.infra

import com.teamsparta.jangtrello.infra.security.CustomAuthenticationEntryPoint
import com.teamsparta.jangtrello.infra.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity // http 기반 통신시 관련 보안 기능 켜기위해 설정
@EnableMethodSecurity // @Secured, @POreAutorize 등 사용 가능하게 함
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val accessDeniedHandler: AccessDeniedHandler,
) {
    @Bean
    fun filterChain(http: HttpSecurity):SecurityFilterChain{
        return http
            .httpBasic{it.disable()}  // 사용하지 않는 필터 끄기
            .formLogin{it.disable()}
            .csrf{ it.disable()} // 일종의 보안 공격 방지. 꺼버림.
            .headers { it.frameOptions{ option -> option.sameOrigin() } } // H2 설정
            // 요청에 대한 인가 규칙 정의
            .authorizeHttpRequests{
                it.requestMatchers( // 특정 경로 설정 지정
                    "/logIn",
                    "/signUp",
                    "/swagger-ui/**", // swagger페이지
                    "v3/api-docs/**", // 내용 docs
                    "/h2-console/**",
                    "/error"       // security 에서 내부적으로 에러 발생시 이 경로로 리다이렉션 됨. -> 새 요청에 인가가 안되서 먼저 에러가 jwt invalid

                ).permitAll() // 위 URL은 승인처리
                    .anyRequest() // 나머지 요청들은
                    .authenticated() // 인증이 된 사용자만 허용
            }
            // 기존 UsernamePasswordAuthenticationFilter 가 존재하던 자리에 JwtAuthenticationFilter 추가
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling{
                it.authenticationEntryPoint(authenticationEntryPoint)
                it.accessDeniedHandler(accessDeniedHandler) // 에러 연결하기
            }
            .build()
    }
}