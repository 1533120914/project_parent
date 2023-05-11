<template>
<div>
  <div style="display: flex; align-items: stretch">
    <el-card class="box-card" style="width: 50%;margin-right: 10px">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>任务数量统计</span>
      </div>
      <div>
        <div class="box-pie" style="height: 600px;width: 100%" ref="taskCountChart"></div>
      </div>
    </el-card>
    <el-card class="box-card" style="width: 50%">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>优秀志愿者排行榜</span>
      </div>
      <div>
        <div class="box-pie" style="height: 600px;width: 100%" ref="taskApplyCountChart"></div>
      </div>
    </el-card>
  </div>
</div>
</template>

<script>
export default {
  name: "StatisticsView",
  data() {
    return{
      taskCountChart: null,
      taskApplyCountChart:null,
      taskCountOption : {
        xAxis: {
          type: 'category',
          data: ['未领取','未完成','已完成']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [],
            type: 'bar'
          }
        ]
      },
      taskApplyCountOption : {
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [],
            type: 'bar'
          }
        ]
      }
    }
  },
  methods: {
    initTaskCountChart() {
      this.taskCountChart = this.$echarts.init(this.$refs.taskCountChart,'dark')
      // 使用刚指定的配置项和数据显示图表。
      this.taskCountChart.setOption(this.taskCountOption)
    },
    getTaskCountData() {
      this.$http.get('/statistics/task-count')
      .then((data) => {
        let arr = []
        data.forEach((item) => {
          arr.push(item.count)
        })
        this.taskCountOption.series[0].data = arr
        this.initTaskCountChart()
      })
    },
    initTaskApplyCountChart() {
      this.taskApplyCountChart = this.$echarts.init(this.$refs.taskApplyCountChart,'dark')
      // 使用刚指定的配置项和数据显示图表。
      this.taskApplyCountChart.setOption(this.taskApplyCountOption)
    },
    getTaskApplyCountData() {
      this.$http.get('/statistics/task-apply-count')
          .then((data) => {
            let arr1 = []
            let arr2 = []
            data.forEach((item) => {
              arr1.push(item.volunteerName)
              arr2.push(item.count)
            })
            this.taskApplyCountOption.xAxis.data = arr1
            this.taskApplyCountOption.series[0].data = arr2
            this.initTaskApplyCountChart()
          })
    }
  },
  mounted() {
    this.getTaskCountData()
    this.getTaskApplyCountData()
  }
}
</script>

<style scoped>

</style>