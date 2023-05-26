# forJ
일상에서 계획을 수립하는걸 좋아하는 MBTI J를 위한 웹 플래닝 서비스


## 📆 개발 기간
> 2023.04.03 ~ 2023.05.26


## 👫 TEAM: 내 손을 JAVA
|<img src="https://user-images.githubusercontent.com/96401350/229277093-a968a9c7-19ca-4629-b19b-a8253739a124.jpeg" width="35%" />|<img src="https://user-images.githubusercontent.com/96401350/229572946-b001bd4a-039b-4506-a7fb-6252b415761c.jpeg" width="35%" />|
|:---:|:---:|
|최한윤|조해린|
|[@chlgksdbs](https://github.com/chlgksdbs)|[@zosunny](https://github.com/zosunny)|


## 🌳 기능
사용자의 니즈를 파악하기 위해 요구사항 명세서를 작성했다. 주요 기능을 모두 구현하고, 나머지는 명세서의 90% 정도까지 개발을 완료하는 것을 목표로 잡았다.
<img width="1004" alt="forJ 요구사항 명세서" src="https://github.com/chlgksdbs/forJ/assets/96401350/eea4c98c-fd82-45fa-b152-455d0ab884d9">


## 🧍 유스케이스 다이어그램
![_forJ_Usecase_Diagram drawio](https://github.com/chlgksdbs/forJ/assets/96401350/966d9e6d-c3a2-49f6-aa14-66747fd12593)


## 📋 클래스 다이어그램
![Class Diagram](https://github.com/chlgksdbs/forJ/assets/96401350/34a251b7-14c1-4f83-95c5-3cef0bd761c6)


## 🔧 사용 기술

### Front-end
| Category          | Stack                                                                           |
| ----------------- | ------------------------------------------------------------------------------- |
| Language          | - Javascript                                                                    |
| Framework         | - Vue 2                                                                         |

### Back-end
| Category          | Stack                                                                           |
| ----------------- | ------------------------------------------------------------------------------- |
| Framework         | - Spring Boot 2.7.11                                                            |
| Test              | - JUnit 5                                                                       |
| Infra             | - AWS EC2 <br>- Maven <br>                                                      |
| Database          | - MySql <br>- 공공데이터                                                           |

### Communication
| Category          | Stack                                                                           |
| ----------------- | ------------------------------------------------------------------------------- |
| Sprint Management | - Jira                                                                          |
| Documents         | - Google Sheets <br>- Notion                                                    |
| Tools             | - Figma <br>- Swagger                                                           |


## 🗓 프로젝트 진행일정
<br>
<img width="1179" alt="forJ Jira" src="https://user-images.githubusercontent.com/96401350/235462578-0b5fd635-5dca-4c50-b762-538e3f34071c.png">

### 스프린트별 목표 정의
- **Sprint #1**
  - 요구사항 명세서 작성
  - 협업 툴 설정 (Jira, google sheets)
  - UI 설계 (Figma)
  - 디자인 시안 확정
  - DevOps 환경 구축
  - DB (ERD) 설계
  - 로그인 / 회원가입 구현
  - 여행 계획 공유 게시판 구현
- **Sprint #2**
  - 여행 계획 관련 기능 구현
  - 공공 데이터 활용
  - 개인정보 CRUD 기능 구현
  - 스크랩 + 장바구니 기능 구현
- **Sprint #3**
  - 소셜 로그인(카카오, 네이버) open API 구현
  - 공통 component (header, footer) UI 구현
  - Auth Page, User Page, Planning Page UI 구현
- **Sprint #4**
  - Board Page UI 구현
  - Open API Page UI 구현
  - 전체 Html, CSS, JS 코드 리팩토링
- **Sprint #5**
  - 개발 마무리 및 통합 테스트
  - AWS EC2를 이용한 배포
  - 결과 보고서 작성 (ppt)


## 🖥 주요 화면

> **`로그인`**
![로그인 화면](https://github.com/chlgksdbs/forJ/assets/96401350/295c9c04-10ba-4e4e-bb2c-beec1a10855c)

> **`메인화면`**
![(로그인 후) 메인화면 (1)](https://github.com/chlgksdbs/forJ/assets/96401350/a507a6d4-e14c-4984-b5c5-2f7fee86acd3)

> **`여행 계획 페이지`**
![여행 계획 작성 페이지](https://github.com/chlgksdbs/forJ/assets/96401350/8ee60f4b-03cb-4ed6-9c0f-c21d451b3b98)

> **`여행 계획 상세 페이지`**
![여행 계획 체크 페이지](https://github.com/chlgksdbs/forJ/assets/96401350/1816c33b-71eb-4f94-be26-4041959ab990)

> **`마이페이지: 나의 여행일정`**
![마이페이지 나의 여행일정 화면](https://github.com/chlgksdbs/forJ/assets/96401350/b0db799a-d90f-467a-a9ac-ce448bcc2605)

> **`마이페이지: 회원정보 수정`**
![마이페이지 회원정보 수정 화면](https://github.com/chlgksdbs/forJ/assets/96401350/c2df708b-04da-4ad8-a104-bb99542df3d3)

> **`계획 공유 게시판`**
![공유 게시판 글 목록 페이지](https://github.com/chlgksdbs/forJ/assets/96401350/3676235e-5c8b-4aca-9e6c-3544727ab7d8)


## 🎥 시연 영상
https://drive.google.com/file/d/1yheitgYTWrnKlVnY4n6kIrInAh4j4BS1/view?usp=share_link
