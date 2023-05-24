<template>
  <div>
    <plan-select-list v-for="item in selectAreaItems" :key="item.contentId" :img="item.img" :title="item.title" :contentId="item.contentId"></plan-select-list>
  </div>
</template>

<script>
import PlanSelectList from "./PlanSelectList.vue";

export default {
  name: 'PlanCheckAllDayView',
  components: {PlanSelectList},
  data() {
    return {
      areaItems: [],
    };
  },
  props: {
    selectAreaItems : Array,
  },
  created() {
  },
  mounted() {
    console.log(this.selectAreaItems);
  },
  methods: {},
};
</script>

<style scoped></style>