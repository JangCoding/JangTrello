package com.teamsparta.jangtrello.domain.card.model

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.cardlist.model.CardList
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.*
import java.time.LocalDateTime
@Entity
@Table(name = "card")
class Card(
    @Column(name = "title")
    var title: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: CardStatus = CardStatus.FALSE,

    @Column(name = "contents")
    var contents: String,

    @Column(name = "date")
    val date: String = LocalDateTime.now().toString(),

    @Column(name = "username")
    var username: String,


    @ManyToOne(fetch = FetchType.LAZY)  // 1:N 관계에서 FK(course_id) 를 들고 있기 때문에 연관관계의 주인이 됨? // 주인 아닌 쪽에 mappedBy
    @JoinColumn(name = "cardlist_id") // MappedBy 할 때 알아서 추적하지만 명시적으로 표현
    //table 의 column 따라
    val cardList: CardList

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
fun Card.toResponse(): CardResponse {
    return CardResponse(
        id = id!!,
        title = title,
        status = status.name,
        contents = contents,
        date = date,
        userName = username
    )
}