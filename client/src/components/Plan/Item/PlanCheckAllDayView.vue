<template>
  <div>
    <div class="dayViewTitle">
      {{ dateFormat(selectAreaItems[0].start) }} ~ {{ dateFormat(selectAreaItems[0].end) }}
      <!-- 전체 일정 리스트 -->
    </div>
    <!-- <plan-check-list v-for="item in selectAreaItems" :key="item.contentId" :img="item.img" :title="item.title" :contentId="item.contentId"></plan-check-list> -->
    <draggable>
      <plan-check-list
        v-for="item in areaItems"
        :key="item.contentId"
        draggable="true"
        @dragstart="dragStart"
        :img="item.img"
        :title="item.title"
        :contentId="item.contentId"
        @storeCost="setCost"
      ></plan-check-list>

      <!-- 날짜별 일정 리스트 -->
      <div class="dropBox" v-for="day in selectAreaItems[0].period + 1" :key="day">
        <div @drop="drop">
          <div>DAY {{ day }}</div>
          <plan-check-list
            v-for="item in eachDayList[day]"
            :key="item.contentId"
            :img="item.img"
            :title="item.title"
            :contentId="item.contentId"
            @storeCost="setCost"
          ></plan-check-list>
        </div>
      </div>
    </draggable>
  </div>
</template>

<script>
import PlanCheckList from "./PlanCheckList.vue";
import draggable from "vuedraggable";

export default {
  name: "PlanCheckAllDayView",
  components: {
    PlanCheckList,
    draggable,
  },
  data() {
    return {
      areaItems: [],
      costList: [],
      eachDayList: [[]],
    };
  },
  props: {
    selectAreaItems: Array,
  },
  watch: {
    eachDayList(value) {
      console.log(value);
    },
  },
  created() {},
  mounted() {
    // console.log(this.selectAreaItems);
    for (let i = 1; i < this.selectAreaItems.length; i++) {
      this.areaItems.push(this.selectAreaItems[i]);
    }
    console.log(this.areaItems);
  },
  methods: {
    // 날짜 포맷을 변경하는 메서드
    dateFormat(date) {
      let dateFormat =
        (date.getMonth() + 1 < 9 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1) +
        "월" +
        (date.getDate() < 9 ? "0" + date.getDate() : date.getDate()) +
        "일";

      return dateFormat;
    },
    // 각 여행지별 예상 경비를 리스트에 모두 중복불가능하게 저장해놓는 메서드
    setCost(costInfo) {
      let flag = 0;
      for (let i = 0; i < this.costList.length; i++) {
        if (this.costList[i].contentId == costInfo.contentId) {
          this.costList[i].cost = costInfo.cost;
          flag = 1;
          break;
        }
      }
      if (flag == 0) {
        this.costList.push(costInfo);
      }
      // console.log(this.costList);
    },
    // 각각의 div들이 데이터를 전달하는 이벤트 트리거 메서드
    dragStart(event) {
      event.dataTransfer.setData("text/plain", event.target.dataset.contentId);

      console.log(this.eachDayList);
    },
    // dataTransfer 사용해 드롭된 객체의 contentID를 가져와 이 객체를 새 div의 배열로 이동
    // 기존 배열에서는 해당 객체 제거
    drop(event) {
      const itemContentId = event.dataTransfer.getData("text/plain");
      const item = this.draggableItems.find((item) => item.contentId.toString() == itemContentId);
      if (item) {
        const colIdx = parseInt(event.currentTarget.getAttribute("data-column-index"));
        // 열 없으면 새 열 추가
        if (!this.eachDayList[colIdx]) {
          this.eachDayList[colIdx] = [];
        }
        // 해당 위치에 item 삽입
        this.eachDayList[colIdx].splice(0, 0, item);
        this.areaItems = this.areaItems.filter(
          (item) => item.contentId.toString() != itemContentId
        );
        console.log(this.eachDayList);
      }
    },
  },
};
</script>

<style scoped>
.dayViewTitle {
  width: 85%;
  margin: 10px;
  padding: 5px;
  border: 2px solid #6ab2ed;
}
.dropBox {
  width: 85%;
  margin: 15px;
  padding: 1px;
  border: 1px solid #6ab2ed;
}
</style>
