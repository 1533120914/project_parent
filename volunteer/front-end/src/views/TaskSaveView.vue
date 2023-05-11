<template>
  <el-card class="box-card">
    <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
      <span>发布新任务</span>
    </div>
    <div>
      <el-form ref="form" :model="formData" :rules="rules" label-width="100px">
        <el-form-item label="任务标题" prop="title">
          <el-input v-model="formData.title"  placeholder="请输入任务标题"></el-input>
        </el-form-item>
        <el-form-item label="任务类型" prop="typeId">
          <el-select v-model="formData.typeId" placeholder="请选择任务类型" style="width: 100%">
            <el-option v-for="type in typeList" :label="type.name" :value="type.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="任务描述" prop="description">
          <el-input type="textarea" rows="5" v-model="formData.description"  placeholder="请输入任务描述"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"  @click="submitForm('form')">保存</el-button>
          <el-button  @click="resetForm('form')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "TaskSaveView",
  data() {
    return {
      typeList:[],
      formData: {
        typeId: null,
        typeName: null,
        title: null,
        description: null
      },
      rules: {
        typeId: [
          {required: true, message:'请选择任务类型', trigger:'blur'}
        ],
        title: [
          {required: true, message:'请输入任务标题', trigger:'blur'}
        ],
        description: [
          {required: true, message:'请输入任务描述', trigger:'blur'}
        ]
      }
    }
  },
  methods: {
    getTaskTypeList() {
      this.$http.get('/task-type/list').then((data) => {
        this.typeList = data
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.typeList.forEach((type) => {
            if(type.id == this.formData.typeId) {
              this.formData.typeName = type.name
            }
          })
          this.$http.post('/task/save', this.formData)
              .then((data)=> {
                this.$message.success('发布新任务成功!')
                this.$router.push('/task')
              })
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  mounted() {
    this.getTaskTypeList()
  }
}
</script>

<style scoped>

</style>