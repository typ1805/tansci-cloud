<script setup lang="ts">
    import {onMounted, reactive, toRefs} from 'vue'
    import {ElMessage} from 'element-plus'
    import {sourceList, sqlExecutor} from '@/api/data/dataRetrieval'

    const state = reactive({
        searchForm: {
            id: null,
            sql: null,
            offset: null,
            size: null,
        },
        sourceData: [],
        loading: false,
        result: null,
    })

    const {
        searchForm,sourceData,loading,result
    } = toRefs(state)

    onMounted(()=>{
        onSourceData();
    })

    const onSourceData = () =>{
        sourceList({}).then(res=>{
            state.sourceData = res.result
        })
    }

    const onSubmit = () =>{
        if(!state.searchForm.id || !state.searchForm.sql){
            ElMessage.warning('请求参数有误，数据源和SQL语句不能!');
            return false;
        }
        state.loading = true;
        sqlExecutor(state.searchForm).then(res=>{
            state.result = res.result;
            state.loading = false;
            ElMessage.success('执行成功!');
        })
    }

</script>
<template>
    <el-card class="sqlexecutor-container" shadow="always">
        <div class="executor-card">
            <el-card shadow="never" :body-style="{ padding: '0px' }">
                <template #header>
                    <div class="executor-card-header">
                        <el-select v-model="searchForm.id" placeholder="请选择数据源">
                            <el-option v-for="item in sourceData" :key="item.id" :label="item.name" :value="item.id" />
                        </el-select>
                        <el-button @click="onSubmit" type="success" :loading="loading">执行</el-button>
                    </div>
                </template>
                <div>
                    <el-input v-model="searchForm.sql" type="textarea" rows="10" maxlength="1000" clearable placeholder="请输入正确的SQL语句..." show-word-limit/>
                </div>
            </el-card>
            <el-card shadow="never" style="border-top: none;">
                <template #header>
                    <span>执行结果</span>
                </template>
                <div style="height:300px;">
                    {{result}}
                </div>
            </el-card>
        </div>
    </el-card>
</template>
<style scoped lang="scss">
    .sqlexecutor-container{
        .executor-card{
            .executor-card-header{
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
        }
    }
</style>