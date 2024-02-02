package com.teamsparta.jangtrello.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class TestAop {
    // 포인트컷의 앞/뒤로 실행된다는 뜻. ( 디자이네이터 ( 포인트 컷이 적용될 경로.  )
    @Around("execution(* com.teamsparta.jangtrello.domain.card.service.CardService.getCard(..))") // *(..) 모든 메서드
    fun thisIsAdvide(joinPoint: ProceedingJoinPoint){
        println("------AOP START ! -------------------------------")
        joinPoint.proceed()
        println("------AOP END ! ---------------------------------")
    }
}