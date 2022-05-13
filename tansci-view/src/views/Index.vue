<script setup lang="ts">

</script>
<template>
    <div class="index-container">
        <el-card class="header-card" shadow="always">
            <div class="header-card-left">
                <el-card shadow="always" style="background-color: #e7e1fb;">
                    2142 Gitee 访问量
                </el-card>
            </div>
            <div class="header-card-right">
                <el-card shadow="always" style="background-color: #e7e1fb;">
                    2142 Gitee 访问量
                </el-card>
                <el-card shadow="always" style="background-color: #e8faea;">
                    2142 Gitee 访问量
                </el-card>
                <el-card shadow="always" style="background-color: #fdf3e9;">
                    2142 Gitee 访问量
                </el-card>
                <el-card shadow="always" style="background-color: #f0f5fb;">
                    2142 Gitee 访问量
                </el-card>
                <el-card shadow="always" style="background-color: #f0f5fb;">
                    2142 Gitee 访问量
                </el-card>
                <el-card shadow="always" style="background-color: #f0f5fb;">
                    2142 Gitee 访问量
                </el-card>
                <el-card shadow="always" style="background-color: #f0f5fb;">
                    2142 Gitee 访问量
                </el-card>
            </div>
        </el-card>
    </div>
</template>
<style scoped lang="scss">
    .index-container{
        .header-card{
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            .header-card-left{
                float: left;
                padding: 3rem;
            }
            .header-card-right{
                display: flex;
                flex-wrap: wrap;
                justify-content: left;
                .el-card{
                    margin: 0.4rem;
                    padding: 3rem;
                    border-radius: 40px;
                }
            }
        }
    }
</style>