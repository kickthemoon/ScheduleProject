POSTMAN 설정 { Body > raw > josn } / 코드 내 {id}와 password,userId 는 알맞게 수정해주세요.


POST 'User 생성' 
localhost:8080/users/signup 
{
    "username": "name1",
    "password": "1234",
    "email": "exmple@gmail.com"
}


POST '로그인'
localhost:8080/auth/login
{
    "username": "name1",
    "password": "1234"
}


POST '로그아웃'
localhost:8080/auth/logout


GET 'User 목록 조회'
localhost:8080/users


GET 'User 개별 조회'
localhost:8080/users/{id}


PATCH 'User 수정'
localhost:8080/users/{id}
{
    "newUsername": "새로운이름1",
    "newEmail": "new@gamil.com",
    "checkPassword": "1234"
}


DELETE 'User 삭제'
localhost:8080/users/{id}
{
    "checkPassword": "1234"
}


POST 'Schedule 생성'
localhost:8080/schedules/registration
{
    "title": "일정 제목1",
    "contents": "일정 내용1",
    "password": "1234",
    "userId": 1
}


GET 'Schedule 목록 조회'
localhost:8080/schedules


GET 'Schedule 개별 조회'
localhost:8080/schedules/{id}


PATCH 'Schedule 수정'
localhost:8080/schedules/{id}
{
    "newTitle": "새로운 일정 제목1",
    "newContents": "새로운 일정 내용1",
    "checkPassword": "12345"
}


DELETE 'Schedule 삭제'
localhost:8080/schedules/{id}
{
    "checkPassword": "1234"
}
