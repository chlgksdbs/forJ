<template>
  <div id="TheBoardDetail">
    <div class="detail_content">
      <p class="detail_content_text">
        안녕하세요~!! 저는 막학기를 앞둔 대학생입니다. 이번에 4학년 1학기를 끝내고 방학기간 동안 그동안 받았던 스트레스를 풀기위해 제주도 힐링 여행을 떠났는데요~ 
        <br><br>
        평소 먹는걸 좋아하기 때문에 이번 여행은 오로지 먹방으로 힐링하는 제주도 맛집만 탐방하는 먹방 코스를 짜봤습니다!!! 유명한 인스타 맛집부터 현지인에게 추천받은 로컬 맛집까지 모아봤으니 잘 봐주세요~!~!~!
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