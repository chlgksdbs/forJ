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
                <router-link to="/mypage" class="main_menu_bar" style="font-family: OTWelcomeRA">마이페이지</router-link>
            </div>
            <div class="main_nav">
                <a class="main_menu_bar" @click="logout" style="font-family: OTWelcomeRA">로그아웃</a>
            </div>
        </div>
        <div class="main_nav_nouser_bar" v-else>
            <div class="main_nav">
                <router-link to="/login" class="main_menu_bar" style="font-family: OTWelcomeRA">로그인</router-link>
            </div>
            <div class="main_nav">
                <router-link to="/join" class="main_menu_bar" style="font-family: OTWelcomeRA">회원가입</router-link>
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
            <!-- MainContent 중, 지역별 여행 정보 영역-->
            <div class="main_content_region_wrap">
                <div class="main_content_region">
                    <h2 style="font-family: LINESeedKR-Bd">인기있는 여행지</h2>
                    <vue-slick-carousel
                        :infinite="true"
                        :initialSlide="2"
                        :speed="500"
                        :slidesToShow="4"
                        :slidesToScroll="1"
                        :swipeToSlide="true"
                    >
                        <div class="main_content_region_item">
                            <router-link to="#">
                                <span class="main_content_region_item_img">
                                    <img src="@/assets/img/region_seoul.jpg">
                                </span>
                                <span class="main_content_region_item_area">
                                    <span>
                                        서울
                                        <p>대한민국의 수도</p>
                                        <br>
                                        <p>지역보기</p>
                                    </span>
                                </span>
                            </router-link>
                        </div>
                        <div class="main_content_region_item">
                            <router-link to="#">
                                <span class="main_content_region_item_img">
                                    <img src="@/assets/img/region_busan.jpg">
                                </span>
                                <span class="main_content_region_item_area">
                                    <span>
                                        부산
                                        <p>한국 제 2의 수도</p>
                                        <br>
                                        <p>지역보기</p>
                                    </span>
                                </span>
                            </router-link>
                        </div>
                        <div class="main_content_region_item">
                            <router-link to="#">
                                <span class="main_content_region_item_img">
                                    <img src="@/assets/img/region_gangneung.jpg">
                                </span>
                                <span class="main_content_region_item_area">
                                    <span>
                                        강릉
                                        <p>아름다운 동해안 해안도로 도시</p>
                                        <br>
                                        <p>지역보기</p>
                                    </span>
                                </span>
                            </router-link>
                        </div>
                        <div class="main_content_region_item">
                            <router-link to="#">
                                <span class="main_content_region_item_img">
                                    <img src="@/assets/img/region_jeju.jpg">
                                </span>
                                <span class="main_content_region_item_area">
                                    <span>
                                        제주
                                        <p>한라산의 도시</p>
                                        <br>
                                        <p>지역보기</p>
                                    </span>
                                </span>
                            </router-link>
                        </div>
                        <div class="main_content_region_item">
                            <router-link to="#">
                                <span class="main_content_region_item_img">
                                    <img src="@/assets/img/region_incheon.jpg">
                                </span>
                                <span class="main_content_region_item_area">
                                    <span>
                                        인천
                                        <p>서울에서 가장 가까운 바다 도시</p>
                                        <br>
                                        <p>지역보기</p>
                                    </span>
                                </span>
                            </router-link>
                        </div>
                        <div class="main_content_region_item">
                            <router-link to="#">
                                <span class="main_content_region_item_img">
                                    <img src="@/assets/img/region_gyeongju.jpg">
                                </span>
                                <span class="main_content_region_item_area">
                                    <span>
                                        경주
                                        <p>신라의 수도, 문화재의 도시</p>
                                        <br>
                                        <p>지역보기</p>
                                    </span>
                                </span>
                            </router-link>
                        </div>
                        <div class="main_content_region_item">
                            <router-link to="#">
                                <span class="main_content_region_item_img">
                                    <img src="@/assets/img/region_jeonju.jpg">
                                </span>
                                <span class="main_content_region_item_area">
                                    <span>
                                        전주
                                        <p>한옥 마을과 비빔밥의 도시</p>
                                        <br>
                                        <p>지역보기</p>
                                    </span>
                                </span>
                            </router-link>
                        </div>
                    </vue-slick-carousel>
                </div>
            </div>
            <!-- MainContent 중, 여행 후기 영역 -->
            <div class="main_content_review_wrap">
                <div class="best_tour_review_box">
                    <h2 style="font-family: LINESeedKR-Bd">인기있는 여행 후기</h2>
                    <div class="more_plus">
                        <router-link to="/board">더보기</router-link>
                    </div>
                    <router-link to="/board/view/3">
                        <div class="best_tour_review">
                            <div class="best_tour_img">
                                <img src="@/assets/img/best_tour_img.jpg" width="300px" height="200px">
                            </div>
                            <div class="best_tour_info">
                                <h3 class="tour_title">2박 3일 부산 커플 여행 일정 공유해요~!!</h3>
                                <p class="tour_date">2023.05.01(월) ~ 2023.05.03(수) 2박 3일</p>
                                <p class="tour_content">휴가 기간에, 2박 3일로 부산 여행을 다녀왔다. 이번에 다녀온 여행지는 ...</p>
                                <p class="tour_writer">김** | 2023.05.04</p>
                            </div>
                        </div>
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
            // console.log(this.userInfo);

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
  color: #000;
  cursor: pointer;
}
.main_nav_bar {
    position: absolute;
    top: 8%;
    left: 50%;
    transform: translate( -50%, -50% );
    width: 450px;
    display: flex;
    justify-content: space-between;
}
.main_nav_bar div {
    transition: all 0.2s linear;
}
.main_nav_bar div:hover {
    transform: scale(1.1);
}
.main_nav_user_bar {
    position: absolute;
    top: 8%;
    left: 90%;
    transform: translate( -50%, -50% );
    width: 180px;
    display: flex;
    justify-content: space-between;
}
.main_nav_nouser_bar {
    position: absolute;
    top: 8%;
    left: 90%;
    transform: translate( -50%, -50% );
    width: 150px;
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
    height: 85vh;
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
    height: 85vh;
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
    height: 85vh;
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
/* Main Content: 지역별 여행 정보 카드 CSS Start */
.main_content_region_wrap {
    display: flex;
    justify-content: center;
    margin-bottom: 50px;
}
.main_content_region {
    position: relative;
    width: 800px;
}

.main_content_region h2 {
    font-size: 28px;
    text-align: left;
}
.main_content_region_item {
    float: left;
    width: 198px;
    height: 180px;
    border-radius: 10px;
    overflow: hidden;
}
.main_content_region_item a {
    position: relative;
    display: block;
    width: 100%;
    height: 100%;
    transition: all 0.25s linear;
}
.main_content_region_item a:hover {
    transform: scale(1.05);
}
.main_content_region_item_img img {
    border-radius: 10px;
    width: 95%;
    height: 100%;
    opacity: 0.9;
}
.main_content_region_item_area {
    position: absolute;
    top: 0px;
    left: 0;
    width: 100%;
    height: 100%;
    color: #fff;
    font-size: 20px;
    display: table;
}
.main_content_region_item_area > span {
    padding: 0 20px;
    text-align: center;
    display: table-cell;
    vertical-align: middle;
    font-weight: 700;
    font-size: 20px;
    letter-spacing: -0.08em;
}
.main_content_region_item_area > span > p {
    font-size: 12px;
    margin: 0;
    padding: 0;
}
/* Main Content: 지역별 여행 정보 카드 CSS End */
/* Main Content: 인기있는 여행 후기 CSS Start */
.main_content_review_wrap {
    display: flex;
    justify-content: center;
}
.best_tour_review_box {
    position: relative;
    width: 800px;
}
.best_tour_review_box h2 {
    font-size: 28px;
    text-align: left;
}
.more_plus {
    position: absolute;
    right: 0;
    top: 10%;
}
.more_plus a {
    display: block;
    color: #595959;
    font-size: 16px;
    font-weight: 700;
    line-height: 16px;
}
.more_plus a::before {
    content: '+';
    display: inline-block;
    color: #595959;
    font-size: 25px;
    font-weight: 700;
    width: 14px;
    height: 22px;
    line-height: 1;
    vertical-align: bottom;
    margin-right: 8px;
}
.best_tour_review {
    cursor: pointer;
}
.best_tour_img {
    float: left;
    width: 300px;
    height: 200px;
}
.best_tour_info {
    position: relative;
    border: 1px solid #ddd;
    margin-left: 300px;
    min-height: 200px;
}
.tour_title {
    font-size: 20px;
    margin: 30px 0 0 0;
}
.tour_date {
    position: relative;
    color: #005bd7;
    padding-bottom: 15px;
    margin: 5px 0 15px 0;
    width: 70%;
    left: 15%;
    font-weight: bold;
}
.tour_date:after {
    content: '';
    display: block;
    height: 1px;
    background: #ebebeb;
    position: absolute;
    left: 0;
    bottom: 0;
    right: 0;
}
.tour_content {
    position: relative;
    left: 15%;
    width: 70%;
}
.tour_writer {
    font-size: 14px;
    position: absolute;
    right: 15px;
    bottom: 0;
    color: #a6a6a6;
}
/* Main Content: 인기있는 여행 후기 CSS End*/
#MainContent {
    margin: 65px 100px;
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
/* slick 설정 End */
</style>