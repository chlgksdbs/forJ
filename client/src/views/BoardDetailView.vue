<template>
  <div id="BoardDetailView">
    <the-heading></the-heading>
    <div class="detail_header">
      <img src="@/assets/img/detail_logo.jpg" class="detail_logo" />
    </div>
    <div class="detail_title">
      <h1>2박 3일 먹방으로 힐링하는 제주도 여행 일정 공유</h1>
    </div>
    <div class="detail_nav">
      <div class="detail_nav_profile_img">
        <div class="detail_nav_profile_img_box">
          <img src="@/assets/img/detail_profile.jpg">
        </div>
      </div>
      <div class="detail_nav_profile_text">
        <div class="detail_nav_profile_nickname">
          먹깨비
        </div>
        <div class="detail_nav_profile_write_date">
          2023-05-04 18:05:12
        </div>
      </div>
      <div class="detail_nav_hit">
        조회수 : 138
      </div>
    </div>
    <the-board-detail></the-board-detail>
    <the-footer></the-footer>
  </div>
</template>

<script>
import TheHeading from "@/components/Header/TheHeading.vue";
import TheBoardDetail from "@/components/BoardDetail/TheBoardDetail.vue";
import TheFooter from "@/components/Footer/TheFooter.vue";

export default {
  name: 'BoardDetailView',
  components: {
    TheHeading,
    TheFooter,
    TheBoardDetail,
  },
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
.detail_logo {
  width: 100%;
}
.detail_title {
  margin: 40px 0;
}
.detail_nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100px;
}
.detail_nav_hit {
  width: 30%;
  color: #888;
}
.detail_nav_profile_img {
  width: 10%;
  margin-left: 100px;
}
.detail_nav_profile_img_box {
  width: 100px;
  height: 100px;
  border-radius: 70%;
  overflow: hidden;
}
.detail_nav_profile_text {
  width: 40%;
  color: #888;
}
.detail_nav_profile_img_box img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.detail_nav_profile_nickname {
  text-align: left;
}
.detail_nav_profile_write_date {
  text-align: left;
}
</style>