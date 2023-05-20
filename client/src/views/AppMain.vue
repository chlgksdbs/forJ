<template>
    <div id="AppMain">
        <div class="carousel_box">
            <vue-slick-carousel
                :arrows="true"
                :autoplay="true"
                :autoplaySpeed="6500"
                :speed="2500"
                :dots="false"
                :rows="1"
                :slidesToShow="1"
            >
            <div class="carousel_one">
            </div>
            <div class="carousel_two">
            </div>
            <div class="carousel_three">
            </div>
        </vue-slick-carousel>
        <div class="main_nav_bar">
            <div class="main_nav">
                <router-link to="/plan" class="main_menu_bar">MAKE PLAN</router-link>
            </div>
            <span style="color: #dadada">|</span>
            <div class="main_nav">
                <router-link to="/board" class="main_menu_bar">SHARE PLAN</router-link>
            </div>
            <span style="color: #dadada">|</span>
            <div class="main_nav">
                <router-link to="/mypage/cart" class="main_menu_bar">MY PLAN</router-link>
            </div>
        </div>
        <div class="main_nav_user_bar" v-if="userInfo">
            <div class="main_nav">
                <router-link to="/mypage" class="main_menu_bar">마이페이지</router-link>
            </div>
            <div class="main_nav">
                <a class="main_menu_bar" @click="logout">로그아웃</a>
            </div>
        </div>
        <div class="main_nav_nouser_bar" v-else>
            <div class="main_nav">
                <router-link to="/login" class="main_menu_bar">로그인</router-link>
            </div>
            <div class="main_nav">
                <router-link to="/join" class="main_menu_bar">회원가입</router-link>
            </div>
        </div>
        <div class="carousel_text_box">
            <h1 class="carousel_text">FOR MBTI J</h1>
            <h2 class="carousel_text_subtitle">당신만의 여행 플래너</h2>
            <div style="display: flex; ">
                <div class="banner_search">
                    <div class="banner_search_icon">
                        <img src="@/assets/img/icon_search.png" width="24" height="24">
                    </div>
                    <div style="flex: 1; position: relative;">
                        <input type="text" class="banner_search_input" placeholder="원하는 여행지를 입력하세요." style="outline: none;" />
                    </div>
                </div>
                <div class="banner_search_button">
                    <span>검색</span>
                </div>
            </div>
        </div>
        </div>
        <div id="MainContent">
            <div id="TheCard">
                <div class="card_box">
                    <router-link to="/plan">
                        <img src="@/assets/img/main_card1.png" class="img_card">
                        <h1 class="img_card_text">MAKE PLAN</h1>
                    </router-link>
                </div>
                <div class="card_box">
                    <router-link to="/board">
                        <img src="@/assets/img/main_card2.png" class="img_card">
                        <h1 class="img_card_text">SHARE PLAN</h1>
                    </router-link>
                </div>
                <div class="card_box">
                    <router-link to="/mypage/cart">
                        <img src="@/assets/img/main_card3.png" class="img_card">
                        <h1 class="img_card_text">MY PLAN</h1>
                    </router-link>
                </div>
            </div>
        </div>
        <the-footer></the-footer>
    </div>
</template>
<script>
import VueSlickCarousel from "vue-slick-carousel";
import 'vue-slick-carousel/dist/vue-slick-carousel.css';
import 'vue-slick-carousel/dist/vue-slick-carousel-theme.css';

import { mapState, mapGetters, mapActions } from "vuex";
import TheFooter from "@/components/TheFooter.vue";

const memberStore = "memberStore";

export default {
    name: "AppMain",
    components: {
        VueSlickCarousel,
        TheFooter
    },
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
a {
  text-decoration: none;
  cursor: pointer;
}
.main_nav_bar {
    position: absolute;
    top: 10%;
    left: 50%;
    transform: translate( -50%, -50% );
    width: 450px;
    display: flex;
    justify-content: space-between;
}
.main_nav_user_bar {
    position: absolute;
    top: 10%;
    left: 90%;
    transform: translate( -50%, -50% );
    width: 150px;
    display: flex;
    justify-content: space-between;
}
.main_nav_nouser_bar {
    position: absolute;
    top: 10%;
    left: 90%;
    transform: translate( -50%, -50% );
    width: 120px;
    display: flex;
    justify-content: space-between;
}
.main_menu_bar {
  font-size: 17px;
  color: #FFF;
}
.carousel_box {
    margin: 0px;
}
.carousel_text_box {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate( -50%, -50% );
}
.carousel_text {
    font-size: 50px;
    color: #D3D3D3;
    text-align: left;
    margin: 0;
}
.carousel_text_subtitle {
    font-size: 25px;
    color: #D3D3D3;
    text-align: left;
    margin: 5px 0 15px 0;
}
.carousel_one {
    display: flex;
    flex-direction: column;
    width: 100vw;
    height: 80vh;
    background: url("@/assets/img/main_carousel1.jpg");
    background-size: cover;
    text-align: center;
    position: relative;
    justify-content: center;
    align-items: center;
    z-index: 1;
}
.carousel_two {
    display: flex;
    flex-direction: column;
    width: 100vw;
    height: 80vh;
    background: url("@/assets/img/main_carousel2.jpg");
    background-size: cover;
    text-align: center;
    position: relative;
    justify-content: center;
    align-items: center;
    z-index: 1;
}
.carousel_three {
    display: flex;
    flex-direction: column;
    width: 100vw;
    height: 80vh;
    background: url("@/assets/img/main_carousel3.jpg");
    background-size: cover;
    text-align: center;
    position: relative;
    justify-content: center;
    align-items: center;
    z-index: 1;
}
.carousel_one::after, .carousel_two::after, .carousel_three::after{
    content: "";
    width: 100%;
    height: 100%;
    background-color: black;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: -1;
    opacity: 0.3;
}
.banner_search {
    align-items: center;
    background: #fff;
    border: 1px solid transparent;
    border-radius: 12px;
    box-sizing: border-box;
    color: #212121;
    display: flex;
    height: 50px;
    padding: 0 3px 0 16px;
    position: relative;
    width: 550px;
    z-index: 5;
}
.banner_search_icon {
    flex: 0 0 24px;
    margin-right: 8px;
}
.banner_search_input {
    background-color: transparent;
    border: 1px solid #fff;
    border-radius: 6px;
    box-sizing: border-box;
    caret-color: #2E75B6;
    color: #212121;
    font-size: 16px;
    font-size: 18px;
    font-weight: 400;
    height: 50px;
    line-height: 1.5;
    overflow: hidden;
    padding: 15px 0;
    position: relative;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 95%;
    z-index: 400;
}
.banner_search_button {
    align-items: center;
    background: #6AB2ED;
    border: 1px solid #D3D3D3;
    border-radius: 8px;
    color: #fff;
    cursor: pointer;
    display: flex;
    flex: none;
    font-size: 16px;
    font-weight: 500;
    height: 48px;
    line-height: 1.5;
    margin-left: 16px;
    padding: 0 40px;
}
.card_box {
    margin: 0 20px;
    position: relative;
}
.img_card {
    width: 100%;
}
.img_card_text {
    font-size: 110%;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate( -50%, -50% );
    color: white;
}
#MainContent {
    margin: 50px 100px;
}
#TheCard {
    display: flex;
    justify-content: center;
}
</style>

<!-- VueSlickCarousel Custom을 위한 전역 Style -->
<style>
/* slick 설정 Start */
.slick-prev {
    left: 10px;
    z-index: 99;
}
.slick-next {
    right: 10px;
    z-index: 99;
}
/* .slick-dots {
    position: static;
} */
/* slick 설정 End */
</style>