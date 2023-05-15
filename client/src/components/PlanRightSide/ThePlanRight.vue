<template>
    <div id="ThePlanRight">
        <div class="plan_right_search_bar">
            <the-category-search-bar></the-category-search-bar>
        </div>
        <div class="plan_right_category">
            <the-category-btn :title="'호텔'"></the-category-btn>
            <the-category-btn :title="'식당'"></the-category-btn>
            <the-category-btn :title="'카페'"></the-category-btn>
            <the-category-btn :title="'여행지'"></the-category-btn>
        </div>
        <div class="plan_right_card">
            <the-search-list-card :img="require('@/assets/img/ex_img1.png')" :title="'충남대학교'"></the-search-list-card>
            <the-search-list-card :img="require('@/assets/img/ex_img2.png')" :title="'유성온천역'"></the-search-list-card>
            <the-search-list-card :img="require('@/assets/img/ex_img3.png')" :title="'대전월드컵경기장'"></the-search-list-card>
            <the-search-list-card :img="require('@/assets/img/ex_img4.png')" :title="'한밭대학교'"></the-search-list-card>
            <the-search-list-card :img="require('@/assets/img/ex_img5.png')" :title="'삼성화재 유성연수원'"></the-search-list-card>
            <the-search-list-card :img="require('@/assets/img/ex_img6.png')" :title="'에이트'"></the-search-list-card>
        </div>
    </div>
</template>
<script>
import TheCategorySearchBar from "../CategorySearchBar/TheCategorySearchBar.vue";
import TheCategoryBtn from "../CategoryBtn/TheCategoryBtn.vue";
import TheSearchListCard from "../SearchListCard/TheSearchListCard.vue";

export default {
    name: "ThePlanRight",
    components: {
        TheCategorySearchBar,
        TheCategoryBtn,
        TheSearchListCard,
    }
}
</script>
<style scoped>
#ThePlanRight {
    margin: auto;
    background-color: #F5F5F5;
    width: 20%;
    height: 661px;
}
.plan_right_category {
    display: flex;
}
</style>