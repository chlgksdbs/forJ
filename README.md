# forJ
일상에서 계획을 수립하는걸 좋아하는 MBTI J를 위한 웹 플래닝 서비스

## 📆 개발 기간
> 2023.04.03 ~ 2023.05.26


## 👫 TEAM: 내 손을 JAVA
|<img src="https://user-images.githubusercontent.com/96401350/229277093-a968a9c7-19ca-4629-b19b-a8253739a124.jpeg" width="35%" />|<img src="https://user-images.githubusercontent.com/96401350/229572946-b001bd4a-039b-4506-a7fb-6252b415761c.jpeg" width="35%" />|
|:---:|:---:|
|최한윤|조해린|
|[@chlgksdbs](https://github.com/chlgksdbs)|[@zosunny](https://github.com/zosunny)|


<!-- ## 📂 Folder Structure

```javascript
📂 forj
    📂 Java Resources
     └── 📂 src
     |    ├── 📂 Controller
     |    |    ├── 📄 TourController.java
     |    |    ├── 📄 UserController.java
     |    ├── 📂 dao
     |    |    ├── 📄 TourDao.java
     |    |    ├── 📄 UserDao.java
     |    └── 📂 dto
     |    |    ├── 📄 SidoDto.java
     |    |    ├── 📄 TourDto.java
     |    |    ├── 📄 UserDto.java
     |    ├── 📂 service
     |    |    ├── 📄 TourService.java
     |    |    ├── 📄 UserService.java
     |    ├── 📂 util
     |    |    ├── 📄 DBUtil.java
     |    |    ├── 📄 PageNavigation.java
     |    |    ├── 📄 ParameterCheck.java
     |    |    ├── 📄 SizeConstant.java
     |    |    ├── 📄 SHA256.java
    📂 resources
    ├── 📄 database.sql
    ├── 📄 enjoytrip_er_diagram.png
    📂 WebContent
    ├── 📄 index.jsp
    └── 📂 assets
         └── 📂 css
         |    ├── 📄 login.css
         |    ├── 📄 main.css
         |    ├── 📄 search.css
         └── 📂 img
         |    ├── 📄 aerial.jpg
         |    ├── 📄 bgImg.png
         |    ├── 📄 logo.png
         |    ├── 📄 noimg.png
         |    ├── 📄 originBG.png
         |    ├── 📄 sungsimdang.jpg
         └── 📂 js
         |    ├── 📄 key.js
         |    ├── 📄 login.js
         |    ├── 📄 register.js
         |    ├── 📄 search.js
         |    ├── 📄 searchPwd.js
    └── 📂 error
    |    ├── 📄 changePwFail.jsp
    |    ├── 📄 deleteUserFail.jsp
    |    ├── 📄 error.jsp
    |    ├── 📄 loginFail.jsp
    |    ├── 📄 notEqPw.jsp
    └── 📂 include
    |    ├── 📄 footer.jsp
    |    ├── 📄 head.jsp
    |    ├── 📄 nav.jsp
    └── 📂 tourplace
    |    ├── 📄 map.jsp
    |    ├── 📄 search.jsp
    └── 📂 user
    |    ├── 📄 changePwSuccess.jsp
    |    ├── 📄 deleteUserSuccess.jsp
    |    ├── 📄 findpwd.jsp
    |    ├── 📄 findPwdSuccess.jsp
    |    ├── 📄 login.jsp
    |    ├── 📄 myPage.jsp
    |    ├── 📄 regist.jsp
    |    ├── 📄 registerSuccess.jsp         
    📄 README.md
    📄 pom.xml
``` -->


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
| Infra             | - Docker <br>- AWS EC2 <br>- Jenkins <br>                                       |
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
