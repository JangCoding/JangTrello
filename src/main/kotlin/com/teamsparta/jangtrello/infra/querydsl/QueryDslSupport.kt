package com.teamsparta.jangtrello.infra.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager

import jakarta.persistence.PersistenceContext

// querydsl 통해 쿼리 작성시 항상 queryFactory 가 필요.
abstract class QueryDslSupport {
    // 해당 어노테이션이 붙은 필드나 메소드 매개변수에 영속성 컨텍스트를 주입한다는 의미.
    // 이를 통해 엔터티 매니저를 사용하여 영속성 컨텍스트를 관리하고, 데이터베이스와의 상호 작용을 수행할 수 있음
    // 영속성 컨텍스트를 객체에 제공하여 JPA 에서 제공하는 기능을 사용할 수 있게 한다
    @PersistenceContext
    protected lateinit var entityManager: EntityManager

    protected val queryFactory : JPAQueryFactory
        get(){
            return JPAQueryFactory(entityManager)
        }
}