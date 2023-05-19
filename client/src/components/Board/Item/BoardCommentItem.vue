<template>
  <div id="BoardCommentItem">
    <!-- 댓글 목록 div -->
    <div class="detail_comment_content_box">
      <div class="comment_profile_img_box">
        <img src="@/assets/img/profile.jpg">
      </div>
      <div class="comment_content_box">
        <div class="comment_content_header">
          <div>{{ comment.userId }}</div>
          <div>{{ comment.writeDate }}</div>
          <button id="modifyBtn" @click="commentModify">수정</button>
          <button id="deleteBtn" @click="commentDelete">삭제</button>
        </div>
        <div class="comment_content_body">
          <p :class="'static_content' + comment.commentId" style="">{{ comment.content }}</p>
          <div :class="'input_content_box' + comment.commentId" style="display: none;">
            <input type="text" class="input_content" v-model="comment.content" @keyup.enter="commentModifyApply" />
            <button id="modifyApplyBtn" @click="commentModifyApply">변경</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'BoardCommentItem',
  components: {},
  data() {
    return {
      comment: {
        "commentId": '',
        "boardId": '',
        "userId": '',
        "content": '',
        "writeDate": ''
      },
    };
  },
  props: {
    commentItem: Object,
  },
  created() {
    this.comment = this.commentItem;
  },
  methods: {
    // 댓글 수정 모드 메서드
    commentModify() {
      // 댓글 수정 모드로 변경
      document.querySelector('.static_content' + this.comment.commentId).style.display = "none";
      document.querySelector('.input_content_box' + this.comment.commentId).style.display = "";
    },
    // 댓글 삭제 메서드
    commentDelete() {
      console.log(this.comment.commentId);
      axios.delete("http://localhost/comment/" + this.comment.commentId)
        .then(() => {
          // console.log('댓글이 성공적으로 삭제되었습니다!'); // 디버깅
          // 현재 페이지 새로고침
          this.$router.go(0);
        });
    },
    // 댓글 수정 메서드
    commentModifyApply() {
      console.log(this.comment); // 디버깅
      axios.put("http://localhost/comment", this.comment)
        .then(() => {
          console.log(this.comment.commentId);
          // 수정 후, 조회
          axios.get("http://localhost/comment/view/" + this.comment.commentId)
            .then((resp) => {
              console.log(resp); // 디버깅
              this.comment = resp.data;
            });
        });

      // 기존 댓글 모드로 변경
      document.querySelector('.static_content' + this.comment.commentId).style.display = "";
      document.querySelector('.input_content_box' + this.comment.commentId).style.display = "none";
    }
  },
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
.comment_content_body {
  display: flex;
  justify-content: left;
}
.comment_content_body p {
  margin: 5px 0;
}
.input_content {
  margin: 5px 0;
  width: 50%;
  height: 30px;
  border: 1px solid #000000;
  border-radius: 10px;
  padding: 0 15px;
}
#BoardCommentItem {
  margin: 20px 150px;
}
#modifyBtn {
  margin-left: 10px;
  cursor: pointer;
  border: none;
  background-color: #FFF;
  color: #2ecc71;
}
#deleteBtn {
  margin-left: 10px;
  cursor: pointer;
  border: none;
  background-color: #FFF;
  color: #e74c3c;
}
#modifyApplyBtn {
  background-color: #FFF;
  margin-left: 10px;
  cursor: pointer;
  border: none;
  color: #3498db;
}
</style>