package com.teamsparta.jangtrello.infra.security.jwt


import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetails

// jwt(액세스토큰) 가져와서 새로운 인증 토큰을 만듦
class JwtAuthenticationToken(
    private val principal: UserPrincipal,
    details : WebAuthenticationDetails // 요청하고 있는 주소, 세션 정보
) : AbstractAuthenticationToken(principal.authoricies){ // .authoricies = role
    init {
        // super = AbstractAuthenticationToken //
        super.setAuthenticated(true)  // 검증 여부
        super.setDetails(details) // 디테일 정보 전달
    }
    override fun getPrincipal() = principal // UserPrincipal 정보 바로 반환
    override fun getCredentials() = null // 민감정보는 안줌.
    override fun isAuthenticated(): Boolean {
        return true
    }
}