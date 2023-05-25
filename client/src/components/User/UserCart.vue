<template>
  <div>
    <h2 class="modifyTitle">나의 장바구니</h2>
    <div class="modifyBox" style="overflow: scroll; overflow-x:hidden; height: 90%;">
      <user-plan-item v-for="item in planItems" :key="item.no" :planItem="item"></user-plan-item>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

import UserPlanItem from "./Item/UserPlanItem.vue";

const memberStore = "memberStore";

export default {
  components: {
    UserPlanItem,
  },
  data() {
    return {
      message: '',
      planItems: [],
    };
  },
  computed: {
    ...mapState(memberStore, ['userInfo']),
  },
  created() {

    // TODO: 여행 리스트 DB로부터 가져오기
    axios.get('http://localhost/plan/list/' + this.userInfo.userId)
      .then((resp) => {
        // console.log(resp.data[0]); // 디버깅
        for (let i = 0; i < resp.data.length; i++) {
          // TODO: 여행 상세정보 DB로부터 가져오기
          let sumBudget = 0;
          axios.get('http://localhost/plan/detail/' + resp.data[i].planId + '/' + this.userInfo.userId)
            .then((val) => {
              // console.log(val.data);
              for (let j = 0; j < val.data.length; j++) {
                sumBudget += val.data[j].planBudget;
              }
              this.planItems.push({
                'no': resp.data[i].planId,
                'date': this.dateFormat(resp.data[i].planStartdate) + ' ~ ' + this.dateFormat(resp.data[i].planEnddate),
                'location': resp.data[i].planId + '번째 여행',
                'budget': sumBudget,
              });
            });
          
        }
      });
  },
  methods: {
    // TODO: 날짜를 형식에 맞게 변환 (yyyy-mm-dd)
    dateFormat(date) {
      return date.substr(0, 10);
    },
  },
};
</script>

<style scoped>
.modifyBox{
  margin: 20px 0;
}
</style>