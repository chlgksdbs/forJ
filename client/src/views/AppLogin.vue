<template>
  <div id="AppLogin">
    <div id="logo_box">
      <router-link to="/">
        <img src="@/assets/img/logo.png" id="login_logo" alt="">
      </router-link>
    </div>
    <div id="login_box">
      <div class="input_login_box">
        <img src="@/assets/img/icon_id.png" class="login_icon" alt=""/>
        <input type="text" placeholder="아이디" maxlength="45" v-model="user.userId" class="input_login"/>
        <img src="@/assets/img/icon_delete.png" class="login_delete_icon" alt="" @click="input_delete_id"/>
      </div>
      <div class="input_login_box">
        <img src="@/assets/img/icon_pw.png" class="login_icon" alt=""/>
        <input type="password" placeholder="비밀번호" maxlength="100" v-model="user.userPw" @keyup.enter="loginCheck" class="input_login"/>
        <img src="@/assets/img/icon_delete.png" class="login_delete_icon" alt="" @click="input_delete_pw"/>
      </div>
      <div>
        <button id="loginBtn" @click="loginCheck">로그인</button>
      </div>
      <div class="find_box">
        <router-link to="#" class="find_text">아이디 찾기</router-link>
        <router-link to="#" class="find_text">비밀번호 찾기</router-link>
        <router-link to="/join" class="find_text">회원가입</router-link>
      </div>
      <div>
        <router-link to="#">
          <img src="@/assets/img/kakao_loginBtn.png" alt="" id="kakaoLoginBtn" class="socialLoginBtn">
        </router-link>
        <router-link to="#">
          <img src="@/assets/img/naver_loginBtn.png" alt="" id="naverLoginBtn" class="socialLoginBtn">
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: 'AppLogin',
  components: {},
  data() {
    return {
      user: {
        userId: null,
        userPw: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    input_delete_id() { 
      this.user.userId='';
    },
    input_delete_pw() {
      this.user.userPw='';
    },
    // 로그인 메서드
    async loginCheck() {
      // 입력이 안된 정보가 있는 지 체크
      // if (this.user.userId.length == 0 || this.user.userPw.length == 0) {
      //   alert('아이디 또는 비밀번호를 입력하세요');
      //   return;
      // }

      await this.userConfirm(this.user);

      let token = sessionStorage.getItem("access-token");
      console.log(token);

      // 로그인이 된 상태인 경우
      if (this.isLogin) {
        // access token을 이용하여 사용자 인증 요청 확인
        await this.getUserInfo(token);
        this.$router.push("/");
      }
    },
  },
};
</script>

<style scoped>
div {
  padding: 5px 0 0 0;
}
a {
  text-decoration: none;
}
.input_login_box {
  position: relative;
  width: 400px;
  border: 1px solid #dadada;
  margin: 10px auto;
}
.input_login_box:focus-within {
  border: 1px solid #2E75B6;
}
.input_login {
  width: 300px;
  height: 50px;
  border: none;
}
.input_login:focus {
  outline: none;
}
.login_icon {
  position: absolute;
  top: 18px;
  left: 20px;
  width: 16px;
}
.login_delete_icon {
  position: absolute;
  top: 18px;
  right: 25px;
  width: 16px;
  cursor: pointer;
}
.socialLoginBtn {
  width: 190px;
  height: 50px;
  margin: 10px 10px;
  border-radius: 7px;
}
.find_text {
  font-size: 14px;
  color: #888;
}
.find_text+.find_text::before {
  content: " | ";
  color: #dadada;
}
#AppLogin {
  height: 100vh;
}
#logo_box {
  margin-bottom: 50px;
}
#login_logo {
  width: 450px;
}
#login_box {
  box-sizing: border-box;
  width: 460px;
  margin: 0 auto;
}
#loginBtn {
  width: 400px;
  height: 50px;
  background-color: #2E75B6;
  color: #FFFFFF;
  cursor: pointer;
  border: none;
}
</style>