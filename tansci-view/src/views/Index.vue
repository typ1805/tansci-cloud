<script setup lang="ts">
    import {onMounted, reactive, toRefs} from 'vue'
    import * as echarts from 'echarts'
    import {useUserStore} from '@/store/settings'
    import {statistics} from '@/api/system/home'

    const userStore = useUserStore();
    const username = userStore.getUser.username == null ? '未登录':userStore.getUser.username
    const state = reactive({
        statisticsList: [
            {value: null,icon: 'Sunny',color:'#006000',bgcolor: '#f0f5fb',text: '操作日志量'}, 
            {value: null,icon: 'Sunrise',color:'#f56c6c',bgcolor: '#fdf3e9',text: '异常日志量'}, 
            {value: null,icon: 'AlarmClock',color:'#0084ff',bgcolor: '#e8faea',text: '登录次数'}, 
            {value: null,icon: 'Timer',color:'#63ba4d',bgcolor: '#e7e1fb',text: '总任务量/正在运行的任务数'}
        ],
        todoList: [],
        
    })

    const {
        statisticsList,todoList
    } = toRefs(state)

    onMounted(()=>{
        onStatistics();
        onTodoList();
        noColumnar();
		noPie();
    })

    const onStatistics = () =>{
        statistics({}).then(res=>{
            if(res){
                state.statisticsList[0].value = res.result.operationNum;
                state.statisticsList[1].value = res.result.errorNum;
                state.statisticsList[2].value = res.result.loginNum;
                state.statisticsList[3].value = res.result.taskTotal + '/' + res.result.taskNum;
            }
        })
    }

    const onTodoList = () =>{
		state.todoList = [
			{name:'待办事项',total:100,untreated:28,icon:'Bell',color:'#f56c6c'},
			{name:'待办事项',total:84,untreated:78,icon:'ChatDotSquare',color:'#0084ff'},
			{name:'待办事项',total:25,untreated:21,icon:'Warning',color:'#63ba4d'},
			{name:'待办事项',total:12,untreated:5,icon:'Edit',color:'#006000'},
		]
	}

    const noColumnar = () =>{
		let myColumnar = echarts.init(document.getElementById("myColumnar"));
		myColumnar.setOption({
			legend: {},
			tooltip: {},
			color: ['#bdc3c7', '#2c3e50', '#242e42'],
			dataset: {
				dimensions: ['product', '2021', '2020', '2019'],
				source: [
					['1月', 43.3, 85.8, 93.7 ],
					['2月', 83.1, 73.4, 55.1 ],
					['3月', 86.4, 65.2, 82.5 ],
					['4月', 72.4, 53.9, 39.1 ],
					['5月', 72.4, 53.9, 39.1 ],
					['6月', 72.4, 53.9, 39.1 ],
					['7月', 72.4, 53.9, 39.1 ],
					['8月', 72.4, 53.9, 39.1 ],
					['9月', 72.4, 53.9, 39.1 ],
					['10月', 72.4, 53.9, 39.1 ],
					['11月', 72.4, 53.9, 39.1 ],
					['12月', 72.4, 53.9, 39.1 ],
				]
			},
			xAxis: { 
				type: 'category',
				axisTick: {
					show: false // 不显示坐标轴刻度线
				},
			},
			yAxis: {
				show: true, // 不显示坐标轴线、坐标轴刻度线和坐标轴上的文字
				axisTick: {
					show: false // 不显示坐标轴刻度线
				},
				axisLine: {
					show: false // 不显示坐标轴线
				},
				splitLine: {
					show: true // 不显示网格线
				},
			},
			series: [
				{type: 'bar'}, 
				{type: 'bar'}, 
				{type: 'bar'}
			]
		});
		window.onresize = function () {
			myColumnar.resize();
		};
	}

	const noPie = () =>{
		let myPie = echarts.init(document.getElementById("myPie"));
		myPie.setOption({
			title: {},
			legend: {},
			tooltip: {
				trigger: 'item',
                formatter: '{a} <br/>{b} : {c}￥ ({d}%)'
			},
			color: ['#94716B', '#6C5B7B', '#355C7D'],
			series: [
				{
					name: '渠道',
					type: 'pie',
					radius: '90%',
					avoidLabelOverlap: false,
                    roseType: 'radius',
					center: ['50%', '50%'],
					itemStyle: {
						borderRadius: 10,
						borderColor: '#fff',
						borderWidth: 2
					},
					label:{
						show: true,
						position: 'inner'
					},
					labelLine: {
						show:false
					},
					data: [
						{value: 335, name: '指标一'},
						{value: 310, name: '指标二'},
						{value: 274, name: '指标三'},
					].sort(function (a, b) {
						return a.value - b.value;
					})
				}
			]
		});
		window.onresize = function () {
			myPie.resize();
		};
	}

