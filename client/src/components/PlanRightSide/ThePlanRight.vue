<template>
    <div id="ThePlanRight">
        <div class="plan_right_search_bar">
            <input type="text" placeholder="검색어를 입력하세요.">
        </div>
        <div class="plan_right_category">
            <button>호텔</button>
            <button>식당</button>
            <button>카페</button>
            <button>여행지</button>
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
export default {
    name: "ThePlanRight",
}
</script>
<style scoped>
#ThePlanRight {
    margin: auto;
    background-color: #F5F5F5;
    width: 15%;
    height: 661px;
}
</style>