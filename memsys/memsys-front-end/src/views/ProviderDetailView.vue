<template>
<div>
  <el-card class="box-card">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>供应商信息</span>
    </div>
    <div>
      <el-descriptions class="margin-top" :column="2" border>
        <template slot="extra"></template>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            供应商编号
          </template>
          {{ provider.id }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            供应商名称
          </template>
          {{ provider.providerName }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            供应商联系人
          </template>
          {{ provider.linkman }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            供应商联系方式
          </template>
          {{ provider.tel }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            供应商邮箱
          </template>
          {{ provider.email }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            收款银行
          </template>
          {{ provider.bankName }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            收款账户
          </template>
          {{ provider.bankAccount }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            供应商地址
          </template>
          {{ provider.address }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            供应商注册日期
          </template>
          {{ provider.createDate }}
        </el-descriptions-item>
      </el-descriptions>
    </div>
  </el-card>
  <el-card class="box-card" style="margin-top: 10px">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>设备供应信息</span>
    </div>
    <div>
      <el-table :data="deviceList" highlight-current-row border stripe style="width: 100%">
        <el-table-column prop="providerName" label="供应商名称" width="220" min-width="100"></el-table-column>
        <el-table-column prop="deviceId" label="设备编号" width="220" min-width="100"></el-table-column>
        <el-table-column prop="deviceName" label="设备名称" width="220" min-width="100"></el-table-column>
        <el-table-column prop="price" label="设备报价" width="220" min-width="100"></el-table-column>
        <el-table-column label="操作" min-width="250">
          <template v-slot="scope">
            <el-button size="mini" type="primary" @click="handleProcurement(scope.row)">采购</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </el-card>
</div>
</template>

<script>
export default {
  name: "ProviderDetailView",
  data() {
    return {
      account:null,
      providerId : null,
      provider: {},
      deviceList: []
    }
  },
  methods:{
    getProviderDetail() {
      this.$http.get('/provider/detail/' + this.providerId)
      .then((data) => {
        this.provider = data.provider
        this.deviceList = data.list
      })
    },
    handleProcurement(row){
      this.$http.get('/device-type/exists', {
        params:{
          id:row.deviceId,
          buyerId: this.account.id
        }
      }).then((data) => {
        if(data == "success") {
          this.$router.push({
            name:'DeviceProcurementView',
            params: row
          })
        }else {
          this.$message.error('你没有权限采购该设备!')
        }
      })
    }
  },
  created() {
    this.providerId = this.$route.params.id
    this.account = JSON.parse(localStorage.getItem('account'))
    this.getProviderDetail()
  }
}
</script>

<style scoped>

</style>