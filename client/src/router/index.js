import Vue from "vue";
import VueRouter from "vue-router";

import AppMain from "@/views/AppMain.vue";
import AppLogin from "@/views/AppLogin.vue";
import AppAuth from "@/views/AppAuth.vue";
import AppUser from "../views/AppUser.vue";
import AppBoard from "@/views/AppBoard.vue";
import AppPlan from "@/views/AppPlan.vue";

import store from "@/store";

Vue.use(VueRouter);

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
// vue router의 to, from, next 문법
const onlyAuthUser = async (to, from, next) => {
  // vuex에 존재하는 user 정보 불러오기
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];

  // vuex에 존재하는 token 유효성 정보 불러오기
  const checkToken = store.getters["memberStore/checkToken"];

  // sessionStorage에 존재하는 access token 정보 불러오기
  let token = sessionStorage.getItem("access-token");
  // console.log("로그인 처리 전", checkUserInfo, token);

  // (1) user 정보가 존재하며, access token이 유효한 경우
  if (checkUserInfo != null && token) {
    // console.log("토큰 유효성 체크하러 가자!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  // (2) 토큰이 유효하지 않거나, user 정보가 없는 경우
  if (!checkToken || checkUserInfo == null) {
    // 로그인 페이지로 이동
    alert("로그인이 필요한 페이지입니다.");
    router.push("/login");
  }
  // (3) 토큰과 user 정보 둘다 유효한 경우
  else {
    // console.log("로그인 성공!");
    next();
  }
};

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
    path: "/auth",
    name: "auth",
    component: AppAuth,
    children: [
      {
        path: "join",
        name: "join",
        component: () => import("@/components/Auth/AuthJoin"),
      },
      {
        path: "findid",
        name: "findid",
        component: () => import("@/components/Auth/AuthFindId"),
      },
      {
        path: "findpw",
        name: "findpw",
        component: () => import("@/components/Auth/AuthFindPw"),
      },
    ],
  },
  {
    path: "/mypage",
    name: "mypage",
    component: AppUser,
    beforeEnter: onlyAuthUser,
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
        path: "calendar",
        name: "mycalendar",
        component: () => import("@/components/User/UserCalendar"),
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
    redirect: "/board/list/1",
    children: [
      {
        path: "list/:currentPage",
        name: "boardlist",
        component: () => import("@/components/Board/BoardList"),
      },
      {
        path: "notice",
        name: "notice",
        component: () => import("@/components/Notice/NoticeList"),
      },
      {
        path: "qna",
        name: "boardqna",
        component: () => import("@/components/Board/BoardQna"),
      },
      {
        path: "write",
        name: "boardwrite",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/Board/BoardWrite"),
      },
      {
        path: "noticewrite",
        name: "noticewrite",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/Notice/NoticeWrite"),
      },
      {
        path: "modify/:boardId",
        name: "boardmodify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/Board/BoardModify"),
      },
      {
        path: "noticemodify/:noticeId",
        name: "noticemodify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/Notice/NoticeModify"),
      },
      {
        path: "view/:boardId",
        name: "boardview",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/Board/BoardView"),
      },
      {
        path: "noticeview/:noticeId",
        name: "noticeview",
        component: () => import("@/components/Notice/NoticeView"),
      },
    ],
  },
  {
    path: "/plan",
    name: "plan",
    component: AppPlan,
    beforeEnter: onlyAuthUser,
    redirect: "/plan/write",
    children: [
      {
        path: "write",
        name: "planwrite",
        component: () => import("@/components/Plan/PlanWrite"),
      },
      {
        path: "check",
        name: "plancheck",
        redirect: "check/day/all",
        component: () => import("@/components/Plan/PlanCheck"),
        children: [
          {
            path: "day/all",
            name: "plancheckall",
            component: () => import("@/components/Plan/Item/PlanCheckAllDayView"),
          },
          {
            path: "day/:dayNumber",
            name: "plancheckday",
            component: () => import("@/components/Plan/Item/PlanCheckDayView"),
          },
        ],
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
