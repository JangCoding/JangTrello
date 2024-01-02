package com.teamsparta.jangtrello.domain.comment.model

import com.teamsparta.jangtrello.domain.card.model.Card
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comment")
class Comment(
    @Column(name = "username")
    var userName : String,

    @Column(name = "password")
    var password : String,

    @Column(name = "contents")
    var contents : String,

    @Column(name = "date")
    val date: String = LocalDateTime.now().toString(),

    @Column(name = "count")
    var count :Long = 0,

    @ManyToOne(fetch = FetchType.LAZY) // 주인 아닌 쪽에 mappedBy
    @JoinColumn(name = "card_id") // MappedBy 할 때 알아서 추적하지만 명시적으로 표현
    //table 의 column 따라
    val card: Card

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null

}
