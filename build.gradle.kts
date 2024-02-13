import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.7"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.noarg") version "1.8.22"

	kotlin("kapt") version "1.8.22" // Kotlin Annotation Processing Tool. 어노테이션 분석 > QuertyDsl에 전달!
}

group = "com.teamsparta"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

// Entity 작성하기
noArg {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

val queryDslVersion = "5.0.0" // QueryDsl 버전 선택

val kotestVersion = "5.5.5" // Junit 기반 테스팅 툴
val mockkVersion = "1.13.8" // mock 위한 툴

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310") // jackson 라이브러리에 datatype 관련 모듈 추가
	implementation("com.fasterxml.jackson.core:jackson-databind")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") // 테스트는 기본 의존성.

	testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")     // kotest 설치
	testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")   //
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3") //
	testImplementation("io.mockk:mockk:$mockkVersion") // mokk 설치
	testImplementation("org.postgresql:postgresql") // 테스트용 DB 연결

	implementation("com.querydsl:querydsl-jpa:$queryDslVersion:jakarta") // querydsl-jpa 라이브러리 추가!
	kapt("com.querydsl:querydsl-apt:$queryDslVersion:jakarta") //  Querydsl JPA의 Annotation Processor를 프로젝트에 추가!

    // springdoc 설치
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    // 트랜잭션이 담긴 패키지 jpa 추가
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	//  레포지토리 사용하기 위해 h2 db 추가 // DB 연결하면 안써도 됨
	implementation("com.h2database:h2")

	// 어플리케이션이 실행될 때만 DB 드라이버를 설치하겠다.
	runtimeOnly("org.postgresql:postgresql")

	// Spring Security 추가
	implementation("org.springframework.boot:spring-boot-starter-security")
	// jwt 관련 라이브러리 중 jjwt 추가
	implementation("io.jsonwebtoken:jjwt-api:0.12.3")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")

	// AOP 패키지 설정
	implementation("org.springframework.boot:spring-boot-starter-aop")

	// Data JPA TEST 의존성 추가
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	//implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	//implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	//kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
	kapt("jakarta.annotation:jakarta.annotation-api")
	kapt("jakarta.persistence:jakarta.persistence-api")

	//implementation("org.springframework.boot:spring-boot-starter-web")
	//implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	//implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Test Code
	runtimeOnly("com.h2database:h2")
	//testImplementation("org.springframework.boot:spring-boot-starter-test")
	//testImplementation("io.mockk:mockk:1.13.9")
	//testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
	//testImplementation("io.kotest:kotest-assertions-core:5.7.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

//tasks.withType<Test> {
//	useJUnitPlatform()
//}

tasks.withType<Test>().configureEach() { // 변경 !!
	useJUnitPlatform()
}

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-base:latest")
}
