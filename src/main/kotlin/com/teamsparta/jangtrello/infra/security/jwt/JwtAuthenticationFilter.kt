package com.teamsparta.jangtrello.infra.security.jwt

import com.teamsparta.jangtrello.infra.security.UserPrincipal
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
@Component // 클래스를 빈으로 등록
class JwtAuthenticationFilter(
    private val jwtPlugin: JwtPlugin  // 토큰 검증
) : OncePerRequestFilter() { // 매 요청 마다 JWT 확인, 검증. Spring Web 제공

    //패턴정규화?
    companion object{
        private val BEARER_PATTERN = Regex("^Bearer (.+?)$") // " Authorization" Bearer {JWT} // JWT 부분만 획득하기 위해
        // Regex() : 정규식
        // ^~~ : ~~로 시작하는 문자열 / (.+) : 가장 긴 문자열 / (.+?) 가장 짧은 문자열
        // $ : 문자열의 끝.
    }
    override fun doFilterInternal( // OncePerRequestFilter의 메서드. 필수구현해야함
        request: HttpServletRequest, // HttpServletRequest extend ServletRequest
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken() // 현재 JwtAuthenticationFilter 에서 자체제작 HttpServletRequest 메서드
        // {JWT} 부분만 가져오기
        if(jwt != null){
            jwtPlugin.validateToken(jwt)  // 토큰 검증하고
                .onSuccess { // 만약 성공하면
                    val userId = it.payload.subject.toLong() // 토큰 내부 payload 에서 정보 추출해서
                    val role = it.payload.get("role", String::class.java)
                    val email = it.payload.get("email", String::class.java)

                    val principal = UserPrincipal( // 식별자 정보도 가져오고
                        id = userId,
                        email = email,
                        roles = setOf(role)
                    )

                    // Authentication 구현체 생성, 객체에 토큰 만들어서 저장
                    val authentication = JwtAuthenticationToken(
                        principal = principal,  // 주요 인증 정보. 식별자.
                        details = WebAuthenticationDetailsSource().buildDetails(request) // 추가 정보. address, sessionId
                    )

                    //SecurityContext : Authentication 담는 콘테이너
                    SecurityContextHolder.getContext().authentication = authentication
                }
                .onFailure {
                    TODO(" 실패 이유 처리해주어야함")
                }
        }
        filterChain.doFilter(request, response) // 다음 필터 호출
    }

    // "Authorization" : Bearer {JWT} 에서 JWT 부분만 가져오기
    //확장함수(extension function). 이 클래스 에서만 HttpServletRequest에서 getBearerToken() 쓸 수 있음
    private fun HttpServletRequest.getBearerToken() : String?{ // Bearer토큰이 없을 수도 있음
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) //그냥 Headers 에 정의된 String 상수
            ?: return null // 위 키워드를 기반으로 헤더값을 가져올 수 있음 // Bearer {JWT} 로 되어있음

        return BEARER_PATTERN.find(headerValue)?.groupValues?.get(1)
        // 헤더값들을 getHeader로 가져왔는데 그중 PATTERN에 맞는 것 중에 첫번 째 것만 가져오기
    }

}
