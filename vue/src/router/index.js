import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AdminLayout from "@/views/AdminLayout";
const routes = [
  {
    path: '/',
    redirect:"/login",
  },
  {
    path: '/login',
    name: 'login',
    component: () => import("@/views/Login"),
    meta: {
      title: '欢迎登录商用画屏发布系统'
    }
  },
  {
    path: '/adminCreateProgram',
    name: 'adminCreateProgram',
    component: () => import("@/components/AdminCreateProgram"),
    meta: {
      title: '欢迎登录商用画屏发布系统'
    }
  },
  {
    path: '/admin',
    component: AdminLayout,
    redirect: "/adminIndex",
    children: [
      {
        path: '/adminIndex',
        name: 'adminIndex',
        component: () => import("@/components/AdminIndex"),
        meta: {
          title: '欢迎登录商用画屏发布系统'
        }
      },
      {
        path: '/adminManage',
        name: 'adminManage',
        component: () => import("@/components/AdminManage"),
        meta: {
          title: '欢迎登录商用画屏发布系统'
        }
      },
      {
        path: '/adminGrouping',
        name: 'adminGrouping',
        component: () => import("@/components/AdminGrouping"),
        meta: {
          title: '欢迎登录商用画屏发布系统'
        }
      },
      {
        path: '/adminPlanList',
        name: 'adminPlanList',
        component: () => import("@/components/AdminPlanList"),
        meta: {
          title: '欢迎登录商用画屏发布系统'
        }
      },
      {
        path: '/adminPlanCheck',
        name: 'adminPlanCheck',
        component: () => import("@/components/AdminPlanCheck"),
        meta: {
          title: '欢迎登录商用画屏发布系统'
        }
      },
      {
        path: '/adminEquipment',
        name: 'adminEquipment',
        component: () => import("@/components/AdminEquipment"),
        meta: {
          title: '欢迎登录商用画屏发布系统'
        }
      },
      {
        path: '/adminReleaseProgram',
        name: 'adminReleaseProgram',
        component: () => import("@/components/AdminReleaseProgram"),
        meta: {
          title: '欢迎登录商用画屏发布系统'
        }
      },
      {
        path: '/adminAnnouncement',
        name: 'adminAnnouncement',
        component: () => import("@/components/AdminAnnouncement"),
        meta: {
          title: '欢迎登录商用画屏发布系统'
        }
      },
      {
        path: '/adminCreateAnnouncement',
        name: 'adminCreateAnnouncement',
        component: () => import("@/components/AdminCreateAnnouncement"),
        meta: {
          title: '欢迎登录商用画屏发布系统'        }
      },
      {
        path: '/adminMaterialManage',
        name: 'adminMaterialManage',
        component: () => import("@/components/AdminMaterialManage"),
        meta: {
          title: '欢迎登录商用画屏发布系统'
        }
      },

      {
        path: '/adminMaterialDetail',
        name: 'adminMaterialDetail',
        component: () => import("@/components/AdminMaterialDetail"),
        meta: {
          title: '欢迎登录商用画屏发布系统'
        }
      },
      {
        path: '/adminCreatePlan',
        name: 'adminCreatePlan',
        component: () => import("@/components/AdminCreatePlan"),
        meta: {
          title: '欢迎登录商用画屏发布系统'
        }
      },
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
