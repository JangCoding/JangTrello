package com.teamsparta.jangtrello.domain.card.repository


// CardRepositoryImpl 로 흡수 합병

//@Repository
//class QueryDslCardRepository : QueryDslSupport(){ // 괄호 안에 아무 값도 전달하지 않고 기본 생성자 호출
//    private val card = QCard.card
//
//    fun searchCardByTitle(title:String):List<Card>{
//        return queryFactory.selectFrom(card)
//            .where(card.title.containsIgnoreCase(title))
//            .fetch()
//    }
//}