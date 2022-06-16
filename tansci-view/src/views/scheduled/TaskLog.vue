<script setup lang="ts">
    import {onMounted, reactive, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from 'element-plus'
    import Table from '@/components/Table.vue'
    import {taskLogPage,clearTaskLog} from '@/api/scheduled/taskLog'
    
    const state = reactive({
        searchForm:{
            serverName: null,
        },
        loading: false,
        page: {
            current: 1,
            size: 20,
            total: 0,
        },
        tableTitle: [
            {prop:'taskName',label:'调度名称'},
            {prop:'taskCode',label:'任务编码'},
            {prop:'creater',label:'创建人'},
            {prop:'serverName',label:'服务名称'},
            {prop:'status',alias:'statusName',label:'状态'},
            {prop:'executionTime',label:'执行时间'},
            {prop:'remarks',label:'描述'}
        ],
        tableData:[],
    })

    const {
        searchForm,loading,page,tableTitle,tableData
    } = toRefs(state)

    onMounted(() => {
        onTaskLogPage();
    })

    const onTaskLogPage = () =>{
        state.loading = true;
        taskLogPage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onTaskLogPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onTaskLogPage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            serverName: null,
        }
        onTaskLogPage();
    }
    const onSearch = () =>{
        onTaskLogPage();
    }

    const onClearLog = () =>{
        ElMessageBox.confirm('此操作将清空所有日志, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            clearTaskLog({}).then(res=>{
                if(res){
                    ElMessage.success('操作成功!');
                    onTaskLogPage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }

    const setCellColor = (e:any, callback:any) => {
        /**
         * e.row：表格那一行的数据
         * e.column：表格单元格那一列的信息
         */ 
        if(e.row.status === 0 && e.column.property === 'statusName'){
            callback({color: '#67C23A'});
        } else if(e.row.status === 1 && e.column.property === 'statusName'){
            callback({color: '#F56C6C'});
        } else {
            callback({});
        }
    }

</script>
<template>
    <el-card class="tasklog-container" shadow="always">
        <Table :data="tableData" :column="tableTitle" :operation="false" :page="page" :loading="loading"
            @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange" @setCellColor="setCellColor">
            <template #search>
                <div><el-button type="warning" @click="onClearLog">清除日志</el-button></div>
                <div><el-input v-model="searchForm.serverName" placeholder="请输入服务名称"></el-input></div>
                <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
                <div><el-button @click="onSearch" type="primary" icon="Search">查询</el-button></div>
            </template>
        </Table>
    </el-card>
</template>
<style scoped lang="scss">
    .tasklog-container {
    }
</style>