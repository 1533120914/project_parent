<template>
<el-card>
  <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
    <span>修改密码</span>
  </div>
  <div>
    <el-form ref="form" :rules="rules" :model="formData">
      <el-form-item label="请输入原密码" prop="oldPassword">
        <el-input v-model="formData.oldPassword" placeholder="请输入原密码"></el-input>
      </el-form-item>
      <el-form-item label="请输入新密码"  prop="newPassword">
        <el-input v-model="formData.newPassword" placeholder="请输入原密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">保存</el-button>
        <el-button @click="resetForm('form')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</el-card>
</template>

<script>
export default {
  name: "ModifyPasswordView",
  data() {
    return {
      account: null,
      formData: {
        oldPassword: null,
        newPassword: null
      },
      rules: {
        oldPassword: [
          {required: true, message:'原密码不能为空', trigger:'blur'}
        ],
        newPassword: [
          {required: true, message:'新密码不能为空', trigger:'blur'}
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 把用户id和identity一起放入formData发给后端
          this.formData.id = this.account.id
          this.formData.identity = this.account.identity
          this.$http.post('/account/modify-password', this.formData)
          .then((data) => {
            localStorage.removeItem('account')
            this.$msgbox.alert('请重新登录', '密码修改成功', {
              confirmButtonText: '确定',
              callback: action => {
                this.$router.push('/')
              }
            })
          })
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  },
  created() {
    this.account = JSON.parse(localStorage.getItem('account'))
  }
}
</script>

<style scoped>

</style>