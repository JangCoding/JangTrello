package com.teamsparta.jangtrello.domain.card.repository

import com.querydsl.core.BooleanBuilder
import com.teamsparta.jangtrello.domain.card.model.Card
import com.teamsparta.jangtrello.domain.card.model.CardStatus
import com.teamsparta.jangtrello.domain.card.model.QCard
import com.teamsparta.jangtrello.domain.comment.model.QComment
import com.teamsparta.jangtrello.infra.querydsl.QueryDslSupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class CardRepositoryImpl : CustomCardRepository, QueryDslSupport() { // 괄호 안에 아무 값도 전달하지 않고 기본 생성자 호출
    private val card = QCard.card

    override fun searchCardByTitle(title: String): List<Card> {
        return queryFactory.selectFrom(card)
            .where(card.title.containsIgnoreCase(title)) // 대소문자 무시
            .fetch()
    }

    override fun findByPageableAndStatus(pageable: Pageable, status: CardStatus?): Page<Card> {
        val whereClause = BooleanBuilder()

        //status가 null이 아니면 where에 equal 조건 추가
        status?.let{whereClause.and(card.status.eq(status))}

        val totalCount = queryFactory
            .select(card.count()) // 카운트 쿼리 생성. 결과는 한 행
            .from(card)           // Q.카드 엔티티에서
            .where(whereClause)
            .fetchOne()           // 첫 행 or null ( 어차피 count 는 1 행 )
            ?: 0L                 // 결과 null 이면 0 Long

        val comment = QComment.comment

        // order를 동적으로 설정하기 위해 미리 query를 만들어둠
        val query = queryFactory.selectFrom(card)
            .where(whereClause)         // 현재 조건따라
            .leftJoin(card.comments, comment) // N+1 문제 해결, comment 가져오기3
            .fetchJoin()
            .offset(pageable.offset)    // 현재 페이지 시작 위치
            .limit(pageable.pageSize.toLong()) // 페이지당 표시 항목 수

        // order 가 존재하는 지 확인( @PageableDefault 의 sort = ["id"] )
        if (pageable.sort.isSorted){
            when(pageable.sort.first()?.property){
                "id" -> query.orderBy(card.id.asc())
                "title" -> query.orderBy(card.title.asc())
                else -> query.orderBy(card.id.asc())
                // 위에서 만들어둔 query 결과값에 추가로 정렬
            }
        }
        else{
            query.orderBy(card.id.asc())
        }

        // 최종적으로 쿼리 수행
        val contents = query.fetch()

        return PageImpl(contents, pageable, totalCount)
    }
}