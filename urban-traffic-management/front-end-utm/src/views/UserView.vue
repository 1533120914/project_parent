<template>
  <el-card class="box-card">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>投诉列表</span>
    </div>
    <div>
      <el-form :inline="true" :model="param" style="min-width: 1000px">
        <el-form-item>
          <el-input v-model="param.username" placeholder="用户名"/>
        </el-form-item>
        <el-form-item>
          <el-select v-model="param.identity" placeholder="用户类型">
            <el-option label="管理员" value="1"></el-option>
            <el-option label="工作人员" value="2"></el-option>
            <el-option label="市民" value="3"></el-option>
          </el-select>
        </el-form-item>
        <!-- 搜索按钮也是调用getCarList方法 -->
        <el-button type="primary" @click="getTableData">搜索</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form>
      <el-table :data="tableData" stripe border max-height="600" style="width: 100%">
        <el-table-column prop="id" label="用户编号" min-width="150"></el-table-column>
        <el-table-column prop="username" label="用户名" min-width="150"></el-table-column>
        <el-table-column label="性别" min-width="150">
          <template v-slot="scope">
            <el-tag v-if="scope.row.sex=='女'" type="danger">女</el-tag>
            <el-tag v-if="scope.row.sex=='男'">男</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="mobile" label="联系方式" min-width="150"></el-table-column>
        <el-table-column label="头像" min-width="150">
          <template v-slot="scope">
            <el-image style="width: 40px; height: 40px" :src="avatar(scope.row.avatar)" ></el-image>
          </template>
        </el-table-column>
        <el-table-column label="操作" min-width="300px">
          <template v-slot="scope">
            <el-button v-if="scope.row.state == 0" type="primary" size="mini" @click="lockAccount(scope.row.id,1)">封号</el-button>
            <el-button v-if="scope.row.state == 1" type="success"  size="mini"  @click="lockAccount(scope.row.id,0)">解封</el-button>
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
  name: "UserView",
  data() {
    return {
      param:{
        username: null,
        identity: null,
        current: 1,
        size: 10
      },
      tableData: [],
      total:0,
      pages:0,
      sizes:[5, 10, 20, 50, 100]
    }
  },
  methods: {
    getTableData() {
      this.$http.get('/account/query', {
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
      this.param.username = null
      this.param.identity = null
    },
    avatar(avatar) {
      return 'http://localhost:8888/file/download/avatar?fileName=' + avatar
    },
    lockAccount(id, state){
      this.$http.post('/account/lock', {
        id, state
      }).then((data) => {
        this.getTableData()
      })
    }
  },
  created() {
    this.getTableData()
  }
}
</script>

<style scoped>

</style>