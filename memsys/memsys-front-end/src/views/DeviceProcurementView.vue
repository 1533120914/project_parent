<template>
<div>
  <el-card class="box-card">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>添加设备采购记录</span>
    </div>
    <div>
      <el-form :model="formData" ref="form" :rules="rules">
        <el-descriptions class="margin-top" :column="1" border>
          <template slot="extra"></template>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              采购设备编号
            </template>
            {{ provider.deviceId }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              采购设备名称
            </template>
            {{ provider.deviceName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              供应商编号
            </template>
            {{ provider.providerId }}
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
              设备报价
            </template>
            {{ provider.price }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              采购人
            </template>
            {{ account.username }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              采购数量
            </template>
            <el-input v-model="formData.quantity" placeholder="请输入采购数量"/>
          </el-descriptions-item>
        </el-descriptions>
        <div style="float: right;margin: 20px 0px">
          <el-button type="primary" @click="save">下单</el-button>
        </div>
      </el-form>
    </div>
  </el-card>
</div>
</template>

<script>
export default {
  name: "DeviceProcurementView",
  data() {
    return {
      account:null,
      provider:null,
      formData:{
        quantity:null
      },
      rules: {
        name:[
          { required: true, message:'部门名称不能为空', trigger:'blur' }
        ]
      }
    }
  },
  methods:{
    save() {
      if(this.formData.quantity && this.formData.quantity < 1) {
        this.$message.error('请输入正确的采购数量')
      }
      this.formData.buyerId = this.account.id
      this.formData.deviceTypeId = this.provider.deviceId
      this.formData.providerId = this.provider.providerId
      this.$http.post('/device-procurement/save', this.formData)
        .then((data) => {

        })
    }
  },
  created() {
    this.account = JSON.parse(localStorage.getItem('account'))
    this.provider = this.$route.params
  }
}
</script>

<style scoped>

</style>