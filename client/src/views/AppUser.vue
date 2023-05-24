<template>
  <div id="AppUser">
    <the-heading></the-heading>
    <div class="MyPage">
      <!-- 카테고리를 이루는 왼쪽 화면 -->
      <div class="leftBox">
        <!-- User 프로필 사진 -->
        <img src="@/assets/img/default_profile_img.png" class="profileBox" alt="profile" v-if="imageUrl == 0"/>
        <img :src="imageUrl" class="profileBox" alt="profile" v-else/>
        <!-- 이름 -->
        <div style="color: blue; font-weight: bold;">{{ userInfo.userNickname }}</div>
        <!-- Category List -->
        <div class="MyPgCateBox" align="center">
          <div>
            <h3>회원정보</h3>
            <router-link to="/mypage/modify" class="MyPgcateList" >회원정보 수정</router-link><br/>
            <router-link to="/mypage/cart" class="MyPgcateList" >My 장바구니</router-link><br/>
            <router-link to="/mypage/qna" class="MyPgcateList" >1:1 문의</router-link><br/>
            <router-link to="/mypage/like" class="MyPgcateList" >좋아요</router-link><br/>
          </div>
          <div style="border-top: 1px solid #ddd; margin-top: 10px;">
            <h3>커뮤니티</h3>
            <router-link to="/mypage/myboard" class="MyPgcateList" >내가 쓴 게시글</router-link><br/>
            <router-link to="/mypage/mycomment" class="MyPgcateList" >내가 쓴 댓글</router-link><br/>
          </div>
        </div>
      </div>
      <!-- 각 카테고리별 변경되는 오른쪽 화면 -->
      <router-view class="rightBox"></router-view>
    </div>
    <the-footer></the-footer>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

import TheHeading from '@/components/TheHeading.vue';
import TheFooter from '@/components/TheFooter.vue';

const memberStore = "memberStore";

export default {
  name: 'AppUser',
  components: {TheHeading, TheFooter},
  data() {
    return {
      imageUrl: '',
    };
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
      })
      .catch(() => {
        // console.log(error);
      });
  },
  methods: {
  },
  computed: {
    ...mapState(memberStore, ['userInfo']),
  },
};
</script>

<style scoped>
.MyPage{
  display: flex;
  /* flex-direction: row; */
  /* flex-wrap: wrap; */
  justify-content: center;
  width: 100%;
}
.leftBox{
  width: 220px;
  margin-right: 30px;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  border-radius: 15px;
  padding: 20px 0;
}
.profileBox{
  border-radius: 15px;
  width: 150px;
}
.MyPgCateBox{
  width: 150px;
  padding-bottom: 20px;
  margin: auto;
  margin-top: 25px;
  line-height: 40px;
  border-top: 1px solid #ddd;
}
.MyPgcateList{
  text-decoration-line: none;
  color: black;
}
.rightBox{
  width: 800px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 15px;
}
/* #AppUser{
  background-color: #f8f8f8;
} */
</style>