<script setup lang="ts">
    import {defineProps, defineEmits} from 'vue'
	const props = defineProps({
		data: Array
	})

	const emit = defineEmits(['onNestedLink']);
	const handleClick = (e)=>{
		emit('onNestedLink', e)
	}
</script>

<template>
    <el-sub-menu :index="data.path">
		<template #title>
			<el-icon v-if="data.icon" style="vertical-align: middle;">
				<component :is="data.icon"></component>
			</el-icon>
			<span style="vertical-align: middle;">{{data.chineseName}}</span>
		</template>
		<template v-for="item in data.children" :key="item">
            <el-menu-item v-if="!item.children || item.children.length == 0" :index="item.type == 0 ? item.path : ''">
				<el-icon v-if="item.icon" style="vertical-align: middle;">
					<component :is="item.icon"></component>
				</el-icon>
				<span v-if="item.type == 0" style="vertical-align: middle;">{{item.chineseName}}</span>
                <a v-else-if="item.type == 2" :href='item.url' target='_blank' style="vertical-align: middle;text-decoration: none;color:#242e42;">{{item.chineseName}}</a>
                <span v-else-if="item.type == 3" @click="handleClick(item)" style="vertical-align: middle;">{{item.chineseName}}</span>
			</el-menu-item>
			<Submenu v-else :data='item' @onNestedLink="onNestedLink"></Submenu>
		</template>
	</el-sub-menu>
</template>
<style scoped lang="scss">
</style>