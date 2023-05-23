<template>
  <div>
    <h2 class="modifyTitle">회원정보 수정</h2>
    <div class="modifyBox">
      <div>
        <span style="vertical-align: top;">프로필 사진</span>
        <span>
          <img :src="profileImg" class="imgmodify"/>
          <input type="file" @change="onInputImage" ref="newProfileImg" />
        </span>
        <span><button @click="modifyImg">변경</button></span>
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
import axios from "axios";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: 'UserModify',
  components: {},
  data() {
    return {
      profileImg: require('@/assets/img/default_profile_img.png'),
      photoFile: '',
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"])
  },
  methods: {
    // TODO: 현재 input type="file"에 있는 이미지 링크를 data에 있는 profileImg 변수에 추가
    onInputImage() {
      this.photoFile = this.$refs.newProfileImg.files[0];

      // URL.createObjectURL로 사용자가 올린 이미지를 URL로 만들어서 화면에 표시할 수 있게 함
      // img 태그의 src값에 바인딩 해준다.
      this.profileImg = URL.createObjectURL(this.photoFile);
      // console.log(this.photoFile); // 디버깅
    },
    // TODO: 프로필 이미지 변경 메서드 구현
    modifyImg() {
      // 파일 업로드를 위한 FormData 객체 생성 (Key, Value)
      let formData = new FormData();
      formData.append("profileImg", this.photoFile);
      formData.append("userId", this.userInfo.userId); // 사용자 ID 첨부

      // console.log(formData); // 디버깅

      // formData와 headers부분의 content-type을 보냄
      axios.put('http://localhost/user/profileImg', formData, {
          headers: {
            'Content-Type': 'multipart/form-data; charset=UTF-8'
          },
        })
        .then(() => {
          // TODO: 프로필 이미지 변경 후, 이미지를 띄우기
          axios.get('http://localhost/user/profileImg/' + this.userInfo.userId)
            .then((resp) => {
              console.log(resp); // 디버깅 -> 글자 깨짐 현상 발생
              // Blob 데이터로 이미지 URL 생성 후 표시하기
              // (1) https://aljjabaegi.tistory.com/443
              // (2) https://kyounghwan01.github.io/blog/JS/JSbasic/Blob-url/

              // https://okky.kr/articles/245037 -> 이걸로 도전...

              let blob = new Blob([new Uint8Array(resp.data)], { type: 'image/jpg' });

              const imageURL = window.URL.createObjectURL(blob);
              this.profileImg = imageURL;
            });
          // TODO: 프로필 이미지 업로드 시, .jpg와 .png만 가능하도록 설정하고, 크기 제한 걸기
        });
      // .then((resp) => {
      //   console.log(resp); // 백엔드와 디버깅
      // });
    }
  },
};
</script>

<style>
.modifyTitle{
  text-align: left;
  margin-top: 0px;
}
.modifyBox{
  display: block;
  width: 800px;
  height: 555px;
  border: 1px solid rgb(255, 255, 255);
  border-radius: 10px;
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
  outline: 1px solid #40A3FF;
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
  background-color: #FFFFFF;
  color: #40A3FF;
  border: 1px solid #40A3FF;
  border-radius: 5px;
  cursor: pointer;
}
.modifyBox>div>span:nth-child(3)>button:hover{
  border: 1px solid #FFFFFF;
  color: #FFFFFF;
  background-color: #40A3FF;
}
.imgmodify{
  width: 100px;
  border-radius: 15px;
}
</style>
