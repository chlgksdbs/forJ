<template>
    <div id="TheHeading">
        <div id="header_left"></div>
        <div id="header_center">
            <router-link to="/">
                <img src="@/assets/img/logo.png">
            </router-link>
        </div>
        <!-- 로그인 정보 O -->
        <div id="header_right" v-if="userInfo">
            <router-link to="/notice">공지사항</router-link>
            <span @click="logout">로그아웃</span>
        </div>
        <!-- 로그인 정보 X -->
        <div id="header_right" v-else>
            <router-link to="/login">로그인</router-link>
            <router-link to="/join">회원가입</router-link>
        </div>
    </div>
</template>
<script>
import { mapState, mapActions, mapGetters } from "vuex";

const memberStore = "memberStore";

export default {
    name: "TheHeading",
    computed: {
        ...mapState(memberStore, ["isLogin", "userInfo"]),
        ...mapGetters(["checkUserInfo"]),
    },
    methods: {
        ...mapActions(memberStore, ["userLogout"]),
        logout() {
            console.log(this.userInfo);

            this.userLogout(this.userInfo.userId);

            sessionStorage.removeItem("access-token"); // 저장된 access token 삭제
            sessionStorage.removeItem("refresh-token"); // 저장된 refresh token 삭제

            // 로그인 페이지로 이동
            if (this.$route.path != "/login") this.$router.push("/login");
        }
    },
}
</script>
<style scoped>
img {
    width: 250px;
}
a, span {
    text-decoration: none;
    color: #888;
    margin: 0 10px;
    cursor: pointer;
}
#TheHeading {
    display: flex;
    align-items: center;
    justify-content: space-between;
}
#header_left {
    width: 25%;
}
#header_center {
    width: 50%;
}
#header_right {
    width: 25%;
    font-size: 100%;
}
</style>