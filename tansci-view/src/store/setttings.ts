import { defineStore } from 'pinia'

export const userStore = defineStore({
    id: 'user',
    state: () => ({
        username: '',
        loginTime: '',
        token: '',
    }),
    getters: {},
    persist: {
        enabled: true,
        strategies: [
            {
                key: 'user', 
                storage: localStorage, 
                paths: ['user']
            }
        ]
    },
    actions: {
        setUser (data:any) {
            this.username = data.username,
            this.loginTime = data.loginTime,
            this.token = data.token
        }
    }
})
