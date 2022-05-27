import Layout from '@/components/layout/Index.vue'
export default[
    {
        path: '/',
        name: 'index',
        chineseName: '首页',
        englishName: 'Index',
        type: 1,
        icon: "HomeFilled",
        meta: {title: "首页"},
        component: Layout,
        children: [{
            path: "/index",
            name: "index",
            chineseName: '首页',
            englishName: 'Index',
            icon: "HomeFilled",
            meta: { title: "首页" },
            component: () => import('@/views/Index.vue')
        }]
    }
]