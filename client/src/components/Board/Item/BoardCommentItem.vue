<template>
  <div id="BoardCommentItem">
    <!-- 댓글 목록 div -->
    <div class="detail_comment_content_box">
      <div class="comment_profile_img_box">
        <img src="@/assets/img/profile.jpg">
      </div>
      <div class="comment_content_box">
        <div class="comment_content_header">
          <div>단대복학생</div>
          <div>2023-05-04 18:05:12</div>
        </div>
        <div class="comment_content_body">
          <p>우와 너무 좋은 코스인 것 같아요!! 여자친구랑 가도 좋을 것 같네요~ㅎㅎ</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BoardCommentItem',
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
.detail_comment_content_box {
  display: flex;
  justify-content: left;
}
.comment_profile_img_box {
  width: 55px;
  height: 55px;
  margin: auto 20px;
  border-radius: 70%;
  overflow: hidden;
}
.comment_profile_img_box img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.comment_content_header {
  display: flex;
  justify-content: left;
}
.comment_content_header > div:nth-child(1) {
  width: auto;
  height: 30px;
  line-height: 30px;
  margin-right: 10px;
  font-weight: bold;
  font-size: 17px;
}
.comment_content_header > div:nth-child(2) {
  width: 150px;
  height: 30px;
  line-height: 30px;
}
.comment_content_body p {
  margin: 5px 0;
}
#BoardCommentItem {
  margin: 20px 150px;
}
</style>