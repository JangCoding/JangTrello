# JangTrello
## Entity-Relationship Diagram (ERD)
### USER(Entity): TODO 서비스 사용자 개체

- USER_ID (Primary Key) : 사용자 아이디  
- PASSWORD : 사용자 비밀번호  
- NAME : 사용자 이름  
- EMAIL : 사용자 이메일  
- CREATED_DATE : 사용자 계정 생성일

### CARDLIST(Entity): 할 일 카드를 모아두는 리스트

- CARDLIST_ID (Primary Key) : 리스트 아이디 
- CREATE_DATE : 리스트 생성일
- COUNT : 리스트 내부 카드의 갯수
- USER_ID (Foreign Key referencing USER) : 리스트 생성한 사용자 아이디
  
### CARD(Entity): 할 일을 기록한 카드

- CARD_ID (Primary Key) : 카드 아이디
- STATUS : 할 일 완료 여부( 진행중(FALSE) / 완료(TRUE) )
- CONTENTS : 할 일 내용
- CREATE_DATE : 카드 생성일
- USERNAME (Assuming it's the user who created the card) : 카드 생성 유저 이름
- COMMENTS : 카드에 추가된 댓글 리스트
- CARDLIST_ID (Foreign Key referencing LIST) : 리스트 아이디

### COMMENT(Entity): 카드에 기록된 댓글

- COMMENT_ID (Primary Key) : 댓글 아이디
- CONTENT : 댓글 내용
- CREATE_DATE : 댓글 생성일
- USER_ID (Foreign Key referencing USER) : 댓글 작성한 사용자 아이디
- CARD_ID (Foreign Key referencing CARD) : 댓글이 작성된 카드 아이디
  
---

## Relationships:
- USER to CARDLIST: One-to-Many (하나의 사용자가 여러 리스트를 가질 수 있음)
- CARDLIST to CARD: One-to-Many (하나의 리스트가 여러 카드를 가질 수 있음)
- CARD to COMMENT: One-to-Many (하나의 카드가 여러 댓글을 가질 수 있음)
- USER to COMMENT: One-to-Many (하나의 사용자가 여러 댓글을 작성할 수 있음)
