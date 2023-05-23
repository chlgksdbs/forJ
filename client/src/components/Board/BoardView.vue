<template>
  <div id="BoardView">
    <div class="detail_header">
      <img src="@/assets/img/detail_logo.png" class="detail_logo" />
    </div>
    <div class="detail_title">
      <h1>{{ boardItem.title }}</h1>
    </div>
    <div class="detail_nav">
      <div class="detail_nav_profile_img_text">
        <!-- 사용자 프로필 사진이 있는 경우 -->
        <div class="detail_nav_profile_img_box" v-if="imageUrl.length != 0">
          <img :src="imageUrl">
        </div>
        <!-- 사용자 프로필 사진이 없는 경우-->
        <div class="detail_nav_profile_default_img_box" v-else>
          <img src="@/assets/img/icon_profile.png">
        </div>
        <div class="detail_nav_profile_text">
          <div class="detail_nav_profile_nickname">
            {{ boardItem.writer }}
          </div>
          <div class="detail_nav_profile_write_date">
            {{ boardItem.writeDate }}
          </div>
        </div>
      </div>
      <div class="detail_nav_hit">
        조회수 : {{ boardItem.hit }}
      </div>
    </div>
    <div class="detail_btn_bar">
      <button id="boardModifyBtn" @click="boardModify" v-if="userInfo.userId == boardItem.writerId">수정</button>
      <button id="boardDeleteBtn" @click="boardDelete" v-if="userInfo.userId == boardItem.writerId || userInfo.userId == 'admin'">삭제</button>
    </div>
    <div id="BoardContent">
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
      <div class="detail_btn_box">
        <div class="scrap_box">
          <img src="@/assets/img/icon_scrap.png" />
          <div class="btn_box_text">스크랩</div>
        </div>
        <div class="heart_box" @click="heartClick">
          <span class="material-symbols-outlined" id="heart_icon" style="display: ">favorite</span>
          <span class="material-symbols-outlined" id="fill_heart_icon" style="display: none;">favorite</span>
          <div class="btn_box_text">좋아요 {{ heartCount }}</div>
        </div>
      </div>
      <div class="view_line">
        <hr id="comment_line">
      </div>
    </div>
    <!-- 댓글 작성 div -->
    <div class="detail_comment_input_box">
      <div class="comment_title">댓글</div>
      <div class="comment_body">
        <div class="comment_profile_img_box">
          <img src="@/assets/img/default_profile_img.png" v-if="commentImageUrl.length == 0">
          <img :src="commentImageUrl" v-else>
        </div>
        <div class="comment_content_box">
          <input type="text" v-model="commentContent" @keyup.enter="commentWrite" placeholder="댓글을 입력하세요." />
          <button id="commentBtn" @click="commentWrite">작성</button>
        </div>
      </div>
    </div>
    <!-- 댓글 목록 -->
    <board-comment-item v-for="commentItem in commentItems" :commentItem="commentItem" :key="commentItem.commentId"></board-comment-item>
  </div>
</template>

<script>
import axios from 'axios';
import "@toast-ui/editor/dist/toastui-editor.css";
import { Viewer } from "@toast-ui/vue-editor";

