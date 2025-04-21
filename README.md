# 🚀 SPRING ADVANCED
<div style="display: flex; gap: 5px;">
<img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white" />
</div>

##  개요
- [📌 프로젝트 회고(velog)](https://velog.io/@wannabeing/%ED%9A%8C%EA%B3%A0)
- 개발기간: 2025.04.15(화) ~ 2025.04.21(월)
- 자바버전: OpenJDK 17
- 사용기술: Spring Boot, JPA, MySQL
- 코드 개선, AOP 적용, 테스트 코드 작성

---

## Lv1. 코드개선
1. 조건에 맞지 않는 경우 즉시 리턴(Early Return)하여 불필요한 로직 실행을 방지하고, 성능을 향상시킨다.
   - [Lv1. 얼리리턴 커밋](https://github.com/wannabeing/spring-advanced/commit/ced83363aab80b4d668fca2e4b1165299a80a657)
2. 불필요한 else 블록을 제거하여 가독성을 높이고, 코드를 간결하게 만든다.
   - [Lv1. 불필요한 if-else 개선 커밋](https://github.com/wannabeing/spring-advanced/commit/b562e0cfa6c09d066bbc4925357e78d8c2dc9017)
3. `@Validation` 적용하여 유효성 검증을 통해 코드를 개선하였다.
    - [Lv1. @Validation 적용 커밋](https://github.com/wannabeing/spring-advanced/commit/644e35b339eacda898679bd06199e434abf430d6)
## Lv2. N+1문제 해결
JPQL로 해결했던 N+1 문제를 @EntityGraph를 사용하여 코드를 간결하고 명시적으로 개선하였다.
- [@EntityGraph 적용 커밋](https://github.com/wannabeing/spring-advanced/commit/cab0750c14f54f95b449c254d239cd3a79bdd607)

## Lv3. 테스트코드
테스트 코드를 작성하고 개선하여 Spring 테스트 환경에 적응하였다.
- [1-1. 테스트케이스 커밋](https://github.com/wannabeing/spring-advanced/commit/48dee2967c174a91ccf201427a9bd22834265955)
- [1-2. 테스트케이스 커밋](https://github.com/wannabeing/spring-advanced/commit/5ed8b467aa8666fc3837de500e01f83e49ee5454)
- [2. 테스트케이스 커밋](https://github.com/wannabeing/spring-advanced/commit/a5798ec8d2348075e65cac276c4a40689b434754)
- [3. 테스트케이스 커밋](https://github.com/wannabeing/spring-advanced/commit/df9f2f4493bc1ca46a5ae2d782109b2c153d0d81)

## Lv4. API 로깅
AOP를 학습하고 적용하여, 관리자 API 요청/응답 데이터를 로깅하였다.
- [Comment Admin Controller 테스트케이스 추가 커밋](https://github.com/wannabeing/spring-advanced/commit/215e661ec0bc1ffa1eb55de6e4d955370e87b996)
- [User Admin Controller 테스트케이스 추가 커밋](https://github.com/wannabeing/spring-advanced/commit/4bf47fe6c60ba93c0cceea4b1f2b8e6799ffd4ec)
- [Admin Logging AOP 클래스 추가 커밋](https://github.com/wannabeing/spring-advanced/commit/3cb5973a428b2548fb225fc3d6c7a36c757a9813)

## Lv5. 코드 리팩토링
API 성공 응답 DTO가 도메인별로 제각각이라 일관성이 부족하고 가독성이 떨어진다고 생각했다.
`SuccessResponseDto`를 생성하여 모든 API 응답 형식을 통일하고 가독성을 높였다.

---

## 패키지 구조
```
src
└── java
    └── org.example.expert
        ├── client                # 외부 API 연동
        ├── config                # 전역 설정 관련 클래스
        ├── domain                # 도메인 패키지
        │   ├── auth              # 인증/인가 (Sign) 관련 기능
        │   ├── comment           # 댓글(Comment) 기능
        │   ├── common            # 공통 DTO, 예외 클래스 등
        │   ├── manager           # 담당자(Manager) 관련 기능
        │   ├── todo              # 일정(Todo) 관련 기능
        │   └── user              # 유저(User) 관련 기능
        └── ExpertApplication     # ✅ 메인
```