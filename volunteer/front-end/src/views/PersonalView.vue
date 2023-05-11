<template>
  <div>
    <el-card class="box-card">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>个人信息</span>
      </div>
      <div>
        <el-descriptions class="margin-top" :column="1" border>
          <template slot="extra"></template>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              用户头像
            </template>
            <el-image style="width: 100px; height: 100px" :src="avatar" ></el-image>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              用户编号
            </template>
            {{ user.id }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              用户名
            </template>
            {{ user.username }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              用户类型
            </template>
            {{ account.identity == 1 ? '管理员':'志愿者' }}
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-card>
    <el-card class="box-card" style="margin-top: 20px">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span v-if="account.identity==1">我发布的任务</span>
        <span v-if="account.identity==2">我完成的任务</span>
      </div>
      <div>
        <el-table :data="tableData" stripe border max-height="600" style="width: 100%">
          <el-table-column prop="id" label="任务编号" min-width="150"></el-table-column>
          <el-table-column prop="typeName" label="类型名称" min-width="150"></el-table-column>
          <el-table-column prop="title" label="任务标题" min-width="150"></el-table-column>
          <el-table-column label="任务状态" min-width="150">
            <template v-slot="scope">
              <el-tag v-if="scope.row.state==0" type="info">未领取</el-tag>
              <el-tag v-if="scope.row.state==1" type="warning">未完成</el-tag>
              <el-tag v-if="scope.row.state==2" type="success">已完成</el-tag>
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
  </div>
</template>

<script>
export default {
  name: "PersonalView",
  data() {
    return {
      account: null,
      user: {},
      tableData:[],
      total:0,
      pages:0,
      sizes:[5, 10, 20, 50, 100],
      param: {
        current: 1,
        size: 10
      }
    }
  },
  computed: {
    avatar() {
      return 'http://localhost:8888/file/download/avatar?fileName=' + this.user.avatar
    }
  },
  methods: {
    getPersonal() {
      this.$http.get('/account/personal', {
        params: this.account
      }).then((data) => {
        this.user = data
      })
    },
    getTableData() {
      if(this.account.identity == 1) {
        this.param.createBy = this.account.id
      }else if(this.account.identity == 2) {
        this.param.volunteerId = this.account.id
        this.param.state = 2
      }
      this.$http.get('/task/query', {
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
    }
  },
  created() {
    this.account = JSON.parse(localStorage.getItem('account'))
    this.getPersonal()
    this.getTableData()
  }
}
</script>

<style scoped>

</style>