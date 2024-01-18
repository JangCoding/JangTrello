package com.teamsparta.jangtrello.domain.card.controller

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.service.CardService
import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cards")
class CardController(
    private val cardService: CardService
) {
    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")

    fun getCards(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ):ResponseEntity<List<CardResponse>>{

        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCards(userPrincipal))
    }
//
//    @GetMapping("sorted/{sortBy}")
//    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
//    fun getCardsSorted(
//        @PathVariable sortBy : String,
//        @AuthenticationPrincipal userDetails: UserDetails,
//    ):ResponseEntity<List<CardResponse>>{
//
//        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCardsSorted(userDetails, sortBy))
//    }
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
//    @GetMapping("/{cardId}")
//    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
//    fun getCard(
//        @AuthenticationPrincipal userDetails: UserDetails,
//        @PathVariable cardId : Long
//    ): ResponseEntity<CardResponse>{
//
//        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCard(cardId)) //cardListId,
//    }
//
    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun createCard(
    @AuthenticationPrincipal userPrincipal: UserPrincipal,
    @RequestBody request : CreateCardRequest
    ): ResponseEntity<CardResponse> {

        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.createCard(userPrincipal, request))
    }
//    @PutMapping("/{cardId}")
//    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
//    fun updateCard(
//        @AuthenticationPrincipal userDetails: UserDetails,
//        @PathVariable cardId : Long,
//        @RequestBody request : UpdateCardRequest,
//    ) : ResponseEntity<CardResponse> {
//
//        return ResponseEntity.status(HttpStatus.OK).body(cardService.updateCard(userDetails,cardId, request))
//    }
//
//    @DeleteMapping("/{cardId}")
//    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
//    fun deleteCard(
//        @AuthenticationPrincipal userDetails: UserDetails,
//        @PathVariable cardId : Long,
//    ) : ResponseEntity<Unit>{
//
//        cardService.deleteCard(userDetails)
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
//    }

}