<script setup lang="ts">
    import {onMounted, reactive, toRefs} from 'vue'
    import {ElMessage} from 'element-plus'
    import Table from '@/components/Table.vue'
    import {sourceList,getDbTables,getDbTableColumns} from '@/api/data/dataRetrieval'

    const state = reactive({
        searchForm: {
            id: null,
            type: null,
            value: null
        },
        sourceData: [],
        tableLoading: false,
        tableTitle: [
            {prop:'tableName',label:'表名称',type:'button',option:{type:'info',size:'small'}},
            {prop:'tableComment',label:'表描述'},
        ],
        tableData:[],
        fieldLoading: false,
        fieldTitle: [
            {prop:'colName',label:'字段名称'},
            {prop:'dataType',label:'字段类型'},
            {prop:'dataLength',label:'字段长度'},
            {prop:'dataPrecision',label:'字段顺序'},
            {prop:'colKey',label:'是否主键'},
            {prop:'nullable',label:'是否为空'},
            {prop:'colPosition',label:'字段位置'},
            {prop:'dataDefault',label:'默认值'},
            {prop:'colComment',label:'字段描述'},
        ],
        fieldData:[],
    })

    const {
        searchForm,sourceData,tableTitle,tableData,fieldTitle,fieldData
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
        if(!state.searchForm.id){
            ElMessage.warning('请选择数据源!');
            return false;
        }

        // 数据表
        getDbTables({id: state.searchForm.id}).then(res=>{
            state.tableData = res.result
        })
    }

    // 数据元
    const onDataElement = (val:any) =>{
        getDbTableColumns({id: state.searchForm.id, tableName: val.tableName}).then(res=>{
            state.fieldData = res.result
        })
    }

</script>
<template>
    <el-card class="retrieval-container" shadow="always">
        <Table :data="tableData" :column="tableTitle" :operation="false" :page="null" :tableHeight="340" :loading="tableLoading" @onButtonClick="onDataElement">
            <template #search>
                <div>
                    <el-select v-model="searchForm.id" placeholder="请选择数据源">
                        <el-option v-for="item in sourceData" :key="item.id" :label="item.name" :value="item.id" />
                    </el-select>
                </div>
                <div><el-button @click="onSearch" type="primary" icon="Search">查询</el-button></div>
            </template>
        </Table>
        <Table :data="fieldData" :column="fieldTitle" :page="null" :tableHeight="340" :loading="fieldLoading">
        </Table>
    </el-card>
</template>
<style scoped lang="scss">
    .retrieval-container{
        
    }
</style>