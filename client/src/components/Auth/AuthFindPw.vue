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
      // 아이디와 이메일이 유효한 지 체크
      if (this.userId.length == 0 || this.userEmail.length == 0) {
        alert('입력 항목을 모두 입력하세요.');
        return;
      }

      axios.get('http://localhost/user/check/' + this.userId + "/" + this.userEmail)
        .then((resp) => {
          // error 값이 넘어온 경우, 에러창 발생
          if (resp.data == "error") {
            alert('아이디와 이메일 정보가 일치하지 않습니다.');
            return;
          }
          else {
            alert('인증번호가 전송되었습니다.');
            this.secretCertNumber = resp.data;
            return;
          }
        });
      },
    // TODO: 비밀번호 찾기 버튼 클릭 시, 아이디와 이메일 유효성 및 이메일 인증번호 확인
    // 새로운 비밀번호 설정 페이지로 redirect
    // 설정 후, 로그인 페이지로 redirect
    findPw() {
        // 1. 입력란에 공백이 있지 않은 지 확인
        if (this.userId.length == 0 || this.userEmail.length == 0) {
            alert('모든 항목란은 필수 입력입니다.');
            return;
        }
        // 2. 인증번호가 일치하지 않는 경우
        if (this.certNumber != this.secretCertNumber) {
            alert('인증번호를 확인해주세요!');
            return;
        }
        // 3. 인증번호가 일치하는 경우
        if (this.certNumber == this.secretCertNumber) {
          axios.get('http://localhost/user/findpw/' + this.userId + "/" + this.userEmail)
            .then((resp) => {
              alert('회원님의 비밀번호는 (' + resp.data.userPw + ') 입니다.');
              this.$router.push("/login");
            });
        }
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
#AuthFindPw {
  height: 500px;
  padding-top: 50px;
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