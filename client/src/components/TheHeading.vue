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
            <div class="profile_dropdown_box">
                <img src="@/assets/img/default_profile_img.png" v-if="imageUrl.length == 0">
                <img :src="imageUrl" v-else >
            </div>
            <div>
                <router-link to="/mypage">마이페이지</router-link>
            </div>
            <div>
                <span @click="logout">로그아웃</span>
            </div>
        </div>
        <!-- 로그인 정보 X -->
        <div id="header_right" v-else>
            <router-link to="/login">로그인</router-link>
            <router-link to="/join">회원가입</router-link>
        </div>
    </div>
</template>
<script>
import axios from "axios";
import { mapState, mapActions, mapGetters } from "vuex";

const memberStore = "memberStore";

export default {
    name: "TheHeading",
    data() {
        return {
            imageUrl: '',
        }
    },
    computed: {
        ...mapState(memberStore, ["isLogin", "userInfo"]),
        ...mapGetters(["checkUserInfo"]),
    },
    created() {
        // TODO: 프로필 이미지 변경 후, 이미지를 띄우기
        axios.get('http://localhost/user/profileimg/' + this.userInfo.userId, { responseType: 'blob'})
        .then((resp) => {
            // console.log(resp); // 디버깅 -> 글자 깨짐 현상 발생

            // 파일의 크기가 0인 경우(backend에서 넘어온 데이터가 없는 경우), 종료
            if (resp.data.size == 0) return;

            const reader = new FileReader();
            reader.onloadend = () => {
            this.imageUrl = reader.result;
            };
            reader.readAsDataURL(resp.data);
        })
        .catch(() => {
            // console.log(error);
        });
    },
    methods: {
        ...mapActions(memberStore, ["userLogout"]),
        logout() {
            // console.log(this.userInfo); // 디버깅

            this.userLogout(this.userInfo.userId);

            sessionStorage.removeItem("access-token"); // 저장된 access token 삭제
            sessionStorage.removeItem("refresh-token"); // 저장된 refresh token 삭제

            // 로그인 페이지로 이동
            if (this.$route.path != "/login") this.$router.push("/login");
        },
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
.profile_dropdown_box {
    width: 50px;
    height: 50px;
    margin: 0px 20px;
    border-radius: 70%;
    overflow: hidden;
}
.profile_dropdown_box img {
    width: 100%;
    height: 100%;
    object-fit: cover;
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
#header_right div {
    display: inline-block;
}
</style>