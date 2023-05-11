import Vue from "vue";
import VueRouter from "vue-router";
import MainPageView from "@/views/MainPageView.vue";
import MyPageView from "../views/MyPageView.vue";
import MyPageModify from "../components/MyPage/MyPageModify.vue";
import LoginPageView from "@/views/LoginPageView.vue";
import JoinPageView from "@/views/JoinPageView.vue";
import BoardPageView from "@/views/BoardPageView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: MainPageView,
  },
  {
    path: "/login",
    name: "login",
    component: LoginPageView,
  },
  {
    path: "/join",
    name: "join",
    component: JoinPageView,
  },
  {
    path: "/mypage",
    name: "mypage",
    component: MyPageView,
    redirect: "/modify",
    children: [
      {
        path: "/modify",
        name: "mypagemodify",
        component: MyPageModify,
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: BoardPageView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
