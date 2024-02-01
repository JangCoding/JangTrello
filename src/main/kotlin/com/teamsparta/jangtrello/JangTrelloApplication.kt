package com.teamsparta.jangtrello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing // Auditing 기능 활성화
class JangTrelloApplication

fun main(args: Array<String>) {
	runApplication<JangTrelloApplication>(*args)
}
