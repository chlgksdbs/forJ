<template>
    <div id="AuthJoin">
        <h2>회원가입</h2>
        <div id="form_box">
            <div>
                <div class="form_text">아이디</div>
                <div class="form_input_box">
                <input type="text" v-model="userId" maxlength="45">
                </div>
            </div>
            <div>
                <div class="form_text">비밀번호</div>
                <div class="form_input_box">
                <input type="password" v-model="userPw" maxlength="100">
                </div>
            </div>
            <div>
                <div class="form_text">비밀번호 재확인</div>
                <div class="form_input_box">
                <input type="password" v-model="userPwCheck" maxlength="100">
                </div>
                <span class="login_error_message" v-if="userPw != userPwCheck">비밀번호가 일치하지 않습니다.</span>
            </div>
            <div>
                <div class="form_text">이름</div>
                <div class="form_input_box">
                <input type="text" v-model="userName" maxlength="45">
                </div>
            </div>
            <div>
                <div class="form_text">성별</div>
                <div class="form_input_box">
                <select name="gender" v-model="userGender">
                    <option>성별</option>
                    <option>남자</option>
                    <option>여자</option>
                </select>
                </div>
            </div>
            <div>
                <div class="form_text">휴대전화</div>
                <div class="form_input_box">
                <input type="number" v-model="userPhone" maxlength="45">
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
                <button id="joinBtn" @click="joinCheck">가입하기</button>
                </router-link>
            </div>
        </div>
    </div>
</template>
<script>
import axios from "axios";

export default {
  name: 'AuthJoin',
  data() {
    return {
      userId: '',
      userPw: '',
      userPwCheck: '',
      userName: '',
      userGender: '',
      userPhone: '',
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
    // 가입하기 버튼 클릭 시, 수행하는 함수
    joinCheck() {
      // 1. 입력란에 공백이 있지 않은 지 확인
      if (this.userId.length == 0 || this.userName.length == 0 || this.userPhone.length == 0 || this.userEmail.length == 0) {
        alert('모든 항목란은 필수 입력입니다.');
        return;
      }

      // 2. 성별을 입력했는 지 확인
      if (this.userGender.length == 0 || this.userGender == '성별') {
        alert('성별을 입력해주세요.');
        return;
      }

      // 3. 비밀번호와 비밀번호 재확인이 동일한 지 체크
      if (this.userPw.length == 0 || this.userPw !== this.userPwCheck) {
        alert('비밀번호를 확인해주세요!');
        return;
      }

      // 4. 이메일 인증번호가 유효한 지 체크
      if (this.certNumber != this.secretCertNumber) {
        alert('인증번호를 확인해주세요!');
        return;
      }

      // 5. 사용자 정보 백엔드로 보내서 유효한 지 체크 후 가입
      // 사용자 정보 JSON 형태로 전송
      let userDto = {
        "userId": this.userId,
        "userPw": this.userPw,
        "userName": this.userName,
        "userGender": this.userGender,
        "userPhone": this.userPhone,
        "userEmail": this.userEmail,
      }

      axios.post('http://localhost/user/join', userDto)
        .then(() => {
          alert('(' + this.userName + ')님 forJ에 가입하신걸 환영합니다!');
          this.$router.push('/login');
        });

    }
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
#joinBtn {
  width: 100%;
  height: 50px;
  background-color: #2E75B6;
  color: #FFFFFF;
  cursor: pointer;
  border: none;
}
</style>