<template>
  <div id="TheBoardDetail">
    <div class="detail_content">
      <p class="detail_content_text">
        <viewer v-if="boardItem.content != null" :initialValue="boardItem.content"/>
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
import "@toast-ui/editor/dist/toastui-editor.css";
import { Viewer } from "@toast-ui/vue-editor";

export default {
  name: 'TheBoardDetail',
  components: {
    Viewer,
  },
  data() {
    return {};
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