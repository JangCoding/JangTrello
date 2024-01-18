package com.teamsparta.jangtrello.domain.user.model

import com.teamsparta.jangtrello.domain.cardlist.model.CardList
import com.teamsparta.jangtrello.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
    @Column(name = "password")
    var password : String,

    @Column(name = "email")
    var email:String,

    @Column(name = "nickname")
    val nickName:String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role:UserRole,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
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
        nickName = nickName,
        role = role.name
    )
}
