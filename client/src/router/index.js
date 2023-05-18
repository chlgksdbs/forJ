import Vue from "vue";
import VueRouter from "vue-router";

import AppMain from "@/views/AppMain.vue";
import AppLogin from "@/views/AppLogin.vue";
import AppJoin from "@/views/AppJoin.vue";
import AppUser from "../views/AppUser.vue";
import AppBoard from "@/views/AppBoard.vue";
import AppPlan from "@/views/AppPlan.vue";

// import store from "@/store";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/login",
    name: "login",
    component: AppLogin,
  },
  {
    path: "/join",
    name: "join",
    component: AppJoin,
  },
  {
    path: "/mypage",
    name: "mypage",
    component: AppUser,
    redirect: "/mypage/modify",
    children: [
      {
        path: "modify",
        name: "mypagemodify",
        component: () => import("@/components/User/UserModify"),
      },
      {
        path: "cart",
        name: "mypagecart",
        component: () => import("@/components/User/UserCart"),
      },
      {
        path: "qna",
        name: "mypageqna",
        component: () => import("@/components/User/UserInquiry"),
      },
      {
        path: "like",
        name: "mypagelike",
        component: () => import("@/components/User/UserLike"),
      },
      {
        path: "myboard",
        name: "mypagemyboard",
        component: () => import("@/components/User/UserBoard"),
      },
      {
        path: "mycomment",
        name: "mypagemycomment",
        component: () => import("@/components/User/UserComment"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: AppBoard,
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import("@/components/Board/BoardList"),
      },
      {
        path: "write",
        name: "boardwrite",
        component: () => import("@/components/Board/BoardWrite"),
      },
      {
        path: "view/:boardId",
        name: "boardview",
        component: () => import("@/components/Board/BoardView"),
      },
    ],
  },
  {
    path: "/plan",
    name: "plan",
    component: AppPlan,
    redirect: "/plan/write",
    children: [
      {
        path: "write",
        name: "planwrite",
        component: () => import("@/components/Plan/PlanWrite"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
