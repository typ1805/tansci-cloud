<script setup lang="ts">
    import {onMounted, reactive, ref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from 'element-plus'
    import type {FormInstance} from 'element-plus'
    import Table from '@/components/Table.vue'
    import {dateFormat}from '@/utils/utils'
    import {userPage,addUser,updateUser,delUser} from '@/api/system/user'
    import {roleList,userRoleSave} from '@/api/system/role'

    const userFormRef = ref<FormInstance>()
    const roleFormRef = ref<FormInstance>()
    const state = reactive({
        searchForm:{
            nickname: null,
        },
        loading: false,
        page: {
            current: 1,
            size: 10,
            total: 1,
        },
        tableTitle: [
            {prop:'',label:'',fixed:'left'},
            {prop:'username',label:'名称'},
            {prop:'nickname',label:'昵称'},
            {prop:'type',alias:'typeName',label:'类型',type:'tag',option:{type:'info',size:'small',effect:'plain'}},
            {prop:'status',alias:'statusName',label:'状态'},
            {prop:'sex',alias:'sexName',label:'性别'},
            {prop:'birthday',label:'出生日期'},
            {prop:'address',label:'地址'},
            {prop:'phone',label:'手机号'},
            {prop:'email',label:'邮箱'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'}
        ],
        tableData:[],
        userVisible: false,
        operate: 0,
        userForm:{
            id:'',
            username:'',
            nickname:'',
            status: 0,
            type: 0,
            birthday:'',
            sex: 0,
            email:'',
            phone:'',
            idCard:'',
            address:'',
            password:'',
            rePassword:''
        },
        roles: [],
        roleVisible: false,
        roleForm:{
            roleId:'',
            userId:'',
        },
    })

    const {
        searchForm,loading,page,tableTitle,tableData,userVisible,operate,userForm,roles,roleVisible,roleForm
    } = toRefs(state)

    onMounted(() => {
        onUserPage();
        onRoleList();
    })

    // 初始化数据
    const onUserPage = () =>{
        state.loading = true;
        userPage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onUserPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onUserPage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            nickname: null,
        }
        onUserPage();
    }
    const onSearch = () =>{
        onUserPage();
    }

    // 编辑
    const onEdit = (val:any) =>{
        state.operate = 1;
        state.userForm = {
            id: val.column.row.id,
            username: val.column.row.username,
            nickname: val.column.row.nickname,
            status: val.column.row.status,
            type: val.column.row.type,
            sex: val.column.row.sex,
            birthday: val.column.row.birthday,
            address: val.column.row.address,
            phone: val.column.row.phone,
            email: val.column.row.email,
            idCard: val.column.row.idCard,
            password: 'pw123456',
            rePassword: 'pw123456'
        }
        state.userVisible = true;
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
            delUser(param).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onUserPage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }

    // 设置权限
    const onRoleList = () =>{
        roleList({status: 1}).then(res=>{
            if(res){
                state.roles = res.result
            }
        })
    }

    const onRole = (val:any) =>{
        state.roleForm = {
            userId: val.column.row.id,
            roleId: val.column.row.roleId,
        };
        state.roleVisible = true;
    }

    const onAddRole = async (formEl: FormInstance | undefined) =>{
        if (!formEl) return;
        await formEl.validate((valid)=>{
            if(valid){
                userRoleSave(state.roleForm).then(res=>{
                    if(res){
                        ElMessage.success('配置权限成功！');
                        onUserPage();
                        state.roleVisible = false;
                    }
                })
            }
        });
    }

    // 添加用户信息
    const onAddUser = () =>{
        state.userForm = {
            id:'',
            username:'',
            nickname:'',
            status: 0,
            type: 0,
            birthday:'',
            sex: 0,
            email:'',
            phone:'',
            idCard:'',
            address:'',
            password:'',
            rePassword:''
        };
        state.operate = 0;
        state.userVisible = true;
    }

    const validatePass = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('请输入确认密码'));
        } else if (value !== state.userForm.password) {
            callback(new Error('两次输入密码不一致!'));
        } else {
            callback();
        }
    }

    const onSubmit = async (formEl: FormInstance | undefined) =>{
        if (!formEl) return;
        await formEl.validate((valid)=>{
            if(valid){
                if(state.userForm.birthday){
                    state.userForm.birthday = dateFormat(state.userForm.birthday);
                }

                if(state.operate == 0){
                    addUser(state.userForm).then(res=>{
                        if(res){
                            ElMessage.success('添加成功！');
                            onUserPage();
                            state.userVisible = false;
                        }
                    });
                } else {
                    updateUser(state.userForm).then(res=>{
                        if(res){
                            ElMessage.success('更新成功！');
                            onUserPage();
                            state.userVisible = false;
                        }
                    });
                }
            }
        });
    }

    const setCellColor = (e:any, callback:any) => {
        /**
         * e.row：表格那一行的数据
         * e.column：表格单元格那一列的信息
         */ 
        if((e.row.status === 0 && e.column.property === 'statusName') || (e.row.type === 0 && e.column.property === 'typeName')){
            callback({color: '#67C23A', fontWeight: '700'});
        } else if(e.row.status === 1 && e.column.property === 'statusName' || (e.row.type === 1 && e.column.property === 'typeName')){
            callback({color: '#F56C6C', fontWeight: '700'});
        }else if(e.row.status === 2 && e.column.property === 'statusName'){
            callback({color: '#E6A23C', fontWeight: '700'});
        } else {
            callback({});
        }
    }

