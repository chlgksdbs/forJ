<template>
  <div class="mypageBox">
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
</template>

<script>
export default {
  name: 'MyPageView',
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
.mypageBox{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
}
/* .mypage{
  background-color: lightgray;
} */
.leftBox{
  float: left;
  /* width: 20%; */
  /* height: 80%; */
  margin-right: 30px;
  /* margin-left: 30px; */
}
.profileBox{
  border-radius: 15px;
  width: 150px;
}
.MyPgCateBox{
  width: 150px;
  padding-bottom: 20px;
  /* padding: 0px 3px 20px; */
  /* margin: 10px 0px; */
  margin: auto;
  line-height: 30px;
  border: 1px solid lightgrey;
  background-color: #fff;
  border-radius: 10px;
}
.MyPgcateList{
  text-decoration-line: none;
  color: black;
}
.rightBox{
  width: 70%;
}
</style>