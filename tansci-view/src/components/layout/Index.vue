<script setup lang="ts">
    import {onBeforeMount, onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessageBox} from 'element-plus'
    import Submenu from "@/components/Submenu.vue"
    import {timeFormate} from '@/utils/utils'
    import {useRouter} from 'vue-router'
    import {useUserStore, useTokenStore, useMenuStore} from '@/store/setttings'
    import {logout} from '@/api/login'

	const userStore = useUserStore();
    const tokenStore = useTokenStore();
    const menuStore = useMenuStore();
    const router = useRouter()
    const nowTimes = ref('')
    const username = userStore.$state.username == null ? '未登录':userStore.$state.username
    const state = reactive({
        isCollapse: false,
        asideWidth: '240px',
        defaultHeight: {
            height: ''
        },
        routers: [],
    })
    const {
        isCollapse,asideWidth,defaultHeight,routers,
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight.height = (document.body.clientHeight || document.documentElement.clientHeight) + "px";
    })

    onMounted(()=>{
        // 获取菜单
        let routers:any = [];
        let _routes = router.options.routes;
        _routes.forEach(item=>{
            if (item.type === 1){
                routers.push(item)
            }
        })
        state.routers = routers;

        window.onresize = () => {
            return (() => {
                state.defaultHeight.height = (document.body.clientHeight || document.documentElement.clientHeight) + "px";
            })()
        }

        onNowTimes();
    })

    // 退出
    const onLogout = () =>{
        ElMessageBox.confirm('您确定要退出吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            console.log("退出")
            logout().then((res:any) =>{
                if(res){
                    // 清除相关缓存信息
                    userStore.delUser();
                    tokenStore.delToken();
                    menuStore.delMenu();
                    // 获取菜单
                    router.push({path: 'login'});
                }
            }).catch(()=>{
            })
            
        }).catch(e=>{
            console.log(e)
        })
    }

    // 当前时间
    const onNowTimes = () =>{
        setInterval(()=>{
            nowTimes.value = timeFormate(new Date());
        },1000);
    }

    const onCollapse = () => {
        if (state.isCollapse) {
            state.asideWidth = '240px'
            state.isCollapse = false
        } else {
            state.isCollapse = true
            state.asideWidth = '64px'
        }
    }
</script>
<template>
    <div class="layout-container">
        <el-container>
            <el-aside :style="defaultHeight" :width="asideWidth">
                <el-card v-show="!isCollapse" shadow="always">
                    <div>
                        <el-icon :size="26" style="vertical-align: middle;"><OfficeBuilding/></el-icon>
                        <span style="vertical-align: middle;padding-left:1rem;">TANSCI</span>
                    </div>
                </el-card>
                <el-menu router :default-active="$route.path" :collapse="isCollapse" @select="onSelect" text-color="#242e42" active-text-color="#2F9688" background-color="var(--bg1)">
                    <template v-for="item in routers" :key="item">
                        <el-menu-item v-if="!item.children || item.children.length == 1" :index="item.path">
                            <el-icon v-if="item.icon" style="vertical-align: middle;">
                                <component :is="item.icon"></component>
                            </el-icon>
                            <span style="vertical-align: middle;">{{item.chineseName}}</span>
                        </el-menu-item>
                        <Submenu v-else :data="item"></Submenu>
                    </template>
                </el-menu>
            </el-aside>
            <el-container>
                <el-header height="60">
                    <div>
                        <el-button @click="onCollapse" type="text" :icon="isCollapse?'Grid':'Menu'" >菜单折叠</el-button>
                    </div>
                    <div>
                        <el-icon :size="16" color="#55bc8a" style="vertical-align: middle;padding-right:0.2rem;">
                            <Timer/>
                        </el-icon>
                        <span style="padding-right: 2rem;vertical-align: middle;">{{nowTimes}}</span>
                        <el-dropdown style="line-height: 60px;">
                            <span class="el-dropdown-link" style="color:var(--theme);">
                                <span style="cursor:pointer;vertical-align: middle;">{{username}} 欢迎您</span>
                                <el-icon style="vertical-align: middle;"><arrow-down /></el-icon>
                            </span>
                            <template #dropdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item @click="onLogout" icon="SwitchButton">退出</el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>
                    </div>
                </el-header>
                <el-main>
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<style scoped lang="scss">
    .layout-container{
        .el-aside{
            height: 100%;
            transition: all .5s;
            background: var(--bg1);
            overflow-y: auto;
            overflow-x: hidden;
            .el-card{
                margin: 0.4rem 0.6rem;
                background-color: var(--theme);
                color:#fff;
                .el-card__body{
                    padding: 0.5rem 2rem;
                }
            }
            .el-menu{
                border: none;
                .el-menu-item, .el-sub-menu__title{
                    height: 40px;
                    line-height: 40px;
                }
                .el-sub-menu__title:hover{
                    background: var(--bg1) !important;
                }
                .el-menu-item:hover{
                    background: var(--bg1) !important;
                }
            }
        }
        .el-aside::-webkit-scrollbar{
            width: 0px;
        }
        .el-header{
            display: flex;
            justify-content: space-between;
            line-height: 60px;
            color: var(--theme);
            background: var(--bg1);
            border: 1px transparent solid;
            border-image: linear-gradient(to right, var(--bg1),#DCDFE6, var(--bg1)) 1 10;
            box-shadow: 0 4px 8px 0 rgba(36,46,66,.06)!important;
        }
        .el-main{
            padding: 0.6rem 0 0 0;
            overflow-x: hidden;
            overflow-y: auto;
            background: var(--bg1);
        }
        .el-main::-webkit-scrollbar{
            width: 0px;
        }
    }
</style>