<template>
  <el-card class="box-card">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>修改设备类型</span>
    </div>
    <div>
      <el-form :model="formData" ref="form" :rules="rules">
        <el-form-item label="类型名称" prop="name">
          <el-input v-model="formData.name" placeholder="请输入类型名称"/>
        </el-form-item>
        <el-form-item label="采购负责人">
          <el-select v-model="formData.buyerId" placeholder="请选择采购负责人" style="width: 100%">
            <el-option v-for="user in userList" :label="user.username" :value="user.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="管理负责人">
          <el-select v-model="formData.managerId" placeholder="请选择管理负责人" style="width: 100%">
            <el-option v-for="user in userList" :label="user.username" :value="user.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit('form')">保存</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "DeviceTypeSaveView",
  data() {
    return {
      userList:[],
      deviceTypeId:null,
      formData:{
        name: null
      },
      rules: {
        name:[
          { required: true, message:'类型名称不能为空', trigger:'blur' }
        ]
      }
    }
  },
  methods: {
    getUserList() {
      this.$http.get('/account/list')
      .then((data) => {
        this.userList = data
      })
    },
    getDeviceType() {
      this.$http.get('/device-type/detail/' + this.deviceTypeId)
      .then((data) => {
        this.formData = data
      })
    },
    submit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if( this.formData.buyerId ) {
            this.userList.forEach((user) => {
              if(user.id == this.formData.buyerId) {
                this.formData.buyerName = user.username
              }
            })
          }
          if( this.formData.managerId ) {
            this.userList.forEach((user) => {
              if(user.id == this.formData.managerId) {
                this.formData.managerName = user.username
              }
            })
          }
          this.$http.post('/device-type/save', this.formData).then((data) => {
            this.$router.push('/device-type')
          })
        }
      })
    }
  },
  created() {
    this.deviceTypeId = this.$route.params.id
    this.getDeviceType()
    this.getUserList()
  }
}
</script>

<style scoped>

</style>