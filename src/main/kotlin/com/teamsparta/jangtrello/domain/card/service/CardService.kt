package com.teamsparta.jangtrello.domain.card.service

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.model.Card
import com.teamsparta.jangtrello.domain.card.model.toResponse
import com.teamsparta.jangtrello.domain.card.repository.CardRepository
import com.teamsparta.jangtrello.domain.cardlist.repository.CommentRepository
import com.teamsparta.jangtrello.domain.exception.InvalidInputException
import com.teamsparta.jangtrello.domain.user.repository.UserRepository
import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.stereotype.Service

@Service
class CardService(
    private val cardRepository: CardRepository,
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository,
) {
    fun getCards(userPrincipal: UserPrincipal): List<CardResponse> {
        return cardRepository.findAllByUserId(userPrincipal.id).map { it.toResponse() }.sortedByDescending { it.date }
            .sortedBy { it.status }
    }

    //
//    fun getCardsSorted(cardListId: Long, sortBy : String) : List<CardResponse>    {
//        when (sortBy.uppercase()){
//            "ASC" ->return cardRepository.findAllByCardListId(cardListId).map { it.toResponse() }.sortedBy { it.date }
//            "DESC"-> return cardRepository.findAllByCardListId(cardListId).map { it.toResponse() }.sortedByDescending { it.date }
//            else -> throw IllegalArgumentException("Invalid sortBy value: $sortBy. Should be 'ASC' or 'DESC'.")
//        }
//    }
//
////    fun getCardsUserNamed(cardListId: Long, userName : String) : List<CardResponse>    {
////        return cardRepository.findAllByCardListId(cardListId).map { it.toResponse() }.sortedByDescending { it.date }.sortedBy { it.status }.filter{it.userName == userName }
////    }
//
//    fun getCard(cardId: Long): CardResponse { //cardListId: Long,
//        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)
//        return card.toResponse()
//    }
//
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
        }
        else
            throw InvalidInputException(request.title.length.toLong(), request.contents.length.toLong())
    }


//
//    fun updateCard(cardId: Long, request: UpdateCardRequest): CardResponse {
//        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)
//        card.comments = commentRepository.findAllByCardId(cardId).toMutableList()
//
//        card.title = request.title
//        card.status = when(request.status.uppercase()){
//            "FALSE" -> CardStatus.FALSE
//            "TRUE" -> CardStatus.TRUE
//            else -> throw IllegalStateException("Invalid role")
//        }
//        card.contents = request.contents
//        card.username = request.userName
//
//        return cardRepository.save(card).toResponse()
//    }
//
//    fun deleteCard(cardId: Long) {
//        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)
//
//        cardRepository.save(card)
//    }
}
fun chkInputValidation(title:String, contents : String) : Boolean{
    if(title.length in 1..200 && contents.length in 1..1000)
        return true
    else
        return false
}