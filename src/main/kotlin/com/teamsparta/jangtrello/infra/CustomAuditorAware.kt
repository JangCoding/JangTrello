package com.teamsparta.jangtrello.infra

import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*



@Component                // Audidting 에 반응하여 자동으로 실행됨
class CustomAuditorAware : AuditorAware<Long> {  // Long : id 타입


    override fun getCurrentAuditor(): Optional<Long> {
        val authentication = SecurityContextHolder.getContext().authentication

        return if (authentication != null && authentication.principal is UserPrincipal) {
            Optional.ofNullable((authentication.principal as UserPrincipal).id)
        } else {
            Optional.empty()
        }
    }
}

//    override fun getCurrentAuditor(): Optional<Long> {
//        return Optional.ofNullable(SecurityContextHolder.getContext())
//            .map{it.authentication}
//            .map{it.principal as UserPrincipal }
//            .map{it.id}
//    }