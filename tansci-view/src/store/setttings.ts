import { defineStore } from 'pinia'

// 存储用户信息
export const useUserStore = defineStore('user', {
    state: () => ({
        username: '',
        loginTime: '',
    }),
    getters: {},
    actions: {
        setUser (data:any) {
            this.username = data.username
            this.loginTime = data.loginTime
            sessionStorage.setItem('user', JSON.stringify({
                username: data.username,
                loginTime: data.loginTime
            } as any))
        },
        delUser () {
            sessionStorage.clear()
        }
    }
})

// 存储 token
export const useTokenStore = defineStore('token', {
    state: () => ({
        token: '',
    }),
    getters: {},
    actions: {
        setToken (data:any) {
            this.token = data
            sessionStorage.setItem('token', data)
        },
        delToken () {
            sessionStorage.clear()
        }
    }
})

// 存储菜单信息
export const useMenuStore = defineStore('menu', {
    state: () => ({
        menu: '',
    }),
    getters: {},
    actions: {
        setMenu (data:any) {
            this.menu = data
            sessionStorage.setItem('menu', JSON.stringify(data))
        },
        delMenu () {
            sessionStorage.clear()
        }
    }
})
