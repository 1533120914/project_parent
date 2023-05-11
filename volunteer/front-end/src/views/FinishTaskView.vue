<template>
  <div>
    <el-card class="box-card">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>任务基本信息</span>
      </div>
      <div>
        <el-descriptions class="margin-top" :title="task.title" :column="1" border>
          <template slot="extra"></template>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              任务编号
            </template>
            {{ task.id }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              任务标题
            </template>
            {{ task.title }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              志愿者
            </template>
            {{ task.volunteerName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              任务描述
            </template>
            {{ task.description }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              任务发布日期
            </template>
            {{ task.createDate }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              任务状态
            </template>
            <el-tag v-if="task.state==0" type="info">未领取</el-tag>
            <el-tag v-if="task.state==1" type="warning">已领取,未完成</el-tag>
            <el-tag v-if="task.state==2" type="success">已完成</el-tag>
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-card>
    <el-card class="box-card" style="margin-top: 20px">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>任务图片</span>
      </div>
      <div>
        <el-upload
            class="upload-demo"
            ref="upload"
            action="http://localhost:8888/task/picture/upload"
            :data="{id: taskId}"
            :auto-upload="false"
            :before-remove="handleBeforeRemove"
            :on-success="handleSuccess"
            list-type="picture">
          <el-button slot="trigger" size="small" type="primary">选择图片</el-button>
          <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传图片</el-button>
        </el-upload>
        <el-button style="margin-top: 20px" size="small" type="primary" v-if="flag" @click="handleFinishTask">完成任务</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "FinishTaskView",
  data() {
    return {
      account: null,
      taskId: null,
      task:{},
      flag: false
    }
  },
  methods:{
    getTaskDetail() {
      this.$http.get('/task/detail/' + this.taskId)
      .then((data) => {
        this.task = data.task
      })
    },
    submitUpload() {
      this.$refs.upload.submit();
    },
    handleBeforeRemove(file) {
      this.$http.get('/task/picture/del' + file.id)
    },
    handleSuccess(response, file) {
      if(response.code = '000000') {
        this.$message.success(file.name + '图片上传成功!')
        file.id = response.data.id
        this.flag = true
      }else {
        this.$message.error(`${response.code}:${response.msg}`)
      }
    },
    handleFinishTask() {
      this.$confirm('点击完成后将不可以继续上传图片,是否确定完成任务?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(() => {
        this.$http.post('/task/finish/' + this.taskId)
            .then((data) => {
              this.$router.push('/task')
            })
      })
    }
  },
  created() {
    this.account = JSON.parse(localStorage.getItem('account'))
    this.taskId = this.$route.params.id
    this.getTaskDetail()
  }
}
</script>

<style scoped>

</style>