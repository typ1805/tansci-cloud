<script setup lang="ts">
    import {onMounted, reactive, ref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from 'element-plus'
    import type {FormInstance} from 'element-plus'
    import {menuList,saveMenu,updateMenu,delMenu} from '@/api/system/menu'
    import Icon from '@/components/Icon.vue'

    const menuFormRef = ref<FormInstance>();
    const state = reactive({
        treeData: [],
        operate: 0,
        menuForm:{
            id: '',
            parentId: '',
            name: '',
            chineseName: '',
            englishName: '',
            status: 1,
            url: '',
            icon: '',
            level: 1,
            sort: 0,
            type: 0,
            remarks: ''
        },
        menuId: null,
        iconVisible: false,
    })
    const {
        treeData,operate,menuForm,menuId,iconVisible
    } = toRefs(state)

    onMounted(()=>{
        onMenuTree()
    })

    const onMenuTree = () =>{
        menuList({status: 1}).then(res=>{
            if(res){
                state.treeData = res.result;
            }
        })
    }

    const onNodeClick = (data:any) =>{
        state.operate = 0;
        state.menuId = data.id;
        state.menuForm = {
            id: data.id,
            parentId: data.parentId,
            name: data.name,
            chineseName: data.chineseName,
            englishName: data.englishName,
            status: data.status,
            url: data.url,
            icon: data.icon,
            level: data.level,
            sort: data.sort,
            type: data.type,
            remarks: data.remarks
        }
    }

    const onOperateChange = (val:any) =>{
        if(val == 1){
            let menuId = '0'
            if(state.menuId){
                menuId = state.menuId
            }
            state.operate = 1;
            state.menuForm = {
                id: '',
                parentId: menuId,
                name: '',
                chineseName: '',
                englishName: '',
                status: 1,
                url: '',
                icon: '',
                level: 1,
                sort: 0,
                type: 0,
                remarks: ''
            }
        } else if(val == 2) {
            state.operate = 2;
        } else {
            if(!state.menuId){
                ElMessage.warning("请选择要删除的菜单！")
                return false;
            }
            state.operate = 0;
            ElMessageBox.confirm('此操作将永久删除该菜单, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                delMenu({id: state.menuId}).then(res=>{
                    if(res){
                        ElMessage.success("删除成功!");
                        state.menuForm = {
                            id: '',
                            parentId: '',
                            name: '',
                            chineseName: '',
                            englishName: '',
                            status: 1,
                            url: '',
                            icon: '',
                            level: 1,
                            sort: 0,
                            type: 0,
                            remarks: ''
                        };
                        onMenuTree();
                    }
                })
            }).catch(e=>{
                console.log(e)
            })
        }
    }

    const onFormIcon = () =>{
        state.iconVisible = true;
    }
    const onIcon = (val:any) =>{
        state.menuForm.icon = val.name;
        state.iconVisible = false;
    }

    const onSubmit = async (formEl: FormInstance | undefined) =>{
        if (!formEl) return;
        await formEl.validate((valid)=>{
            if(valid){
                if(state.operate == 1){
                    saveMenu(state.menuForm).then(res=>{
                        if(res){
                            ElMessage.success("添加成功！");
                            state.operate = 0;
                            state.menuForm = {
                                id: '',
                                parentId: '',
                                name: '',
                                chineseName: '',
                                englishName: '',
                                status: 1,
                                url: '',
                                icon: '',
                                level: 1,
                                sort: 0,
                                type: 0,
                                remarks: ''
                            };
                            onMenuTree();
                        }
                    })
                } else if (state.operate == 2) {
                    updateMenu(state.menuForm).then(res=>{
                        if(res){
                            ElMessage.success("更新成功！");
                            state.operate = 0;
                            state.menuForm = {
                                id: '',
                                parentId: '',
                                name: '',
                                chineseName: '',
                                englishName: '',
                                status: 1,
                                url: '',
                                icon: '',
                                level: 1,
                                sort: 0,
                                type: 0,
                                remarks: ''
                            };
                            onMenuTree();
                        }
                    })
                }
            }
        })
    }

