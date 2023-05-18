// import jwtDecode from "jwt-decode";
// import router from "@/router";

// vuex life cycle : actions -> mutations -> state
const memberStore = {
    state: {
        isLogin: false, // 현재 로그인 상태인 지에 대한 변수
        isLoginError: false, // 로그인 중, 에러가 발생했는 지에 대한 state 변수
        userInfo: null, // user 정보에 대한 stae 객체
        isValidToken: false, // token이 유효한 지에 대한 state 변수
    },
    getters: {

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
        // client의 로그인 요청 처리 메서드
        // async userConfirm({ commit }, user) {
        //     await login(
        //         user,
        //         ({ data }) => {
        //             // Backend에서 넘어온 data 객체 중, message의 값이 success인 경우 (로그인 요청 성공)
        //             if (data.message === "success") {

        //                 // data 객체에서 access token과 refresh token 추출
        //                 let accessToken = data["access-token"];
        //                 let refreshToken = data["refresh-token"];

        //                 commit("SET_IS_LOGIN", true); // 로그인 성공에 대한 commit
        //                 commit("SET_IS_LOGIN_ERROR", false); // 로그인 에러에 대한 commit
        //                 commit("SET_IS_VALID_TOKEN", true); // 토큰 유효성에 대한 commit

        //                 // access token과 refresh token을 sessionStorage에 설정
        //                 sessionStorage.setItem("access-token", accessToken);
        //                 sessionStorage.setItem("refresh-token", refreshToken);
        //             }
        //             // message의 값이 fail인 경우 (로그인 요청 실패)
        //             else {
        //                 commit("SET_IS_LOGIN", false); // 로그인 실패에 대한 commit
        //                 commit("SET_IS_LOGIN_ERROR", true); // 로그인 에러 발생에 대한 commit
        //                 commit("SET_IS_VALID_TOKEN", false); // 토큰이 유효하지 않은 것에 대한 commit
        //             }
        //         },
        //         (error) => {
        //             console.log(error);
        //         }
        //     )
        // }
    },
};

export default memberStore;