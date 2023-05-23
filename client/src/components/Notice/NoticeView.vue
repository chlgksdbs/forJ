<template>
  <div id="NoticeView">
    <div class="detail_header">
      <img src="@/assets/img/detail_logo.png" class="detail_logo" />
    </div>
    <div class="detail_title">
      <h1>{{ noticeItem.noticeTitle }}</h1>
    </div>
    <div class="detail_nav">
      <div class="detail_nav_profile_img_text">
        <div class="detail_nav_profile_default_img_box">
          <img src="@/assets/img/icon_profile.png">
        </div>
        <div class="detail_nav_profile_text">
          <div class="detail_nav_profile_nickname">
            {{ noticeItem.noticeWriter }}
          </div>
          <div class="detail_nav_profile_write_date">
            {{ noticeItem.noticeDate }}
          </div>
        </div>
      </div>
      <div class="detail_nav_hit">
        조회수 : {{ noticeItem.noticeHit }}
      </div>
    </div>
    <div class="detail_btn_bar">
      <button id="noticeModifyBtn" @click="noticeModify" v-if="userInfo.userId == 'admin'">수정</button>
      <button id="noticeDeleteBtn" @click="noticeDelete" v-if="userInfo.userId == 'admin'">삭제</button>
    </div>
    <div id="NoticeContent">
      <div class="detail_content">
        <p class="detail_content_text">
          <viewer v-if="noticeItem.noticeContent != null" :initialValue="noticeItem.noticeContent"/>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import "@toast-ui/editor/dist/toastui-editor.css";
import { Viewer } from "@toast-ui/vue-editor";

import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: 'NoticeView',
  components: {
    Viewer,
  },
  data() {
    return {
      noticeItem: {},
    };
  },
  created() {
    // console.log(this.$route.params.boardId); // 디버깅
    // TODO: axios 비동기 통신으로 server에서 detail 가져오기
    axios.get('http://localhost/notice/detail/' + this.$route.params.noticeId)
      .then((resp) => {
        // console.log(resp.data.writerId); // 디버깅
        this.noticeItem = resp.data;
      });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    // TODO: 공지사항 수정 버튼 이벤트 메서드
    noticeModify() {
      this.$router.push("/board/noticemodify/" + this.noticeItem.noticeId);
    },
    // TODO: 공지사항 삭제 버튼 이벤트 베서드
    noticeDelete() {
      // 현재 로그인된 사용자가 관리자인 경우에만 수행
      if (this.userInfo.userId == 'admin') {
        if (confirm('정말로 삭제하시겠습니까?')) {
        axios.delete('http://localhost/notice/delete/' + this.noticeItem.noticeId)
          .then(() => {
              alert('게시글 삭제가 완료되었습니다.')
              this.$router.push("/board/notice");
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
#NoticeView {
  margin: 0 100px;
}
#NoticeContent {
  margin: 30px 150px;
}
#noticeModifyBtn {
  margin-left: 10px;
  cursor: pointer;
  border: 1px solid;
  background-color: #FFF;
  color: #2ecc71;
  height: 30px;
}
#noticeModifyBtn:hover {
  background-color: #2ecc71;
  color: #FFF;
}
#noticeDeleteBtn {
  margin-left: 10px;
  cursor: pointer;
  border: 1px solid;
  background-color: #FFF;
  color: #e74c3c;
  height: 30px;
}
#noticeDeleteBtn:hover {
  background-color: #e74c3c;
  color: #FFF;
}
</style>