<template>
  <el-card class="box-card">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>{{$route.query.deptName}} - 添加员工</span>
    </div>
    <div>
      <el-form :model="formData" ref="form" :rules="rules">
        <el-form-item label="员工名称" prop="username">
          <el-input v-model="formData.username" placeholder="请输入员工名称"/>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="formData.sex" placeholder="请选择性别" style="width: 100%">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系方式" prop="mobile">
          <el-input v-model="formData.mobile" placeholder="请输入联系方式"/>
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
  name: "AccountSaveView",
  data() {
    return {
      formData:{
        name: null
      },
      rules: {
        username:[
          { required: true, message:'员工名称不能为空', trigger:'blur' }
        ]
      }
    }
  },
  methods: {
    submit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post('/account/save', this.formData).then((data) => {
            this.$router.back()
          })
        }
      })
    }
  },
  created() {
    this.formData.deptId = this.$route.query.deptId
  }
}
</script>

<style scoped>

</style>