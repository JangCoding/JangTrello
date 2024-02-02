package com.teamsparta.jangtrello.domain.user.repository

import com.querydsl.core.BooleanBuilder
import com.teamsparta.jangtrello.domain.user.model.QUser
import com.teamsparta.jangtrello.domain.user.model.User
import com.teamsparta.jangtrello.domain.user.model.UserRole
import com.teamsparta.jangtrello.infra.querydsl.QueryDslSupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class UserRepositoryImpl : CustomUserRepository, QueryDslSupport() {
    private var user = QUser.user
    override fun findByPageableAndRole(pageable: Pageable, role: UserRole?): Page<User> {
        val whereClause = BooleanBuilder()

        role?.let{ whereClause.and(user.role.eq(role)) }

        val totalCounts = queryFactory
            .select(user.count())
            .from(user)
            .where(whereClause)
            .fetchOne()
            ?: 0L

        val query = queryFactory
            .selectFrom(user)
            .where(whereClause)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        if(pageable.sort.isSorted){  // @
            when(pageable.sort.first()?.property){
                "email" -> query.orderBy(user.email.asc())
                "modifiedAt" -> query.orderBy(user.modifiedAt.asc())
                "createdAt" -> query.orderBy(user.modifiedAt.asc())
                else -> query.orderBy(user.id.asc())
            }
        }
        else{
            query.orderBy(user.id.asc())
        }

        val contents = query.fetch()

        return PageImpl(contents, pageable, totalCounts)
    }
}