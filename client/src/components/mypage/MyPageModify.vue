<template>
  <div>
    <h2 class="modifyTitle">회원정보 수정</h2>
    <div class="modifyBox">
      <div>
        <span style="vertical-align: top;">프로필 사진</span>
        <span><img src="@/assets/img/profile.jpg" class="imgmodify"/></span>
        <span><button>변경</button></span>
      </div>
      <div>
        <span>이름</span>
        <span><input type="text" /></span>
        <span><button>수정</button></span>
      </div>
      <div>
        <span>닉네임</span>
        <span><input type="text" /></span>
        <span><button>변경</button></span>
      </div>
      <div>
        <span>아이디</span>
        <span><input type="text" /></span>
        <span><button>변경</button></span>
      </div>
      <div>
        <span>비밀번호</span>
        <span><input type="text" /></span>
        <span><button>변경</button></span>
      </div>
      <div>
        <span>비밀번호 재확인</span>
        <span><input type="text" /></span>
        <span><button>변경</button></span>
      </div>
      <div>
        <span>이메일</span>
        <span><input type="text" /></span>
        <span><button>변경</button></span>
      </div>
      <div>
        <span>휴대전화</span>
        <span><input type="text" /></span>
        <span><button>변경</button></span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyPageModify',
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

<style>
/* .modifyTotalBox{
  width: 70%;
  
} */
.modifyTitle{
  text-align: left;
  margin-top: 0px;
}
.modifyBox{
  display: block;
  border: 1px solid lightgray;
  border-radius: 10px;
}
.modifyBox>div>span:first-child{
  display: inline-block;
  width: 20%;
  text-align: left;
}
.modifyBox>div>span:nth-child(2){
  display: inline-block;
  width: 70%;
  text-align: left;
}
.modifyBox>div>span:nth-child(3){
  display: inline-block;
  width: 10%;
  text-align: right;
  /* float: right; */
}
.imgmodify{
  width: 100px;
  border-radius: 15px;
}
</style>