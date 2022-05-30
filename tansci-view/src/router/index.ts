import { createRouter, createWebHistory } from "vue-router"
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

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


// 设置title
router.beforeEach((to: any, from: any, next) => {
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

    next()
})

router.afterEach(() => {
    // 关闭进度条
    NProgress.done()
})

// 格式化路由
import {menuList} from '@/api/system/menu'
import Layout from '@/components/layout/Index.vue'
import {useMenuStore} from '@/store/setttings'
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
                    // component: modules['../views' + item.url + '.vue']
                    component: () => import('@/views/' + item.url + '.vue')
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
            // item.component = modules['../views' + item.url + '.vue'];
            item.component = () => import('@/views/' + item.url + '.vue');
        }
        if(item.children && item.children.length && !flag){
            routerFilter(item.children)
        }
    })
    return data;
}

let aaa = true
router.beforeEach(async (to: any, from: any, next)=>{
    // let _routers = sessionStorage.getItem("menu");
    // if(sessionStorage.getItem('token') && !_routers){
    //     const menuStore = useMenuStore();
    //     menuList({type: 0,status: 1}).then((res:any)=>{
    //         const routes = routerFilter(res.result)
    //         menuStore.setMenu(routes)
    //         routes.forEach((item:any) => {
    //             router.addRoute("main", item)
    //         });

    //         next({ ...to, replace: true })
    //     })
    // } else {
    //     next()
    // }

    if(aaa){
        const menuStore = useMenuStore();
        menuStore.setMenu(data)
        data.forEach((item:any) => {
            router.addRoute("main", item)
        });
        aaa = false
        next({ ...to, replace: true })
    } else{
        next()
    }
})

export default router




const data = [
    // {
    //     path:'/index',
    //     name:'index',
    //     chineseName:'首页',
    //     englishName:'Index',
    //     icon:'HomeFilled',
    //     meta:{
    //         title:'首页'
    //     },
    //     redirect:'index',
    //     component: Layout,
    //     children:[
    //         {
    //             path:'/index',
    //             name:'index',
    //             icon:'HomeFilled',
    //             chineseName:'首页',
    //             englishName:'Index',
    //             meta:{
    //                 title:'首页'
    //             },
    //             component: modules['../views/Index.vue']
    //         }
    //     ]
    // },
    {
        name:'system',
        path:'/system',
        meta:{
            title:'系统管理'
        },
        redirect:'system',
        component: Layout,
        chineseName:'系统管理',
        englishName:'System',
        icon:'Setting',
        children:[
            {
                name:'user',
                chineseName:'用户管理',
                englishName:'User',
                icon:'UserFilled',
                path:'/user',
                meta:{
                    title:'用户管理'
                },
                component: modules['../views/system/User.vue']
            },
            {
                name:'role',
                chineseName:'权限管理',
                englishName:'Role',
                icon:'Coin',
                path:'/role',
                meta:{
                    title:'权限管理'
                },
                component: modules['../views/system/Role.vue']
            },
            {
                name:'menu',
                chineseName:'菜单管理',
                englishName:'Menu',
                icon:'Menu',
                path:'/menu',
                meta:{
                    title:'菜单管理'
                },
                component: modules['../views/system/Menu.vue']
            },
            {
                name:'log',
                chineseName:'日志管理',
                englishName:'Log',
                icon:'Cpu',
                path:'/log',
                meta:{
                    title:'日志管理'
                },
                component: modules['../views/system/Log.vue']
            },
            {
                name:'dicInfo',
                chineseName:'字典管理',
                englishName:'DicInfo',
                icon:'Reading',
                path:'/dicInfo',
                meta:{
                    title:'字典管理'
                },
                component: modules['../views/system/DicInfo.vue']
            }
        ],
    }
]