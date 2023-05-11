<template>
  <div class="login-container">
    <el-card class="box-card">
      <div class="login-body">
        <div class="login-title">疫情志愿者管理系统</div>
        <el-form ref="form" :model="account" :rules="rules">
          <el-form-item prop="username">
            <el-input placeholder="请输入登录名..." v-model="account.username">
              <template slot="prepend">
                <div class="el-icon-user-solid"></div>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input placeholder="请输入密码..." v-model="account.password"
                      @keyup.enter.native="submitForm('form')"  show-password>
              <template slot="prepend">
                <div class="el-icon-lock"></div>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-radio-group v-model="account.identity" style="display: flex;justify-content: center;margin-bottom: 20px">
              <el-radio :label="1">管理员</el-radio>
              <el-radio :label="2">志愿者</el-radio>
            </el-radio-group>
          </el-form-item>
          <div class="login-submit">
            <el-button type="primary" @click="submitForm('form')">登录</el-button>
          </div>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "LoginView",
  data() {
    return {
      account: {
        username: null,
        password: null,
        identity: 1
      },
      rules: {
        username: [
          { required:true, message:'请输入登录名', trigger:'blur'}
        ],
        password: [
          { required:true, message:'请输入密码', trigger:'blur'}
        ]
      }
    };
  },

  methods: {
    submitForm(formName) {
      // 根据传过来的form名称寻找这个form,然后调用validate函数进行校验 (根据rules)
      // valid是校验结果
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 执行登录
          this.$http.post('/account/login', this.account)
          .then((data) => {  //data就是返回的account对象
            // 存储当前用户到localStorage
            localStorage.setItem('account', JSON.stringify(data))
            // 跳转到主页
            this.$router.push('/home')
          })
        } else {
          // 不执行
          return false;
        }
      });
    }
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
  background-color: #f1f1f1;
}

.login-body {
  padding: 30px;
  width: 400px;
  height: 100%;
}

.login-title {
  padding-bottom: 30px;
  text-align: center;
  font-weight: 600;
  font-size: 20px;
  color: #409EFF;
  cursor: pointer;
}


.login-submit {
  display: flex;
  justify-content: center;
}

.sign-in-container {
  padding: 0 10px;
}

.sign-in-text {
  color: #409EFF;
  font-size: 16px;
  text-decoration: none;
  line-height:28px;
}
.other-submit{
  display:flex;
  justify-content: space-between;
  margin-top: 10px;
}
</style>