<template>
    <div id="ThePlanLeft">
        <div class="plan_left_calendar">
            <h3>여행 일정</h3>
            <p>{{ dateFormat(attr[0].dates.start)}} ~ {{ dateFormat(attr[0].dates.end) }} ({{ calcPeriod(attr[0].dates.start, attr[0].dates.end) }}일)</p>
            <button class="plan_left_modify_btn" @click="modifyDate">변경</button>
            <v-calendar
            class="vcalendar"
            :min-date="new Date()"
            :attributes="attr"
            @dayclick="dayClickEvent"
            v-if="openmodal == true" />
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
    data() {
        return {
            openmodal: false,
            attr: [
                {
                    highlight: {
                        start: { fillMode: 'outline' },
                        base: { fillMode: 'light' },
                        end: { fillMode: 'outline'},
                    },
                    dates: {
                        start: new Date(2023, 4, 1),
                        end: new Date(2023, 4, 3),
                    },
                },
            ],
            clickCount: 0,
        }
    },
    methods: {
        // 캘린더 모달창 On, Off하는 메서드
        modifyDate() {
            this.openmodal = !this.openmodal;
        },
        // 날짜 포맷을 변경하는 메서드
        dateFormat(date) {
            let dateFormat = date.getFullYear()
                + '.' + ((date.getMonth() + 1) < 9 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1))
                + '.' + ((date.getDate()) < 9 ? "0" + (date.getDate()) : (date.getDate()));

            return dateFormat;
        },
        // 여행 일정 기간을 계산하는 메서드
        calcPeriod(date1, date2) {
            let period = date2.getTime() - date1.getTime();
            if (date1 > date2) {
                alert('출발일은 도착일보다 늦어야합니다.');
                return "?";
            }
            return Math.floor(period / (1000 * 60 * 60 * 24)) + 1;
        },
        // 캘린더 날짜 변경 이벤트 메서드
        dayClickEvent(date) {
            // 1. clickCount가 0인 경우, startDate를 변경
            if (this.clickCount == 0) {
                this.attr[0].dates.start = date.date;
                this.attr[0].dates.end = date.date;
                this.clickCount++;
            }
            // 2. clickCount가 1인 경우, endDate를 변경 후, clickCount 0으로 변경
            else {
                this.attr[0].dates.end = date.date;
                this.clickCount = 0;
            }
        },
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
.vcalendar {
    margin: 10px;
    z-index: 9999999;
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