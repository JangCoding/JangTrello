# JangTrello
## Entity-Relationship Diagram (ERD):
### USER(Entity):
- USER_ID (Primary Key)
- PASSWORD
- NAME
- EMAIL
- CREATED_DATE
- LOGIN_DATE
### LIST(Entity):

- LIST_ID (Primary Key)
- CREATE_DATE
- COUNT
- USER_ID (Foreign Key referencing USER)
### CARD(Entity):

- CARD_ID (Primary Key)
- STATUS
- CONTENTS
- CREATE_DATE
- USERNAME (Assuming it's the user who created the card)
- COMMENTS
- LIST_ID (Foreign Key referencing LIST)
### COMMENT(Entity):

- COMMENT_ID (Primary Key)
- CONTENT
- CREATE_DATE
- USER_ID (Foreign Key referencing USER)
- CARD_ID (Foreign Key referencing CARD)
---

## Relationships:
- USER to LIST: One-to-Many (하나의 사용자가 여러 리스트를 가질 수 있음)
- LIST to CARD: One-to-Many (하나의 리스트가 여러 카드를 가질 수 있음)
- CARD to COMMENT: One-to-Many (하나의 카드가 여러 댓글을 가질 수 있음)
USER to COMMENT: One-to-Many (하나의 사용자가 여러 댓글을 작성할 수 있음)
