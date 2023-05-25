<template>
  <div>
    <h2 class="modifyTitle" style="font-family: LINESeedKR-Bd">내가 쓴 댓글</h2>
    <div class="modifyBox">
      <div align="center" style="overflow: scroll; overflow-x:hidden; height: 90%;">
        <table id="board_table" frame="void">
            <tr id="board_table_top"></tr>
            <tr class="board_table_line">
              <th id="table_head_board_id">No</th>
              <th id="table_head_title">제목</th>
              <th id="table_head_content">내용</th>
              <th id="table_head_write_date">작성일시</th>
            </tr>
            <tr class="board_table_line" v-for="(item, idx) in myCommentList" :key="3 + idx">
                <td>{{ item.boardId }}</td>
                <td><router-link :to="'/board/view/' + item.boardId">{{ item.boardTitle }}</router-link></td>
                <td>{{ item.content }}</td>
                <td>{{ dateFormat(item.writeDate) }}</td>
            </tr>
            <tr class="board_table_line"></tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  components: {},
  data() {
    return {
      myCommentList: [],
    };
  },
  created() { },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    // 날짜 포맷을 변경하는 메서드 (시, 분, 초 제외)
    dateFormat(date) {
      let dateFormat = date.substring(0, 10);
      return dateFormat;
    },
  },
  mounted() {
    axios.get('http://localhost/comment/mylist/' + this.userInfo.userId)
      .then((resp) => {
        this.myCommentList = resp.data;
        // console.log(resp.data); // 디버깅
      });
  },
};
</script>

<style scoped>
.modifyBox {
  margin: 20px 0;
  height: 90%;
}
a {
    text-decoration: none;
    color: #A1A1A1;
}
th {
    text-align: center;
    padding: 5px;
}
td {
    text-align: center;
    padding: 5px;
}
.modifyBox {
    margin: 20px 0px;
}
.board_table_line {
    border-bottom: 1px solid #dadada;
}
#board_table {
    width: 80%;
    border-collapse: collapse;
}
#board_table_top {
    border-bottom: 1px solid #000000;
}
#table_head_board_id {
    width: 15%;
}
#table_head_title {
    width: 20%;
}
#table_head_content {
    width: 50%;
}
#table_head_write_date {
    width: 15%;
}
</style>