<template>
  <div>
    <h2 class="modifyTitle">나의 장바구니</h2>
    <div class="modifyBox" style="overflow: scroll; overflow-x:hidden; height: 90%;">
      <user-plan-item v-for="item in planItems" :key="item.no" :planItem="item"></user-plan-item>
    </div>
  </div>
</template>

<script>
import UserPlanItem from "./Item/UserPlanItem.vue";

export default {
  components: {
    UserPlanItem,
  },
  data() {
    return {
      message: '',
      planItems: [
        {
          'no': 1,
          'date': '2023-05-21',
          'location': '전주',
          'budget': '50,000원',
        },
        {
          'no': 2,
          'date': '2023-05-29 ~ 2023-05-31',
          'location': '제주',
          'budget': '250,000원',
        },
      ],
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
.modifyBox{
  margin: 20px 0;
}
</style>