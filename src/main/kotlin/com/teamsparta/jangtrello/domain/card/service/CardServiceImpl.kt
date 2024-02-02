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

    // 할 일 카드 생성
    @Transactional
    override fun createCard(userPrincipal: UserPrincipal, request: CreateCardRequest): CardResponse {
        val user = userRepository.findByIdOrNull(userPrincipal.id)
            ?:throw ModelNotFoundException("User",userPrincipal.id)

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


    // 할 일 카드 전부 조회 ( 생성일, 상태 기준 정렬 )
    override fun getCards(userPrincipal: UserPrincipal): List<CardResponse> {
        return cardRepository.findAllByUserId(userPrincipal.id).map { it.toResponse() }
            .sortedByDescending { it.createdAt }.sortedBy { it.status }
    }

    // 할 일 카드 생성일 기준 정렬 조회 ( 오름차순 / 내림차순 )
    override fun getCardsSorted(userPrincipal: UserPrincipal, sortBy : String) : List<CardResponse>    {
        return when (sortBy.uppercase()){
            "ASC" -> cardRepository.findAllByUserId(userPrincipal.id).map { it.toResponse() }.sortedBy { it.createdAt }
            "DESC"-> cardRepository.findAllByUserId(userPrincipal.id).map { it.toResponse() }.sortedByDescending { it.createdAt }
            else -> throw IllegalArgumentException("Invalid sortBy value: $sortBy. Should be 'ASC' or 'DESC'.")
        }
    }

    // 할 일 카드 상태 기준 조회 ( 페이지 적용 , 기본값 : id 정렬 )
    override fun getPagedCards(pageable: Pageable, status: String?, userPrincipal: UserPrincipal): Page<CardResponse>? {
        val cardStatus = when(status?.uppercase()){
            "FALSE" -> CardStatus.FALSE
            "TRUE" -> CardStatus.TRUE
            null -> null
            else -> throw IllegalArgumentException("The Status is invalid")
        }
        return cardRepository.findByPageableAndStatus(pageable,cardStatus).map{ it.toResponse()}
    }

    // 할 일 카드 단건 조회
    override fun getCard(userPrincipal: UserPrincipal, cardId: Long): CardResponse { //cardListId: Long,
        val card = cardRepository.findByUserIdAndId(userPrincipal.id, cardId)
            ?: throw ModelNotFoundException("Card", cardId)
        return card.toResponse()
    }

    // 할 일 카드 제목 검색  // -> 내용 검색도 추가 해보기 ?
    override fun searchCards(title:String) : List<CardResponse>{
        return cardRepository.searchCardByTitle(title).map{it.toResponse()}
    }

    // 할 일 카드 업데이트
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

    // 할 일 카드 삭제
    @Transactional
    override fun deleteCard(userPrincipal: UserPrincipal, cardId: Long, password:String) {

        val card = cardRepository.findByUserIdAndId(userPrincipal.id, cardId)
            ?: throw ModelNotFoundException("Card", cardId)

        val user = userRepository.findByIdOrNull(userPrincipal.id)
            ?: throw ModelNotFoundException("User",userPrincipal.id)

        if (!passwordEncoder.matches(password, user.password))
            throw InvalidCredentialsException("Password", password)

        cardRepository.delete(card)

        TODO("카드 삭제시 유저 본인 확인 과정 필요 ")
        TODO("soft delete 구현")
    }
}

// Valid 로 대체
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