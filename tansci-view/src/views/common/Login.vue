<script setup lang="ts">
	import {onBeforeMount,reactive,ref,toRefs} from "vue"
	import type {FormInstance} from 'element-plus'
	import {useRouter} from 'vue-router'
	import SlidingVerify from '@/components/SlidingVerify.vue'
	import {useUserStore, useTokenStore} from '@/store/settings'
	import {login} from '@/api/login'

	const userStore = useUserStore();
	const tokenStore = useTokenStore();
	const router = useRouter()
	const loginFormRef = ref<FormInstance>() 
	let slidingVerify = ref()
	const logo = new URL('../../assets/image/logo.png', import.meta.url).href
	const loginLogo = new URL('../../assets/image/login-icon.png', import.meta.url).href

	const state = reactive({
		loading: false,
		loginStyle: {
			height: '',
		},
		loginForm: {
			username: '',
			password: '',
			verifyStatus: null,
			keepPassword: null,
		},
	})
	const {loading,loginStyle,loginForm} = toRefs(state)

	onBeforeMount(() => {
		state.loginStyle.height = (document.body.clientHeight || document.documentElement.clientHeight) + "px"
	})

	const onVerifySuccess = (e:any) =>{
		state.loginForm.verifyStatus = e;
	}
	const onVerifyError = (e:any) =>{
		state.loginForm.verifyStatus = e;
	}

	const submit = async (formEl: FormInstance | undefined) => {
		if (!formEl) return;
		await formEl.validate((valid)=>{
			if(valid){
				// 登录成功后设置token到缓存
				let param:any = {
					username: state.loginForm.username,
					password: state.loginForm.password
				}
				state.loading = true;
				login(param).then((res:any) =>{
					if(res){
						// 存储用户信息和token
						userStore.setUser(res.result);
						tokenStore.setToken(res.result.token);
						state.loading = false;
						router.push({path: 'main'});
					}
				}).catch(()=>{
					state.loginForm.verifyStatus = null;
					slidingVerify.value.onRefresh()
				})
			}
		});
	}

	function copyYear(){
		let date = new Date();
		return date.getFullYear();
	}

</script>
<template>
	<div class="login-container" :style="loginStyle">
		<div class="login-header">
			<div>
				<el-image :src="logo" style="width: 100%; height: 100%;"></el-image>
			</div>
			<div>
				<span class="title">Tansci Cloud</span>
			</div>
		</div>
		<div class="login-main">
			<div class="main-title">帐号登录</div>
			<div class="main-container">
				<div class="logo">
					<el-image :src="loginLogo"  style="width: 100%; height: 100%;"></el-image>
				</div>
				<div class="form">
					<el-form :model="loginForm" :rules="rules" size="large" ref="loginFormRef">
						<el-form-item prop="username" :rules="[
								{required: true,message: '请输入账号',trigger: 'blur'},
								{pattern: /^[a-zA-Z]\w{4,17}$/,message: '账号有误，请重新输入',trigger: 'blur'}]">
							<el-input v-model="state.loginForm.username" prefix-icon="Avatar" placeholder="请输入账号" style="width:100%"></el-input>
						</el-form-item>
						<el-form-item prop="password" :rules="[
								{required: true,message: '请输入密码',trigger: 'blur'},
								{pattern: /^[a-zA-Z]\w{5,17}$/,message: '密码有误，请重新输入',trigger: 'blur'}]">
							<el-input type="password" v-model="loginForm.password" prefix-icon="Lock" show-password placeholder="请输入密码" style="width:100%"></el-input>
						</el-form-item>
						<el-form-item prop="verifyStatus" :rules="[{required: true,message: '请拖动滑块验证',trigger: 'blur'}]">
							<SlidingVerify ref="slidingVerify" :status="loginForm.verifyStatus" :successFun="onVerifySuccess" :errorFun="onVerifyError"></SlidingVerify>
						</el-form-item>
						<el-form-item>
							<el-checkbox v-model="loginForm.keepPassword" label="记住密码"></el-checkbox>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="submit(loginFormRef)" :loading="loading" style="width:100%">登录</el-button>
						</el-form-item>
					</el-form>
				</div>
			</div>
		</div>
		<div class="login-footer">
			<div>
				<el-link href="https://typ1805.gitee.io" target="_blank">关于作者</el-link>
				<el-divider direction="vertical" />
				<el-link href="https://gitee.com/typ1805/tansci-cloud" target="_blank">源码地址 Gitee GitHub</el-link>
				<el-divider direction="vertical" />
				<el-link href="https://typ1805.gitee.io" target="_blank">联系作者</el-link>
			</div>
			<div class="copy">
				&copy; 2022- {{copyYear()}} Tansci Cloud 版权所有
			</div>
		</div>
	</div>
</template>
<style lang="scss" scoped="scoped">
	.login-container {
		background-image: radial-gradient( white 0%, #FAFDFE 10%, #ddf8e7 50%, #FAFDFE 90%, white 100%);
		// background-image: radial-gradient(#ddf8e7 00%, #FAFDFE 80%, white 100%);
		.login-header{
			width: 100%;
			height: 5rem;
			line-height: 5rem;
			display: flex;
			padding: 0 20%;
			.title{
				padding: 0 1rem;
				color: var(--t9);
				font-size: 20px;
				font-weight: 700;
			}
		}
		.login-main{
			height: 80%;
			.main-title{
				font-size: 32px;
				text-align: center;
				padding: 6rem 0;
			}
			.main-container{
				display: flex;
				flex-wrap: wrap;
				justify-content: center;
				align-items: center;
				.logo{
					width: 36rem;
					padding-right: 2rem;
					// transition: all .2s;
				}
				// .logo:hover{
				// 	transform: scaleY(1.1) scaleX(1.1) translateZ(0);
				// }
				.form{
					width: 26rem;
					padding-left: 4rem;
					border-left: 1px solid #c7f6da;
				}
			}
		}
		.login-footer{
			height: 100%;
			text-align: center;
			color: #606266;
			padding-top: 1.2rem;
			.copy{
				padding-top: 1rem;
			}
		}
	}
</style>
