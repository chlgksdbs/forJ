<template>
  <div id="UserPlanItem">
    <div class="item_wrap">
      <!-- (1) 여행 계획 전체 보기 -->
      <table class="item_table" v-if="flag">
        <colgroup>
          <col style="width: 25%">
          <col style="width: 75%">
        </colgroup>
        <tr class="item_title">
          <td>여행 일정</td>
          <td>{{ planItem.date }}</td>
        </tr>
        <tr>
          <td>여행 장소</td>
          <td>{{ planItem.location }}</td>
        </tr>
        <tr>
          <td>여행 경비</td>
          <td>{{ planItem.budget }}</td>
        </tr>
        <tr>
          <td>여행 계획</td>
          <td><button class="item_detail_btn" @click="flag = !flag">상세보기</button></td>
        </tr>
      </table>
      <!-- (2) 여행 계획 상세 보기 -->
      <table class="item_table" v-else>
        <colgroup>
          <col style="width: 25%">
          <col style="width: 75%">
        </colgroup>
        <tr class="item_title">
          <td>여행 일정</td>
          <td>{{ planItem.date }}</td>
        </tr>
        <tr>
          <td>여행 장소</td>
          <td>{{ planItem.location }}</td>
        </tr>
        <tr>
          <td>여행 경비</td>
          <td>{{ planItem.budget }}</td>
        </tr>
        <tr>
          <td>여행 계획</td>
          <td><button class="item_detail_btn" @click="flag = !flag">돌아가기</button></td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserPlanItem',
  components: {},
  data() {
    return {
      flag: true, // 여행 계획 상세보기 페이지 view에 대한 변수 값
    };
  },
  props: {
    planItem: Object,
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
.item_wrap {
  border: 1px solid #d9d9d9;
}
.item_table {
  width: 100%;
  border-collapse: collapse;
}
.item_title {
  background-color: #ddd;
  font-weight: bold;
}
.item_table tr td {
  border-bottom: 1px solid #d9d9d9;
  padding: 10px;
}
.item_detail_btn {
  cursor: pointer;
  background-color: #fff;
  border: 1px solid #ddd;
  padding: 3px 5px;
}
.item_detail_btn:hover {
  background-color: #ddd;
  color: #000;
}
#UserPlanItem {
  margin: 15px 10px;
}
</style>