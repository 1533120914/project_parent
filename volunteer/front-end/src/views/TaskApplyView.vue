<template>
  <el-card class="box-card">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>待领取的任务列表</span>
    </div>
    <div>
      <el-form :inline="true" :model="param" style="min-width: 1000px">
        <el-form-item>
          <el-input v-model="param.title" placeholder="任务标题"/>
        </el-form-item>
        <el-form-item>
          <el-select v-model="param.typeId" placeholder="任务类型">
            <el-option v-for="type in typeList" :key="type.id" :label="type.name" :value="type.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-date-picker
              v-model="createDate"
              type="daterange"
              align="right"
              unlink-panels
              value-format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              :picker-options="pickerOptions">
            <!-- pickerOptions官方写好了  -->
          </el-date-picker>
        </el-form-item>
        <!-- 搜索按钮也是调用getCarList方法 -->
        <el-button type="primary" @click="getTableData">搜索</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form>
      <el-table :data="tableData" stripe border max-height="600" style="width: 100%">
        <el-table-column prop="id" label="任务编号" min-width="150"></el-table-column>
        <el-table-column prop="typeName" label="类型名称" min-width="150"></el-table-column>
        <el-table-column prop="title" label="任务标题" min-width="150"></el-table-column>
        <el-table-column label="任务状态" min-width="150">
          <template v-slot="scope">
            <el-tag v-if="scope.row.state==0" type="info">未领取</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" min-width="300px">
          <template v-slot="scope">
            <el-button size="mini" @click="$router.push('/task/detail/' + scope.row.id)">查看详情</el-button>
            <el-button type="primary" size="mini" @click="handleApply(scope.row.id)">领取任务</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          style="margin-top: 20px"
          background
          layout="sizes,prev, pager, next, jumper, total"
          :page-size="param.size"
          :current-page="param.current"
          :page-sizes="sizes"
          :total="total"
          :page-count="pages"
          @current-change="currentChange"
          @size-change="sizeChange">
      </el-pagination>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "TaskView",
  data() {
    return {
      account: null,
      typeList:[],
      createDate: null,
      param:{
        title: null,
        typeId: null,
        lowDate: null,
        highDate: null,
        state:0,
        current: 1,
        size: 10
      },
      tableData: [],
      total:0,
      pages:0,
      sizes:[5, 10, 20, 50, 100],
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      }
    }
  },
  watch: {
    createDate: {
      handler(newVal, oldVal) {
        if (newVal) {
          this.param.lowDate = newVal[0]
          this.param.highDate = newVal[1]
        }else {
          this.param.lowDate = null
          this.param.highDate = null
        }
      }
    }
  },
  methods: {
    getTaskTypeList() {
      this.$http.get('/task-type/list').then((data) => {
        this.typeList = data
      })
    },
    getTableData() {
      this.$http.get('/task/query', {
        params: this.param
      }).then((data) => {
        this.tableData = data.records
        this.total = data.total
        this.pages = data.pages
      })
    },
    handleApply(id) {
      this.$confirm('是否确认领取此任务?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(() => {
        this.$http.post('/task/apply/', {
          id: id,
          volunteerId: this.account.id,
          volunteerName: this.account.username
        }).then((data) => {
              this.$alert('领取成功!')
              this.getTableData()
            })
      })
    },
    currentChange(val){
      this.param.current = val
      this.getTableData()
    },
    // 当size改变时的回调行数
    sizeChange(val){
      this.param.size = val
      this.getTableData()
    },
    resetForm() {
      this.param.title = null
      this.param.typeId = null
      this.param.lowDate = null
      this.param.highDate = null
    }
  },
  created() {
    this.account = JSON.parse(localStorage.getItem('account'))
    this.getTaskTypeList()
    this.getTableData()
  }
}
</script>

<style scoped>

</style>