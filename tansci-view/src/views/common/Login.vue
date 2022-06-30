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
	const loginLogo = new URL('../../assets/image/login-left.png', import.meta.url).href

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
						router.push({path: 'index'});
					}
				}).catch(()=>{
					state.loginForm.verifyStatus = null;
					slidingVerify.value.onRefresh()
				})
			}
		});
	}

</script>
<template>
	<div class="login-container" :style="loginStyle">
		<el-card shadow="always">
			<div class="login-main">
				<div class="login-logo">
					<el-image :src="loginLogo"  style="width: 100%; height: 100%;"></el-image>
				</div>
				<div class="login-form">
					<el-form :model="loginForm" :rules="rules" ref="loginFormRef">
						<div class="login-form-title">欢迎登录</div>
						<el-form-item prop="username" :rules="[
								{required: true,message: '请输入用户名',trigger: 'blur'},
								{pattern: /^[a-zA-Z]\w{4,17}$/,message: '用户名式有误，请重新输入',trigger: 'blur'}]">
							<el-input v-model="state.loginForm.username" prefix-icon="Avatar" placeholder="请输入用户名称" style="width:100%"></el-input>
						</el-form-item>
						<el-form-item prop="password" :rules="[
								{required: true,message: '请输入密码',trigger: 'blur'},
								{pattern: /^[a-zA-Z]\w{5,17}$/,message: '密码格式有误，请重新输入',trigger: 'blur'}]">
							<el-input type="password" v-model="loginForm.password" prefix-icon="Lock" show-password placeholder="请输入密码" style="width:100%"></el-input>
						</el-form-item>
						<el-form-item prop="verifyStatus" :rules="[{required: true,message: '请拖动滑块验证',trigger: 'blur'}]">
							<SlidingVerify ref="slidingVerify" :status="loginForm.verifyStatus" :successFun="onVerifySuccess" :errorFun="onVerifyError"></SlidingVerify>
						</el-form-item>
						<el-form-item>
							<el-checkbox v-model="loginForm.keepPassword" label="记住密码"></el-checkbox>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" round @click="submit(loginFormRef)" :loading="loading" style="width:100%">登录</el-button>
						</el-form-item>
					</el-form>
					<el-divider><el-icon><star-filled /></el-icon></el-divider>
				</div>
			</div>
		</el-card>
	</div>
</template>
<style lang="scss" scoped="scoped">
	.login-container {
		background-image: url('../../assets/image/login-bg.svg');
		background-size: 100% 100%;
		height: 100%;
		width: 100%;
		display: flex;
		flex-wrap: wrap;
		justify-content: center;
		align-items: center;
		.el-card__body{
			padding: 0;
		}
		.login-main{
			display: flex;
			flex-wrap: wrap;
			justify-content: center;
			.login-logo{
				background: var(--theme);
				width: 20rem;
				padding: 8rem 5rem;
				border-top-right-radius: 38px;
				border-bottom-right-radius: 38px;
			}
			.login-form{
				width: 20rem;
				padding: 8rem 5rem;
				.login-form-title{
					font-size: 18px;
					font-weight: 700;
					text-align: center;
					padding-bottom: 2rem;
				}
			}
		}
	}
</style>
