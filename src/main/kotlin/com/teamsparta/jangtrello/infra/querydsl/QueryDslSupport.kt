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
    private lateinit var entityManager: EntityManager

    // 각 레포지토리에서 생성자 주입으로 사용할 수 있지만 그러면 각 객체마다 별개의 팩토리가 생성됨.
    // 하나만 만들어서 전체를 관리하도록 하는 것.
    protected val queryFactory : JPAQueryFactory
        get(){  // 매번 새로 생성되서 사용하게됨. 한번 생성해서 매번 비우는 것 보다 효율적
            return JPAQueryFactory(entityManager)
        }
}