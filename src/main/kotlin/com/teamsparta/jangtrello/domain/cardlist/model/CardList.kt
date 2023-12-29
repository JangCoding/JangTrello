package com.teamsparta.jangtrello.domain.cardlist.model

import com.teamsparta.jangtrello.domain.card.model.Card
import com.teamsparta.jangtrello.domain.cardlist.dto.CardListResponse
import com.teamsparta.jangtrello.domain.user.model.User
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "cardlist")
class CardList(
    @Column(name = "title")
    var title : String,

    @Column(name = "date")
    val date: String = LocalDateTime.now().toString(),

    @Column(name = "count")
    var count :Long = 0,

    @Column(name = "username")
    var userName : String,

    //carmelCase 를 따라가기 때문에 클래스명 CardList 를 cardList 로 지정해주어야함!!
    @OneToMany(mappedBy = "cardList", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var cards: MutableList<Card> = mutableListOf(),

//    @ManyToOne(fetch = FetchType.LAZY)  // 1:N 관계에서 FK(course_id) 를 들고 있기 때문에 연관관계의 주인이 됨? // 주인 쪽에 mappedBy
//    @JoinColumn(name = "user_id") // MappedBy 할 때 알아서 추적하지만 명시적으로 표현
//    val user: User

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null

    fun addCard(card: Card) {
        cards.add(card)
        updateCount()
    }

    fun removeCard(card: Card) {
        cards.remove(card)
        updateCount()
    }
}
fun CardList.toResponse(): CardListResponse {
    return CardListResponse(
        id = id!!,
        title = title,
        date = date,
        count = count,
        userName = userName
    )
}

fun CardList.updateCount() {
    count = cards.size.toLong()
}