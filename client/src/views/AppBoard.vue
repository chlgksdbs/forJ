<template>
    <div id="AppBoard">
        <the-heading></the-heading>
        <div class="board_category_bar">
            <h1 class="board_category_title">{{ boardTitle }}</h1>
            <div class="board_category_menu">
                <router-link to="/board/notice">
                    <div class="board_category_menu_item" v-if="boardTitle == '공지사항'" style="color: #000; text-decoration: underline; text-underline-offset : 5px;">공지사항</div>
                    <div class="board_category_menu_item" v-else>공지사항</div>
                </router-link>
                <router-link to="/board/list">
                    <div class="board_category_menu_item" v-if="boardTitle == '자유 게시판'" style="color: #000; text-decoration: underline; text-underline-offset : 5px;">자유 게시판</div>
                    <div class="board_category_menu_item" v-else>자유 게시판</div>
                </router-link>
                <router-link to="/board/qna">
                    <div class="board_category_menu_item" v-if="boardTitle == 'Q&A'" style="color: #000; text-decoration: underline; text-underline-offset : 5px;">Q&A</div>
                    <div class="board_category_menu_item" v-else>Q&A</div>
                </router-link>
            </div>
        </div>
        <router-view @setTitle="setTitle"></router-view>
        <the-footer></the-footer>
    </div>
</template>
<script>
import TheHeading from "@/components/TheHeading.vue";
import TheFooter from "@/components/TheFooter.vue";

export default {
    name: "AppBoard",
    components: {
        TheHeading,
        TheFooter,
    },
    data() {
        return {
            boardTitle: '',
        }
    },
    created() {
        
    },
    methods: {
        // TODO: 자식 컴포넌트에 따른 게시판 이름 설정
        setTitle(name) {
            this.boardTitle = name;
        },
        // TODO: 현재 게시판 제목에 따른 category bar CSS 스타일 변경
    },
}
</script>
<style scoped>
.board_category_bar {
    margin-top: 25px;
    margin-bottom: 30px;
}
.board_category_title {
    margin-bottom: 35px;
    text-decoration: underline;
    text-underline-offset : 10px;
}
.board_category_menu_item {
  display: inline-block;
  margin: 0 15px;
  color: #888;
  cursor: pointer;
}
</style>