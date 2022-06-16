<script setup lang="ts">
    import {onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from 'element-plus'
    import type {FormInstance} from 'element-plus'
    import Table from '@/components/Table.vue'
    import {getCheckTreeIds} from '@/utils/utils'
    import {rolePage,addRole,updateRole,delRole,menuRoleSave} from '@/api/system/role'
    import {menuList} from '@/api/system/menu'

    const roleFormRef = ref<FormInstance>()
    const roleTree = ref(null)
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
            {prop:'status',alias:'statusName',label:'状态',type:'tag',option:{type:'success',size:'small',effect:'plain'}},
            {prop:'creator',alias:'creatorName',label:'创建人',type:'tag',option:{type:'info',size:'small',effect:'plain'}},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
            {prop:'remarks',label:'描述'}
        ],
        tableData:[],
        roleVisible: false,
        operate: 0,
        roleForm:{
            id:'',
            name:'',
            status: 0,
            remarks: ''
        },
        menuData: [],
        roleLoading: false,
        menuVisible: false,
        menuRoleForm: {
            roleId: ''
        },
        checkedKeys:[],
    })

    const {
        searchForm,loading,page,tableTitle,tableData,operate,roleVisible,roleForm,menuData,menuVisible,menuRoleForm,roleLoading,checkedKeys
    } = toRefs(state)

    onMounted(() => {
        onRolePage();
    })

    // 初始化数据
    const onRolePage = () =>{
        state.loading = true;
        rolePage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onRolePage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onRolePage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            name: null,
        }
        onRolePage();
    }
    const onSearch = () =>{
        onRolePage();
    }

    // 编辑
    const onEdit = (val:any) =>{
        state.operate = 1;
        state.roleForm = {
            id: val.column.row.id,
            name: val.column.row.name,
            status: val.column.row.status,
            remarks: val.column.row.remarks
        }
        state.roleVisible = true;
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
            delRole(param).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onRolePage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }

    // 添加信息
    const onAddRole = () =>{
        state.roleForm = {
            id:'',
            name:'',
            status: 0,
            remarks: ''
        };
        state.operate = 0;
        state.roleVisible = true;
    }

    const onSubmit = async (formEl: FormInstance | undefined) =>{
        if (!formEl) return;
        await formEl.validate((valid)=>{
            if(valid){
                if(state.operate == 0){
                    addRole(state.roleForm).then(res=>{
                        if(res){
                        ElMessage.success('添加成功！');
                        onRolePage();
                        state.roleVisible = false;
                        }
                    });
                } else {
                    updateRole(state.roleForm).then(res=>{
                        if(res){
                            ElMessage.success('更新成功！');
                            onRolePage();
                            state.roleVisible = false;
                        }
                    });
                }
            }
        });
    }

    const onMenuRole = (val:any) => {
        state.menuData = [];
        state.checkedKeys = [];
        menuList({roleId: val.column.row.id,status: 1}).then(res=>{
            if(res){
                state.menuData = res.result;
                state.checkedKeys = getCheckTreeIds(res.result);
                state.menuRoleForm.roleId = val.column.row.id;
                state.menuVisible = true;
            }
        })
    }
    const onMenuSubmit = () =>{
        const keys = unref(roleTree);
        if(keys.getCheckedKeys().length == 0){
            ElMessage.error("请先选择菜单！");
            return false;
        }
        state.roleLoading = true;
        let param = {
            roleId: state.menuRoleForm.roleId,
            menuIds: keys.getCheckedKeys()
        }
        menuRoleSave(param).then(res=>{
            if(res){
                ElMessage.success("操作成功！");
                state.roleLoading = false;
                state.menuVisible = false;
            }
        })
    }

</script>
<template>
    <el-card class="role-container" shadow="always">
        <Table :data="tableData" :column="tableTitle" :operation="true" :page="page" :loading="loading"
            @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
            <template #search>
                <div><el-button type="info" @click="onAddRole">添加</el-button></div>
                <div><el-input v-model="searchForm.name" placeholder="请输入名称"></el-input></div>
                <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
                <div><el-button @click="onSearch" type="primary" icon="Search">查询</el-button></div>
            </template>
            <template #column="scope">
                <el-button @click="onEdit(scope)" type='primary' text='primary' style="color:var(--edit); padding:0;">编辑</el-button>
                <el-button @click="onMenuRole(scope)" type='primary' text='primary' style="color:var(--role); padding:0;">权限</el-button>
                <el-button @click="onDelete(scope)" type='primary' text='primary' style="color:var(--delete); padding:0;">删除</el-button>
            </template>
        </Table>
        <el-dialog title="权限信息" v-model="roleVisible" :show-close="false" width="40%">
            <el-form :model="roleForm" :rules="rules" ref="roleFormRef" status-icon>
                <el-form-item prop="name" label="名称" :rules="[
                    {required: true, message: '请输入名称', trigger: 'blur'}]">
                    <el-input v-model="roleForm.name" placeholder="请输入名称"></el-input>
                </el-form-item>
                <el-form-item prop="status" label="状态" :rules="[{required: true, message: '请选择状态', trigger: 'change'}]">
                    <el-radio-group v-model="roleForm.status">
                        <el-radio :label="0">未启用</el-radio>
                        <el-radio :label="1">启用</el-radio>
                        <el-radio :label="2">禁用</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item prop="remarks" label="备注">
                    <el-input v-model="roleForm.remarks" type="textarea" :rows="4" placeholder="请输入"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="roleVisible = false">取消</el-button>
                    <el-button type="primary" @click="onSubmit(roleFormRef)">提交</el-button>
                </span>
            </template>
        </el-dialog>
        <el-dialog title="菜单权限" v-model="menuVisible" :show-close="false" width="30%">
            <el-tree :data="menuData" show-checkbox node-key="id" ref="roleTree" 
                :default-checked-keys="checkedKeys"
                :props="{children: 'children', label: 'chineseName'}"
                empty-text="暂无菜单">
            </el-tree>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="menuVisible = false">取消</el-button>
                    <el-button type="primary" @click="onMenuSubmit" :loading="roleLoading">提交</el-button>
                </span>
            </template>
        </el-dialog>
    </el-card>
</template>
<style scoped lang="scss">
    .role-container{

    }
</style>