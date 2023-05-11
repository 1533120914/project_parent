<template>
<div>
  <div>
    <el-card class="box-card">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>优秀工作人员排行榜</span>
      </div>
      <div>
        <div class="box-pie" style="height: 350px;width: 100%" ref="workerApplyCountChart"></div>
      </div>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>优秀市民排行榜</span>
      </div>
      <div>
        <div class="box-pie" style="height: 350px;width: 100%" ref="userPublishCountChart"></div>
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
      workerApplyCountChart: null,
      userPublishCountChart:null,
      workerApplyCountOption : {
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
      userPublishCountOption : {
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
    initWorkerApplyCountChart() {
      this.workerApplyCountChart = this.$echarts.init(this.$refs.workerApplyCountChart,'dark')
      // 使用刚指定的配置项和数据显示图表。
      this.workerApplyCountChart.setOption(this.workerApplyCountOption)
    },
    getWorkerApplyCountData() {
      this.$http.get('/statistics/worker-apply-count')
      .then((data) => {
        let arr1 = []
        let arr2 = []
        data.forEach((item) => {
          arr1.push(item.workerName)
          arr2.push(item.count)
        })
        this.workerApplyCountOption.xAxis.data = arr1
        this.workerApplyCountOption.series[0].data = arr2
        this.initWorkerApplyCountChart()
      })
    },
    initUserPublishCountChart() {
      this.userPublishCountChart = this.$echarts.init(this.$refs.userPublishCountChart,'dark')
      // 使用刚指定的配置项和数据显示图表。
      this.userPublishCountChart.setOption(this.userPublishCountOption)
    },
    getUserPublishCountData() {
      this.$http.get('/statistics/user-publish-count')
          .then((data) => {
            let arr1 = []
            let arr2 = []
            data.forEach((item) => {
              arr1.push(item.userName)
              arr2.push(item.count)
            })
            this.userPublishCountOption.xAxis.data = arr1
            this.userPublishCountOption.series[0].data = arr2
            this.initUserPublishCountChart()
          })
    }
  },
  mounted() {
    this.getWorkerApplyCountData()
    this.getUserPublishCountData()
  }
}
</script>

<style scoped>

</style>