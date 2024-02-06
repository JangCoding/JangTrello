# JangTrello

### 코드 개선 내역
- Controller, Service 패키지 내 클래스 개선
 - Controller Advice 로 예외 공통화 처리
 - Service 인터페이스와 구현체 분리하여 추상화
- CustomException 정의 및 SpringAOP 적용
- QueryDSL 을 사용한 검색 기능
- Pageable 을 사용하여 페이징 및 정렬 기능
- 다양한 조건을 동적 쿼리로 처리 ( ID, 역할 )
 - 제목/태그/카테고리/게시글 상태/N일 전 게시글 ( 미완 )
 
### TODO
- SERVICE / REPOSITORY 테스트 코드 만들기

## INTRODUCE
본 프로그램은 사용자 인증/인가 기능을 적용하여 로그인 한 사용자는 할 일을 카드(Card) 단위로 작성하고, 할 일 카드를 확인/추가/수정/제거하며 현황을 파악할 수 있습니다.
또한 자신 혹은 다른 사용자의 할 일 카드에 댓글(Comment)를 추가할 수도 있습니다.
카드와 댓글의 수정/삭제 시 다시 한 번 비밀번호를 입력하여 본인을 확인한 뒤 진행하도록 설정되어 있습니다.

![image](https://github.com/JangCoding/JangTrello/assets/62090021/7870ce13-7df2-4d73-a121-92732c4b8440)
![image](https://github.com/JangCoding/JangTrello/assets/62090021/c7ab4f6c-811c-4dfb-836d-8ea35eead3e9)




## Entity-Relationship Diagram (ERD)
![image](https://github.com/JangCoding/JangTrello/assets/62090021/15f7326a-69be-481e-a564-f983bffda13c)



### BASE ENTITY
- CREATED_AT : 생성일
- CREATED_BY : 생성한 사람
- MODIFIED_AT : 수정일
- MODIFIED_BY : 수정한 사람

### USER(Entity): TODO 서비스 사용자 개체

- ID (Primary Key) : 사용자 아이디  
- PASSWORD : 사용자 비밀번호  
- EMAIL : 사용자 이메일  
- NICKNAME : 사용자 이름
- ROLE : 사용자 권한 
 
### CARD(Entity): 할 일을 기록한 카드

- ID (Primary Key) : 카드 아이디
- EMAIL : 카드 생성 유저 이메일
- NICKNAME : 카드 생성 유저 이름
- TITLE : 카드 제목
- STATUS : 할 일 완료 여부( 진행중(FALSE) / 완료(TRUE) )
- CONTENTS : 할 일 내용
- COMMENTS : 댓글 목록
- USER_ID (Foreign Key referencing LIST) : 리스트 아이디

### COMMENT(Entity): 카드에 기록된 댓글

- ID (Primary Key) : 댓글 아이디
- EMAIL : 댓글 작성한 사용자 이메일
- NICKNAME : 댓글 작성한 사용자 이름
- CONTENTS : 댓글 내용
- USER_ID (Foreign Key referencing USER) : 댓글을 작성한 유저 아이디
- CARD_ID (Foreign Key referencing CARD) : 댓글이 작성된 카드 아이디

## Relationships:
- CARD to USER : Many-to-One (여러 카드를 한 사용자가 관계를 갖음)
- COMMENT to User : Many-to-One (여러 댓글을 한 사용자가 관계를 갖음)
- COMMENT to Card : Many-to-One (여러 댓글을 한 카드가 관계를 갖음)
    
--- 
## REST API 
![Brainstorming (3)](https://github.com/JangCoding/JangTrello/assets/62090021/9bfeb4cf-30a0-4a1c-8312-fc6d5925b093)

## STACKS
- INTELLIJ
- KOTLIN
- SPRING BOOT
- SUPABASE ( POSTGRES DATABASE )

