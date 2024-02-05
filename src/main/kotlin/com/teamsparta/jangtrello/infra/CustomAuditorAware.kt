package com.teamsparta.jangtrello.infra

import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomAuditorAware : AuditorAware<Long> {  // Long : id 타입

    override fun getCurrentAuditor(): Optional<Long> {
        return Optional.ofNullable(SecurityContextHolder.getContext())
            .map{it.authentication}
            .map{it.principal as UserPrincipal}
            .map{it.id}
    }
}