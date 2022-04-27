import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import * as path from 'path';

const url = 'http://127.0.0.1:8001';

// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src')
    }
  },
  plugins: [vue()],
  server: {
    headers: {
      'Access-Control-Allow-Origin': '*',
    },
    port: 3000,
    hmr: {
      host: '127.0.0.1',
      port: 3000
    },
    // 设置 https 代理
    proxy: {
      '/tansci': {
          target: url,
          changeOrigin: true,
          rewrite: (path: string) => path.replace(/^\/tansci/, '')
      }
    }
  }
})
