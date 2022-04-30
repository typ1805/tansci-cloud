import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'

const url: string = 'http://127.0.0.1:8001';

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue()],
    resolve: {
        alias: {
            '@': path.resolve(__dirname, 'src'),
        }
    },
    css: {
        preprocessorOptions: {
            scss: {
                additionalData: `@use "@/styles/element/index.scss" as *;`,
            },
        },
    },
    server: {
        port: 3000,
        host: '0.0.0.0',
        proxy: {
            '/auth': {
                target: url,
                changeOrigin: true,
                rewrite: (path) => path
            },
            '/admin': {
                target: url,
                changeOrigin: true,
                rewrite: (path) => path
            },
        }
    }

})
