package com.teamsparta.jangtrello.domain.card.model

import com.teamsparta.jangtrello.domain.BaseEntity
import com.teamsparta.jangtrello.domain.comment.model.Comment
import com.teamsparta.jangtrello.domain.user.model.User
import jakarta.persistence.*


@Entity
@Table(name = "card")
class Card(

    @Column(name = "email")
    var email: String,

    @Column(name = "nickname")
    var nickName: String,

    @Column(name = "title")
    var title: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: CardStatus = CardStatus.FALSE,

    @Column(name = "contents")
    var contents: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @OneToMany(mappedBy = "card", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY, ) //, orphanRemoval = true
    var comments: MutableList<Comment> = mutableListOf(),

    ) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


//
//    fun addComment(comment: Comment) {
//        comments.add(comment)
//    }
//
//    fun removeComment(comment: Comment) {
//        comments.remove(comment)
//    }
}
