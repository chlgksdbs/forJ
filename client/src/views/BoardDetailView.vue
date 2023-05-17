<template>
  <div id="BoardDetailView">
    <the-heading></the-heading>
    <div class="detail_header">
      <img src="@/assets/img/detail_logo.jpg" class="detail_logo" />
    </div>
    <div class="detail_title">
      <h1>{{ boardItem.title }}</h1>
    </div>
    <div class="detail_nav">
      <div class="detail_nav_profile_img_text">
        <div class="detail_nav_profile_img_box">
          <img src="@/assets/img/detail_profile.jpg">
        </div>
        <div class="detail_nav_profile_text">
          <div class="detail_nav_profile_nickname">
            {{ boardItem.writer }}
          </div>
          <div class="detail_nav_profile_write_date">
            {{ boardItem.writeDate }}
          </div>
        </div>
      </div>
      <div class="detail_nav_hit">
        조회수 : {{ boardItem.hit }}
      </div>
    </div>
    <the-board-detail :boardItem="boardItem"></the-board-detail>
    <the-footer></the-footer>
  </div>
</template>

<script>
import axios from 'axios';
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
      boardItem: {},
    };
  },
  created() {
    // console.log(this.$route.params.boardId); // 디버깅
    // axios 비동기 통신으로 server에서 detail 가져오기
    axios.get('http://localhost/board/detail/' + this.$route.params.boardId)
      .then((resp) => {
        // console.log(resp.data); // 디버깅
        this.boardItem = resp.data;
      });
  },
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

.detail_nav_profile_img_box {
  width: 100px;
  height: 100px;
  margin: 0px 50px;
  border-radius: 70%;
  overflow: hidden;
}
.detail_nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100px;
}
.detail_nav_profile_img_text{
  display: flex;
  align-items: center;
}
.detail_nav_profile_text {
  color: #888;
}
.detail_nav_hit {
  margin: 0px 150px;
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