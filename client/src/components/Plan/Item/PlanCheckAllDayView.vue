<template>
  <div>
    <div class="dayViewTitle">{{ dateFormat(selectAreaItems[0].start) }} ~ {{ dateFormat(selectAreaItems[0].end) }}</div>
    <!-- <plan-check-list v-for="item in selectAreaItems" :key="item.contentId" :img="item.img" :title="item.title" :contentId="item.contentId"></plan-check-list> -->
    <draggable>
      <plan-check-list v-for="item in areaItems" :key="item.contentId" :img="item.img" :title="item.title" :contentId="item.contentId" @storeCost="setCost"></plan-check-list>
    </draggable>
  </div>
</template>

<script>
import PlanCheckList from "./PlanCheckList.vue";
import draggable from 'vuedraggable';

export default {
  name: 'PlanCheckAllDayView',
  components: {
    PlanCheckList,
    draggable,
  },
  data() {
    return {
      areaItems: [],
      costList:[],
    };
  },
  props: {
    selectAreaItems: Array,
  },
  created() {
  },
  mounted() {
    // console.log(this.selectAreaItems);
    for (let i = 1; i < this.selectAreaItems.length; i++){
      this.areaItems.push(this.selectAreaItems[i]);
    }
    console.log(this.areaItems);

  },
  methods: {
    // 날짜 포맷을 변경하는 메서드
    dateFormat(date) {
        let dateFormat = 
            ((date.getMonth() + 1) < 9 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1))
            + '월' + ((date.getDate()) < 9 ? "0" + (date.getDate()) : (date.getDate())) + '일';

        return dateFormat;
    },
    //
    setCost(costInfo) {
      let flag = 0;
      for (let i = 0; i < this.costList.length; i++){
        if (this.costList[i].contentId == costInfo.contentId) {
          this.costList[i].cost = costInfo.cost;
          flag = 1;
          break;
        }
      }
      if (flag == 0) {
        this.costList.push(costInfo);
      }
      console.log(this.costList);
    }
  },
};
</script>

<style scoped>
  .dayViewTitle{
    width: 85%;
    margin: 10px;
    padding: 5px;
    border: 2px solid #6ab2ed;
  }
</style>