import BoardCommentItem from "./Item/BoardCommentItem.vue";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: 'BoardView',
  components: {
    Viewer,
    BoardCommentItem,
  },
  data() {
    return {
      boardItem: {},
      commentItems: {},
      commentContent: '',
      heartStatus: 0,
      heartCount: 0,
      imageUrl: '',
      commentImageUrl: '',
    };
  },
  created() {
    // console.log(this.$route.params.boardId); // 디버깅
    // TODO: axios 비동기 통신으로 server에서 detail 가져오기
    axios.get('http://localhost/board/detail/' + this.$route.params.boardId)
      .then((resp) => {
        // console.log(resp.data.writerId); // 디버깅
        this.boardItem = resp.data;

        // TODO: axios 비동기 통신으로 server에서 comment 가져오기
        axios.get('http://localhost/comment/' + this.boardItem.boardId)
          .then((resp) => {
            // console.log(resp.data); // 디버깅
            this.commentItems = resp.data;
          });

        // TODO: 현재 사용자의 좋아요 버튼 상태(heartStatus)값 초기화
        axios.get('http://localhost/heart/status/' + this.boardItem.boardId + '/' + this.userInfo.userId)
          .then((resp) => {
            this.heartStatus = resp.data;
          });

        // TODO: 게시글 작성자의 프로필 이미지 가져오기
        axios.get('http://localhost/user/profileimg/' + this.boardItem.writerId, { responseType: 'blob'})
          .then((resp) => {
            // console.log(resp); // 디버깅 -> 글자 깨짐 현상 발생

            const reader = new FileReader();
            reader.onloadend = () => {
              this.imageUrl = reader.result;
            };
            reader.readAsDataURL(resp.data);
            console.log(this.imageUrl);
          })
          .catch((error) => {
            console.log(error);
          });
      });

    // TODO: 좋아요 count값 초기화
    axios.get('http://localhost/heart/count/' + this.$route.params.boardId)
      .then((resp) => {
        // console.log(resp.data); // 디버깅
        this.heartCount = resp.data;
      });

    // TODO: 로그인 중인 사용자의 프로필 이미지 가져오기
    axios.get('http://localhost/user/profileimg/' + this.userInfo.userId, { responseType: 'blob'})
      .then((resp) => {
        // console.log(resp); // 디버깅 -> 글자 깨짐 현상 발생

        const reader = new FileReader();
        reader.onloadend = () => {
          this.commentImageUrl = reader.result;
        };
        reader.readAsDataURL(resp.data);
        console.log(this.imageUrl);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  watch: {
    heartStatus(value) {
      // TODO: 좋아요 버튼 활성화
      if (value == 1) {
        document.querySelector('#heart_icon').style.display = "none";
        document.querySelector('#fill_heart_icon').style.display = "";
      }
      // TODO: 좋아요 버튼 비활성화
      else {
        document.querySelector('#heart_icon').style.display = "";
        document.querySelector('#fill_heart_icon').style.display = "none";
      }
    },
  },
  methods: {
    // TODO: 댓글 작성 메서드
    commentWrite() {

      if (this.commentContent.length == 0) {
        alert('댓글을 입력하세요.');
        return;
      }

      let comment = {
        "boardId": this.boardItem.boardId,
        "userId": this.userInfo.userId,
        "content": this.commentContent
      };

      console.log(comment);

      axios.post('http://localhost/comment', comment)
        .then(() => {
          // console.log(resp + "댓글 작성 성공!"); // 디버깅
          this.$router.go(0); // 현재 페이지 새로 고침
        });
    },
    // TODO: 좋아요 기능 메서드
    heartClick() {
      let heart = {
        "boardId": this.boardItem.boardId,
        "userId": this.userInfo.userId,
      }

      if (this.heartStatus == 1) {
        this.heartCount--;
      }
      else {
        this.heartCount++;
      }

      axios.post('http://localhost/heart/check', heart)
        .then((resp) => {
          // console.log(resp.data); // 디버깅
          this.heartStatus = resp.data; // 좋아요 상태 변경
        });
    },
    // TODO: 게시글 수정 버튼 이벤트 메서드
    boardModify() {
      this.$router.push("/board/modify/" + this.boardItem.boardId);
    },
    // TODO: 게시글 삭제 버튼 이벤트 베서드
    boardDelete() {
      // 현재 로그인된 사용자와 글쓴 사용자가 같은 경우 실행
      if (this.userInfo.userId == this.boardItem.writerId || this.userInfo.userId == 'admin') {
        if (confirm('정말로 삭제하시겠습니까?')) {
        axios.delete('http://localhost/board/delete/' + this.boardItem.boardId)
          .then(() => {
              alert('게시글 삭제가 완료되었습니다.')
              this.$router.push("/board");
            });
          }
      }
      // 다른 경우, 에러 처리
      else {
        alert('해당 게시글에 대한 권한이 없습니다.');
      }
    },
  },
};
</script>

<style scoped>
.detail_logo {
  width: 100%;
}
.detail_title {
  margin: 40px 0;
}
.detail_content_text {
  text-align: left;
}
.detail_content_img_box, .detail_route_img_box {
  margin: 40px 0;
  text-align: left;
}
.detail_route_img_box h2 {
  color: #0085ff;
}
.detail_route_img_box img {
  width: 65%;
}
.detail_nav_profile_img_box {
  width: 100px;
  height: 100px;
  margin: 0px 50px;
  border-radius: 70%;
  overflow: hidden;
}
.detail_nav_profile_img_box img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.detail_nav_profile_default_img_box {
  width: 65px;
  height: 65px;
  margin: 0px 50px;
  border-radius: 70%;
  overflow: hidden;
}
.detail_nav_profile_default_img_box img {
  width: 100%;
  object-fit: cover;
}
.detail_nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100px;
}
.detail_nav_profile_img_text{
  display: flex;
  align-items: center;
}
.detail_nav_profile_text {
  color: #888;
}
.detail_nav_hit {
  margin: 0px 150px;
  color: #888;
}
.detail_nav_profile_nickname {
  text-align: left;
}
.detail_nav_profile_write_date {
  text-align: left;
}
.detail_btn_bar {
  display: flex;
  justify-content: right;
  margin: 0px 150px;
}
.detail_btn_box {
  display: flex;
  justify-content: right;
}
.detail_btn_box div {
  margin: 5px;
  cursor: pointer;
}
.detail_btn_box div img {
  width: 25px;
}
.btn_box_text {
  font-size: 14px;
}
.view_line {
  line-height: 160%;
  box-sizing: content-box;
}
.detail_comment_input_box {
  margin: 0 150px;
}
.comment_title {
  color: #555555;
  font-size: 24px;
  font-weight: bold;
  text-align: left;
  margin-bottom: 15px;
}
.comment_body {
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
.comment_content_box {
  width: 80%;
  margin: auto 5px;
  text-align: left;
}
.comment_content_box input {
  width: 70%;
  height: 38px;
  border: 1px solid #000000;
  border-radius: 15px;
  padding: 0 30px;
}
#BoardView {
  margin: 0 100px;
}
#BoardContent {
  margin: 30px 150px;
}
#comment_line {
  border-top: 1px solid #eee;
  margin: 16px 0;
}
#commentBtn {
  margin: auto 15px;
  width: 60px;
  height: 40px;
  border-radius: 5px;
  border: 1px solid #40A3FF;
  background-color: #FFFFFF;
  color: #40A3FF;
  cursor: pointer;
}
#commentBtn:hover {
  background-color: #40A3FF;
  color: #FFFFFF;
  border: 1px solid #FFFFFF;
}
#heart_icon {
  font-variation-settings:
  'FILL' 0,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
}
#fill_heart_icon {
  color: red;
  font-variation-settings:
  'FILL' 100,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
}
#boardModifyBtn {
  margin-left: 10px;
  cursor: pointer;
  border: 1px solid;
  background-color: #FFF;
  color: #2ecc71;
  height: 30px;
}
#boardModifyBtn:hover {
  background-color: #2ecc71;
  color: #FFF;
}
#boardDeleteBtn {
  margin-left: 10px;
  cursor: pointer;
  border: 1px solid;
  background-color: #FFF;
  color: #e74c3c;
  height: 30px;
}
#boardDeleteBtn:hover {
  background-color: #e74c3c;
  color: #FFF;
}
</style>