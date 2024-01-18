package com.teamsparta.jangtrello.domain.card.service

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.dto.DeleteCardRequest
import com.teamsparta.jangtrello.domain.card.dto.UpdateCardRequest
import com.teamsparta.jangtrello.domain.card.model.Card
import com.teamsparta.jangtrello.domain.card.model.CardStatus
import com.teamsparta.jangtrello.domain.card.model.toResponse
import com.teamsparta.jangtrello.domain.card.repository.CardRepository
import com.teamsparta.jangtrello.domain.exception.InvalidCredentialsException
import com.teamsparta.jangtrello.domain.exception.InvalidInputException
import com.teamsparta.jangtrello.domain.exception.ModelNotFoundException
import com.teamsparta.jangtrello.domain.user.repository.UserRepository
import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CardService(
    private val cardRepository: CardRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun getCards(userPrincipal: UserPrincipal): List<CardResponse> {
        return cardRepository.findAllByUserId(userPrincipal.id).map { it.toResponse() }.sortedByDescending { it.date }
            .sortedBy { it.status }
    }


    fun getCardsSorted(userPrincipal: UserPrincipal, sortBy : String) : List<CardResponse>    {
        when (sortBy.uppercase()){
            "ASC" ->return  cardRepository.findAllByUserId(userPrincipal.id).map { it.toResponse() }.sortedBy { it.date }
            "DESC"-> return cardRepository.findAllByUserId(userPrincipal.id).map { it.toResponse() }.sortedByDescending { it.date }
            else -> throw IllegalArgumentException("Invalid sortBy value: $sortBy. Should be 'ASC' or 'DESC'.")
        }
    }

//    fun getCardsUserNamed(cardListId: Long, userName : String) : List<CardResponse>    {
//        return cardRepository.findAllByCardListId(cardListId).map { it.toResponse() }.sortedByDescending { it.date }.sortedBy { it.status }.filter{it.userName == userName }
//    }

    fun getCard(userPrincipal: UserPrincipal, cardId: Long): CardResponse { //cardListId: Long,
        val card = cardRepository.findByUserIdAndId(userPrincipal.id, cardId)
            ?: throw ModelNotFoundException("Card", cardId)
        return card.toResponse()
    }

    fun createCard(userPrincipal: UserPrincipal, request: CreateCardRequest): CardResponse {
        val user = userRepository.findById(userPrincipal.id).orElse(null)
            ?: throw IllegalStateException("User not found with id: ${userPrincipal.id}")

        if (chkInputValidation(request.title, request.contents)) {
            val card = Card(
                email = userPrincipal.email,
                title = request.title,
                contents = request.contents,
                user = user,
            )
            cardRepository.save(card)
            return card.toResponse()
        } else
            throw InvalidInputException(request.title.length.toLong(), request.contents.length.toLong())
    }


    fun updateCard(userPrincipal: UserPrincipal, cardId: Long, request: UpdateCardRequest): CardResponse {
        val user = userRepository.findById(userPrincipal.id).orElse(null)
            ?: throw IllegalStateException("User not found with id: ${userPrincipal.id}")

        if (!passwordEncoder.matches(request.password, user.password))
            throw InvalidCredentialsException("Password", request.password)

        val card = cardRepository.findByUserIdAndId(userPrincipal.id, cardId)
            ?: throw ModelNotFoundException("Card", cardId)


        card.title = request.title
        card.status = when (request.status.uppercase()) {
            "FALSE" -> CardStatus.FALSE
            "TRUE" -> CardStatus.TRUE
            else -> throw IllegalStateException("Invalid role")
        }
        card.contents = request.contents

        return cardRepository.save(card).toResponse()
    }

    fun deleteCard(userPrincipal: UserPrincipal, cardId: Long, request: DeleteCardRequest) {
        val card = cardRepository.findByUserIdAndId(userPrincipal.id, cardId)
            ?: throw ModelNotFoundException("Card", cardId)

        val user = userRepository.findById(userPrincipal.id).orElse(null)
            ?: throw IllegalStateException("User not found with id: ${userPrincipal.id}")

        if (!passwordEncoder.matches(request.password, user.password))
            throw InvalidCredentialsException("Password", request.password)

        cardRepository.delete(card)
    }
}

fun chkInputValidation(title: String, contents: String): Boolean {
    if (title.length in 1..200 && contents.length in 1..1000)
        return true
    else
        return false
}