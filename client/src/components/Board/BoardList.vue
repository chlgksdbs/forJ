<template>
  <div id="BoardList">
    <div id="TheSearchBar" align="right">
      <div id="input_search_bar">
          <input type="text" v-model="searchText" placeholder="검색어를 입력하세요." @keyup.enter="searchBoard" maxlength="45" />
          <img src="@/assets/img/icon_search.png" class="search_bar" @click="searchBoard">
      </div>
    </div>
    <div id="BoardListItem">
        <div align="center">
            <table id="board_table" frame="void">
                <tr id="board_table_top"></tr>
                <tr class="board_table_line">
                    <th id="table_head_board_id">No</th>
                    <th id="table_head_title">제목</th>
                    <th id="table_head_writer">작성자</th>
                    <th id="table_head_write_date">작성일시</th>
                    <th id="table_head_hit">조회수</th>
                </tr>
                <tr class="board_table_line board_notice" v-for="(notice, idx) in notices" :key="idx">
                    <td><img src="@/assets/img/icon_flag.png" class="icon_flag" /></td>
                    <td><router-link :to="'noticeview/' + notice.noticeId">{{ notice.noticeTitle }}</router-link></td>
                    <td>{{ notice.noticeWriter }}</td>
                    <td>{{ dateFormat(notice.noticeDate) }}</td>
                    <td>{{ notice.noticeHit }}</td>
                </tr>
                <tr class="board_table_line board_list" v-for="(item, idx) in items" :key="3 + idx">
                    <td>{{ item.boardId }}</td>
                    <td><router-link :to="'/board/view/' + item.boardId">{{ item.title }}</router-link></td>
                    <td>{{ item.writer }}</td>
                    <td>{{ dateFormat(item.writeDate) }}</td>
                    <td>{{ item.hit }}</td>
                </tr>
                <tr class="board_table_line"></tr>
            </table>
        </div>
    </div>
    <div class="post_area">
        <router-link to="/board/write" class="write_btn">글작성</router-link>
    </div>
    <div id="BoardPageNavigation">
      <ul>
        <li v-if="currentPage > 1">
          <router-link @click.native="prePage" to="#" class="page_pre_btn">이전</router-link>
        </li>
        <li v-for="page in totalPages" :key="page">
          <router-link @click.native="somePage(page)" to="#" class="page_no" :class="{ active: currentPage == page}">{{ page }}</router-link>
        </li>
        <li v-if="currentPage < totalPages">
          <router-link @click.native="nextPage" to="#" class="page_next_btn">다음</router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'BoardList',
  components: {
  },
  data() {
    return {
      searchText: '',
      searchItems: [],
      currentPage: 1,
      totalPages: 0,
      items: [],
      notices: [],
    };
  },
  created() {
    this.$emit("setTitle", "자유 게시판");
    // axios 비동기 통신으로 server에서 list 가져오기
    axios.get('http://localhost/board/list/' + this.currentPage)
      .then((resp) => {
          // console.log(resp); // 디버깅
        this.items = resp.data.boardList;
        this.currentPage = resp.data.startRange;
        this.totalPages = resp.data.totalPage;
      });
    // axios 비동기 통신으로 server에서 notice list 가져오기
    axios.get('http://localhost/board/notice/list')
      .then((resp) => {
          this.notices = resp.data;
      });
  },
  watch: {
    searchItems(arr) {
      if (arr != null) {
        this.items = arr;
        // console.log(this.items); // 디버깅
      }
      else {
        axios.get('http://localhost/board/list/1')
        .then((resp) => {
            // console.log(resp); // 디버깅
            this.items = resp.data.boardList;
        });
      }
    },
    currentPage() {
       // axios 비동기 통신으로 server에서 list 가져오기
      axios.get('http://localhost/board/list/' + this.currentPage)
      .then((resp) => {
          // console.log(resp); // 디버깅
        this.items = resp.data.boardList;
        this.currentpage = resp.data.startRange;
        this.totalPages = resp.data.totalPage;
      });
    }
  },
  methods: {
    // TODO: 자유 게시판(여행 계획 공유 게시판) 검색바 이벤트 메서드
    searchBoard() {
      if (this.searchText.length != 0) {
        axios.get('http://localhost/board/search/' + this.searchText)
          .then((resp) => {
            this.searchItems = resp.data;
          });
      }
      else {
        this.searchItems = null;
      }
    },
    // 날짜 포맷을 변경하는 메서드 (시, 분, 초 제외)
    dateFormat(date) {
      let dateFormat = date.substring(0, 10);
      return dateFormat;
    },
    // TODO: 이전 버튼을 눌렀을 때 페이지 이동
    prePage() {
      this.currentPage = this.currentPage - 1;
      this.$router.push("/board/list/" + this.currentPage);
    },
    // TODO: 페이지 번호를 눌렀을 때 페이지 이동
    somePage(page) {
      this.currentPage = page;
      this.$router.push("/board/list/" + this.currentPage);
      // console.log(this.currentPage);
    },
    // TODO: 다음 버튼을 눌렀을 때 페이지 이동
    nextPage() {
      this.currentPage = this.currentPage + 1;
      this.$router.push("/board/list/" + this.currentPage);
    },
  },
};
</script>

<style scoped>
ul {
  list-style: none;
}
ul li {
  display: inline-block;
}
ul li a {
  display: block;
  margin: 0 6px;
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
th {
    text-align: center;
    padding: 20px;
}
td {
    text-align: center;
    padding: 10px;
}
.search_bar {
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
.icon_flag {
  width: 24px;
}
.board_table_line {
    border-bottom: 1px solid #dadada;
}
.board_notice {
    background-color: #E9E9E9;
}
.board_list a {
    text-decoration: none;
    color: #A1A1A1;
}
.board_notice a {
    color: #000;
}
.write_btn {
    text-decoration: none;
    color: #222;
    border: 1px solid #000;
    border-radius: 10px;
    padding: 10px 12px;
}
.write_btn:hover {
  background-color: #000;
  color: #FFF;
}
.page_no {
  width: 24px;
  height: 24px;
  line-height: 24px;
  text-decoration: none;
  cursor: pointer;
  font-size: 14px;
  color: #222;
}
.active {
  border: 1px solid #f0f0f0;
}
.page_pre_btn {
  text-decoration: none;
  cursor: pointer;
  font-size: 14px;
  color: #222;
}
.page_pre_btn::before {
  content: "";
  display: block;
  float: left;
  margin: 5px 2px 0 0;
  transform: rotate(-45deg);
  width: 7px;
  height: 7px;
  border-top: 1px solid #828282;
  border-left: 1px solid #828282;
}
.page_next_btn {
  text-decoration: none;
  cursor: pointer;
  font-size: 14px;
  color: #222;
}
.page_next_btn::after {
  content: "";
  display: block;
  float: right;
  margin: 5px 0 0 2px;
  transform: rotate(135deg);
  width: 7px;
  height: 7px;
  border-top: 1px solid #828282;
  border-left: 1px solid #828282;
  text-decoration: none;
  cursor: pointer;
  font-size: 14px;
  color: #222;
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
#BoardListItem {
    margin: 20px 50px;
}
#board_table {
    width: 100%;
    border-collapse: collapse;
}
#board_table_top {
    border-bottom: 1px solid #000000;
}
#table_head_board_id {
    width: 13%;
}
#table_head_title {
    width: 50%;
}
#table_head_writer {
    width: 12%;
}
#table_head_write_date {
    width: 15%;
}
#table_head_hit {
    width: 10%;
}
</style>