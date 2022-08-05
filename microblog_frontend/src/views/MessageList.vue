<template>
  <el-container>
    <el-header>
      <div>
        <div style="width:200px; margin-left:0">
          <el-button icon="el-icon-arrow-left" onclick="window.location.href='http://localhost:8080/home'">返回</el-button>
        </div>
      </div>
    </el-header>
    <h1 style="font-size: 30px">私信列表</h1>
    <el-main>
      <dl>
        <dd v-for="message in messageList">
          <div style="width: 600px; height: 150px; background-color: #e8fcd2; margin: 0 auto; border:1px solid #000000" @click="showMessagePage(message.personId)">
            <div style="height: 25px">
              <div style="height: 15px; width: 15px; background-color: crimson; border-radius: 50%" v-show="message.state === 'UNREAD'"></div>
            </div>
            <div style="float: left; margin-left: 40px">
              <el-avatar :size="80" :src=getImgUrl(message.personPic) @click.native="showPersonalPage(message.personId)"></el-avatar>
              <div style="font-size: 20px">{{ message.personName }}</div>
            </div>
            <div style="float: left; margin-left: 50px">
              <div style="width:200px; font-size: 20px; color: darkslategray" >{{ message.lastTime }}</div>
              <div style="height: 20px"></div>
              <div style="font-size: 30px">{{ message.lastContent }}</div>
            </div>
            <div style="float: right; margin-right: 10px">
              <el-link type="danger">删除</el-link>
            </div>
          </div>
          <div style="height: 10px"></div>
        </dd>
      </dl>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "MessageList",
  data () {
    return {
      messageList: [
        {
          messageId: 123,
          personId: 123,
          personPic: "Stewart/1.jpg",
          personName: "name",
          lastTime: "time",
          lastContent: "Content",
          state: "READ"
        },
        {
          messageId: 123,
          personId: 123,
          personPic: "Stewart/1.jpg",
          personName: "name2",
          lastTime: "time2",
          lastContent: "Content2",
          state: "UNREAD"
        }
      ]
    }
  },
  methods: {
    getImgUrl(src) {
      if (src === '') return
      return require('../../static/' + src)
    },
    showPersonalPage(id) {
      window.location.href='http://localhost:8080/personalPage/' + id
    },
    showMessagePage(id) {
      window.location.href='http://localhost:8080/messagePage/' + id
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/message/messageList').then(function (resp) {
      console.log(resp.data)
      if(resp.data.data === "USER_NOT_LOGIN") {
        _this.$router.replace({path: '/loginPage'})
      }
      else if(resp.data.data === "USER_ACCOUNT_USE_BY_OTHERS") {
        alert("账号被挤下线！")
        _this.$router.replace({path: '/loginPage'})
      }
      else {
        _this.messageList = resp.data.data.messages
      }
    })
  }
}
</script>

<style scoped>

</style>