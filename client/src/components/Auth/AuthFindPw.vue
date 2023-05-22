<template>
    <div id="AuthFindPw">
        <h2>비밀번호 찾기</h2>
        <div id="form_box">
            <div>
                <div class="form_text">아이디</div>
                <div class="form_input_box">
                <input type="text" v-model="userId" maxlength="45">
                </div>
            </div>
            <div>
                <div class="form_text">이메일</div>
                <div class="form_email_box">
                <input type="email" v-model="userEmail" id="email_text" maxlength="100">
                </div>
                <router-link to="#">
                <button id="certBtn" @click="emailCheck">인증번호 받기</button>
                </router-link>
            </div>
            <div>
                <div class="form_input_box">
                <input type="number" v-model="certNumber" placeholder="인증번호를 입력하세요.">
                </div>
                <span class="login_error_message" v-if="certNumber != secretCertNumber">인증번호가 일치하지 않습니다.</span>
            </div>
            <div>
                <router-link to="#">
                <button id="findPwBtn" @click="findPw">비밀번호 찾기</button>
                </router-link>
            </div>
        </div>
    </div>
</template>
<script>
import axios from "axios";

export default {
  name: 'AuthFindPw',
  data() {
    return {
      userId: '',
      userEmail: '',
      certNumber: '',
      secretCertNumber: '',
    };
  },
  created() {},
  methods: {
    // 인증번호 받기 버튼 클릭 시, 수행하는 함수
    emailCheck() {
      // 이메일이 유효한 지 체크
      if (this.userEmail.length == 0) {
        alert('이메일을 입력하세요.');
        return;
      }

      // JSON 형태로 전송
      let userDto = {
        "userEmail": this.userEmail,
      };

      axios.post('http://localhost/user/certmail', userDto)
        .then((resp) => {
          alert('인증번호가 전송되었습니다.');
          this.secretCertNumber = resp.data;
        });
      },
    // TODO: 비밀번호 찾기 버튼 클릭 시, 인증번호 확인 후 이메일에 해당하는 아이디 alert 출력
    // 출력 후, 로그인 페이지로 redirect
    findPw() {
        
    },
  },
};
</script>

<style scoped>
div {
  margin: 8px 0 0;
}
input, select {
  border: none;
  width: 95%;
  height: 30px;
  margin: 12px 0;
  font-size: 13px;
}
input::-webkit-inner-spin-button {
  appearance: none;
}
input:focus, select:focus {
  outline: none;
}
.form_input_box {
  width: 100%;
  height: 49px;
  border: 1px solid #dadada;
}
.login_error_message {
  display: block;
  margin: 9px 0 2px;
  font-size: 12px;
  line-height: 14px;
  color: red;
  text-align: left;
}
.form_email_box {
  display: inline-block;
  width: 72%;
  margin-right: 5px;
  height: 50px;
  border: 1px solid #dadada;
}
.form_input_box:focus-within, .form_email_box:focus-within {
  border: 1px solid #2E75B6;
}
.form_text {
  text-align: left;
  font-size: 15px;
  color: #888;
}
#join_logo {
  width: 250px;
}
#form_box {
  width: 460px;
  margin: auto;
}
#form_box div input[type="email"] {
  width: 90%;
  margin-right: 10px;
}
#certBtn {
  width: 120px;
  height: 50px;
  background-color: #2E75B6;
  color: #FFFFFF;
  cursor: pointer;
  border: none;
}
#findPwBtn {
  width: 100%;
  height: 50px;
  background-color: #2E75B6;
  color: #FFFFFF;
  cursor: pointer;
  border: none;
}
</style>