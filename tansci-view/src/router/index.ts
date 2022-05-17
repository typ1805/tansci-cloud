import { createRouter, createWebHistory } from "vue-router"
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

// 路由按模块分类
import common from './common'
import dynamic from './dynamic'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        ...common,
        ...dynamic,
        {path:'/:pathMatch(.*)*', redirect:'/404'}
    ]
})

NProgress.inc(0.2)
NProgress.configure({ easing: 'ease', speed: 600, showSpinner: false })

// 设置title
router.beforeEach((to: any, from: any, next: any) => {
    // 启动进度条
    NProgress.start()

    //未登陆
    if (!sessionStorage.getItem("token") && to.path !== "/login") {
        return next({ path: "/login" });
    };

    // 设置头部
    if (to.meta.title) {
        document.title = to.meta.title
    } else {
        document.title = "Tansci"
    }

    next()
})

router.afterEach(() => {
    // 关闭进度条
    NProgress.done()
})

export default router