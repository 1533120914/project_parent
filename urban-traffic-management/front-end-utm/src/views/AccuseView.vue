<template>
  <el-card class="box-card">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>投诉列表</span>
    </div>
    <div>
      <el-form :inline="true" :model="param" style="min-width: 1000px">
        <el-form-item>
          <el-input v-model="param.title" placeholder="投诉标题"/>
        </el-form-item>
        <el-form-item>
          <el-select v-model="param.typeId" placeholder="投诉类型">
            <el-option v-for="type in typeList" :key="type.id" :label="type.name" :value="type.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-select v-model="param.state" placeholder="投诉状态">
            <el-option label="未处理" value="0"></el-option>
            <el-option label="已处理" value="1"></el-option>
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
        <el-table-column prop="id" label="投诉编号" min-width="150"></el-table-column>
        <el-table-column prop="typeName" label="投诉类型" min-width="150"></el-table-column>
        <el-table-column prop="title" label="投诉主题" min-width="150"></el-table-column>
        <el-table-column label="投诉状态" min-width="150">
          <template v-slot="scope">
            <el-tag v-if="scope.row.state==0" type="info">未处理</el-tag>
            <el-tag v-if="scope.row.state==1" type="success">已处理</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" min-width="300px">
          <template v-slot="scope">
            <el-button v-if="account.identity==1" type="danger" size="mini" @click="handleDelete(scope.row.id)">删除</el-button>
            <el-button v-if="scope.row.state == 1" size="mini" @click="$router.push('/accuse/apply/' + scope.row.id)">查看详情</el-button>
            <el-button v-if="account.identity==2 && scope.row.state == 0" type="primary" size="mini" @click="$router.push('/accuse/apply/' + scope.row.id)">去处理</el-button>
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
      this.$http.get('/accuse-type/list').then((data) => {
        this.typeList = data
      })
    },
    getTableData() {
      if(this.account.identity == 3) {
        this.param.userId = this.account.id
      }
      this.$http.get('/accuse/query', {
        params: this.param
      }).then((data) => {
        this.tableData = data.records
        this.total = data.total
        this.pages = data.pages
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
      this.param.state = null
    },
    handleDelete(id) {
      this.$confirm('是否确认删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(() => {
        this.$http.get('/accuse/del/' + id)
            .then((data) => {
              this.getTableData()
            })
      })
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