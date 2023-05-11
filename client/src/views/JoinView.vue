<template>
  <div id="JoinView">
    <div id="logo_box">
      <router-link to="/">
        <img src="@/assets/img/logo.png" id="join_logo" alt="">
      </router-link>
    </div>
    <div id="form_box">
      <div>
        <div class="form_text">아이디</div>
        <div class="form_input_box">
          <input type="text" maxlength="45">
        </div>
      </div>
      <div>
        <div class="form_text">비밀번호</div>
        <div class="form_input_box">
          <input type="password" maxlength="100">
        </div>
      </div>
      <div>
        <div class="form_text">비밀번호 재확인</div>
        <div class="form_input_box">
          <input type="password" maxlength="100">
        </div>
      </div>
      <div>
        <div class="form_text">이름</div>
        <div class="form_input_box">
          <input type="text" maxlength="45">
        </div>
      </div>
      <div>
        <div class="form_text">성별</div>
        <div class="form_input_box">
          <select name="gender">
            <option value="choice">성별</option>
            <option value="man">남자</option>
            <option value="woman">여자</option>
          </select>
        </div>
      </div>
      <div>
        <div class="form_text">휴대전화</div>
        <div class="form_input_box">
          <input type="number" maxlength="45">
        </div>
      </div>
      <div>
        <div class="form_text">이메일</div>
        <div class="form_email_box">
          <input type="email" id="email_text" maxlength="100">
        </div>
        <router-link to="#">
          <button id="certBtn">인증번호 받기</button>
        </router-link>
      </div>
      <div>
        <div class="form_input_box">
          <input type="number" placeholder="인증번호를 입력하세요.">
        </div>
      </div>
      <div>
        <router-link to="#">
          <button id="joinBtn">가입하기</button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'JoinView',
  components: {},
  data() {
    return {
      message: '',
    };
  },
  created() {},
  methods: {},
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
  font-size: 15px;
}
input::-webkit-inner-spin-button {
  appearance: none;
}
input:focus, select:focus {
  outline: none;
}
.form_input_box {
  width: 100%;
  height: 50px;
  border: 1px solid #dadada;
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
  font-size: 18px;
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