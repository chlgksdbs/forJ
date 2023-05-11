<<<<<<< HEAD
import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MyPageView from '../views/MyPageView.vue'
import MyPageModify from '../components/mypage/MyPageModify.vue'
=======
import Vue from "vue";
import VueRouter from "vue-router";
import LoginView from "@/views/LoginView.vue";
import JoinView from "@/views/JoinView.vue";
>>>>>>> b1d47859236ac116c1a68c15af9f0e0a74b3af3e

Vue.use(VueRouter);

const routes = [
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
<<<<<<< HEAD
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: MyPageView,
    children: [
      {
        path: '/modify',
        name: 'mypagemodify',
        component: MyPageModify,
      },
    ]
  }
]
=======
    path: "/join",
    name: "join",
    component: JoinView,
  },
];
>>>>>>> b1d47859236ac116c1a68c15af9f0e0a74b3af3e

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
