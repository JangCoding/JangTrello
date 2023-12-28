package com.teamsparta.jangtrello.domain.cardlist.model

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.cardlist.dto.CardListResponse
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.*
import java.sql.Time
import java.time.LocalDateTime
import java.util.Date

@Entity
@Table(name = "cardlist")
class CardList(
    @Column(name = "title")
    var title : String,

    @Column(name = "date")
    val date: String = LocalDateTime.now().toString(),

    @Column(name = "count")
    var count : Long,

    @Column(name = "username")
    var username : String,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null
}
fun CardList.toResponse(): CardListResponse {
    return CardListResponse(
        id = id!!,
        title = title,
        date = date,
        count = count,
        userName = username
    )
}