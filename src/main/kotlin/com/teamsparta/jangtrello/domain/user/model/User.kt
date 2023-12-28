package com.teamsparta.jangtrello.domain.user.model

import com.teamsparta.jangtrello.domain.cardlist.model.CardList
import com.teamsparta.jangtrello.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
    @Column(name = "password")
    var password : String,

    @Column(name = "email")
    var email:String,

    @Column(name = "name")
    val name:String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val cardLists: MutableList<CardList> = mutableListOf()

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null

    fun addCardList(cardList: CardList) {
        cardLists.add(cardList)
    }

    fun removeCardList(cardList: CardList) {
        cardLists.remove(cardList)
    }
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        email = email,
        name = name,
    )
}
