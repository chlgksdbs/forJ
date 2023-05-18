<template>
    <div id="AppMain">
        <the-heading></the-heading>
        <div id="TheCarousel">
            <div>
                <img src="@/assets/img/main_carousel.jpg" class="img_carousel">
            </div>
        </div>
        <h1>나만의 여행 일정을 만들고 공유</h1>
        <div id="TheCard">
            <div class="card_box">
                <router-link to="/plan">
                    <img src="@/assets/img/main_card1.png" class="img_card">
                    <h1 class="img_card_text">여행 일정 만들기</h1>
                </router-link>
            </div>
            <div class="card_box">
                <router-link to="/board">
                    <img src="@/assets/img/main_card2.png" class="img_card">
                    <h1 class="img_card_text">일정 공유 게시판</h1>
                </router-link>
            </div>
            <div class="card_box">
                <router-link to="/mypage/cart">
                    <img src="@/assets/img/main_card3.png" class="img_card">
                    <h1 class="img_card_text">장바구니</h1>
                </router-link>
            </div>
        </div>
        <the-footer></the-footer>
    </div>
</template>
<script>
import TheHeading from "@/components/TheHeading.vue";
import TheFooter from "@/components/TheFooter.vue";

export default {
    name: "AppMain",
    components: {
        TheHeading,
        TheFooter
    },
}
</script>
<style scoped>
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
.img_carousel {
    width: 100%;
}
#TheCard {
    display: flex;
    justify-content: center;
}
</style>