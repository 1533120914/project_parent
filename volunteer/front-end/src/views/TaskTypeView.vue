<template>
  <el-card class="box-card">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>任务类型列表</span>
      <el-button @click="$router.push('/task-type/save')" type="primary">添加类型</el-button>
    </div>
    <div>
      <el-table :data="tableData" stripe border max-height="600" style="width: 100%">
        <el-table-column prop="id" label="类型编号" min-width="150"></el-table-column>
        <el-table-column prop="name" label="类型名称" min-width="150"></el-table-column>
      </el-table>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "TaskTypeView",
  data() {
    return {
      tableData: []
    }
  },
  methods:{
    getTableData() {
      this.$http.get('/task-type/list').then((data) => {
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