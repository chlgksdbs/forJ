<template>
  <div id="AppAuth">
    <div id="logo_box">
      <router-link to="/">
        <img src="@/assets/img/logo.png" id="join_logo" alt="">
      </router-link>
    </div>
    <router-view></router-view>
    <the-footer></the-footer>
  </div>
</template>

<script>
import TheFooter from "@/components/TheFooter.vue";

export default {
  name: 'AppAuth',
  components: {
    TheFooter,
  },
  data() {
    return {};
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
div {
  margin: 8px 0 0;
}
#join_logo {
  width: 250px;
}
</style>