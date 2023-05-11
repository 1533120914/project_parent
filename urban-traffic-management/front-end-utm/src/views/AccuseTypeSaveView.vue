<template>
  <el-card class="box-card">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>添加投诉类型</span>
    </div>
    <div>
      <el-form :model="formData" ref="form" :rules="rules">
        <el-form-item label="类型名称" prop="name">
          <el-input v-model="formData.name" placeholder="请输入类型名称"/>
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
  name: "AccuseTypeSaveView",
  data() {
    return {
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
    submit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post('/accuse-type/save', this.formData).then((data) => {
            this.$router.push('/accuse-type')
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>