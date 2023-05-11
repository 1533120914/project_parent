import Vue from 'vue'
import VueRouter from 'vue-router'
import {MessageBox} from "element-ui";

import LoginView from "@/views/LoginView";
import HomeView from "@/views/HomeView";
import TaskTypeView from "@/views/TaskTypeView";
import TaskTypeSaveView from "@/views/TaskTypeSaveView";
import TaskView from "@/views/TaskView";
import TaskApplyView from "@/views/TaskApplyView";
import FinishTaskView from "@/views/FinishTaskView";
import TaskDetailView from "@/views/TaskDetailView";
import TaskSaveView from "@/views/TaskSaveView";
import ModifyPasswordView from "@/views/ModifyPasswordView";
import PersonalView from "@/views/PersonalView";
import VolunteerView from "@/views/VolunteerView";
import StatisticsView from "@/views/StatisticsView";
Vue.use(VueRouter)

const routes = [
  { path:'/', component:LoginView },
  { path:'/home', component:HomeView, children: [
      { path:'/task-type', component:TaskTypeView },
      { path:'/task-type/save', component:TaskTypeSaveView },
      { path:'/task', component:TaskView },
      { path:'/task/apply', component:TaskApplyView },
      { path:'/task/finish/:id', component:FinishTaskView },
      { path:'/task/detail/:id', component:TaskDetailView },
      { path:'/task/save', component:TaskSaveView },
      { path:'/modify-password', component:ModifyPasswordView },
      { path:'/personal', component:PersonalView },
      { path:'/volunteer', component:VolunteerView },
      { path:'/statistics', component:StatisticsView },
    ]}
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由守卫 (路由拦截器, 拦截每一次的路由请求, 不要与http的拦截器搞混了)
// beforeEach()这个函数在进入每个路由之前触发
// to 是要去的路由对象
// from 是从哪来的路由对象
// next 是函数 next()放行 next('路由地址')去指定的路由地址
router.beforeEach((to, from, next) => {
  // 如果要去的路由地址是'/' (去登录界面)
  if (to.path == '/') {
    next() // 直接放行
    return
  }
  // 如果不是去登录界面, 就要校验他有没有登录
  // 1. 从存储中获取account
  let account = localStorage.getItem('account');
  // 2. 判断account是否为null
  if (account == null){ // account为null
    MessageBox.alert('请重新登录', '登录过期', {
      confirmButtonText: '确定',
      callback: action => {
        // 点确定后, 跳转去登录界面
        next('/')
      }
    })
  }else { // account不为null, 直接放行
    next();
  }
})

export default router
