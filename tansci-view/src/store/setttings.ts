import { defineStore } from 'pinia'

// 存储用户信息
export const useUserStore = defineStore({
    id: 'user',
    state: () => ({
        username: '',
        loginTime: '',
    }),
    getters: {},
    persist: {
        enabled: true,
        strategies: [
            {
                storage: sessionStorage, 
                paths: ['username','loginTime']
            }
        ]
    },
    actions: {
        setUser (data:any) {
            this.username = data.username
            this.loginTime = data.loginTime
        },
        delUser () {
            sessionStorage.clear()
        }
    }
})

// 存储 token
export const useTokenStore = defineStore({
    id: 'token',
    state: () => ({
        token: '',
    }),
    getters: {},
    persist: {
        enabled: true,
        strategies: [
            {storage: sessionStorage, paths: ['token']}
        ]
    },
    actions: {
        setToken (data:any) {
            this.token = data
        },
        delToken () {
            sessionStorage.clear()
        }
    }
})

// 存储菜单信息
export const useMenuStore = defineStore({
    id: 'menu',
    state: () => ({
        menu: '',
    }),
    getters: {},
    persist: {
        enabled: true,
        strategies: [
            {storage: sessionStorage, paths: ['menu']}
        ]
    },
    actions: {
        setMenu (data:any) {
            this.menu = data
        },
        delMenu () {
            sessionStorage.clear()
        }
    }
})
