<template>
<div>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>供应商列表</span>
    </div>
    <div class="text item">
      <el-form :inline="true" :model="param" class="demo-form-inline" style="min-width: 800px">
        <el-form-item label="选择设备">
          <el-select style="width: 100%" v-model="param.deviceTypeId" placeholder="选择设备">
            <el-option v-for="item in deviceTypeList" :value="item.id" :label="item.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="选择供应商">
          <el-select style="width: 100%" v-model="param.providerId" placeholder="选择供应商">
            <el-option v-for="item in providerList" :value="item.id" :label="item.providerName"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="选择采购状态">
          <el-select style="width: 100%" v-model="param.state" placeholder="采购状态">
            <el-option value="0" label="未审核"></el-option>
            <el-option value="1" label="已审核"></el-option>
            <el-option value="2" label="已结算"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getTableData">搜索</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
      <el-table :data="tableData" highlight-current-row border stripe style="width: 100%">
        <el-table-column prop="id" label="订单编号" width="140" min-width="100"></el-table-column>
        <el-table-column prop="buyerName" label="采购人" width="140" min-width="100"></el-table-column>
        <el-table-column prop="providerName" label="供应商名称" width="140" min-width="100"></el-table-column>
        <el-table-column prop="deviceName" label="设备名称" width="140" min-width="100"></el-table-column>
        <el-table-column prop="quantity" label="采购数量" width="140" min-width="100"></el-table-column>
        <el-table-column prop="price" label="设备报价" width="140" min-width="100"></el-table-column>
        <el-table-column label="状态" width="220" min-width="100">
          <template v-slot="scope">
            <el-tag type="info" v-if="scope.row.state==0">未审核</el-tag>
            <el-tag v-if="scope.row.state==1">已审核</el-tag>
            <el-tag type="success" v-if="scope.row.state==2">已结算</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" min-width="250">
          <template v-slot="scope">
            <el-button size="mini" type="primary" v-if="account.identity==1 && scope.row.state==0" @click="handle(scope.row.id, 1)">审核</el-button>
            <el-button size="mini" type="primary" v-if="account.identity==2 && scope.row.state==1" @click="handle(scope.row.id, 2)">结算</el-button>
            <el-button size="mini" type="primary" v-if="scope.row.state==3" @click="">查看详情</el-button>
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
  name: "DeviceProcurementRecordView",
  data() {
    return {
      account:null,
      tableData: [],
      deviceTypeList:[],
      providerList:[],
      param:{
        deviceTypeId:null,
        providerId:null,
        state:null,
        current:1,
        size:10
      },
      total:0,
      pages:0,
      sizes:[5,10,20,50,100],
    }
  },
  methods: {
    getTableData() {
      if(this.account.identity == 2) {
        this.param.buyerId = this.account.id
      }
      this.$http.get('/device-procurement/view', {
        params: this.param
      }).then((data) => {
        this.tableData = data.records
        this.total = data.total
        this.pages = data.pages
      })
    },
    getDeviceTypeList() {
      this.$http.get('device-type/list')
      .then((data) => {
        this.deviceTypeList = data
      })
    },
    getProviderList() {
      this.$http.get('provider/list')
      .then((data) => {
        this.providerList = data
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
    handle(id, state) {
      this.$http.post('/device-procurement/state', {
        id, state
      }).then((data) => {
        this.getTableData()
      })
    },
    resetForm() {
      this.param.providerId=null
      this.param.deviceTypeId=null
      this.param.state=null
    }
  },
  created() {
    this.account = JSON.parse(localStorage.getItem('account'))
    this.getProviderList()
    this.getDeviceTypeList()
    this.getTableData()
  }
}
</script>

<style scoped>

</style>