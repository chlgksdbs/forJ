import jwtDecode from "jwt-decode";
import router from "@/router";
import http from "@/util/http-common.js";

// vuex life cycle : actions -> mutations -> state
const memberStore = {
  namespaced: true, // ???
  state: {
    isLogin: false, // 현재 로그인 상태인 지에 대한 변수
    isLoginError: false, // 로그인 중, 에러가 발생했는 지에 대한 state 변수
    userInfo: null, // user 정보에 대한 stae 객체
    isValidToken: false, // token이 유효한 지에 대한 state 변수
  },
  getters: {
    // state에 있는 userInfo를 반환하는 메서드
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    // state에 있는 isValidToken을 반환하는 메서드
    checkToken: function (state) {
      return state.isValidToken;
    },
  },
  // actions에서 commit을 통해 넘어온 부분 (여기서 state를 관리)
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  // actions가 Backend API와 동작하는 부분
  actions: {
    // (1) client의 로그인 요청 처리 메서드
    userConfirm({ commit }, user) {
      http
        // 로그인은 post 요청
        .post("/user/login", user)
        .then(({ data }) => {
          console.log("From Backend", data); // 디버깅

          // Backend에서 넘어온 data 객체 중, message의 값이 success인 경우 (로그인 요청 성공)
          if (data.message == "success") {
            // data 객체에서 access token과 refresh token 추출
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];

            commit("SET_IS_LOGIN", true); // 로그인 성공에 대한 commit
            commit("SET_IS_LOGIN_ERROR", false); // 로그인 에러에 대한 commit
            commit("SET_IS_VALID_TOKEN", true); // 토큰 유효성에 대한 commit

            // access token과 refresh token을 sessionStorage에 설정
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          }
          // Backend에서 넘어온 data 객체 중, message의 값이 success가 아닌 경우 (로그인 요청 실패)
          else {
            commit("SET_IS_LOGIN", false); // 로그인 실패에 대한 commit
            commit("SET_IS_LOGIN_ERROR", true); // 로그인 에러 발생에 대한 commit
            commit("SET_IS_VALID_TOKEN", false); // 토큰이 유효하지 않은 것에 대한 commit
          }
        })
        // 에러 발생 시 처리
        .catch((error) => {
          console.log("OMG", error);
        });
    },

    // (2) 사용자 인증 요청 (token의 유효성 검증)
    getUserInfo({ commit, dispatch }, token) {
      // jwtDecode 라이브러리를 이용하여 token decode
      let decodeToken = jwtDecode(token);

      // Backend로 보내는 요청의 Header부분에 access-token을 포함
      http.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
      http
        .get("/user/info/" + decodeToken.userId)
        .then(({ data }) => {
          // Backend에서 넘어온 data 객체 중, message의 값이 success인 경우 (회원 인증 성공)
          if (data.message == "success") {
            commit("SET_USER_INFO", data.userInfo);
          }
          // Backend에서 넘어온 data 객체 중, message의 값이 success가 아닌 경우 (회원 인증 실패)
          else {
            console.log("유저 정보 없음!!!");
          }
        })
        .catch((error) => {
          console.log(
            "getUserInfo() error code [토큰 만료되어 사용 불가능.] :::",
            error.response.status
          );
          commit("SET_IS_VALID_TOKEN", false); // 토큰이 유효하지 않은 것에 대한 commit

          // 토큰 재발급 메서드 실행
          dispatch("tokenRegeneration");
        });
    },

    // (3) refresh token을 이용한 access token 재발급 메서드
    tokenRegeneration({ commit, state }) {
      console.log("토큰 재발급 >> 기존 토큰 정보 : {}", sessionStorage.getItem("access-token"));

      // Backend로 보내는 요청의 Header부분에 refresh-token을 포함
      http.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token");
      http
        .post("/user/refresh", state.userInfo)
        .then(({ data }) => {
          // Backend에서 넘어온 data 객체 중, message의 값이 success인 경우 (refresh token 유효 O)
          if (data.message == "success") {
            // Backend로부터 새로 발급받은 access token 추출
            let accessToken = data["access-token"];

            console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            // 새로운 access token을 sessionStorage에 저장
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        })
        .catch((error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인
          if (error.response.status == 401) {
            console.log("갱신 실패");

            // DB에 저장되어 있는 refresh token 제거
            http
              .get("/user/logout/" + state.userInfo.userId)
              .then(({ data }) => {
                if (data.message == "success") {
                  console.log("refresh token 제거 성공!");
                } else {
                  console.log("refresh token 제거 실패...");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인 해주세요.");

                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);

                // login 페이지로 이동
                router.push("/login");
              })
              .catch((error) => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              });
          }
        });
    },

    // (4) 사용자 로그아웃 메서드
    userLogout({ commit }, userId) {
      http
        .get("/user/logout/" + userId)
        .then(({ data }) => {
          if (data.message == "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
          } else {
            console.log("유저 정보 없음!!!");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default memberStore;
