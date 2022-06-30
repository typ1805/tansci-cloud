<script setup lang="ts">
    import {onBeforeMount, onDeactivated, onMounted, reactive, ref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox } from 'element-plus'
    import Submenu from "@/components/Submenu.vue"
    import MenuTag from "@/components/MenuTag.vue"
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
    const menuTag = ref(null)
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
        menuTag.value.onSelected(e)
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
                <el-card v-show="!isCollapse" shadow="always">
                    <div>
                        <el-icon :size="26" style="vertical-align: middle;"><OfficeBuilding/></el-icon>
                        <span style="vertical-align: middle;padding-left:1rem;">TANSCI CLOUD</span>
                    </div>
                </el-card>
                <el-menu router :default-active="$route.path" :collapse="isCollapse" @select="onSelect" text-color="#242e42" active-text-color="#2F9688" background-color="var(--bg1)">
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
                        <div>
                            <el-icon @click="onCollapse" :size="20" style="vertical-align: middle; cursor: pointer;">
                                <component :is="isCollapse?'Expand':'Fold'"></component>
                            </el-icon>
                            <el-icon :size="18" color="#55bc8a" style="vertical-align: middle;padding: 0 0.2rem 0 1rem;">
                                <Timer/>
                            </el-icon>
                            <span style="padding-right: 2rem;vertical-align: middle;">{{nowTimes}}</span>
                        </div>
                        <div>
                            <el-tooltip content="深色">
                                <el-icon :size="20" style="vertical-align: middle; padding-right: 1rem;"><Moon /></el-icon>
                            </el-tooltip>
                            <el-tooltip content="全屏">
                                <el-icon @click="onFullScreen" :size="20" style="vertical-align: middle; padding-right: 1rem;"><FullScreen /></el-icon>
                            </el-tooltip>
                            <el-dropdown style="line-height: 50px;">
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
                    </div>
                    <div class="header-menu">
                        <MenuTag ref="menuTag" :size="'default'"></MenuTag>
                    </div>
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
                    padding: 1.2rem 2rem;
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
                    color: #2F9688 !important;
                }
                .el-menu-item:hover{
                    background: var(--bg1) !important;
                    color: #2F9688 !important;
                }
            }
        }
        .el-aside::-webkit-scrollbar{
            width: 0px;
        }
        .el-header{
            color: var(--theme);
            background: var(--bg1);
            padding-left: 0;
            .header-content{
                display: flex;
                justify-content: space-between;
                line-height: 50px;
                // border: 1px transparent solid;
                // border-image: linear-gradient(to right, var(--bg1),#DCDFE6, var(--bg1)) 1 10;
                // box-shadow: 0 4px 8px 0 rgba(36,46,66,.06)!important;
            }
            .header-menu{
                padding: 0.2rem 0 0.4rem 0;
                border: 1px transparent solid;
                border-image: linear-gradient(to right, var(--bg1),#DCDFE6, var(--bg1)) 1 10;
                box-shadow: 0 4px 8px 0 rgba(36,46,66,.06)!important;
            }
        }
        .el-main{
            padding: 0;
            overflow-x: hidden;
            overflow-y: auto;
            background: var(--bg1);
        }
        .el-main::-webkit-scrollbar{
            width: 0px;
        }
    }
</style>