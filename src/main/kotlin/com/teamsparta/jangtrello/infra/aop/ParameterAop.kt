package com.teamsparta.jangtrello.infra.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Aspect
@Component
class ParameterAop {

    // com/example/aop/controller 패키지 하위 클래스들 전부 적용하겠다고 지점 설정
    @Pointcut("execution(* com.teamsparta.jangtrello..*.*(..))")
    public fun cut() {} // AspectJ에 의해 해당 메서드의 호출 지점으로 등록되기 때문에 public
    // cut() 메서드가 실행되는 지점 이전에 before() 메서드 실행
    @Before("cut()")
    fun before(joinPoint: JoinPoint) {
        // 실행되는 함수 이름을 가져오고 출력
        val methodSignature = joinPoint.signature as MethodSignature
        val method: Method = methodSignature.method
        println("${method.name} 메서드 실행")

        // 메서드에 들어가는 매개변수 배열을 읽어옴
        val args: Array<Any> = joinPoint.args

        // 매개변수 배열의 종류와 값을 출력
        for (obj in args) {
            println("type : ${obj.javaClass.simpleName}")
            println("value : $obj")
        }
    }

    // cut() 메서드가 종료되는 시점에 afterReturn() 메서드 실행
    // @AfterReturning 어노테이션의 returning 값과 afterReturn 매개변수 obj의 이름이 같아야 함
    @AfterReturning(value = "cut()", returning = "obj")
    fun afterReturn(joinPoint: JoinPoint, obj: Any) {
        println("return obj")
        println(obj)
    }
}