package com.teamsparta.jangtrello.domain.card.controller

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.dto.UpdateCardRequest
import com.teamsparta.jangtrello.domain.card.service.CardService
import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cards")
class CardController(
    private val cardService: CardService
) {

//
////    @GetMapping("usernamed/{userName}")
////    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
////    fun getCardsUserNamed
////        @AuthenticationPrincipal userDetails: UserDetails,
////    ):ResponseEntity<List<CardResponse>>{
////
////        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCardsUserNamed(userDetails))
////    }
//


    @PostMapping()
    fun createCard(
    @AuthenticationPrincipal userPrincipal: UserPrincipal,
    @RequestBody request : CreateCardRequest
    ): ResponseEntity<CardResponse> {

        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.createCard(userPrincipal, request))
    }

    @GetMapping()
    fun getCards(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ):ResponseEntity<List<CardResponse>>{

        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCards(userPrincipal))
    }

    @GetMapping("/paged")
    fun getPagedCards(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,

        // pathVarialble 로 동작
        @PageableDefault(      // 이미 정렬 수행 됨
            size = 3,
            sort = ["id"]
        ) pageable: Pageable, // 페이지네이션 전달 객체


        @RequestParam(value = "status", required = false) status : String? // 할일 완료 여부

    ):ResponseEntity<Page<CardResponse>>{

        return ResponseEntity.status(HttpStatus.OK).body(cardService.getPagedCards(pageable, "false", userPrincipal))
    }

    @GetMapping("sorted/{sortBy}")
    fun getCardsSorted(
        @PathVariable sortBy : String,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ):ResponseEntity<List<CardResponse>>{
        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCardsSorted(userPrincipal, sortBy))
    }

    @GetMapping("/{cardId}")
    fun getCard(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable cardId : Long
    ): ResponseEntity<CardResponse>{

        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCard(userPrincipal, cardId)) //cardListId,
    }

    @GetMapping("/search")
    fun searchCard(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestParam(name = "title") title:String
    ): ResponseEntity<List<CardResponse>>{

        return ResponseEntity.status(HttpStatus.OK).body(cardService.searchCards(title)) //cardListId,
    }

    @PutMapping("/{cardId}")
    fun updateCard(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable cardId : Long,
        @RequestBody request : UpdateCardRequest,
    ) : ResponseEntity<CardResponse> {

        return ResponseEntity.status(HttpStatus.OK).body(cardService.updateCard(userPrincipal,cardId, request,))
    }

    @DeleteMapping("/{cardId}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun deleteCard(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable cardId : Long,
        password : String,
    ) : ResponseEntity<Unit>{

        cardService.deleteCard(userPrincipal, cardId, password)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}