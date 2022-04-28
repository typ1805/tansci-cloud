<script setup lang="ts">
import { ref } from 'vue'
import { useUserStore } from '@/store/user'

const msg = ref('Hello')
const count = ref(710)
const userStore = useUserStore()
userStore.name = '李四'
</script>

<template>
    <h2>{{ msg }}</h2>
    <h2>{{ count }}</h2>
    <div class="divs">{{ userStore.name }}</div>
    <el-button type="primary">Primary</el-button>
    <el-icon color="#409EFC" class="no-inherit">
        <share />
    </el-icon>
</template>

<style scoped lang="scss">
    .divs{
        color: #00ff00;
    }
</style>