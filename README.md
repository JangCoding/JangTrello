# JangTrello

## INTRODUCE
본 프로그램은 사용자 인증/인가 기능을 적용하여 로그인 한 사용자는 할 일을 카드(Card) 단위로 작성하고, 할 일 카드를 확인/추가/수정/제거하며 현황을 파악할 수 있습니다.
또한 자신 혹은 다른 사용자의 할 일 카드에 댓글(Comment)를 추가할 수도 있습니다.
카드와 댓글의 수정/삭제 시 다시 한 번 비밀번호를 입력하여 본인을 확인한 뒤 진행하도록 설정되어 있습니다.


## Entity-Relationship Diagram (ERD)
<p align="center">
  <img src="https://github.com/JangCoding/JangTrello/assets/62090021/c9628314-98fb-479b-a0c3-2d92a491e244">
</p>

### USER(Entity): TODO 서비스 사용자 개체

- ID (Primary Key) : 사용자 아이디  
- EMAIL : 사용자 이메일  
- PASSWORD : 사용자 비밀번호  
- NICKNAME : 사용자 이름  
 
### CARD(Entity): 할 일을 기록한 카드

- ID (Primary Key) : 카드 아이디
- DATE : 카드 생성일
- NICKNAME : 카드 생성 유저 이름
- EMAIL : 카드 생성 유저 이메일
- TITLE : 카드 제목
- STATUS : 할 일 완료 여부( 진행중(FALSE) / 완료(TRUE) )
- CONTENTS : 할 일 내용
- USER_ID (Foreign Key referencing LIST) : 리스트 아이디

### COMMENT(Entity): 카드에 기록된 댓글

- ID (Primary Key) : 댓글 아이디
- DATE : 댓글 생성일
- NICKNAME : 댓글 작성한 사용자 이름
- EMAIL : 댓글 작성한 사용자 이메일
- CONTENT : 댓글 내용
- USER_ID (Foreign Key referencing USER) : 댓글을 작성한 유저 아이디
- CARD_ID (Foreign Key referencing CARD) : 댓글이 작성된 카드 아이디

## Relationships:
- CARD to USER : Many-to-One (여러 카드를 한 사용자가 관계를 갖음)
- COMMENT to User : Many-to-One (여러 댓글을 한 사용자가 관계를 갖음)
- COMMENT to Card : Many-to-One (여러 댓글을 한 카드가 관계를 갖음)
    
---

## USER CASE DIAGRAM
<p align="center">
  <img src="https://github.com/JangCoding/JangTrello/assets/62090021/18d35b34-5ed6-40c6-bf4c-127dcc2e48e0">
</p>

## REST API
<p align="center">
  <img src="https://github.com/JangCoding/JangTrello/assets/62090021/237ed777-4fb2-4b3c-8aa7-3e96e53bb2b3">
</p>

## STACKS
- INTELLIJ
- KOTLIN
- SPRING BOOT
- SUPABASE ( POSTGRES DATABASE )

