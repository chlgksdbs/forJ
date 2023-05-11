import Vue from "vue";
import VueRouter from "vue-router";
import MyPageView from "../views/MyPageView.vue";
import MyPageModify from "../components/mypage/MyPageModify.vue";
import LoginView from "@/views/LoginView.vue";
import JoinView from "@/views/JoinView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/mypage",
    name: "mypage",
    component: MyPageView,
    children: [
      {
        path: "/modify",
        name: "mypagemodify",
        component: MyPageModify,
      },
    ],
  },
  {
    path: "/join",
    name: "join",
    component: JoinView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
