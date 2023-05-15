import Vue from "vue";
import VueRouter from "vue-router";
import MainPageView from "@/views/MainPageView.vue";
import MyPageView from "../views/MyPageView.vue";
import MyPageModify from "../components/MyPage/MyPageModify.vue";
import MyPageCart from "../components/MyPage/MyPageCart.vue";
import MyPageQnA from "../components/MyPage/MyPageQnA.vue";
import MyPageLike from "../components/MyPage/MyPageLike.vue";
import MyPageMyBoard from "../components/MyPage/MyPageMyBoard.vue";
import MyPageMyComment from "../components/MyPage/MyPageMyComment.vue";
import LoginPageView from "@/views/LoginPageView.vue";
import JoinPageView from "@/views/JoinPageView.vue";
import BoardPageView from "@/views/BoardPageView.vue";
import BoardDetailView from "@/views/BoardDetailView.vue";
import UserPlanView from "@/views/UserPlanView.vue";

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
    redirect: "/mypage/modify",
    children: [
      {
        path: "modify",
        name: "mypagemodify",
        component: MyPageModify,
      },
      {
        path: "cart",
        name: "mypagecart",
        component: MyPageCart,
      },
      {
        path: "qna",
        name: "mypageqna",
        component: MyPageQnA,
      },
      {
        path: "like",
        name: "mypagelike",
        component: MyPageLike,
      },
      {
        path: "myboard",
        name: "mypagemyboard",
        component: MyPageMyBoard,
      },
      {
        path: "mycomment",
        name: "mypagemycomment",
        component: MyPageMyComment,
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: BoardPageView,
  },
  {
    path: "/detail",
    name: "detail",
    component: BoardDetailView,
  },
  {
    path: "/plan",
    name: "plan",
    component: UserPlanView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
