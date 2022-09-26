<script setup lang="ts">
    import {onBeforeMount, onDeactivated, onMounted, reactive, ref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox } from 'element-plus'
    import Submenu from "@/components/Submenu.vue"
    import TabsMenu from "@/components/TabsMenu.vue"
    import {timeFormate} from '@/utils/utils'
    import {useRouter} from 'vue-router'
    import {useUserStore, useTokenStore, useMenuStore} from '@/store/settings'
    import screenfull from 'screenfull'
    import {logout} from '@/api/login'
    
	const userStore = useUserStore();
    const tokenStore = useTokenStore();
    const menuStore = useMenuStore();
    const router = useRouter()
    const nowTimes = ref('')
    const logo = new URL('../../assets/image/logo.png', import.meta.url).href
    const username = userStore.getUser.username == null ? '未登录':userStore.getUser.username
    const state = reactive({
        isCollapse: false,
        asideWidth: '240px',
        defaultHeight: null,
        routers: [],
        iframe: {
            isIframe: true,
            src: '',
        },
    })
    const {
        isCollapse,asideWidth,defaultHeight,routers,iframe
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight);
    })

    onMounted(()=>{
        // 获取菜单
        let routers:any = [];
        let _routes = menuStore.getMenu;
        if(_routes){
            _routes.forEach((item:any)=>{
                if (item.children){
                    routers.push(item)
                }
            })
            state.routers = routers;
        }

        onNowTimes();

        window.addEventListener('resize', onDefaultHeight);
    })

    onDeactivated(()=>{
        window.removeEventListener('resize', onDefaultHeight, false)
    })

    const onDefaultHeight = () =>{
        state.defaultHeight = window.innerHeight
    }

    // 嵌套
    const onNestedLink = (e) =>{
        state.iframe = {
            isIframe: true,
            src: e.url
        }
    }

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
                    location.reload();
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

    const onSelect = (e:any) =>{
        if(e){
            state.iframe.isIframe = false
        }
    }

    // 全屏展示
    const onFullScreen = () =>{
        if(!screenfull.isEnabled){
            ElMessage.warning('您的浏览器版本过低不支持全屏显示！');
            return false;
        }
        screenfull.toggle();
    }
</script>
<template>
    <div class="layout-container">
        <el-container>
            <el-aside :style="{height: defaultHeight+'px'}" :width="asideWidth">
                <div class="logo" @click="onCollapse">
                    <el-image :src="logo"></el-image>
                    <div v-show="!isCollapse" class="logo-title">Tansci Cloud</div>
                </div>
                <el-menu router :default-active="$route.path" :collapse="isCollapse" @select="onSelect" text-color="#242e42" active-text-color="#2F9688" background-color="transparent">
                    <template v-for="item in routers" :key="item">
                        <!-- 类型(type)：0、菜单，1、按钮，2、链接，3、嵌套页面 -->
                        <el-menu-item v-if="!item.children || item.children.length == 1" :index="item.type == 0 ? item.path : ''">
                            <el-icon v-if="item.icon" style="vertical-align: middle;">
                                <component :is="item.icon"></component>
                            </el-icon>
                            <span v-if="item.type == 0" style="vertical-align: middle;">{{item.chineseName}}</span>
                            <a v-else-if="item.type == 2" :href='item.url' target='_blank' style="vertical-align: middle;text-decoration: none;color:#242e42;">{{item.chineseName}}</a>
                            <span v-else-if="item.type == 3" @click="onNestedLink(item)" style="vertical-align: middle;">{{item.chineseName}}</span>
                        </el-menu-item>
                        <Submenu v-else :data="item" @onNestedLink="onNestedLink"></Submenu>
                    </template>
                </el-menu>
            </el-aside>
            <el-container>
                <el-header height="50">
                    <div class="header-content">
                        <div class="content-item">
                            <span style="vertical-align: middle;">{{nowTimes}}</span>
                        </div>
                        <div class="content-item">
                            <el-tooltip content="全屏">
                                <el-icon @click="onFullScreen" :size="20" style="vertical-align: middle;"><FullScreen /></el-icon>
                            </el-tooltip>
                        </div>
                        <div class="content-item">
                            <el-dropdown style="line-height: 50px;">
                                <span class="el-dropdown-link" style="color:var(--theme);">
                                    <span style="cursor:pointer;vertical-align: middle;">{{username}}</span>
                                    <el-icon style="vertical-align: middle;"><arrow-down /></el-icon>
                                </span>
                                <template #dropdown>
                                    <el-dropdown-menu>
                                        <el-dropdown-item @click="onLogout" icon="SwitchButton">退出</el-dropdown-item>
                                    </el-dropdown-menu>
                                </template>
                            </el-dropdown>
                        </div>
                    </div>
                    <TabsMenu></TabsMenu>
                </el-header>
                <el-main :style="{height: (defaultHeight-100)+'px'}">
                    <router-view v-show="!iframe.isIframe" style="margin-right: 0.2rem; padding-bottom: 1rem;"/>
                    <iframe v-show="iframe.isIframe" :src="iframe.src" :style="{height:defaultHeight-100+'px', marginRight: '0.4rem', paddingBottom: '1rem'}" width="100%" frameborder="0"></iframe>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<style lang="scss">
    .layout-container{
        // background-image: radial-gradient(#ddf8e7 0%, #FAFDFE 80%, white 100%);
        background-image: radial-gradient(farthest-side at left top,#fcfbf8 0%, #e9f3ed 40%,#eef7f1 80%,#ffffff 100%);
        .el-aside{
            height: 100%;
            transition: all .5s;
            overflow-y: auto;
            overflow-x: hidden;
            .logo{
                display: flex;
                height: 50px;
                line-height: 50px;
                padding-left: 1rem;
                cursor: pointer;
                .logo-title{
                    font-weight: 700;
                    font-size: 18px;
                    color: var(--theme);
                }
            }
            .el-menu{
                border: none;
                .el-menu-item, .el-sub-menu__title{
                    height: 40px;
                    line-height: 40px;
                }
                .el-sub-menu__title:hover{
                    background: transparent !important;
                    color: var(--theme2) !important;
                }
                .el-menu-item:hover{
                    background: transparent !important;
                    color: var(--theme2) !important;
                }
            }
        }
        .el-aside::-webkit-scrollbar{
            width: 0px;
        }
        .el-header{
            color: var(--theme);
            padding: 0;
            .header-content{
                display: flex;
                justify-content: right;
                line-height: 50px;
                .content-item{
                    padding-right: 1rem;
                }
            }
        }
        .el-main{
            padding: 0;
            overflow-x: hidden;
            overflow-y: auto;
        }
        .el-main::-webkit-scrollbar{
            width: 0px;
        }
    }
</style>