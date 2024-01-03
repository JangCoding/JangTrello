package com.teamsparta.jangtrello.domain.card.model

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.cardlist.model.CardList
import com.teamsparta.jangtrello.domain.cardlist.model.updateCount
import com.teamsparta.jangtrello.domain.comment.dto.CommentResponse
import com.teamsparta.jangtrello.domain.comment.model.Comment
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


    @ManyToOne(fetch = FetchType.LAZY) // 주인 아닌 쪽에 mappedBy
    @JoinColumn(name = "cardlist_id") // MappedBy 할 때 알아서 추적하지만 명시적으로 표현
    //table 의 column 따라
    val cardList: CardList,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = [CascadeType.ALL]) //, orphanRemoval = true
    var comments: MutableList<Comment> = mutableListOf(),

    //var cmts:MutableList<CommentResponse> = mutableListOf(),

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null



    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    fun removeComment(comment: Comment) {
        comments.remove(comment)
    }
}
fun Card.toResponse(): CardResponse {
    return CardResponse(
        id = id!!,
        title = title,
        status = status.name,
        contents = contents,
        date = date,
        userName = username,
        comments = comments
    )
}