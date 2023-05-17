<template>
  <div id="TheBoardDetail">
    <div class="detail_content">
      <p class="detail_content_text">
        {{ boardItem.content }}
      </p>
      <div class="detail_content_img_box">
        <h2>흑돼지 맛집 숙성도</h2>
        <img src="@/assets/img/detail_place.jpg">
      </div>
      <p class="detail_content_text">
        첫번째 맛집은 숙성도입니다!!!
        <br>
        숙성도는 제 친구의 추천으로 방문하게 되었는데요~ 생각했던 것보다 더 고기가 맛있...
      </p>
    </div>
    <div class="detail_route">
      <div class="detail_route_img_box">
        <h2>여행 경로 보기</h2>
        <img src="@/assets/img/detail_map.png">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TheBoardDetail',
  components: {},
  data() {
    return {
      message: '',
    };
  },
  props: {
    boardItem: Object,
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
#TheBoardDetail {
  margin: 50px 150px;
}
.detail_content_text {
  text-align: left;
}
.detail_content_img_box, .detail_route_img_box {
  margin: 40px 0;
}
.detail_content_img_box h2, .detail_route_img_box h2 {
  text-align: left;
  color: #0085ff;
}
.detail_content_img_box img, .detail_route_img_box img {
  width: 100%;
}
</style>