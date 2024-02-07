package com.teamsparta.jangtrello.domain.user.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections
import com.teamsparta.jangtrello.domain.user.model.*
import com.teamsparta.jangtrello.infra.querydsl.QueryDslSupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable


// UserRepositoryImpl 로 설정해도 됨
class CustomUserRepositoryImpl : CustomUserRepository, QueryDslSupport() {
    private var q_user = QUser.user

    override fun findByIdDetailed(id: Long): DetailUser? {
        return queryFactory
            .select(
                QDetailUser(    //Q 객체의 생성자 방식으로 Projection. 순서 안지켜도 됨.
                    q_user.createdAt,
                    q_user.email,
                    q_user.nickName,
                    q_user.role
                )
            )
            .from(q_user)
            .where(q_user.id.eq(id))
            .fetchOne()
    }

    override fun findByNickName(pageable: Pageable, nickName : String): Page<SimpleUser> {

        val whereClause = BooleanBuilder()

        whereClause.and(q_user.nickName.eq(nickName))

        val totalCounts = queryFactory
            .select(q_user.count())
            .from(q_user)
            .where(whereClause)
            .fetchOne() // count 하면 어차피 행 하나만 나오기 때문
            ?: 0L

        val contents = queryFactory
            .select(
                Projections.constructor( // 생성자 방식으로 Projection. 순서 지켜야 됨
                    SimpleUser::class.java,
                    q_user.email,
                    q_user.nickName
                )
            )
            .from(q_user)
            .where(whereClause)
            .orderBy(q_user.createdAt.asc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch() // 결과 집합 가져오기


        return PageImpl(contents, pageable, totalCounts)
    }

    override fun findByPageableAndRole(pageable: Pageable, role: UserRole?): Page<User> {
        val whereClause = BooleanBuilder()

        role?.let { whereClause.and(q_user.role.eq(role)) }

        val totalCounts = queryFactory
            .select(q_user.count())
            .from(q_user)
            .where(whereClause)
            .fetchOne()
            ?: 0L

        val query = queryFactory
            .selectFrom(q_user)
            .where(whereClause)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        if (pageable.sort.isSorted) {  // @
            when (pageable.sort.first()?.property) {
                "email" -> query.orderBy(q_user.email.asc())
                "modifiedAt" -> query.orderBy(q_user.modifiedAt.asc())
                "createdAt" -> query.orderBy(q_user.modifiedAt.asc())
                else -> query.orderBy(q_user.id.asc())
            }
        } else {
            query.orderBy(q_user.id.asc())
        }

        val contents = query.fetch()

        return PageImpl(contents, pageable, totalCounts)
    }
}