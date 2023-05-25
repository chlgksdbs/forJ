import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VCalendar from "v-calendar";
import "@/assets/css/fonts.css";

// VCalendar 사용
Vue.use(VCalendar);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  VCalendar,
  render: (h) => h(App),
}).$mount("#app");
