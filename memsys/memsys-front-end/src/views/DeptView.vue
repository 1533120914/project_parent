<template>
  <el-card class="box-card">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>部门列表</span>
      <el-button @click="$router.push('/dept/save')" type="primary">添加部门</el-button>
    </div>
    <div>
      <el-table :data="tableData" stripe border max-height="600" style="width: 100%">
        <el-table-column prop="deptId" label="部门编号" min-width="150"></el-table-column>
        <el-table-column prop="deptName" label="部门名称" min-width="150"></el-table-column>
        <el-table-column prop="username" label="部门负责人" min-width="150"></el-table-column>
        <el-table-column prop="count" label="部门人数" min-width="150"></el-table-column>
        <el-table-column label="操作" min-width="300px">
          <template v-slot="scope">
            <el-button  type="primary" size="mini" @click="$router.push('/dept/detail/' + scope.row.deptId)">部门详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "DeptView",
  data() {
    return {
      tableData: []
    }
  },
  methods:{
    getTableData() {
      this.$http.get('/dept/view').then((data) => {
        this.tableData = data
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