</script>
<template>
    <el-card class="user-container" shadow="always">
        <Table :data="tableData" :column="tableTitle" :operation="true" :page="page" :loading="loading"
            @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange" @setCellColor="setCellColor">
            <template #search>
                <div><el-button type="info" @click="onAddUser">添加</el-button></div>
                <div><el-input v-model="searchForm.nickname" placeholder="请输入用户名称"></el-input></div>
                <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
                <div><el-button @click="onSearch" type="primary" icon="Search">查询</el-button></div>
            </template>
            <template #column="scope">
                <el-button @click="onEdit(scope)" type='primary' text='primary' style="color:var(--edit); padding:0;">编辑</el-button>
                <el-button @click="onRole(scope)" type='primary' text='primary' style="color:var(--role); padding:0;">权限</el-button>
                <el-button @click="onDelete(scope)" type='primary' text='primary' style="color:var(--delete); padding:0;">删除</el-button>
            </template>
        </Table>

        <el-dialog title="用户信息" v-model="userVisible" :show-close="false">
            <el-form :model="userForm" :rules="rules" ref="userFormRef" status-icon label-width="100px">
                <el-divider content-position="left" border-style="dashed"><span style="color: #2F9688;">基本信息</span></el-divider>
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item prop="username" label="用户名" :rules="[
                            {required: true, message: '请输入名称', trigger: 'blur'},
                            {pattern: /^[a-zA-Z]\w{4,17}$/, message: '以字母开头，长度在5~18之间，只能包含字母、数字', trigger: 'blur'}]">
                            <el-input v-if="operate==0" v-model="userForm.username" placeholder="请输入名称"></el-input>
                            <el-input v-if="operate==1" disabled v-model="userForm.username" placeholder="请输入名称"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item prop="nickname" label="昵称" :rules="[{required: true, message: '请输入昵称', trigger: 'blur'}]">
                            <el-input v-model="userForm.nickname" placeholder="请输入昵称"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item prop="type" label="类型" :rules="[{required: true, message: '请选择类型', trigger: 'change'}]">
                            <el-radio-group v-model="userForm.type">
                                <el-radio :label="0">普通用户</el-radio>
                                <el-radio :label="1">管理员</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item prop="sex" label="性别" :rules="[{required: true, message: '请选择性别', trigger: 'change'}]">
                            <el-radio-group v-model="userForm.sex">
                                <el-radio :label="0">男</el-radio>
                                <el-radio :label="1">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item prop="birthday" label="出生日期">
                            <el-date-picker v-model="userForm.birthday" type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item prop="email" label="邮箱地址">
                            <el-input v-model="userForm.email" placeholder="请输入邮箱地址"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item prop="phone" label="手机号" :rules="[   
                            {required: true, message: '请输入手机号', trigger: 'blur'},
                            {pattern: /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/, message: '请输入正确的手机号', trigger: 'blur'}]">
                            <el-input v-model="userForm.phone" placeholder="请输入手机号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item prop="address" label="地址">
                            <el-input v-model="userForm.address" placeholder="请输入地址"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item prop="idCard" label="身份证号">
                            <el-input v-model="userForm.idCard" type="idCard" placeholder="请输入身份证号"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-divider v-show="operate == 0" content-position="left" border-style="dashed"><span style="color: #2F9688;">设置密码</span></el-divider>
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item v-show="operate == 0" prop="password" label="密码" :rules="[
                            {required: true, message: '请输入密码', trigger: 'blur'},
                            {pattern: /^[a-zA-Z]\w{5,17}$/, message: '以字母开头，长度在6~18之间，只能包含字母、数字和下划线', trigger: 'blur'}]">
                            <el-input v-model="userForm.password" type="password" placeholder="请输入密码"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item v-show="operate == 0" prop="rePassword" label="确认密码" :rules="[{validator: validatePass, trigger: 'blur'}]">
                            <el-input v-model="userForm.rePassword" type="password" placeholder="请输入确认密码"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="userVisible = false">取消</el-button>
                    <el-button type="primary" @click="onSubmit(userFormRef)">提交</el-button>
                </span>
            </template>
        </el-dialog>
        
        <el-dialog title="设置权限" v-model="roleVisible" width="30%" :show-close="false">
            <el-form :model="roleForm" :rules="rules" ref="roleFormRef" status-icon>
                <el-form-item prop="roleId" label="权限" :rules="[{required: true,message: '请选择权限',trigger: 'change'}]">
                    <el-select v-model="roleForm.roleId" placeholder="请选择权限" style="width:100%;">
                        <el-option v-for="item in roles" :key="item.id" :label="item.name" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="roleVisible = false">取消</el-button>
                    <el-button type="primary" @click="onAddRole(roleFormRef)">提交</el-button>
                </span>
            </template>
        </el-dialog>
    </el-card>
</template>
<style scoped lang="scss">
    .user-container{
    }
</style>