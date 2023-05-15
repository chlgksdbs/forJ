<template>
    <div id="ThePlanLeft">
        <div class="plan_left_calendar">
            <h3>여행 일정</h3>
            <p>2023.05.01 ~ 2023.05.03 (3일)</p>
            <button class="plan_left_modify_btn">변경</button>
        </div>
        <div class="plan_left_select_list">
            <h3>선택 목록</h3>
            <button class="plan_left_delete_btn">장소 전체 삭제</button>
            <the-select-list-card :img="require('@/assets/img/ex_img5.png')" :title="'삼성화재 유성연수원'"></the-select-list-card>
            <the-select-list-card :img="require('@/assets/img/ex_img2.png')" :title="'유성온천역'"></the-select-list-card>
            <the-select-list-card :img="require('@/assets/img/ex_img1.png')" :title="'충남대학교'"></the-select-list-card>
            <button class="plan_left_create_btn">일정 생성</button>
        </div>
    </div>
</template>
<script>
import TheSelectListCard from "../SelectListCard/TheSelectListCard.vue";

export default {
    name: "ThePlanLeft",
    components: {
        TheSelectListCard,
    },
}
</script>
<style scoped>
#ThePlanLeft {
    margin: auto;
    background-color: #F5F5F5;
    width: 15%;
    height: 661px;
}
.plan_left_calendar {
    width: 70%;
    height: 25%;
    background-color: #FFFFFF;
    border: 1px solid #40A3FF;
    margin: 10px auto;
    padding: 15px;
}
.plan_left_select_list {
    width: 70%;
    height: 60%;
    background-color: #FFFFFF;
    border: 1px solid #40A3FF;
    margin: 10px auto;
    padding: 15px;
    overflow: auto;
}
/* 추후에 modify 버튼과 create 버튼을 하나의 component로 작성 예정 */
.plan_left_modify_btn {
    color: #FFF;
    font-weight: bold;
    background-color: #0085FF;
    border: 1px solid #0085FF;
    border-radius: 15px;
    width: 70%;
    height: 20%;
    cursor: pointer;
}
.plan_left_delete_btn {
    color: #FFF;
    font-weight: bold;
    background-color: #EF5C53;
    border: 1px solid #EF5C53;
    border-radius: 5px;
    width: 99%;
    height: 6%;
    cursor: pointer;
}
.plan_left_create_btn {
    color: #FFF;
    font-weight: bold;
    background-color: #0085FF;
    border: 1px solid #0085FF;
    border-radius: 15px;
    width: 90%;
    height: 10%;
    cursor: pointer;
}
</style>