<template>
  <div>
    <h2 class="modifyTitle">회원정보 수정</h2>
    <div class="modifyBox">
      <div>
        <span style="vertical-align: top;">프로필 사진</span>
        <span>
          <img :src="imageUrl" class="imgmodify"/>
          <input type="file" @change="onInputImage" ref="newProfileImg" />
        </span>
        <span><button @click="modifyImg">변경</button></span>
      </div>
      <div>
        <span>이름</span>
        <span><input type="text" v-model="userInfo.userName" readonly /></span>
        <span></span>
      </div>
      <div>
        <span>닉네임</span>
        <span><input type="text" v-model="userInfo.userNickname" /></span>
        <span><button @click="modifyNickname">변경</button></span>
      </div>
      <div>
        <span>아이디</span>
        <span><input type="text" v-model="userInfo.userId" readonly /></span>
        <span></span>
      </div>
      <div>
        <span>비밀번호</span>
        <span><input type="password" v-model="userPw" /></span>
        <span></span>
      </div>
      <div>
        <span>비밀번호 재확인</span>
        <span><input type="password" v-model="userPwCheck" /></span>
        <span><button @click="checkPw">확인</button></span>
      </div>
      <!-- TODO: 비밀번호 확인 버튼 클릭 시, 아래 숨겨진 div display (새로운 비밀번호 입력 칸)-->
      <div class="password_wrap" style="display: none;">
        <span>새로운 비밀번호</span>
        <span><input type="password" v-model="newPw" /></span>
        <span><button @click="modifyPw">변경</button></span>
      </div>
      <div>
        <span>이메일</span>
        <span><input type="text" v-model="userInfo.userEmail" /></span>
        <span><button @click="checkEmail">변경</button></span>
      </div>
      <div class="email_wrap" style="display: none;">
        <span>이메일 인증번호</span>
        <span><input type="text" v-model="certNo" /></span>
        <span><button @click="modifyEmail">변경</button></span>
      </div>
      <div>
        <span>휴대전화</span>
        <span><input type="text" v-model="userInfo.userPhone" readonly /></span>
        <span></span>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: 'UserModify',
  components: {},
  data() {
    return {
      photoFile: '',
      imageUrl: require('@/assets/img/default_profile_img.png'),
      userPw: '',
      userPwCheck: '',
      newPw: '',
      certNo: '',
      secretCertNumber: '',
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"])
  },
  created() {
    // TODO: 프로필 이미지 변경 후, 이미지를 띄우기
    axios.get('http://localhost/user/profileimg/' + this.userInfo.userId, { responseType: 'blob'})
      .then((resp) => {
        // console.log(resp); // 디버깅 -> 글자 깨짐 현상 발생

        const reader = new FileReader();
        reader.onloadend = () => {
          this.imageUrl = reader.result;
        };
        reader.readAsDataURL(resp.data);
        // Blob 데이터로 이미지 URL 생성 후 표시하기
        // (1) https://aljjabaegi.tistory.com/443
        // (2) https://kyounghwan01.github.io/blog/JS/JSbasic/Blob-url/

        // https://okky.kr/articles/245037 -> 이걸로 도전...

        // let blob = new Blob([new Uint8Array(resp.data)], { type: 'image/jpg' });

        // const imageURL = window.URL.createObjectURL(blob);
        // this.profileImg = imageURL;
      })
      .catch(() => {
        // console.log(error);
      });
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // TODO: 현재 input type="file"에 있는 이미지 링크를 data에 있는 profileImg 변수에 추가
    onInputImage() {
      this.photoFile = this.$refs.newProfileImg.files[0];

      // URL.createObjectURL로 사용자가 올린 이미지를 URL로 만들어서 화면에 표시할 수 있게 함
      // img 태그의 src값에 바인딩 해준다.
      this.imageUrl = URL.createObjectURL(this.photoFile);
      // console.log(this.photoFile); // 디버깅
    },
    // TODO: 프로필 이미지 변경 메서드 구현
    modifyImg() {
      if (confirm('프로필 사진을 변경하시겠습니까?')) {
        // 파일 업로드를 위한 FormData 객체 생성 (Key, Value)
        let formData = new FormData();
        formData.append("profileImg", this.photoFile);
        formData.append("userId", this.userInfo.userId); // 사용자 ID 첨부
  
        // console.log(formData); // 디버깅
  
        // formData와 headers부분의 content-type을 보냄
        axios.put('http://localhost/user/profileimg', formData, {
            headers: {
              'Content-Type': 'multipart/form-data; charset=UTF-8'
            },
          })
          .then(() => {
            this.$router.go(0); // 현재 페이지 새로 고침
            // TODO: 프로필 이미지 업로드 시, .jpg와 .png만 가능하도록 설정하고, 크기 제한 걸기
          });
        // .then((resp) => {
        //   console.log(resp); // 백엔드와 디버깅
        // });
      }
    },
    // TODO: 닉네임 변경 메서드 구현
    modifyNickname() {

      if (this.userInfo.userNickname.length == 0) {
        alert('변경할 이메일을 입력해주세요.');
      }
      else {
        if (confirm('닉네임을 (' + this.userInfo.userNickname + ')으로 변경하시겠습니까?'))
        axios.put('http://localhost/user/modify/nickname', this.userInfo)
          .then(() => {
            alert('닉네임이 변경완료되었습니다.');
          });
        }
      },
    // TODO: 현재 비밀번호와 일치하는지 확인하는 메서드 구현
    checkPw() {
      if (this.userPw == this.userPwCheck) {

        let userDto = {
          "userId": this.userInfo.userId,
          "userPw": this.userPw,
        }

        // console.log(userDto); // 디버깅

        axios.post('http://localhost/user/check/pw', userDto)
          .then((resp) => {
            if (resp.data == "success") {
              document.querySelector('.password_wrap').style.display = "";
            }
            else {
              alert('기존 비밀번호와 일치하지 않습니다.');
            }
          });
      }
      else {
        alert('비밀번호와 비밀번호 재확인 칸이 일치하지 않습니다.');
      }
    },
    // TODO: 비밀번호 변경 메서드 구현
    modifyPw() {
      if (this.newPw.length == 0) {
        alert('변경할 비밀번호를 입력해주세요.');
      }
      else {

        let userDto = {
          "userId": this.userInfo.userId,
          "userPw": this.newPw,
        }

        axios.put('http://localhost/user/modify/pw', userDto)
          .then(() => {
            alert('비밀번호가 변경되었습니다. 다시 로그인해주세요.');

            this.userLogout(this.userInfo.userId);

            sessionStorage.removeItem("access-token"); // 저장된 access token 삭제
            sessionStorage.removeItem("refresh-token"); // 저장된 refresh token 삭제

            // 로그인 페이지로 이동
            if (this.$route.path != "/login") this.$router.push("/login");
          });
      }
    },
    // TODO: 새로운 이메일에 대한 인증 메서드 구현
    checkEmail() {
      if (this.userInfo.userEmail.length == 0) {
        alert('변경할 이메일을 입력해주세요.');
      }
      else {
        if (confirm('이메일을 (' + this.userInfo.userEmail + ')으로 변경하시겠습니까?')) {
          // JSON 형태로 전송
          let userDto = {
            "userEmail": this.userInfo.userEmail,
          };

          axios.post('http://localhost/user/certmail', userDto)
            .then((resp) => {
              alert('인증번호가 전송되었습니다.');
              this.secretCertNumber = resp.data;
            });

          document.querySelector('.email_wrap').style.display = "";
        }
      }
    },
    // TODO: 이메일 변경 메서드 구현
    modifyEmail() {
          if (this.certNo == this.secretCertNumber) {
            axios.put('http://localhost/user/modify/email', this.userInfo)
              .then(() => {
                alert('이메일이 변경완료되었습니다.');
              })
              .catch(() => {
                alert("이미 존재하는 이메일입니다!");
              });
          }
          else {
            alert('인증번호가 일치하지 않습니다.');
          }
      }
  },
};
</script>

<style>
.modifyTitle{
  margin: 20px 0;
}
.modifyBox{
  display: block;
  width: 800px;
  height: 650px;
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
