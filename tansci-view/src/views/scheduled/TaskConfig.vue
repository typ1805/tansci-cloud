<script setup lang="ts">
    import {onMounted, reactive, ref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from 'element-plus'
    import type {FormInstance} from 'element-plus'
    import Table from '@/components/Table.vue'
    import {taskConfigPage,addTaskConfig,updateTaskConfig,delTaskConfig} from '@/api/scheduled/taskConfig'
    
    const taskFormRef = ref<FormInstance>()
    const state = reactive({
        searchForm:{
            name: null,
        },
        loading: false,
        page: {
            current: 1,
            size: 20,
            total: 0,
        },
        tableTitle: [
            {prop:'name',label:'调度名称'},
            {prop:'taskCode',label:'任务编码'},
            {prop:'serverName',label:'服务名称'},
            {prop:'expression',label:'cron表达式',type:'tag',
                option:{type:'info',size:'small',effect:'plain'}
            },
            {prop:'status',label:'状态',type:'switch',
                option:{size:'large',activeValue: 1,inactiveValue: 0,activeColor:'#63ba4d',inactiveColor:'#f56c6c',activeText:'启用',inactiveText:'禁用'}
            },
            {prop:'creater',label:'创建人'},
            {prop:'remarks',label:'描述'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
        ],
        tableData:[],
        taskVisible: false,
        operate: 0,
        taskForm:{
            id:'',
            name:'',
            serverName:'',
            expression: null,
            status: 0,
            remarks:''
        },
    })

    const {
        searchForm,loading,page,tableTitle,tableData,taskVisible,operate,taskForm
    } = toRefs(state)

    onMounted(() => {
        onTaskConfigPage();
    })

    const onTaskConfigPage = () =>{
        state.loading = true;
        taskConfigPage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onTaskConfigPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onTaskConfigPage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            name: null,
        }
        onTaskConfigPage();
    }
    const onSearch = () =>{
        onTaskConfigPage();
    }

    // 编辑
    const onEdit = (val:any) =>{
        state.operate = 1;
        state.taskForm = {
            id: val.column.row.id,
            name: val.column.row.name,
            serverName: val.column.row.serverName,
            expression: val.column.row.expression,
            status: val.column.row.status,
            remarks: val.column.row.remarks
        }
        state.taskVisible = true;
    }

    // 删除
    const onDelete = (val:any) =>{
        ElMessageBox.confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            let param = {
                id: val.column.row.id
            }
            delTaskConfig(param).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onTaskConfigPage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }

    // 添加用户信息
    const onAddTask = () =>{
        state.taskForm = {
            id:'',
            name:'',
            serverName:'',
            expression: null,
            status: 0,
            remarks:''
        };
        state.operate = 0;
        state.taskVisible = true;
    }

    const onSubmit = async (formEl: FormInstance | undefined) =>{
        if (!formEl) return;
        await formEl.validate((valid)=>{
            if(valid){
                if(state.operate == 0){
                    addTaskConfig(state.taskForm).then(res=>{
                        if(res){
                            ElMessage.success('添加成功！');
                            onTaskConfigPage();
                            state.taskVisible = false;
                        }
                    });
                } else {
                    updateTaskConfig(state.taskForm).then(res=>{
                        if(res){
                            ElMessage.success('更新成功！');
                            onTaskConfigPage();
                            state.taskVisible = false;
                        }
                    });
                }
            }
        });
    }

    const onStatusChange = (val:any) =>{
        updateTaskConfig({id:val.id,status:val.status}).then(res=>{
            if(res){
                ElMessage.success('操作成功！');
                onTaskConfigPage();
            }
        });
    }

</script>
<template>
    <el-card class="taskconfig-container" shadow="always">
        <Table :data="tableData" :column="tableTitle" :operation="true" :page="page" :loading="loading"
            @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange" @onSwitchChange="onStatusChange">
            <template #search>
                <div><el-button type="info" @click="onAddTask">添加</el-button></div>
                <div><el-input v-model="searchForm.name" placeholder="请输入名称"></el-input></div>
                <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
                <div><el-button @click="onSearch" type="primary" icon="Search">查询</el-button></div>
            </template>
            <template #column="scope">
                <el-button @click="onEdit(scope)" type='primary' text='primary' style="color:var(--edit); padding:0;">编辑</el-button>
                <el-button @click="onDelete(scope)" type='primary' text='primary' style="color:var(--delete); padding:0;">删除</el-button>
            </template>
        </Table>
        <el-dialog title="调度信息" v-model="taskVisible" width="50%" :show-close="false">
            <el-form :model="taskForm" :rules="rules" ref="taskFormRef" status-icon label-width="100px">
                <el-form-item prop="name" label="调度名称" :rules="[{required: true, message: '请输入调度名称', trigger: 'blur'}]">
                    <el-input v-model="taskForm.name" placeholder="请输入调度名称"></el-input>
                </el-form-item>
                <el-form-item prop="serverName" label="服务名称" :rules="[{required: true, message: '请输入服务名称', trigger: 'blur'}]">
                    <el-input v-model="taskForm.serverName" placeholder="请输入服务名称"></el-input>
                </el-form-item>
                <el-form-item prop="expression" label="cron表达式" :rules="[{required: true, message: '请输入cron表达式', trigger: 'blur'}]">
                    <el-input v-model="taskForm.expression" placeholder="请输入cron表达式"></el-input>
                </el-form-item>
                <el-form-item prop="status" label="状态" :rules="[{required: true, message: '请选择状态', trigger: 'change'}]">
                    <el-radio-group v-model="taskForm.status">
                        <el-radio :label="0">未启用</el-radio>
                        <el-radio :label="1">启用</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item prop="remarks" label="备注">
                    <el-input v-model="taskForm.remarks" type="textarea" placeholder="请输入备注"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                <el-button @click="taskVisible = false">取消</el-button>
                <el-button type="primary" @click="onSubmit(taskFormRef)">提交</el-button>
                </span>
            </template>
        </el-dialog>
    </el-card>
</template>
<style scoped lang="scss">
    .taskconfig-container {
    
    }
</style>