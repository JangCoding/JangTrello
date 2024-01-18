package com.teamsparta.jangtrello.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal( // 사용자 정보를 표현
    val id: Long,
    val email:String,
    val authoricies : Collection<GrantedAuthority> // 사용자 권한 정보 나타냄
    // role : 단순 string 아닌 GrantedAuthority(권한이름들 담겨있음) 적용.
){

    // 인스턴스 생성시 초기화 블록
    constructor(id:Long,email:String,roles:Set<String>):this(
        id,
        email,                                //Prefix : 앞글자 고정
        roles.map{ SimpleGrantedAuthority("ROLE_$it") }
        //사용자의 role 을 Set<String>으로 받아와서 SimpleGrantedAuthority로 변환하여 authorities에 저장.
    )
}
