package com.teamsparta.jangtrello.domain.card.service

import com.teamsparta.jangtrello.domain.card.dto.*
import com.teamsparta.jangtrello.domain.card.model.Card
import com.teamsparta.jangtrello.domain.card.model.CardStatus
import com.teamsparta.jangtrello.domain.card.repository.CardRepository
import com.teamsparta.jangtrello.domain.exception.InvalidCredentialsException
import com.teamsparta.jangtrello.domain.exception.ModelNotFoundException
import com.teamsparta.jangtrello.domain.user.repository.UserRepository
import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CardService(
    private val cardRepository: CardRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun createCard(userPrincipal: UserPrincipal, request: CreateCardRequest): CardResponse {
        val user = userRepository.findByIdOrNull(userPrincipal.id)
            ?:throw ModelNotFoundException("id",0)

        val card = Card(
            email = userPrincipal.email,
            nickName = user.nickName,
            title = request.title,
            contents = request.contents,
            user = user,
        )
        cardRepository.save(card)
        return card.toResponse()
    }


    fun getCards(userPrincipal: UserPrincipal): List<CardResponse> {
        return cardRepository.findAllByUserId(userPrincipal.id).map { it.toResponse() }
            //.sortedByDescending { it.date }.sortedBy { it.status }
    }


    fun getCardsSorted(userPrincipal: UserPrincipal, sortBy : String) : List<CardResponse>    {
        when (sortBy.uppercase()){
            "ASC" ->return  cardRepository.findAllByUserId(userPrincipal.id).map { it.toResponse() }.sortedBy { it.createdAt }
            "DESC"-> return cardRepository.findAllByUserId(userPrincipal.id).map { it.toResponse() }.sortedByDescending { it.createdAt }
            else -> throw IllegalArgumentException("Invalid sortBy value: $sortBy. Should be 'ASC' or 'DESC'.")
        }
    }

    fun getPagedCards(pageable: Pageable, status: String?, userPrincipal: UserPrincipal): Page<CardResponse>? {
        val cardStatus = when(status?.uppercase()){
            "FALSE" -> CardStatus.FALSE
            "TRUE" -> CardStatus.TRUE
            else -> throw IllegalArgumentException("The Status is invalid")
        }
        return cardRepository.findByPageableAndStatus(pageable,cardStatus).map{ it.toResponse()}
    }

    fun getCard(userPrincipal: UserPrincipal, cardId: Long): CardResponse { //cardListId: Long,
        val card = cardRepository.findByUserIdAndId(userPrincipal.id, cardId)
            ?: throw ModelNotFoundException("Card", cardId)
        return card.toResponse()
    }

    fun searchCards(title:String) : List<CardResponse>{
        return cardRepository.searchCardByTitle(title).map{it.toResponse()}
    }


    //    fun getCardsUserNamed(cardListId: Long, userName : String) : List<CardResponse>    {
//        return cardRepository.findAllByCardListId(cardListId).map { it.toResponse() }.sortedByDescending { it.date }.sortedBy { it.status }.filter{it.userName == userName }
//    }

    @Transactional
    fun updateCard(userPrincipal: UserPrincipal, cardId: Long, request: UpdateCardRequest): CardResponse {
        val card = cardRepository.findByUserIdAndId(userPrincipal.id, cardId)
            ?: throw ModelNotFoundException("Card", cardId)

        val user = userRepository.findById(userPrincipal.id).orElse(null)
            ?: throw IllegalStateException("User not found with id: ${userPrincipal.id}")

        if (!passwordEncoder.matches(request.password, user.password))
            throw InvalidCredentialsException("Password", request.password)



        card.title = request.title
        card.status = when (request.status.uppercase()) {
            "FALSE" -> CardStatus.FALSE
            "TRUE" -> CardStatus.TRUE
            else -> throw IllegalStateException("Invalid Status")
        }
        card.contents = request.contents

        return cardRepository.save(card).toResponse()
    }
    @Transactional
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