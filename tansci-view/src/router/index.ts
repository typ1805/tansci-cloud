import { createRouter, createWebHistory } from "vue-router"
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import {menuList} from '@/api/system/menu'
import Layout from '@/components/layout/Index.vue'
import {useMenuStore} from '@/store/setttings'

// 路由按模块分类
import common from './common'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        ...common,
        {path:'/:pathMatch(.*)*', redirect:'/404'}
    ]
})

NProgress.inc(0.2)
NProgress.configure({ easing: 'ease', speed: 600, showSpinner: false })

// 格式化路由
const modules = import.meta.glob('../views/**/**/*.vue')
export function routerFilter(data:any) {
    data.forEach((item:any)=>{
        let flag = false;
        if(item.parentId == '0'){
            item.path = '/' + item.name;
            item.name = item.name;
            item.chineseName = item.chineseName;
            item.englishName = item.englishName;
            item.icon = item.icon;
            item.meta = { title: item.chineseName };
            item.redirect = item.name;
            item.component = Layout;
            if(!item.children || item.children.length == 0){
                item.children = [{
                    path: '/' + item.name,
                    name: item.name,
                    icon: item.icon,
                    chineseName: item.chineseName,
                    englishName: item.englishName,
                    meta: { title: item.chineseName },
                    component: modules['../views' + item.url + '.vue']
                }];
                flag = true;
            }
        } else {
            item.path = '/' + item.name;
            item.name = item.name;
            item.chineseName = item.chineseName;
            item.englishName = item.englishName;
            item.icon = item.icon;
            item.meta = { title: item.chineseName };
            item.component = modules['../views' + item.url + '.vue'];
        }
        if(item.children && item.children.length && !flag){
            routerFilter(item.children)
        }
    })
    return data;
}

// 设置title
router.beforeEach((to: any, from: any, next: any) => {
    // 启动进度条
    NProgress.start()

    //未登陆
    if (!sessionStorage.getItem('token') && to.path !== "/login") {
        return next({ path: "/login" });
    };

    // 设置头部
    if (to.meta.title) {
        document.title = to.meta.title
    } else {
        document.title = "Tansci"
    }

    // 解决子路由刷新出现空白
    let _routers = sessionStorage.getItem("menu");
    if(sessionStorage.getItem('token') && !_routers){
        const menuStore = useMenuStore();
        menuList({type: 0,status: 1}).then((res:any)=>{
            const routes = routerFilter(res.result)
            menuStore.setMenu(routes)
            routes.forEach((item:any) => {
                router.addRoute('main',item)
            });
            next({ ...to, replace: true })
        })
    } else {
        next()
    }
})

router.afterEach(() => {
    // 关闭进度条
    NProgress.done()
})

export default router