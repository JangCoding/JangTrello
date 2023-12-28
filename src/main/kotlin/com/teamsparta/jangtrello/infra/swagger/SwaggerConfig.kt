package com.teamsparta.jangtrello.infra.swagger

//swagger의 구성요소(Coponents)를 나타내는 클래스. API 정의의 일부. 여러 구성요소를 정의하는데 사용
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI // Swagger문서의 최상위객체 OpenAPI 클래스. API의 전반적 정보 나타냄.
import io.swagger.v3.oas.models.info.Info // API에 관한 기본 정보를 나타내는 클래스
import org.springframework.context.annotation.Bean // SpringBean을 반환하는 매서드임을 나타냄. 반환 값ㅇ르 Bean으로 관리
import org.springframework.context.annotation.Configuration // Spring의 구성(Confirugration) 클래스임을 나타냄.


//
@Configuration

// Swagger설정을 담당하는 클래스
class SwaggerConfig {
    // openAPI() : OpenAPI Bean을 생성(OpenAPI()) 하고 반환(:OpenAPI)함.
    // OpenAPI 라는 Bean 을 등록하는 것. 기존 OpenAPI재정의
    @Bean
    fun openAPI():OpenAPI = OpenAPI()
        .components(Components())   //구성요소 초기화
        .info(                      //API 정보 설정
            Info()
                .title("TODO API")
                .description("TODO API schema")
                .version("1.0.0")
        )
}