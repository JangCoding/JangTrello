# JangTrello

## INTRODUCE
본 프로그램은 할 일을 카드(Card) 단위로 작성하고, 이를 묶은 리스트들을 카드 리스트(CardList)로 관리할 수 있게 만들어졌습니다.

사용자들은 원하는 이름을 붙여 카드리스트를 생성하여 카테고리를 구분하거나 관리할 수 있고, 내부에 할 일 카드를 확인/추가/수정/제거하며 현황을 파악할 수 있습니다.

또한 자신 혹은 다른 사용자의 할 일 카드에 이름과 비밀번호를 설정하여 댓글(Comment)를 추가할 수도 있습니다.

이 내용은 카드 조회 시 함께 확인이 가능합니다.


## Entity-Relationship Diagram (ERD)
<p align="center">
  <img src="https://github.com/JangCoding/JangTrello/assets/62090021/c9628314-98fb-479b-a0c3-2d92a491e244">
</p>

### USER(Entity): TODO 서비스 사용자 개체

- ID (Primary Key) : 사용자 아이디  
- PASSWORD : 사용자 비밀번호  
- NAME : 사용자 이름  
- EMAIL : 사용자 이메일  

### CARDLIST(Entity): 할 일 카드를 모아두는 리스트

- ID (Primary Key) : 리스트 아이디
- DATE : 리스트 생성일
- TITLE : 리스트 제목
- COUNT : 리스트 내부 카드의 갯수
- USER_ID (Foreign Key referencing USER) : 리스트 생성한 사용자 아이디
  
### CARD(Entity): 할 일을 기록한 카드

- ID (Primary Key) : 카드 아이디
- DATE : 카드 생성일
- TITLE : 카드 제목
- STATUS : 할 일 완료 여부( 진행중(FALSE) / 완료(TRUE) )
- CONTENTS : 할 일 내용
- USERNAME : 카드 생성 유저 이름
- CARDLIST_ID (Foreign Key referencing LIST) : 리스트 아이디

### COMMENT(Entity): 카드에 기록된 댓글

- ID (Primary Key) : 댓글 아이디
- DATE : 댓글 생성일
- PASSWORD : 댓글 비밀번호 
- CONTENT : 댓글 내용
- USER_NAME : 댓글 작성한 사용자 이름
- CARD_ID (Foreign Key referencing CARD) : 댓글이 작성된 카드 아이디

## Relationships:
- USER to CARDLIST: One-to-Many (하나의 사용자가 여러 리스트를 가질 수 있음)
- CARDLIST to CARD: One-to-Many (하나의 리스트가 여러 카드를 가질 수 있음)
- CARD to COMMENT: One-to-Many (하나의 카드가 여러 댓글을 가질 수 있음)
    
---

## USER CASE DIAGRAM
<p align="center">
  <img src="https://github.com/JangCoding/JangTrello/assets/62090021/18d35b34-5ed6-40c6-bf4c-127dcc2e48e0">
</p>

## REST API
<p align="center">
  <img src="https://github.com/JangCoding/JangTrello/assets/62090021/5299b90b-3043-4262-b1ca-67a10f423692">
</p>

## STACKS
- INTELLIJ
- KOTLIN
- SPRING BOOT
- SUPABASE ( POSTGRES DATABASE )

