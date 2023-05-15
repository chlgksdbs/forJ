<template>
  <div class="totalMyPage">
    <the-heading></the-heading>
    <div class="MyPage">
      <!-- 카테고리를 이루는 왼쪽 화면 -->
    <div class="leftBox">
      <!-- User 프로필 사진 -->
      <img src="@/assets/img/profile.jpg" class="profileBox" alt="profile"/>
      <!-- 이름 -->
      <div>조민트</div>
      <!-- Category List -->
      <div class="MyPgCateBox" align="center">
        <div>
          <h3>회원정보</h3>
          <router-link to="/modify" class="MyPgcateList" >회원정보 수정</router-link><br/>
          <!-- <a href="#" class="MyPgcateList">회원정보 수정</a><br/> -->
          <a href="#" class="MyPgcateList">My 장바구니</a><br/>
          <a href="#" class="MyPgcateList">1:1 문의</a><br/>
          <a href="#" class="MyPgcateList">좋아요</a><br/>
        </div>
        <div>
          <h3>커뮤니티</h3>
          <a href="#" class="MyPgcateList">내가 쓴 게시글</a><br/>
          <a href="#" class="MyPgcateList">내가 쓴 댓글</a>
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
import TheHeading from '@/components/Header/TheHeading.vue';
import TheFooter from '@/components/Footer/TheFooter.vue';

export default {
  name: 'MyPageView',
  components: {TheHeading, TheFooter},
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
.totalMyPage{
  background-color: #f8f8f8;
}
.MyPage{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
}
.leftBox{
  float: left;
  margin-right: 30px;
  margin-bottom: 10px;
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
  border: 1px solid rgb(255, 255, 255);
  background-color: #fff;
  border-radius: 10px;
}
.MyPgcateList{
  text-decoration-line: none;
  color: black;
}
.rightBox{
  width: 45%;
}
</style>