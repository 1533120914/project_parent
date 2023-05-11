<template>
  <div>
    <el-card class="box-card">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>上传图片</span>
      </div>
      <div>
        <el-upload
            class="upload-demo"
            ref="upload"
            action="http://localhost:8888/accuse/save"
            :data="formData"
            :auto-upload="false"
            :on-success="handleSuccess"
            :limit="limit"
            :file-list="fileList"
            list-type="picture">
          <el-button slot="trigger" size="small" type="primary">选择图片</el-button>
        </el-upload>
      </div>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>输入投诉详细信息</span>
      </div>
      <div>
        <el-form ref="form" :model="formData" :rules="rules" label-width="120px">
          <el-form-item label="投诉标题" prop="title">
            <el-input v-model="formData.title"  placeholder="请输入投诉标题"></el-input>
          </el-form-item>
          <el-form-item label="投诉类型" prop="typeId">
            <el-select v-model="formData.typeId" placeholder="请选择投诉类型" style="width: 100%">
              <el-option v-for="type in typeList" :label="type.name" :value="type.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="投诉内容描述" prop="description">
            <el-input type="textarea" rows="5" v-model="formData.description"  placeholder="请输入投诉内容描述"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button style="margin-top: 20px" size="small" type="primary" @click="handleSave('form')">发布投诉</el-button>
            <el-button  size="small" @click="resetForm('form')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "AccuseSaveView",
  data() {
    return {
      account: null,
      typeList:[],
      formData: {
        typeId: null,
        typeName: null,
        title: null,
        description: null
      },
      limit:1,
      fileList:[],
      fileCount:0,
      rules: {
        typeId: [
          {required: true, message:'请选择投诉类型', trigger:'blur'}
        ],
        title: [
          {required: true, message:'请输入投诉标题', trigger:'blur'}
        ],
        description: [
          {required: true, message:'请输入投诉内容描述', trigger:'blur'}
        ]
      }
    }
  },
  methods: {
    getAccuseTypeList() {
      this.$http.get('/accuse-type/list').then((data) => {
        this.typeList = data
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.$refs.upload.clearFiles()
    },
    handleSuccess(response, file) {
      if(response.code = '000000') {
        this.$message.success('您的投诉发布成功!')
      }else {
        this.$message.error(`${response.code}:${response.msg}`)
      }
    },
    handleSave(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.typeList.forEach((type) => {
            if(type.id == this.formData.typeId) {
              this.formData.typeName = type.name
            }
          })
          this.formData.userId = this.account.id
          this.formData.userName = this.account.username
          this.$refs.upload.submit();
        } else {
          return false;
        }
      });
    }
  },
  mounted() {
    this.account = JSON.parse(localStorage.getItem('account'))
    this.getAccuseTypeList()
  }
}
</script>

<style scoped>

</style>