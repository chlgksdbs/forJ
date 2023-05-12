<template>
    <div id="ThePlanLeft">
        <div class="plan_left_calendar">
            <h3>여행 일정</h3>
            <p>2023.05.01 ~ 2023.05.03 (3일)</p>
            <button>변경</button>
        </div>
        <div class="plan_left_select_list">
            <h3>선택 목록</h3>
            <button>장소 전체 삭제</button>
            <div>삼성화재 유성연수원</div>
            <div>유성온천역</div>
            <div>충남대학교</div>
            <button>일정 생성</button>
        </div>
    </div>
</template>
<script>
export default {
    name: "ThePlanLeft",
}
</script>
<style scoped>
#ThePlanLeft {
    margin: auto;
    background-color: #F5F5F5;
}
.plan_left_calendar {
    width: 70%;
    height: 30%;
    background-color: #FFFFFF;
}
.plan_left_select_list {
    width: 70%;
    height: 30%;
    background-color: #FFFFFF;
}
</style>