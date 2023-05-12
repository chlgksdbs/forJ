<template>
    <div id="TheCard">
        <div class="card_box">
            <img src="@/assets/img/main_card1.png" class="img_card">
            <h1 class="img_card_text">여행 일정 만들기</h1>
        </div>
        <div class="card_box">
            <img src="@/assets/img/main_card2.png" class="img_card">
            <h1 class="img_card_text">일정 공유 게시판</h1>
        </div>
        <div class="card_box">
            <img src="@/assets/img/main_card3.png" class="img_card">
            <h1 class="img_card_text">장바구니</h1>
        </div>
    </div>
</template>
<script>
export default {
    name: "TheCard",
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
#TheCard {
    display: flex;
    justify-content: center;
}
</style>