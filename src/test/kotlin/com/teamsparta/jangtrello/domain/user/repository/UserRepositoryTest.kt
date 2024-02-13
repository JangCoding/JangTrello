package com.teamsparta.jangtrello.domain.user.repository

import com.teamsparta.jangtrello.domain.exception.ModelNotFoundException
import com.teamsparta.jangtrello.domain.user.model.User
import com.teamsparta.jangtrello.domain.user.model.UserRole
import com.teamsparta.jangtrello.infra.querydsl.QueryDslConfig
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles


@DataJpaTest // Test 를 위한 준비물들을 스캔하고 가져다줌.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(value = [QueryDslConfig::class])  // @DataJpaTest 외에 필요한 내용 직접 Import
@ActiveProfiles("test")
class UserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository
)  {
    @Test
    fun `findByEmail 잘 되나 확인`(){
        //GIVEN
        userRepository.saveAllAndFlush(DEFAULT_MEMBER_LIST)

        //WHEN
        val result1 : User = userRepository.findByEmail("user6@example.com")
            ?:throw ModelNotFoundException()
        //THEN
        result1.nickName shouldBe "user6"
    }

    @Test
    fun `findByPageableAndRole 에 Role 값에 따른 테스트`(){
         //GIVEN
        userRepository.saveAllAndFlush(DEFAULT_MEMBER_LIST)

        //WHEN
        // 페이지 번호, 페이지 크기, 정렬 방식을 설정하여 Pageable 객체 생성
        val pageable: Pageable = PageRequest.of(
            3,  // 페이지 번호 (0부터 시작)
            3,    // 페이지 크기
        )
        val result1 : Page<User> = userRepository.findByPageableAndRole(pageable, null)
        val result2 : Page<User> = userRepository.findByPageableAndRole(Pageable.ofSize(10), null)
        val result3 : Page<User> = userRepository.findByPageableAndRole(PageRequest.of(0,10), UserRole.MANAGER)
            ?:throw ModelNotFoundException()
        //THEN
        result1.content.size shouldBe 1
        result2.content.size shouldBe 10
        result3.content.size shouldBe 5
    }

    companion object {
        private val DEFAULT_MEMBER_LIST = listOf(
            User(password = "password1", email = "user1@example.com", nickName = "user1", role = UserRole.USER),
            User(password = "password2", email = "user2@example.com", nickName = "user2", role = UserRole.MANAGER),
            User(password = "password3", email = "user3@example.com", nickName = "user3", role = UserRole.USER),
            User(password = "password4", email = "user4@example.com", nickName = "user4", role = UserRole.MANAGER),
            User(password = "password5", email = "user5@example.com", nickName = "user5", role = UserRole.USER),
            User(password = "password6", email = "user6@example.com", nickName = "user6", role = UserRole.MANAGER),
            User(password = "password7", email = "user7@example.com", nickName = "user7", role = UserRole.USER),
            User(password = "password8", email = "user8@example.com", nickName = "user8", role = UserRole.MANAGER),
            User(password = "password9", email = "user9@example.com", nickName = "user9", role = UserRole.USER),
            User(password = "password10", email = "user10@example.com", nickName = "user10", role = UserRole.MANAGER)
        )
    }
}