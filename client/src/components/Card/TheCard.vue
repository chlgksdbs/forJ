<template>
    <div id="TheCard">
        <div class="card_box">
            <img src="@/assets/img/main_card1.png" class="img_card">
        </div>
        <div class="card_box">
            <img src="@/assets/img/main_card2.png" class="img_card">
        </div>
        <div class="card_box">
            <img src="@/assets/img/main_card3.png" class="img_card">
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
}
.img_card {
    width: 100%;
}
#TheCard {
    display: flex;
    justify-content: center;
}
</style>