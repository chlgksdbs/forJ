<template>
  <div>
    <div class="dayViewTitle" style="font-family: OTWelcomeRA">
      {{ dateFormat(selectAreaItems[0].start) }} ~ {{ dateFormat(selectAreaItems[0].end) }}
      <!-- 전체 일정 리스트 -->
    </div>
    <!-- <plan-check-list v-for="item in selectAreaItems" :key="item.contentId" :img="item.img" :title="item.title" :contentId="item.contentId"></plan-check-list> -->
    <!-- <draggable>
      <plan-check-list
        v-for="item in areaItems"
        :key="item.contentId"
        draggable="true"
        @dragstart="dragStart"
        :img="item.img"
        :title="item.title"
        :contentId="item.contentId"
        @storeCost="setCost"
      ></plan-check-list> -->

      <!-- 날짜별 일정 리스트 -->
      <!-- <div class="dropBox" v-for="day in selectAreaItems[0].period + 1" :key="day">
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
    </draggable> -->
    
    <!-- JS로 Drag & Drop 구현 -->
    <div class="dropBox" v-for="(item, idx) in lists" :key="item.id">
      <div v-if="item.id == 0" style="font-weight: bold; font-family: LINESeedKR-Bd">선택목록</div>
      <div v-else style="font-family: LINESeedKR-Bd">DAY {{ item.id }}</div>
      <div
        @drop="onDrop($event, idx)"
        @dragenter.prevent
        @dragover.prevent
      >
        <!-- 아이템이 존재하지 않는 경우 (초기 상태 포함) -->
        <div v-if="item.dayList.length == 0" class="no_item" style="font-family: OTWelcomeRA">Drag & Drop</div>
        <div v-else v-for="(dayItem, idx) in item.dayList" :key="idx"
              @dragstart="startDrag($event, dayItem, item.id)"
              draggable="true"
        >
          <plan-check-list
              :img="dayItem.img"
              :title="dayItem.title"
              :contentId="dayItem.contentId"
              @storeCost="setCost"
            ></plan-check-list>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PlanCheckList from "./PlanCheckList.vue";
// import draggable from "vuedraggable";

export default {
  name: "PlanCheckAllDayView",
  components: {
    PlanCheckList,
    // draggable,
  },
  data() {
    return {
      areaItems: [],
      costList: [],
      eachDayList: [],
      lists: [], // 장소의 div 이동에 따라 동적으로 저장하는 배열
      flag: false, // list값 자체의 변화를 감지하기위해 설정하는 의미없는 변수
    };
  },
  props: {
    selectAreaItems: Array,
  },
  watch: {
    // lists 배열의 변화를 감지
    flag() {
      // console.log(this.lists); // 디버깅
      // Drag & Drop 이벤트가 발생할 때, lists의 id 컬럼이 해당 dday에 맞게 변동됨
      // 변경이 생길 때 마다, 부모에게 $emit 이벤트 발생

      this.$emit("resultItems", this.lists, this.costList);
    },
  },
  created() {
    // TODO: props로 전달받은 값을 lists 배열안에 객체 형태로 저장
    // (1) 0번째 인덱스에는 모든 data를 넣고 초기화
    this.lists.push({
      "id": 0,
      "dayList": this.selectAreaItems.slice(1), // 0번째 배열원소는 제외
    });

    // (2) 1번째 인덱스부터는 빈배열 생성
    for (let i = 1; i <= this.selectAreaItems[0].period + 1; i++) {
      this.lists.push({
        "id": i,
        "dayList": [],
      })
    }

    // console.log(this.lists.length); // 디버깅
    // console.log(this.lists); // 디버깅
  },
  mounted() {
    // console.log(this.selectAreaItems);
    for (let i = 1; i < this.selectAreaItems.length; i++) {
      this.areaItems.push(this.selectAreaItems[i]);
    }
    // console.log(this.areaItems);
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

      // 리스트의 변화를 주기위해 사용 (의미없는 변수 변경)
      this.flag = !this.flag;
      // console.log(this.costList);
    },
    // 각각의 div들이 데이터를 전달하는 이벤트 트리거 메서드
    dragStart(event) {
      event.dataTransfer.setData("text/plain", event.target.dataset.contentId);

      // console.log(this.eachDayList);
    },
    // dataTransfer 사용해 드롭된 객체의 contentID를 가져와 이 객체를 새 div의 배열로 이동
    // 기존 배열에서는 해당 객체 제거
    // drop(event) {
    //   const itemContentId = event.dataTransfer.getData("text/plain");
    //   const item = this.draggableItems.find((item) => item.contentId.toString() == itemContentId);
    //   if (item) {
    //     const colIdx = parseInt(event.currentTarget.getAttribute("data-column-index"));
    //     // 열 없으면 새 열 추가
    //     if (!this.eachDayList[colIdx]) {
    //       this.eachDayList[colIdx] = [];
    //     }
    //     // 해당 위치에 item 삽입
    //     this.eachDayList[colIdx].splice(0, 0, item);
    //     this.areaItems = this.areaItems.filter(
    //       (item) => item.contentId.toString() != itemContentId
    //     );
    //     console.log(this.eachDayList);
    //   }
    // },

    // TODO: startDrag 이벤트 메서드 작성
    startDrag(event, item) {
      event.dataTransfer.dropEffect = "move";
      event.dataTransfer.effectAllowed = "move";
      event.dataTransfer.setData("selectedItem", item.contentId);
    },

    // TODO: onDrop 이벤트 메서드 작성
    onDrop(event, colNum) {
      const selectedItem = Number(event.dataTransfer.getData("selectedItem"));

      // 리스트에서 선택된 아이템과 같은 contentId 값을 가진 요소를 찾아서 index를 반환
      let targetIdx;
      let targetItem;

      this.lists.forEach((obj, index) => {
        obj.dayList.forEach((ob) => {
          if (ob.contentId == selectedItem) {
            targetIdx = index;
            targetItem = ob;
          }
        })
      });

      // drop이 된 <div> index(=colNum)를 받아 리스트에 추가
      // 기존 리스트에서는 요소를 삭제 (splice() 사용)
      this.lists[colNum].dayList.push(targetItem);
      this.lists[targetIdx].dayList.splice(this.lists[targetIdx].dayList.indexOf(targetItem), 1);

      // 리스트의 변화를 주기위해 사용 (의미없는 변수 변경)
      this.flag = !this.flag;
    }
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
.no_item {
  font-size: 15px;
  color: #0085FF;
  margin: auto;
  width: auto;
  height: 80px;
  line-height: 80px;
}
</style>
