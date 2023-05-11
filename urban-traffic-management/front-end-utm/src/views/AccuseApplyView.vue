<template>
 <div>
   <div style="display: flex">
     <el-card class="box-card" style="width: 50%;margin-right: 10px">
       <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
         <span>投诉内容</span>
       </div>
       <div>
         <el-descriptions class="margin-top" :title="accuse.title" :column="1" border>
           <template slot="extra"></template>
           <el-descriptions-item>
             <template slot="label">
               <i class="el-icon-user"></i>
               投诉编号
             </template>
             {{ accuse.id }}
           </el-descriptions-item>
           <el-descriptions-item>
             <template slot="label">
               <i class="el-icon-user"></i>
               投诉标题
             </template>
             {{ accuse.title }}
           </el-descriptions-item>
           <el-descriptions-item>
             <template slot="label">
               <i class="el-icon-user"></i>
               投诉人
             </template>
             {{ accuse.userName }}
           </el-descriptions-item>
           <el-descriptions-item>
             <template slot="label">
               <i class="el-icon-user"></i>
               处理人
             </template>
             {{ accuse.workerName }}
           </el-descriptions-item>
           <el-descriptions-item>
             <template slot="label">
               <i class="el-icon-user"></i>
               投诉日期
             </template>
             {{ accuse.createDate }}
           </el-descriptions-item>
           <el-descriptions-item>
             <template slot="label">
               <i class="el-icon-user"></i>
               处理日期
             </template>
             {{ accuse.modifyDate }}
           </el-descriptions-item>
           <el-descriptions-item>
             <template slot="label">
               <i class="el-icon-user"></i>
               投诉状态
             </template>
             <el-tag v-if="accuse.state==0" type="info">未处理</el-tag>
             <el-tag v-if="accuse.state==1" type="success">已处理</el-tag>
           </el-descriptions-item>
         </el-descriptions>
         <!--        <el-button @click="handleApply(task.id)" style="margin-top: 20px" type="primary" v-if="account.identity==2 && task.state == 0">领取任务</el-button>-->
       </div>
     </el-card>
     <el-card class="box-card" style="width: 50%;">
       <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
         <span>投诉详情</span>
       </div>
       <div>
         <el-descriptions class="margin-top" direction="vertical" :title="accuse.title" :column="2" border>
           <el-descriptions-item>
             <template slot="label">
               <i class="el-icon-user"></i>
               投诉详情
             </template>
             {{ accuse.description }}
           </el-descriptions-item>
           <el-descriptions-item>
             <template slot="label">
               <i class="el-icon-user"></i>
               图片
             </template>
             <div v-if="pictures.length == 0">
               <el-empty description="暂无图片"></el-empty>
             </div>
             <div v-else>
               <el-image style="width: 100px; height: 100px; margin: 10px" v-for="picture in pictures" :src="picture.url"></el-image>
             </div>
           </el-descriptions-item>
         </el-descriptions>
       </div>
     </el-card>
   </div>
   <el-card v-if="account.identity==2 && accuse.state == 0" class="box-card" style="margin-top: 10px">
     <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
       <span>回复</span>
     </div>
     <div>
       <el-input type="textarea" rows="5" placeholder="输入内容回复" v-model="reply"></el-input>
       <el-button @click="handleApply" style="margin-top: 20px" type="primary" v-if="account.identity==2 && account.state == 0">回复投诉</el-button>
     </div>
   </el-card>
   <el-card v-else class="box-card" style="margin-top: 10px">
     <div slot="header" style="display: flex; justify-content: space-between;align-items: center">
       <span>处理结果</span>
     </div>
     <div>
       <el-input type="textarea" rows="5" readonly v-model="accuse.reply"></el-input>
     </div>
   </el-card>
 </div>
</template>

<script>
export default {
  name: "AccuseApplyView",
  data() {
    return {
      account: null,
      accuseId: null,
      accuse: {},
      reply: null,
      pictures:[]
    }
  },
  methods: {
    getAccuseDetail() {
      this.$http.get('/accuse/detail/' + this.accuseId)
          .then((data) => {
            this.accuse = data.accuse
            data.pictureList.forEach((pic) => {
              this.pictures.push({
                name: pic.id,
                url:'http://localhost:8888/accuse/picture/download?fileName=' + pic.fileName
              })
            })
          })
    },
    handleApply() {
      this.$http.post('/accuse/apply', {
        id: this.accuse.id,
        reply: this.reply,
        workerId: this.account.id,
        workerName: this.account.username,
        state: 1
      }).then((data) => {
        this.$alert('回复成功!')
      })
    }
  },
  created() {
    this.account = JSON.parse(localStorage.getItem('account'))
    this.accuseId = this.$route.params.id
    this.getAccuseDetail()
  }
}
</script>

<style scoped>

</style>