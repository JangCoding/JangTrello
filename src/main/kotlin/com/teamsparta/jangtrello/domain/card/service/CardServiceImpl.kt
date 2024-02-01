package com.teamsparta.jangtrello.domain.card.service

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.dto.UpdateCardRequest
import com.teamsparta.jangtrello.domain.card.model.Card
import com.teamsparta.jangtrello.domain.card.model.CardStatus
import com.teamsparta.jangtrello.domain.card.repository.CardRepository
import com.teamsparta.jangtrello.domain.comment.service.toResponse
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
class CardServiceImpl(
    private val cardRepository: CardRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
)  : CardService{
    @Transactional
    override fun createCard(userPrincipal: UserPrincipal, request: CreateCardRequest): CardResponse {
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


    override fun getCards(userPrincipal: UserPrincipal): List<CardResponse> {
        return cardRepository.findAllByUserId(userPrincipal.id).map { it.toResponse() }
        //.sortedByDescending { it.date }.sortedBy { it.status }
    }


    override fun getCardsSorted(userPrincipal: UserPrincipal, sortBy : String) : List<CardResponse>    {
        when (sortBy.uppercase()){
            "ASC" ->return  cardRepository.findAllByUserId(userPrincipal.id).map { it.toResponse() }.sortedBy { it.createdAt }
            "DESC"-> return cardRepository.findAllByUserId(userPrincipal.id).map { it.toResponse() }.sortedByDescending { it.createdAt }
            else -> throw IllegalArgumentException("Invalid sortBy value: $sortBy. Should be 'ASC' or 'DESC'.")
        }
    }

    override fun getPagedCards(pageable: Pageable, status: String?, userPrincipal: UserPrincipal): Page<CardResponse>? {
        val cardStatus = when(status?.uppercase()){
            "FALSE" -> CardStatus.FALSE
            "TRUE" -> CardStatus.TRUE
            else -> throw IllegalArgumentException("The Status is invalid")
        }
        return cardRepository.findByPageableAndStatus(pageable,cardStatus).map{ it.toResponse()}
    }

    override fun getCard(userPrincipal: UserPrincipal, cardId: Long): CardResponse { //cardListId: Long,
        val card = cardRepository.findByUserIdAndId(userPrincipal.id, cardId)
            ?: throw ModelNotFoundException("Card", cardId)
        return card.toResponse()
    }

    override fun searchCards(title:String) : List<CardResponse>{
        return cardRepository.searchCardByTitle(title).map{it.toResponse()}
    }


    //    fun getCardsUserNamed(cardListId: Long, userName : String) : List<CardResponse>    {
//        return cardRepository.findAllByCardListId(cardListId).map { it.toResponse() }.sortedByDescending { it.date }.sortedBy { it.status }.filter{it.userName == userName }
//    }

    @Transactional
    override fun updateCard(userPrincipal: UserPrincipal, cardId: Long, request: UpdateCardRequest): CardResponse {
        val card = cardRepository.findByUserIdAndId(userPrincipal.id, cardId)
            ?: throw ModelNotFoundException("Card", cardId)

        card.title = request.title
        card.status = when (request.status) {
            false  -> CardStatus.FALSE
            true -> CardStatus.TRUE
            else -> throw IllegalStateException("Invalid Status")
        }
        card.contents = request.contents

        cardRepository.save(card)
        return card.toResponse()
    }
    @Transactional
    override fun deleteCard(userPrincipal: UserPrincipal, cardId: Long, password:String) {
        val card = cardRepository.findByUserIdAndId(userPrincipal.id, cardId)
            ?: throw ModelNotFoundException("Card", cardId)

        val user = userRepository.findById(userPrincipal.id).orElse(null)
            ?: throw IllegalStateException("User not found with id: ${userPrincipal.id}")

        if (!passwordEncoder.matches(password, user.password))
            throw InvalidCredentialsException("Password", password)

        cardRepository.delete(card)
    }


}

fun chkInputValidation(title: String, contents: String): Boolean {
    if (title.length in 1..200 && contents.length in 1..1000)
        return true
    else
        return false
}

fun Card.toResponse(): CardResponse {
    return CardResponse(
        id = id!!,
        email = email,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        nickName = nickName,
        title = title,
        status = status.name,
        contents = contents,
        comments = comments.map{ it.toResponse() }
    )
}