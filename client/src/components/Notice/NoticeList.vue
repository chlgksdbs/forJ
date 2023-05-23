<template>
    <div id="NoticeList">
        <div id="TheSearchBar" align="right">
            <div id="input_search_bar">
                <input type="text" v-model="searchText" placeholder="검색어를 입력하세요." @keyup.enter="searchNotice" maxlength="45" />
                <img src="@/assets/img/icon_search.png" @click="searchNotice">
            </div>
        </div>
        <notice-list-item :searchItems="searchItems"></notice-list-item>
        <div class="post_area" v-if="userInfo.userId == 'admin'">
            <router-link to="/board/noticewrite">글작성</router-link>
        </div>
    </div>
</template>
<script>
import axios from "axios";

import NoticeListItem from "../Notice/Item/NoticeListItem.vue";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
    name: "NoticeList",
    components: {
        NoticeListItem,
    },
    data() {
        return {
            searchText: '',
            searchItems: [],
        }
    },
    created() {
        this.$emit("setTitle", "공지사항");
    },
    computed: {
        ...mapState(memberStore, ['userInfo']),
    },
    methods: {
        // TODO: 공지사항 검색바 이벤트 메서드
        searchNotice() {
        if (this.searchText.length != 0) {
            axios.get('http://localhost/notice/search/' + this.searchText)
            .then((resp) => {
                this.searchItems = resp.data;
            });
        }
        else {
            this.searchItems = null;
        }
        },
    },
}
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
#NoticeList {
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