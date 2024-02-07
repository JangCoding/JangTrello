//package com.teamsparta.jangtrello.domain.card.service
//
//import com.teamsparta.jangtrello.domain.card.repository.CardRepository
//import com.teamsparta.jangtrello.domain.exception.ModelNotFoundException
//import com.teamsparta.jangtrello.domain.user.repository.UserRepository
//import com.teamsparta.jangtrello.infra.security.UserPrincipal
//import io.kotest.assertions.throwables.shouldThrow
//import io.kotest.core.spec.style.BehaviorSpec
//import io.kotest.extensions.spring.SpringExtension
//import io.mockk.clearAllMocks
//import io.mockk.every
//import io.mockk.junit5.MockKExtension
//import io.mockk.mockk
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.security.crypto.password.PasswordEncoder
//
//@SpringBootTest
//@ExtendWith(MockKExtension::class)
//class CardServiceTest : BehaviorSpec ({
//  extension(SpringExtension)
//
//  afterContainer {
//      clearAllMocks()
//  }
//    val cardRepository = mockk<CardRepository>()
//    val userRepository = mockk<UserRepository>()
//    var passwordEncoder = mockk<PasswordEncoder>()
//    var userPrincipal = mockk<UserPrincipal>()
//
//    val cardService = CardService(cardRepository,userRepository,passwordEncoder)
//
//    Given("Card 목록이 존재하지 않을 때"){
//        When("특정 Card를 요청하면"){
//            Then("ModelNotFoundException이 발생해야 한다."){
//
//                // given
//                val cardId = 1L
//                every{
//                   cardRepository.findByUserIdAndId(any(), any())} returns null
//
//                shouldThrow<ModelNotFoundException>{
//                    cardService.getCard(userPrincipal, cardId)
//                }
//
//
//            }
//        }
//    }
//
//
//})