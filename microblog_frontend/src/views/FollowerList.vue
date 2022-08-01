<template>
  <el-container>
    <el-header>
      <div class="followerList">
        <div style="width:200px; margin-left:0">
          <el-button icon="el-icon-arrow-left" onclick="window.location.href='http://localhost:8080/home'">返回</el-button>
        </div>
      </div>
    </el-header>
    <h1 style="font-size: 30px">粉丝列表</h1>
    <el-main>
      <dl>
        <dd v-for="user in userList">
          <div style="width: 500px; background-color: #e8fcd2; margin: 0 auto">
            <div style="height: 20px"></div>
            <el-avatar :size="80" :src=getImgUrl(user.userImg) style="margin-left: 0" @click.native="showPersonalPage(user.userId)"></el-avatar>
            <div class="nickname" style="font-size: 20px">{{ user.nickName }}</div>
            <div class="username" style="font-size: 15px; color: gray">{{ user.userName }}</div>
            <el-divider></el-divider>
          </div>
        </dd>
      </dl>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'FollowerList',
  components: {
  },
  data() {
    return {
      userList:[
        {
          userImg: require("../../static/Stewart/2.jpg"),
          nickName: "001userNick",
          userName: "001user",
        },
        {
          userImg: require("../../static/Stewart/1.jpg"),
          nickName: "002userNick",
          userName: "002user",
        }
      ]
    }
  },
  methods: {
    getImgUrl(src) {
      if(src==='') return
      return require('../../static/' + src)
    },
    showPersonalPage(id) {
      window.location.href='http://localhost:8080/personalPage/' + id
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/user/followerList').then(function (resp) {
      console.log(resp.data)
      if(resp.data.data === "USER_NOT_LOGIN") {
        _this.$router.replace({path: '/loginPage'})
      }
      else {
        _this.userList = resp.data.data.userList
      }
    })
  },
}
</script>