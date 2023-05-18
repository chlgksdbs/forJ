<template>
  <div id="BoardList">
    <div id="TheSearchBar" align="right">
      <div id="input_search_bar">
          <input type="text" placeholder="검색어를 입력하세요." maxlength="45" />
          <img src="@/assets/img/icon_search.png">
      </div>
    </div>
    <board-list-item></board-list-item>
    <div class="post_area">
        <router-link to="/board/write">글작성</router-link>
    </div>
    <board-page-navigation></board-page-navigation>
  </div>
</template>

<script>
import BoardListItem from "../Board/Item/BoardListItem.vue";
import BoardPageNavigation from "./PageNavigation/BoardPageNavigation.vue";

export default {
  name: 'BoardList',
  components: {
    BoardListItem,
    BoardPageNavigation,
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
a {
    text-decoration: none;
    color: #000;
    border: 1px solid #000;
    border-radius: 10px;
    padding: 10px 12px;
}
a:hover {
  background-color: #000;
  color: #FFF;
}
input {
    position: absolute;
    border: none;
    width: 180px;
    top: 50%;
    left: 50%;
    transform: translate( -50%, -50% );
}
input:focus {
    outline: none;
}
input::placeholder {
    text-align: center;
}
img {
    position: absolute;
    width: 20px;
    cursor: pointer;
    top: 50%;
    right: 5%;
    transform: translate( -50%, -50% );
}
#TheSearchBar {
    margin: 0 50px;
}
#input_search_bar {
    position: relative;
    width: 300px;
    height: 41px;
    border: 1px solid #dadada;
    border-radius: 10px;
}
.post_area {
    margin: 0 65px 10px 0;
    text-align: right;
}
</style>