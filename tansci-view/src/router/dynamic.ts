import Layout from '@/components/layout/Index.vue'
const modules = import.meta.glob('../views/**/**/*.vue')

export function routers(){
    let _routers = JSON.parse(sessionStorage.getItem("menu"));
    if(_routers && _routers.menu){
        return routerFilter(_routers.menu)
    }
    return [];
}
// 数据处理
export function routerFilter(data:any) {
    data.forEach(item=>{
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

export default routers();


// export default[
//     {
//         path: '/index',
//         name: 'index',
//         chineseName: '首页',
//         englishName: 'Index',
//         type: 1,
//         icon: "HomeFilled",
//         meta: {title: "首页"},
//         component: Layout,
//         children: [{
//             path: "/index",
//             name: "index",
//             chineseName: '首页',
//             englishName: 'Index',
//             icon: "HomeFilled",
//             meta: { title: "首页" },
//             component: () => import('@/views/Index.vue')
//         }]
//     },
// ]

