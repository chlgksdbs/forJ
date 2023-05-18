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
  name: 'UserModify',
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
.modifyTitle{
  text-align: left;
  margin-top: 0px;
}
.modifyBox{
  display: block;
  border: 1px solid rgb(255, 255, 255);
  border-radius: 10px;
  width: 800px;
  height: 555px;
  background-color: #fff;
}
.modifyBox>div{
  margin: 30px 0px;
}
.modifyBox>div>span:first-child{
  display: inline-block;
  width: 20%;
  margin-left:5px;
  text-align: left;
}
.modifyBox>div>span:nth-child(2){
  display: inline-block;
  width: 55%;
  text-align: left;
}
.modifyBox>div>span:nth-child(2)>input{
  width: 90%;
  height: 25px;
  border: none;
  border-bottom: 1px solid #B8B8B8;
  border-radius: 3px;
  background-color: transparent;
}
.modifyBox>div>span:nth-child(2)>input:focus{
  outline: 1px solid #0085FF;
}
.modifyBox>div>span:nth-child(3){
  display: inline-block;
  width: 20%;
  text-align: right;
}
.modifyBox>div>span:nth-child(3)>button{
  margin: 0px 30px;
  width: 50px;
  height: 25px;
  background-color: white;
  border: 1px solid #0085FF;
  border-radius: 5px;
}
.modifyBox>div>span:nth-child(3)>button:hover{
  border: 1px solid #90dfff;
  background-color: #90dfff;
}
.imgmodify{
  width: 100px;
  border-radius: 15px;
}
</style>
