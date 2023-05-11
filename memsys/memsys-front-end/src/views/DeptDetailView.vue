<template>
<div>
  <el-card class="box-card">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>部门基础信息</span>
    </div>
    <div>
      <el-descriptions class="margin-top" :column="4" direction="vertical" border>
        <template slot="extra"></template>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            部门编号
          </template>
          {{ dept.deptId }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            部门名称
          </template>
          {{ dept.deptName }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            部门负责人
          </template>
          {{ dept.username }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            部门人数
          </template>
          {{ dept.count }}
        </el-descriptions-item>
      </el-descriptions>
    </div>
  </el-card>
  <el-card class="box-card" style="margin-top: 10px">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>部门员工信息</span>
      <el-button @click="$router.push('/account/save?deptId=' + deptId + '&deptName=' + dept.deptName)" type="primary">添加员工</el-button>
    </div>
    <div>
      <el-table :data="employeeList" stripe border max-height="600" style="width: 100%">
        <el-table-column prop="id" label="员工编号" min-width="150"></el-table-column>
        <el-table-column label="员工姓名" min-width="150">
          <template v-slot="scope">
            <el-tag type="success" v-if="scope.row.id==dept.employeeId">部门负责人：{{ scope.row.username }}</el-tag>
            <span v-else>{{ scope.row.username }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="sex" label="性别" min-width="150">
          <template v-slot="scope">
            <el-tag v-if="scope.row.sex=='男'">男</el-tag>
            <el-tag v-else type="danger">女</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="mobile" label="联系方式" min-width="150"></el-table-column>
        <el-table-column  v-if="account.identity==1"  label="操作" min-width="250">
          <template v-slot="scope">
            <el-button size="mini" v-if="scope.row.state==0" type="danger" @click="lock(scope.row.id, 1)">封号</el-button>
            <el-button size="mini" v-if="scope.row.state==1" type="success" @click="lock(scope.row.id, 0)">解封</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </el-card>
</div>
</template>

<script>
export default {
  name: "DeptDetailView",
  data() {
    return {
      account:null,
      deptId : null,
      dept: {},
      employeeList: []
    }
  },
  methods:{
    getDeptDetail() {
      this.$http.get('/dept/detail/' + this.deptId)
      .then((data) => {
        this.dept = data.dept
        this.employeeList = data.employeeList
      })
    },
    lock(id,state) {
      this.$http.post('/account/lock', {
        id,state
      }).then((data) => {
        this.getDeptDetail()
      })
    }
  },
  created() {
    this.account = JSON.parse(localStorage.getItem('account'))
    this.deptId = this.$route.params.id
    this.getDeptDetail()
  }
}
</script>

<style scoped>

</style>