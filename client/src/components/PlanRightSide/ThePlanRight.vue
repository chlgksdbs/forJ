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
            <div>충남대학교</div>
            <div>유성온천역</div>
            <div>대전월드컵경기장</div>
            <div>한밭대학교</div>
            <div>삼성화재 유성연수원</div>
            <div>에이트</div>
        </div>
    </div>
</template>
<script>
import TheCategorySearchBar from "../CategorySearchBar/TheCategorySearchBar.vue";
import TheCategoryBtn from "../CategoryBtn/TheCategoryBtn.vue";

export default {
    name: "ThePlanRight",
    components: {
        TheCategorySearchBar,
        TheCategoryBtn,
    }
}
</script>
<style scoped>
#ThePlanRight {
    margin: auto;
    background-color: #F5F5F5;
    width: 15%;
    height: 661px;
}
.plan_right_category {
    display: flex;
}
</style>