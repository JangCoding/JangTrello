package com.teamsparta.jangtrello.domain.card.model

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.*
import java.sql.Time
import java.time.LocalDateTime
import java.util.Date

@Entity
@Table(name = "card")
class Card(
    @Column(name = "title")
    var title: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: CardStatus,

    @Column(name = "contents")
    var contents: String,

    @Column(name = "date")
    val date: String = LocalDateTime.now().toString(),

    @Column(name = "username")
    var username: String,

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