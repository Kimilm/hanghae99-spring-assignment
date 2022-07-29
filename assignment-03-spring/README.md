## Spring 숙련 개인 과제

### ERD
![erd](https://user-images.githubusercontent.com/15075501/181703048-90862c67-38cb-4569-b8e4-24a120cb31b7.png)

### API 명세
| METHOD | URL | TYPE | STATUS | REQUEST | RESPONSE | DESCRIPTION |
|:---:|:---|:---:|:---:|---|---|:---:|
| 회원 |
| POST | /expert/api/users | JSON | 200 | <pre>{<br/>　"nickname": 유저 닉네임,<br/>　"password": 유저 비밀번호<br/>}</pre> | <pre># response header<br/>{<br/>　"Access-Token": 엑세스 토큰<br/>　"Refresh-Token": 리프레시 토큰<br/>}</pre> | 로그인 |
| POST | /expert/api/users | JSON | 200 | <pre>{<br/>　"nickname": 유저 닉네임,<br/>　"password": 유저 비밀번호,<br/>　"passwordConfirm": 유저 비밀번호 확인<br/>}</pre> | <pre>{<br/>　"msg": 가입이 완료되었습니다.<br/>}</pre> | 회원가입 |
| 게시글 |
| GET | /expert/api/posts | JSON | 200 | | <pre>{<br/>　"data": [<br/>　　{<br/>　　　"postId": 게시글 아이디,<br/>　　　"title": 제목,<br/>　　　"autor": 작성자,<br/>　　　"createdAt": 작성 날짜<br/>　　}<br/>　]<br/>}</pre> | 전체 게시글 목록 조회 |
| GET | /expert/api/posts/{postId} | JSON | 200 | | <pre>{<br/>　"data": {<br/>　　"postId": 게시글 아이디,<br/>　　"title": 제목,<br/>　　"autor": 작성자,<br/>　　"contents": 게시글 내용,<br/>　　"createdAt": 작성 날짜,<br/>　　"modifiedAt": 수정 날짜,<br/>　　"comments": [<br/>　　　{<br/>　　　　"postId": 게시글 아이디,<br/>　　　　"commentId": 댓글 아이디,<br/>　　　　"userId": 유저 아이디,<br/>　　　　"contents": 댓글 내용,<br/>　　　　"createdAt": 작성 날짜,<br/>　　　　"modifiedAt": 수정 날짜<br/>　　　}<br/>　　]<br/>　}<br/>}</pre> | 게시글 조회 |
| POST | /expert/api/posts | JSON | 200 | <pre>{<br/>　"token": 액세스 토큰,<br/>　"title": 제목,<br/>　"contents": 게시글 내용,<br/>}</pre> | <pre>{<br/>　"msg": "게시글 작성 성공",<br/>　"postId": 게시글 아이디<br/>}</pre> | 게시글 작성 |
| PUT | /expert/api/posts/{postId} | JSON | 200 | <pre>{<br/>　"token": 액세스 토큰,<br/>　"title": 제목,<br/>　"contents": 게시글 내용<br/>}</pre> | <pre>{<br/>　"msg": "게시글 수정 성공"<br/>　"postId": 게시글 아이디<br/>}</pre> | 게시글 수정 |
| DELETE | /expert/api/posts/{postId} | JSON | 200 | <pre>{<br/>　"token": 액세스 토큰<br/>}</pre> | <pre>{<br/>　"msg": "게시글 삭제 성공"<br/>}</pre> | 게시글 삭제 |
| 댓글 |
| GET | /expert/api/comments/{postId} | JSON | 200 |  | <pre>{<br/>　"data": [<br/>　　{<br/>　　　"postId": 게시글 아이디,<br/>　　　"commentId": 댓글 아이디,<br/>　　　"userId": 유저 아이디,<br/>　　　"contents": 댓글 내용,<br/>　　　"createdAt": 작성 날짜,<br/>　　　"modifiedAt": 수정 날짜<br/>　　}<br/>　]<br/>}</pre> | 게시글 댓글 전체 조회 |
| POST | /expert/api/comments/{postId} | JSON | 200 | <pre>{<br/>　"token": 액세스 토큰,<br/>　"contents": 댓글 내용<br/>}</pre> | <pre>{<br/>　"msg": "댓글 작성 성공",<br/>　"commentId": 댓글 아이디<br/>}</pre> | 게시글 댓글 작성 |
| PUT | /expert/api/comments/{commentId} | JSON | 200 | <pre>{<br/>　"token": 액세스 토큰,<br/>　"contents": 댓글 내용<br/>}</pre> | <pre>{<br/>　"msg": "댓글 수정 성공",<br/>　"commentId": 댓글 아이디<br/>}</pre> | 댓글 수정 |
| DELETE | /expert/api/comments/{commentId} | JSON | 200 | <pre>{<br/>　"token": 액세스 토큰<br/>}</pre> | <pre>{<br/>　"msg": "댓글 삭제 성공"<br/>}</pre> | 댓글 삭제 |

