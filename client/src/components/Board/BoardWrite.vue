<template>
  <div id="BoardWrite">
    <div id="board_table_box">
      <table id="board_write_table">
        <tr class="board_table_line">
          <td><b>제목</b></td>
          <td><input type="text" id="title" v-model="title" maxlength="45" placeholder="제목을 입력해 주세요." /></td>
        </tr>
        <tr class="board_table_line">
          <td><b>작성자</b></td>
          <td>{{ writer }}</td>
        </tr>
        <tr class="board_table_line">
          <td><b>작성일시</b></td>
          <td>{{ dateFormat(writeDate) }}</td>
        </tr>
        <tr class="board_table_line">
          <td><b>첨부파일</b></td>
          <td><input type="file" class="file_btn"></td>
        </tr>
      </table>
    </div>
    <div id="editor_box">
      <editor
      ref="toastuiEditor"
      height="500px"
      initialEditType="markdown"
      />
    </div>
    <div id="btn_box">
      <button @click="writeBtn" class="write_btn">작성완료</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import "@toast-ui/editor/dist/toastui-editor.css";
import { mapState } from "vuex";

import { Editor } from "@toast-ui/vue-editor";

const memberStore = "memberStore";

export default {
  name: 'BoardWrite',
  components: {
    Editor,
  },
  data() {
    return {
      title: '',
      writer: '',
      writeDate: '',
      content: '',
    };
  },
  created() {
    this.writeDate = new Date();
    this.writer = this.userInfo.userId;
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    writeBtn() {
      // content를 markdown 형태로 저장하는 action
      this.content = this.$refs.toastuiEditor.invoke("getMarkdown");
      // console.log(content); // 디버깅

      // content를 html 형태로 저장하는 action
      // var content = this.$refs.toastuiEditor.invoke("getHtml");

      let boardDto = {
        'title': this.title,
        'writer': this.writer,
        'content': this.content,
      };
      axios.post('http://localhost/board/write', boardDto)
        .then(() => {
          // console.log(resp.data);
          this.$router.push("/board");
        });
    },
    // 날짜 포맷을 변경하는 메서드
    dateFormat(date) {
      let dateFormat = date.getFullYear()
        + '-' + ((date.getMonth() + 1) < 9 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1))
        + '-' + ((date.getDate()) < 9 ? "0" + (date.getDate()) : (date.getDate()))
        + ', ' + ((date.getHours()) < 9 ? "0" + (date.getHours()) : (date.getHours()))
        + ':' + ((date.getMinutes()) < 9 ? "0" + (date.getMinutes()) : (date.getMinutes()))
        + ':' + ((date.getSeconds()) < 9 ? "0" + (date.getSeconds()) : (date.getSeconds()));

      return dateFormat;
    },
  },
};
</script>

<style scoped>
td {
  padding: 5px;
}
.board_table_line {
  border-bottom: 1px solid #dadada;
}
.write_btn {
  border: 1px solid #000;
  background-color: #FFF;
  color: #000;
  padding: 5px;
  cursor: pointer;
}
.write_btn:hover {
  background-color: #000;
  color: #FFF;
}
#board_table_box {
  display: flex;
  justify-content: center;
  margin: 10px auto;
  padding: 20px;
}
#board_write_table {
  width: 1060px;
  border-collapse: collapse;
}
#board_write_table > tr > td:nth-child(1) {
  width: 15%;
  text-align: left;
}
#board_write_table > tr > td:nth-child(2) {
  width: 70%;
  text-align: left;
}
#title {
  width: 45%;
  height: 32px;
  outline: none;
  border: none;
}
#editor_box {
  max-width: 1060px;
  margin: 0px auto;
  padding: 0px 20px;
  height: 550px;
}
#btn_box {
  display: flex;
  margin: 0px auto;
  padding: 0px 20px;
  width: 1060px;
}
</style>