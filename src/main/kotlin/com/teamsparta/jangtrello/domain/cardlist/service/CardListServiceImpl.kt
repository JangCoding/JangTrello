package com.teamsparta.jangtrello.domain.cardlist.service

import com.example.courseregistration.domain.exception.ModelNotFoundException
import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.dto.UpdateCardRequest
import com.teamsparta.jangtrello.domain.card.model.Card
import com.teamsparta.jangtrello.domain.card.model.CardStatus
import com.teamsparta.jangtrello.domain.card.model.toResponse
import com.teamsparta.jangtrello.domain.card.repository.CardRepository
import com.teamsparta.jangtrello.domain.cardlist.dto.CardListResponse
import com.teamsparta.jangtrello.domain.cardlist.dto.CreateCardListRequest
import com.teamsparta.jangtrello.domain.cardlist.dto.UpdateCardListRequest
import com.teamsparta.jangtrello.domain.cardlist.model.CardList
import com.teamsparta.jangtrello.domain.cardlist.model.toResponse
import com.teamsparta.jangtrello.domain.cardlist.model.updateCount
import com.teamsparta.jangtrello.domain.cardlist.repository.CardListRepository
import com.teamsparta.jangtrello.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Service
class CardListServiceImpl(
    private val cardListRepository: CardListRepository,
    private val cardRepository: CardRepository,
    private val userRepository: UserRepository,
) : CardListService{
    override fun getCardLists(): List<CardListResponse> {
        return cardListRepository.findAll().map { it.toResponse() }
    }

    override fun getCardList(cardListId: Long): CardListResponse {
        val cardList = cardListRepository.findByIdOrNull(cardListId) ?: throw ModelNotFoundException("CardList", cardListId)
        return cardList.toResponse()

    }

    @Transactional
    override fun createCardList(request: CreateCardListRequest): CardListResponse {

        return cardListRepository.save(
            CardList(
                title = request.title,
                //date 는 자동 생성
                userName = request.userName
            )
        ).toResponse()
    }

    @Transactional
    override fun updateCardList(cardListId: Long, request: UpdateCardListRequest): CardListResponse {
        val cardList = cardListRepository.findByIdOrNull(cardListId) ?: throw ModelNotFoundException("CardList", cardListId)
        val (title, description) = request

        cardList.title = title
        cardList.userName = description

        return cardListRepository.save(cardList).toResponse()
    }
    @Transactional
    override fun deleteCardList(cardListId: Long) {
        val cardList = cardListRepository.findByIdOrNull(cardListId) ?: throw ModelNotFoundException("CardList", cardListId)
        cardListRepository.delete(cardList)
    }


    override fun getCards(cardListId: Long): List<CardResponse> {

        return cardRepository.findAllByCardListId(cardListId).map { it.toResponse() }.sortedByDescending { it.date }.sortedBy { it.status }
    }

    override fun getCard(cardId: Long): CardResponse { //cardListId: Long,
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)
        return card.toResponse()
    }

    override fun createCard(cardListId: Long, request: CreateCardRequest): CardResponse {
        val cardList = cardListRepository.findByIdOrNull(cardListId) ?: throw ModelNotFoundException("CardLIst", cardListId)
        val card = Card(
        title = request.title,
        contents = request.contents,
        username = request.userName,
        //status 는 생성시 TODO
        //date 는 자동 생성
        cardList = cardList
        )

        cardList.addCard(card)

        cardListRepository.save(cardList)
        return card.toResponse()
    }

    override fun updateCard(cardId: Long, request: UpdateCardRequest): CardResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)

        card.title = request.title
        card.status = when(request.status){
            "FALSE" -> CardStatus.FALSE
            "TRUE" -> CardStatus.TRUE
            else -> throw IllegalStateException("Invalid role")
        }
        card.contents = request.contents
        card.username = request.userName

        return cardRepository.save(card).toResponse()
    }

    override fun deleteCard(cardListId: Long, cardId: Long) {
        val cardList = cardListRepository.findByIdOrNull(cardListId) ?: throw ModelNotFoundException("CardLIst", cardListId)
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)

        cardList.removeCard(card)

        cardListRepository.save(cardList)
    }
}