</script>
<template>
    <el-card class="menu-container" shadow="always">
        <el-card class="menu-tree" shadow="never">
            <el-tree :data="treeData" :props="{children: 'children', label: 'chineseName'}" accordion highlight-current @node-click="onNodeClick" 
                empty-text="暂无菜单"></el-tree>
        </el-card>
        <el-card class="menu-form" shadow="never">
            <el-radio-group @change="onOperateChange" v-model="operate">
                <el-radio-button :label="1">添加</el-radio-button>
                <el-radio-button :label="2">编辑</el-radio-button>
                <el-radio-button :label="3">删除</el-radio-button>
            </el-radio-group>
            <el-divider content-position="left">详细信息</el-divider>
            <el-form :model="menuForm" :rules="rules" ref="menuFormRef" :disabled="operate==0 || operate==3?true:false" label-position="right" label-width="150px">
                <el-form-item label="菜单名称" prop="name" :rules="[
                    {required: true, message: '名称不能为空', trigger: 'blur'},
                    {pattern: /^[A-Za-z0-9]+$/, message: '必须是字母', trigger: 'blur'}]">
                    <el-input v-model="menuForm.name" placeholder="请输入名称" style="width:50%"></el-input>
                </el-form-item>
                <el-form-item label="菜单类型" prop="type" :rules="[{required: true, message: '请选择类型', trigger: 'change'}]">
                    <el-select v-model="menuForm.type" placeholder="请选菜单类型" style="width:50%">
                        <el-option label="菜单" :value="0"></el-option>
                        <el-option label="按钮" :value="1"></el-option>
                        <el-option label="链接" :value="2"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="菜单路由" prop="url" :rules="[{required: true, message: '路由不能为空', trigger: 'blur'}]">
                    <el-input v-model="menuForm.url" placeholder="请输入路由" style="width:50%"></el-input>
                </el-form-item>
                <el-form-item label="中文名称" prop="chineseName" :rules="[
                    {required: true, message: '中文名称不能为空', trigger: 'blur'},
                    {pattern: /^[\u4e00-\u9fa5]{0,}$/, message: '必须是汉字', trigger: 'blur'}]">
                    <el-input v-model="menuForm.chineseName" placeholder="请输入中文名称" style="width:50%"></el-input>
                </el-form-item>
                <el-form-item label="英文名称" prop="englishName" :rules="[
                    {required: true, message: '英文名称不能为空', trigger: 'blur'},
                    {pattern: /^[A-Za-z0-9]+$/, message: '必须是字母', trigger: 'blur'}]">
                    <el-input v-model="menuForm.englishName" placeholder="请输入英文名称" autocomplete="off" style="width:50%"></el-input>
                </el-form-item>
                <el-form-item label="菜单图标" prop="icon" :rules="[{required: true, message: '菜单图标不能为空', trigger: 'blur'}]">
                    <el-input v-model="menuForm.icon" @click="onFormIcon" readonly suffix-icon="Platform" style="width:50%"></el-input>
                    <Icon :iconVisible="iconVisible" @onIcon="onIcon"/>
                </el-form-item>
                <el-form-item label="菜单顺序" prop="sort" :rules="[{required: true, message: '菜单顺序不能为空', trigger: 'blur'}]">
                    <el-input-number v-model="menuForm.sort" :min="0" :max="999" style="width:50%"></el-input-number>
                </el-form-item>
                <el-form-item label="菜单级别" prop="level" :rules="[{required: true, message: '请选择级别', trigger: 'change'}]">
                    <el-input-number v-model="menuForm.level" :min="1" :max="999" style="width:50%"></el-input-number>
                </el-form-item>
                <el-form-item label="菜单状态" prop="status" :rules="[{required: true, message: '请选择状态', trigger: 'change'}]">
                    <el-radio-group v-model="menuForm.status">
                        <el-radio :label="1">正常</el-radio>
                        <el-radio :label="2">禁用</el-radio>
                        <el-radio :label="0">已删除</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="备注" prop="remarks">
                    <el-input v-model="menuForm.remarks" type="textarea" :rows="2" placeholder="请输入备注" style="width:50%"></el-input>
                </el-form-item>
                <el-form-item v-show="operate != 0 && operate != 3">
                    <el-button type="primary" @click="onSubmit(menuFormRef)">提交</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </el-card>
</template>
<style  lang="scss">
    .menu-container{
        padding-bottom: 1.5rem;
        .menu-tree{
            float: left;
            margin-right: 1rem;
            min-width: 300px;
            min-height: 700px;

            .el-tree-node:hover>.el-tree-node__content{
                background-color: #fff !important;
                color: var(--theme2) !important;
            }
            
            .el-tree--highlight-current .el-tree-node.is-current > .el-tree-node__content { 
                background-color: #fff !important;
                color: var(--theme2) !important;
            }
        }
        .menu-form{
        }
    }
</style>