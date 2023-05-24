<template>
  <div>
    <h2 class="modifyTitle">1:1 문의</h2>
    <div class="modifyBox">
      <the-construction></the-construction>
    </div>
  </div>
</template>

<script>
import TheConstruction from "../TheConstruction.vue";

export default {
  components: {
    TheConstruction,
  },
  data() {
    return {
      message: '',
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
.modifyBox {
  margin: 20px 0;
}
</style>