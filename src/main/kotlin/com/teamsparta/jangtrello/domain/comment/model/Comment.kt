package com.teamsparta.jangtrello.domain.comment.model

import com.teamsparta.jangtrello.domain.BaseEntity
import com.teamsparta.jangtrello.domain.card.model.Card
import com.teamsparta.jangtrello.domain.user.model.User
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "email")
    var email : String,

    @Column(name = "nickname")
    var nickName : String,

    @Column(name = "contents")
    var contents : String,


    @ManyToOne(fetch = FetchType.LAZY) // 주인 아닌 쪽에 mappedBy
    @JoinColumn(name = "card_id") // MappedBy 할 때 알아서 추적하지만 명시적으로 표현
    val card: Card,               //table 의 column 따라

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    ) : BaseEntity(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null

}


