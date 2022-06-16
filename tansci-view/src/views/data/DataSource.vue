<script setup lang="ts">
    import {onMounted, reactive, ref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from 'element-plus'
    import type {FormInstance} from 'element-plus'
    import Table from '@/components/Table.vue'
    import {sourcePage,addSource,updateSource,delSource,checkConnection} from '@/api/data/dataSource'
    import {getGroupNameByList} from '@/api/system/dic'

    const sourceFormRef = ref<FormInstance>()
    const state = reactive({
        searchForm:{
            name: null,
        },
        loading: false,
        page: {
            current: 1,
            size: 10,
            total: 1,
        },
        tableTitle: [
            {prop:'',label:'',fixed:'left'},
            {prop:'name',label:'名称'},
            {prop:'host',label:'主机'},
            {prop:'type',alias:'typeName',label:'类型'},
            {prop:'status',alias:'statusName',label:'状态'},
            {prop:'port',label:'端口'},
            {prop:'username',label:'账号'},
            {prop:'password',label:'密码'},
            {prop:'remarks',label:'备注'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'}
        ],
        tableData:[],
        sourceVisible: false,
        operate: 0,
        sourceForm:{
            id:'',
            name:'',
            host:'',
            status: 0,
            type: null,
            port:'',
            username:'',
            password:'',
            remarks:''
        },
        sourceTypeList:[],
    })

    const {
        searchForm,loading,page,tableTitle,tableData,sourceVisible,operate,sourceForm,sourceTypeList
    } = toRefs(state)

    onMounted(() => {
        onSourcePage();
        onSourceType();
    })

    // 数据源类型
    const onSourceType = () =>{
        getGroupNameByList({groupName: 'data_source'}).then(res=>{
            state.sourceTypeList = res.result;
        })
    }

    // 初始化数据
    const onSourcePage = () =>{
        state.loading = true;
        sourcePage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onSourcePage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onSourcePage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            name: null,
        }
        onSourcePage();
    }
    const onSearch = () =>{
        onSourcePage();
    }

    // 编辑
    const onEdit = (val:any) =>{
        state.operate = 1;
        state.sourceForm = {
            id: val.column.row.id,
            name: val.column.row.name,
            host: val.column.row.host,
            status: val.column.row.status,
            type: Number(val.column.row.type),
            port: val.column.row.port,
            username: val.column.row.username,
            password: val.column.row.password,
            remarks: val.column.row.remarks
        }
        state.sourceVisible = true;
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
            delSource(param).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onSourcePage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }

    // 添加用户信息
    const onAddSource = () =>{
        state.sourceForm = {
            id:'',
            name:'',
            host:'',
            status: 0,
            type: null,
            port:'',
            username:'',
            password:'',
            remarks:''
        };
        state.operate = 0;
        state.sourceVisible = true;
    }

    const onSubmit = async (formEl: FormInstance | undefined) =>{
        if (!formEl) return;
        await formEl.validate((valid)=>{
        if(valid){
            if(state.operate == 0){
                addSource(state.sourceForm).then(res=>{
                    if(res){
                        ElMessage.success('添加成功！');
                        onSourcePage();
                        state.sourceVisible = false;
                    }
                });
            } else {
                updateSource(state.sourceForm).then(res=>{
                    if(res){
                        ElMessage.success('更新成功！');
                        onSourcePage();
                        state.sourceVisible = false;
                    }
                });
            }
        }
        });
    }

    // 数据库连通性
    const onCheckConnection = (val:any) =>{
        checkConnection({id:val.column.row.id}).then(res=>{
            if(res){
                ElMessage.success(res.result);
            } else {
                ElMessage.error('连接测试失败!');
            }
        })
    }

</script>
<template>
    <el-card class="source-container" shadow="always">
        <Table :data="tableData" :column="tableTitle" :operation="true" :page="page" :loading="loading"
            @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
            <template #search>
                <div><el-button type="info" @click="onAddSource">添加</el-button></div>
                <div><el-input v-model="searchForm.name" placeholder="请输入用户名称"></el-input></div>
                <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
                <div><el-button @click="onSearch" type="primary" icon="Search">查询</el-button></div>
            </template>
            <template #column="scope">
                <el-button @click="onCheckConnection(scope)" type='primary' text='primary' style="color:var(--down); padding:0;">连接验证</el-button>
                <el-button @click="onEdit(scope)" type='primary' text='primary' style="color:var(--edit); padding:0;">编辑</el-button>
                <el-button @click="onDelete(scope)" type='primary' text='primary' style="color:var(--delete); padding:0;">删除</el-button>
            </template>
        </Table>
        <el-dialog title="数据源信息" v-model="sourceVisible" width="50%" :show-close="false">
            <el-form :model="sourceForm" :rules="rules" ref="sourceFormRef" status-icon label-width="100px">
                <el-form-item prop="name" label="库名" :rules="[{required: true, message: '请输入库名', trigger: 'blur'}]">
                    <el-input v-model="sourceForm.name" placeholder="请输入库名"></el-input>
                </el-form-item>
                <el-form-item prop="host" label="主机" :rules="[{required: true, message: '请输入主机', trigger: 'blur'}]">
                    <el-input v-model="sourceForm.host" placeholder="请输入主机"></el-input>
                </el-form-item>
                <el-form-item prop="port" label="端口" :rules="[{required: true, message: '请输入端口', trigger: 'blur'}]">
                    <el-input v-model="sourceForm.port" placeholder="请输入端口"></el-input>
                </el-form-item>
                <el-form-item prop="username" label="账号" :rules="[{required: true, message: '请输入账号', trigger: 'blur'}]">
                    <el-input v-model="sourceForm.username" placeholder="请输入账号"></el-input>
                </el-form-item>
                <el-form-item prop="password" label="密码" :rules="[{required: true, message: '请输入密码', trigger: 'blur'}]">
                    <el-input v-model="sourceForm.password" type="password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item prop="status" label="状态" :rules="[{required: true, message: '请选择状态', trigger: 'change'}]">
                    <el-radio-group v-model="sourceForm.status">
                        <el-radio :label="0">未启用</el-radio>
                        <el-radio :label="1">启用</el-radio>
                        <el-radio :label="2">禁用</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item prop="type" label="类型" :rules="[{required: true, message: '请选择类型', trigger: 'change'}]">
                    <el-select v-model="sourceForm.type" placeholder="请选择类型" style="width: 100%;">
                        <el-option v-for="item in sourceTypeList" :key="item" :label="item.dicLabel" :value="item.dicValue" />
                    </el-select>
                </el-form-item>
                <el-form-item prop="remarks" label="备注">
                    <el-input v-model="sourceForm.remarks" type="textarea" placeholder="请输入备注"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                <el-button @click="sourceVisible = false">取消</el-button>
                <el-button type="primary" @click="onSubmit(sourceFormRef)">提交</el-button>
                </span>
            </template>
        </el-dialog>
    </el-card>
</template>
<style scoped lang="scss">
    .source-container{
    }
</style>