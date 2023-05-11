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
            {{ task.volunteerName ? task.volunteerName: '暂无志愿者领取' }}
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
        <el-button @click="handleApply(task.id)" style="margin-top: 20px" type="primary" v-if="account.identity==2 && task.state == 0">领取任务</el-button>
      </div>
    </el-card>
    <el-card class="box-card" style="margin-top: 20px">
      <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
        <span>任务图片</span>
      </div>
      <div>
        <div v-if="pictures.length == 0">
          <el-empty description="暂无图片"></el-empty>
        </div>
        <div v-else>
          <el-image style="width: 100px; height: 100px; margin: 10px" v-for="picture in pictures" :src="picture.url"></el-image>
        </div>
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
      pictures:[],
      flag: false
    }
  },
  methods:{
    getTaskDetail() {
      this.$http.get('/task/detail/' + this.taskId)
      .then((data) => {
        this.task = data.task
        data.pictureList.forEach((pic) => {
          this.pictures.push({
            name: pic.id,
            url:'http://localhost:8888/task/picture/download?fileName=' + pic.fileName
          })
        })
      })
    },
    handleApply(id) {
      this.$confirm('是否确认领取此任务?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(() => {
        this.$http.post('/task/apply/', {
          id: id,
          volunteerId: this.account.id,
          volunteerName: this.account.username
        }).then((data) => {
          this.$alert('领取成功!')
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