</script>
<template>
    <div class="index-container">
        <el-card :shadow="shadow">
			<div class="index-header">
				<div class="index-header-greetings">
					<div class="greetings-text">您好 {{username}} ！祝您新的一天工作愉快。</div>
					<div class="greetings-weather">
                        <el-icon color="#67C23A" style="vertical-align: middle"><MoonNight /></el-icon>
                        <span>今天阴天，-8~10° 较冷，注意加衣</span>
                    </div>
				</div>
                <div class="index-header-todo">
                    <div v-for="todo in todoList" :key="todo">
                        <div class="todo-title">
                            <el-icon :color="todo.color" style="vertical-align: middle;">
                                <component :is="todo.icon"></component>
                            </el-icon>
                            <span style="vertical-align: middle;">{{todo.name}}</span>
                        </div>
                        <div class="todo-num">{{todo.untreated}}/{{todo.total}}</div>
                    </div>
                </div>
			</div>
		</el-card>
        <div class="index-main">
			<div class="index-main-card">
				<el-card v-for="(card,index) in statisticsList" :key="index" :shadow="shadow" :style="{flex: 1, padding: '2rem 0', marginLeft: index==0?'0':'0.3rem'}">
					<div class="main-card-value">
						<el-icon :color="card.color" style="vertical-align: middle;">
							<component :is="card.icon"></component>
						</el-icon>
						<span class="card-value-num">{{card.value}}</span>
						<!-- <span class="card-value-ratio">, {{card.ratio}}%</span> -->
					</div>
					<div class="main-card-text">{{card.text}}</div>
				</el-card>
			</div>
			<div class="index-main-chart">
				<div class="chart-left">
					<el-card :shadow="shadow">
						<div id="myColumnar" style="height:500px;"></div>
					</el-card>
				</div>
				<div class="chart-right">
					<el-card :shadow="shadow">
						<div id="myPie" style="height:500px;"></div>
					</el-card>
				</div>
			</div>
		</div>
		<div class="index-footer">
		</div>
    </div>
</template>
<style scoped lang="scss">
    .index-container{
        .index-header{
			display: flex;
			justify-content: space-between;
			padding: 1rem 4rem;
			.index-header-greetings{
				.greetings-text{
					font-size: 18px;
					padding-bottom: 0.6rem;
				}
				.greetings-weather{
					font-size: 13px;
					color: var(--t6);
				}
			}
			.index-header-todo{
				display: flex;
				div{
					padding: 0 0.6rem;
					.todo-title{
						font-size: 14px;
						color: var(--t6);
						padding-bottom: 1rem;
						.el-icon{
							padding-right: 0.2rem;
						}
					}
					.todo-num{
						font-size: 18px;
						font-weight: 700;
						padding-left: 1.8rem;
					}
				}
			}
		}
        .index-main{
			.index-main-card{
				display: flex;
				flex-wrap: wrap;
				justify-content: space-between;
				text-align: center;
				padding: 0.3rem 0;
				.main-card-value{
					font-size: 24px;
					font-weight: 700;
					.card-value-num{
						padding: 0 0.4rem;
						vertical-align: middle;
					}
					.card-value-ratio{
						vertical-align: middle;
					}
				}
				.main-card-text{
					font-size: 14px;
					padding-top: 0.4rem;
					color: var(--t6);
				}
			}
			.index-main-chart{
				display: flex;
				justify-content: space-between;
				.chart-left{
					width: 70%;
					padding-right: 0.3rem;
				}
				.chart-right{
					width: 30%;
					padding-right: 0.3rem;
				}
			}
		}
    }
</style>