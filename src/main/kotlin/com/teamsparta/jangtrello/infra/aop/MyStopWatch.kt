package com.teamsparta.jangtrello.infra.aop

@Target(AnnotationTarget.FUNCTION) // 어노테이션이 적용될 대상. 함수 대상
@Retention(AnnotationRetention.RUNTIME) // 런타임 시 사용되거나 지정될 수 있도록 함
annotation class MyStopWatch() { // @MyStopWatch 어노테이션을 등록
}