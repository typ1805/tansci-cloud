<script setup lang="ts">
    import {onMounted, reactive, toRefs} from 'vue'
    import {ElMessage} from 'element-plus'
    import {sourceList,getDbTables,getDbTableColumns} from '@/api/data/dataRetrieval'

    const state = reactive({
        searchForm: {
            id: null,
            type: null,
            value: null
        },
        sourceData: [],
        searchData: []
    })

    const {
        searchForm,sourceData,searchData
    } = toRefs(state)

    onMounted(()=>{
        onSourceData();
    })

    const onSourceData = () =>{
        sourceList({}).then(res=>{
            state.sourceData = res.result
        })
    }

    const onSearch = () =>{
        if(!state.searchForm.id || !state.searchForm.type){
            ElMessage.warning('检索类型或检索关键字不能为空!');
            return false;
        }

        if(state.searchForm.type == 1){
            // 数据表
            getDbTables({id: state.searchForm.id}).then(res=>{
                state.searchData = res.result
            })
        } else if(state.searchForm.type == 2){
            // 数据元
            getDbTableColumns({id: state.searchForm.id, tableName: state.searchForm.value}).then(res=>{
                state.searchData = res.result
            })
        }
    }

</script>
<template>
    <el-card class="retrieval-container" shadow="always">
        <div>
            <span>数据源：</span>
            <el-select v-model="searchForm.id" placeholder="请选择数据源" style="width: 200px;">
                <el-option v-for="item in sourceData" :key="item.id" :label="item.name" :value="item.id" />
            </el-select>
        </div>
        <div class="search-form">
            <el-input v-model="searchForm.value" size="large" placeholder="数据搜索" style="width: 50%">
                <template #prepend>
                    <el-select v-model="searchForm.type" size="large" placeholder="选择类型" style="width: 110px;">
                        <el-option label="数据表" :value="1" />
                        <el-option label="数据元" :value="2" />
                    </el-select>
                </template>
                <template #append>
                    <el-button @click="onSearch" icon="Search" />
                </template>
            </el-input>
        </div>
        <div class="search-list">
            {{searchData}}
        </div>
    </el-card>
</template>
<style scoped lang="scss">
    .retrieval-container{
        .search-form{
            padding: 2rem 0;
            text-align: center;
        }
    }
</style>