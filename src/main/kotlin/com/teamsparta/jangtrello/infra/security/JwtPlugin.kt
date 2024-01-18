package com.teamsparta.jangtrello.infra.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtPlugin(
    @Value("\${auth.jwt.issuer}") private val issuer : String,
    @Value("\${auth.jwt.secret}") private val secret : String,
    @Value("\${auth.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour : Long,
) {

    // 검증 메서드
    fun validateToken(jwt : String) : Result<Jws<Claims>>{ // jws : jwt 의 일종. 서명 검증한 결과.
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor( secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

    // 액세스 토큰 생성
    fun generateAccessToken(subject:String, email : String, role : String) : String { // 토큰은 단순 String
        return generateToken(subject, email, role, Duration.ofHours(accessTokenExpirationHour))
    }

    // 토큰 생성 (확장함수)
    private fun generateToken(subject:String, email : String, role : String, expirationPeriod: Duration) : String{
        val claims : Claims = Jwts.claims() // 커스텀 클레임 생성
            .add(mapOf("role" to role, "email" to email))
            .build()

        //val expirationPeriod = Duration.ofHours(168)
        val now = Instant.now()
        val key = Keys.hmacShaKeyFor( secret.toByteArray(StandardCharsets.UTF_8) )


        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationPeriod))) // 만료기간
            .claims(claims)
            .signWith(key)
            .compact()
    }
}