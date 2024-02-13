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

//
////자동으로 PageImpl 에 맞게 값 채워주는 메서드 // QClass           // 람다식{} 함수. 람다 함수를 실행한 결과를 JPAQuery<T> 로 가져오겠다는 뜻
//fun <T> byPaging(pageable: Pageable, path: EntityPathBase<T>, baseQueryFunc: () -> JPAQuery<T>): Page<T> {
//    val baseQuery = baseQueryFunc() // queryFactory 를 통해 select 하는 Query 생성.
//    val totalCount = baseQuery      // 위의 쿼리문 결과에 추가로 작업
//        .select(path.count())       // 결과값 카운트
//        .fetchOne() ?: 0L
//    val result = baseQuery
//        .select(path)
//        .offset(pageable.offset)
//        .limit(pageable.pageSize.toLong())
//        .fetch()
//    return PageImpl(result, pageable, totalCount)
//}