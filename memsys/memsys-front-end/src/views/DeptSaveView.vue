<template>
  <el-card class="box-card">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>添加部门</span>
    </div>
    <div>
      <el-form :model="formData" ref="form" :rules="rules">
        <el-form-item label="部门名称" prop="name">
          <el-input v-model="formData.name" placeholder="请输入部门名称"/>
        </el-form-item>
        <el-form-item label="部门负责人">
          <el-select v-model="formData.headerId" placeholder="请选择部门负责人" style="width: 100%">
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
  name: "DeptSaveView",
  data() {
    return {
      userList:[],
      formData:{
        name: null
      },
      rules: {
        name:[
          { required: true, message:'部门名称不能为空', trigger:'blur' }
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
    submit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post('/dept/save', this.formData).then((data) => {
            this.$router.push('/dept/view')
          })
        }
      })
    }
  },
  created() {
    this.getUserList()
  }
}
</script>

<style scoped>

</style>