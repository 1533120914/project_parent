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
            {{ account.id }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              用户名
            </template>
            {{ account.username }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              用户类型
            </template>
            <el-tag v-if="account.identity == 1">管理员</el-tag>
            <el-tag v-if="account.identity == 2">工作人员</el-tag>
            <el-tag v-if="account.identity == 3">市民</el-tag>
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-card>
    <el-card class="box-card" style="margin-top: 20px">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span v-if="account.identity==1">已处理的投诉</span>
        <span v-if="account.identity==2">我处理的投诉</span>
        <span v-if="account.identity==3">我发布的投诉</span>
      </div>
      <div>
        <el-table :data="tableData" stripe border max-height="600" style="width: 100%">
          <el-table-column prop="id" label="投诉编号" min-width="150"></el-table-column>
          <el-table-column prop="typeName" label="投诉类型" min-width="150"></el-table-column>
          <el-table-column prop="title" label="投诉主题" min-width="150"></el-table-column>
          <el-table-column label="投诉状态" min-width="150">
            <template v-slot="scope">
              <el-tag v-if="scope.row.state==1" type="success">已处理</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" min-width="150">
            <template v-slot="scope">
              <el-button v-if="scope.row.state == 1" size="mini" @click="$router.push('/accuse/apply/' + scope.row.id)">查看详情</el-button>
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
      return 'http://localhost:8888/file/download/avatar?fileName=' + this.account.avatar
    }
  },
  methods: {
    getTableData() {
      if(this.account.identity == 2) {
        this.param.workerId = this.account.id
      }else if(this.account.identity == 3) {
        this.param.userId = this.account.id
      }
      this.param.state = 1
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
    }
  },
  created() {
    this.account = JSON.parse(localStorage.getItem('account'))
    this.getTableData()
  }
}
</script>

<style scoped>

</style>