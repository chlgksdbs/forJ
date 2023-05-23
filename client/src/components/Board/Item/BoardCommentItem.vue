<template>
  <div id="BoardCommentItem">
    <!-- 댓글 목록 div -->
    <div class="detail_comment_content_box">
      <div class="comment_profile_img_box">
        <img src="@/assets/img/default_profile_img.png" v-if="imageUrl.length == 0">
        <img :src="imageUrl">
      </div>
      <div class="comment_content_box">
        <div class="comment_content_header">
          <div>{{ comment.writer }}</div>
          <div>{{ comment.writeDate }}</div>
          <button id="modifyBtn" @click="commentModify" v-if="userInfo.userId == comment.userId">수정</button>
          <button id="deleteBtn" @click="commentDelete" v-if="userInfo.userId == comment.userId || userInfo.userId == 'admin'">삭제</button>
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
import { mapState } from "vuex";

const memberStore = "memberStore";

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
      imageUrl: '',
    };
  },
  props: {
    commentItem: Object,
  },
  created() {
    this.comment = this.commentItem;
    // TODO: 프로필 이미지 변경 후, 이미지를 띄우기
    axios.get('http://localhost/user/profileimg/' + this.comment.userId, { responseType: 'blob'})
      .then((resp) => {
        // console.log(resp); // 디버깅 -> 글자 깨짐 현상 발생

        const reader = new FileReader();
        reader.onloadend = () => {
          this.imageUrl = reader.result;
        };
        reader.readAsDataURL(resp.data);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  computed: {
    ...mapState(memberStore, ['userInfo']),
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
      // console.log(this.comment); // 디버깅
      if (this.comment.content.length == 0) {
        alert('댓글을 입력하세요.');
        return;
      }
      axios.put("http://localhost/comment", this.comment)
        .then(() => {
          // console.log(this.comment.commentId);
          // 수정 후, 조회
          axios.get("http://localhost/comment/view/" + this.comment.commentId)
            .then((resp) => {
              // console.log(resp); // 디버깅
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
  border: 1px solid;
  background-color: #FFF;
  color: #2ecc71;
}
#modifyBtn:hover {
  background-color: #2ecc71;
  color: #FFF;
}
#deleteBtn {
  margin-left: 10px;
  cursor: pointer;
  border: 1px solid;
  background-color: #FFF;
  color: #e74c3c;
}
#deleteBtn:hover {
  background-color: #e74c3c;
  color: #FFF;
}
#modifyApplyBtn {
  background-color: #FFF;
  margin-left: 10px;
  cursor: pointer;
  border: 1px solid;
  color: #3498db;
  height: 30px;
}
#modifyApplyBtn:hover {
  background-color: #3498db;
  color: #FFF;
}
</style>