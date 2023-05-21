<template>
  <div id="BoardList">
    <div id="TheSearchBar" align="right">
      <div id="input_search_bar">
          <input type="text" placeholder="검색어를 입력하세요." @keyup.enter="searchShareBoard" maxlength="45" />
          <img src="@/assets/img/icon_search.png" @click="searchShareBoard">
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
  created() {
    this.$emit("setTitle", "자유 게시판");
  },
  methods: {
    // TODO: 자유 게시판(여행 계획 공유 게시판) 검색바 이벤트 메서드
    searchShareBoard() {},
  },
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
    width: 160px;
    top: 50%;
    left: 45%;
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
.post_area {
    margin: 0 65px 10px 0;
    text-align: right;
}
#BoardList {
  margin: 0 100px;
}
#TheSearchBar {
    margin: 0 50px;
}
#input_search_bar {
    position: relative;
    width: 250px;
    height: 41px;
    border: 1px solid #dadada;
    border-radius: 10px;
}
</style>