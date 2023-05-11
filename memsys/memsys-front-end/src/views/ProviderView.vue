<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>供应商列表</span>
    </div>
    <div class="text item">
      <el-form :inline="true" :model="param" class="demo-form-inline" style="min-width: 800px">
        <el-form-item>
          <el-input placeholder="供应商名称" v-model="param.providerName"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input placeholder="供应商联系人" v-model="param.linkman"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input placeholder="供应商号码" v-model="param.tel"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getTableData">搜索</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
      <el-table :data="tableData" highlight-current-row border stripe style="width: 100%">
        <el-table-column prop="id" label="供应商编号" width="220" min-width="100"></el-table-column>
        <el-table-column prop="providerName" label="供应商名称" width="220" min-width="100"></el-table-column>
        <el-table-column prop="linkman" label="供应商联系人" width="220" min-width="100"></el-table-column>
        <el-table-column prop="tel" label="电话号码" width="220" min-width="100"></el-table-column>
        <el-table-column prop="email" label="邮箱" width="220" min-width="100"></el-table-column>
        <el-table-column label="操作" min-width="250">
          <template v-slot="scope">
            <el-button size="mini" type="primary" @click="$router.push('/provider/detail/' + scope.row.id)">设备报价</el-button>
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
  name: "DeptView",
  data(){
    return {
      param:{
        providerName:'',
        linkman:'',
        tel:'',
        current:1,
        size:10
      },
      total:0,
      pages:0,
      sizes:[5,10,20,50,100],
      tableData: []
    }
  },
  methods:{
    getTableData() {
      this.$http.get('/provider/query', { params: this.param })
          .then((data)=> {
            this.tableData = data.records
            this.total = data.total
            this.pages = data.pages
          })
    },
    currentChange(val){
      this.param.current = val
      this.getTableData()
    },
    sizeChange(val){
      this.param.size = val
      this.getTableData()
    },
    handleEdit(index, row) {
      this.$router.push('/provider/modify/' + row.id)
    },
    handleDetail(index, row) {
      this.$router.push('/provider/detail/' + row.id)
    },
    handleDelete(index, row) {
      this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.delete('/provider/remove/' + row.id).then((data) => {
          this.$notify({
            type:'success',
            title:'删除成功'
          })
          this.getTableData()
        })
      })
    },
    resetForm() {
      this.param.providerName = null
      this.param.tel = null
      this.param.linkman = null
    },
  },
  mounted() {
    this.getTableData()
  }
}
</script>

<style scoped>

</style>