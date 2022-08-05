<template>
  <el-container>
    <el-header>
      <div>
        <div style="width:200px; margin-left:0">
          <el-button icon="el-icon-arrow-left" onclick="window.location.href='http://localhost:8080/messageList'">返回</el-button>
        </div>
      </div>
    </el-header>
    <h1 style="font-size: 30px">与{{personName}}的对话</h1>
    <el-main>
      <div style="width: 800px; height: 600px; border: 1px solid #000000; margin: 0 auto; overflow-y: scroll" ref="sc">
        <dl>
          <dd v-for="message in messageList">
            <div style="width: 500px; height: 100px; background-color: cornsilk; margin-left: 0; border:1px solid #000000" v-show="message.type === 'RECEIVE'">
              <div style="height: 10px"></div>
              <div style="float: left; margin-left: 40px">
                <el-avatar :size="80" :src=getImgUrl(message.personPic)></el-avatar>
              </div>
              <div style="float: left; margin-left: 20px">
                <div style="width:200px; font-size: 15px; color: darkslategray" >{{ message.time }}</div>
                <div style="height: 10px"></div>
                <div style="font-size: 30px">{{ message.content }}</div>
              </div>
            </div>
            <div style="width: 500px; height: 100px; background-color: #e8fcd2; margin-left: 200px; border:1px solid #000000" v-show="message.type === 'SEND'">
              <div style="height: 10px"></div>
              <div style="float: right; margin-right: 40px">
                <el-avatar :size="80" :src=getImgUrl(message.personPic)></el-avatar>
              </div>
              <div style="float: left; margin-left: 20px">
                <div style="width:200px; font-size: 15px; color: darkslategray" >{{ message.time }}</div>
                <div style="height: 10px"></div>
                <div style="font-size: 30px">{{ message.content }}</div>
              </div>
            </div>
            <div style="height: 25px"></div>
          </dd>
        </dl>
      </div>
      <div style="height: 30px"></div>
      <div>
        <el-input v-model="input" placeholder="请输入内容" style="width: 700px" @keyup.enter.native="sendMessage"></el-input>
        <el-button type="primary" @click="sendMessage">发送</el-button>
      </div>
      <div style="height: 100px"></div>
    </el-main>
  </el-container>
</template>


<script>
let c = 0
export default {
  name: "MessagePage",
  data () {
    return {
      personName: "",
      messageList: [],
      input: ''
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/message/messagePage?personId=' + this.$route.params.id).then(function (resp) {
      console.log(resp.data.data)
      if(resp.data.data === "USER_NOT_LOGIN") {
        _this.$router.replace({path: '/loginPage'})
      }
      else if(resp.data.data === "USER_ACCOUNT_USE_BY_OTHERS") {
        alert("账号被挤下线！")
        _this.$router.replace({path: '/loginPage'})
      }
      else {
        _this.personName = resp.data.data.personName
        _this.messageList = resp.data.data.messages
      }
    })
  },
  mounted() {
    this.initWebSocket()
    this.scrollToBottom()
  },
  updated() {
    this.scrollToBottom()
  },
  destroyed() {
    this.closeWebSocket()
  },
  methods: {
    getImgUrl(src) {
      if (src === '') return
      return require('../../static/' + src)
    },
    initWebSocket: function (){
      if(typeof (WebSocket) === "undefined") {
        alert("您的浏览器不支持SOCKET！")
      }
      else {
        this.socket = new WebSocket("ws://localhost:8181/websocket/" + this.$route.params.id)
        this.socket.onopen = this.open
        this.socket.onerror = this.error
        this.socket.onmessage = this.getMessage
      }
    },
    open() {
      console.log("WebSocket连接成功！")
    },
    error() {
      console.log("连接错误")
    },
    getMessage(msg) {
      console.log(msg.data)
      let a = c + 1
      if(c < a) {
        console.log(a)
        if(c === 1) {
          this.$router.go(0)
        }
        c = a
      }
    },
    send() {
      this.socket.send(this.input)
    },
    closeWebSocket() {
      console.log("WebSocket关闭成功！")
    },
    sendMessage() {
      axios.post('http://localhost:8181/message/send?personId=' + this.$route.params.id + "&content=" + this.input)
      this.send()
    },
    scrollToBottom() {
      this.$nextTick(() => {
        let middle= this.$refs["sc"];
        middle.scrollTop = middle.scrollHeight;
        middle.scrollTo(0, 10000)
      })
      window.scrollTo(0,50000)
    }
  },
}
</script>

<style scoped>

</style>