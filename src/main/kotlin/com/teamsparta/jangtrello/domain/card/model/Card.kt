package com.teamsparta.jangtrello.domain.card.model

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.comment.model.Comment
import com.teamsparta.jangtrello.domain.user.model.User
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "card")
class Card(
    @Column(name = "email")
    var email: String,

    @Column(name = "title")
    var title: String,

    @Column(name = "date")
    val date: String = LocalDateTime.now().toString(),

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: CardStatus = CardStatus.FALSE,

    @Column(name = "contents")
    var contents: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = [CascadeType.ALL]) //, orphanRemoval = true
    var comments: MutableList<Comment> = mutableListOf(),

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
        email = email,
        date = date,
        title = title,
        status = status.name,
        contents = contents,
        comments = comments,
    )
}