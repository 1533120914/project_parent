<template>
<div>
  <div style="display: flex; align-items: stretch">
    <el-card class="box-card" style="width: 50%;margin-right: 10px">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>部门员工数量统计</span>
        <div>
          <el-radio-group size="mini" v-model="type1">
            <el-radio-button label="bar">柱状图</el-radio-button>
            <el-radio-button label="line">折线图</el-radio-button>
            <el-radio-button label="pie">饼图</el-radio-button>
            <el-radio-button label="scatter">散点图</el-radio-button>
          </el-radio-group>
        </div>
      </div>
      <div style="padding: 10px">
        <div class="box-pie" style="height: 300px;width: 100%;" ref="chart1"></div>
      </div>
    </el-card>
    <el-card class="box-card" style="width: 50%">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>员工采购次数统计</span>
        <div>
          <el-radio-group size="mini" v-model="type2">
            <el-radio-button label="bar">柱状图</el-radio-button>
            <el-radio-button label="line">折线图</el-radio-button>
            <el-radio-button label="pie">饼图</el-radio-button>
            <el-radio-button label="scatter">散点图</el-radio-button>
          </el-radio-group>
        </div>
      </div>
      <div style="padding: 10px">
        <div class="box-pie" style="height: 300px;width: 100%;" ref="chart2"></div>
      </div>
    </el-card>
  </div>
  <div style="margin-top:10px;display: flex; align-items: stretch">
    <el-card class="box-card" style="width: 50%;margin-right: 10px">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>设备采购次数统计</span>
        <div>
          <el-radio-group size="mini" v-model="type3">
            <el-radio-button label="bar">柱状图</el-radio-button>
            <el-radio-button label="line">折线图</el-radio-button>
            <el-radio-button label="pie">饼图</el-radio-button>
            <el-radio-button label="scatter">散点图</el-radio-button>
          </el-radio-group>
        </div>
      </div>
      <div style="padding: 10px">
        <div class="box-pie" style="height: 300px;width: 100%;" ref="chart3"></div>
      </div>
    </el-card>
    <el-card class="box-card" style="width: 50%">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>供应商合作次数统计</span>
        <div>
          <el-radio-group size="mini" v-model="type4">
            <el-radio-button label="bar">柱状图</el-radio-button>
            <el-radio-button label="line">折线图</el-radio-button>
            <el-radio-button label="pie">饼图</el-radio-button>
            <el-radio-button label="scatter">散点图</el-radio-button>
          </el-radio-group>
        </div>
      </div>
      <div style="padding: 10px">
        <div class="box-pie" style="height: 300px;width: 100%;" ref="chart4"></div>
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
      chart1: null,
      chart2: null,
      chart3: null,
      chart4: null,
      option1 : {
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
      },
      option2 : {
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
            type: 'line'
          }
        ]
      },
      option3 : {
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
            type: 'pie'
          }
        ]
      },
      option4 : {
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
            type: 'scatter'
          }
        ]
      },
      type1:'bar',
      theme1:'dark',
      type2:'line',
      theme2:'dark',
      type3:'pie',
      theme3:'dark',
      type4:'scatter',
      theme4:'dark'
    }
  },
  methods: {
    initChart1() {
      this.$http.get('/statistics/s4')
      .then((data) => {
        let arr1 = []
        let arr2 = []
        data.forEach((item) => {
          arr1.push(item.deptName)
          arr2.push(item.count)
        })
        this.option1.series[0].data = arr2
        this.option1.xAxis.data = arr1
        this.chart1 = this.$echarts.init(this.$refs.chart1,'dark')
        this.chart1.setOption(this.option1)
      })
    },
    initChart2() {
      this.$http.get('/statistics/s1')
          .then((data) => {
            let arr1 = []
            let arr2 = []
            data.forEach((item) => {
              arr1.push(item.username)
              arr2.push(item.count)
            })
            this.option2.series[0].data = arr2
            this.option2.xAxis.data = arr1
            this.chart2 = this.$echarts.init(this.$refs.chart2,'dark')
            this.chart2.setOption(this.option2)
          })
    },
    initChart3() {
      this.$http.get('/statistics/s3')
          .then((data) => {
            let arr1 = []
            let arr2 = []
            data.forEach((item) => {
              arr1.push(item.deviceName)
              arr2.push(item.count)
            })
            this.option3.series[0].data = arr2
            this.option3.xAxis.data = arr1
            this.chart3 = this.$echarts.init(this.$refs.chart3,'dark')
            this.chart3.setOption(this.option3)
          })
    },
    initChart4() {
      this.$http.get('/statistics/s2')
          .then((data) => {
            let arr1 = []
            let arr2 = []
            data.forEach((item) => {
              arr1.push(item.providerName)
              arr2.push(item.count)
            })
            this.option4.series[0].data = arr2
            this.option4.xAxis.data = arr1
            this.chart4 = this.$echarts.init(this.$refs.chart4,'dark')
            this.chart4.setOption(this.option4)
          })
    }
  },
  mounted() {
    this.initChart1()
    this.initChart2()
    this.initChart3()
    this.initChart4()
  },
  watch: {
    type1: {
      handler(newVal, oldVal) {
        this.option1.series.forEach((item) => {
          item.type = newVal
        })
        this.chart1.setOption(this.option1)
      }
    },
    type2: {
      handler(newVal, oldVal) {
        this.option2.series.forEach((item) => {
          item.type = newVal
        })
        this.chart2.setOption(this.option2)
      }
    },
    type3: {
      handler(newVal, oldVal) {
        this.option3.series.forEach((item) => {
          item.type = newVal
        })
        this.chart3.setOption(this.option3)
      }
    },
    type4: {
      handler(newVal, oldVal) {
        this.option4.series.forEach((item) => {
          item.type = newVal
        })
        this.chart4.setOption(this.option4)
      }
    },
    // theme: {
    //   handler(newVal, oldVal) {
    //     this.myChart.dispose()
    //     this.init()
    //   }
    // }
  },
}
</script>

<style scoped>

</style>