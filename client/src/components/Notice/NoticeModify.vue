<template>
  <div id="NoticeModify">
    <div id="board_table_box">
      <table id="board_write_table">
        <tr class="board_table_line">
          <td><b>제목</b></td>
          <td><input type="text" id="title" maxlength="45" v-model="noticeView.noticeTitle" placeholder="제목을 입력해 주세요." /></td>
        </tr>
        <tr class="board_table_line">
          <td><b>작성자</b></td>
          <td>{{ noticeView.noticeWriter }}</td>
        </tr>
        <tr class="board_table_line">
          <td><b>작성일시</b></td>
          <td>{{ noticeView.noticeDate }}</td>
        </tr>
        <tr class="board_table_line">
          <td><b>첨부파일</b></td>
          <td><input type="file" class="file_btn"></td>
        </tr>
        <tr class="board_table_line">
          <td><b>내용<br>(ctrl+a)</b></td>
          <td>
            <div class="board_content_box">
              <textarea :value="noticeView.noticeContent">
              </textarea>
            </div>
          </td>
        </tr>
      </table>
    </div>
    <div id="editor_box">
      <editor
      ref="toastuiEditor"
      height="500px"
      initialEditType="markdown"
      :initialValue="noticeView.noticeContent"
      />
    </div>
    <div id="btn_box">
      <button @click="modifyBtn" class="write_btn">작성완료</button>
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
  name: 'NoticeModify',
  components: {
    Editor,
  },
  data() {
    return {
      noticeView: [],
    };
  },
  created() {
    axios.get('http://localhost/notice/detail/' + this.$route.params.noticeId)
      .then((resp) => {
        this.noticeView = resp.data;
        // console.log(resp.data); // 디버깅 
      });
  },
  mounted() {
    this.$refs.toastuiEditor.invoke("setMarkdown", this.noticeView.noticeContent);
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    // TODO: 공지사항 수정 백엔드와 연동
    modifyBtn() {
      // content를 markdown 형태로 저장하는 action
      this.noticeView.noticeContent = this.$refs.toastuiEditor.invoke("getMarkdown");

      // content를 html 형태로 저장하는 action
      // var content = this.$refs.toastuiEditor.invoke("getHtml");

      axios.put('http://localhost/notice/modify', this.noticeView)
        .then(() => {
          // console.log(resp.data);
          alert('게시글 수정이 완료되었습니다.');
          this.$router.push("/board/notice");
        });
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
.board_content_box {
  width: 90%;
  height: 100px;
  border: 1px solid #000;
  padding: 10px;
}
.board_content_box textarea {
  width: 100%;
  height: 100%;
  border: none;
}
.board_content_box textarea:focus {
  outline: none;
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