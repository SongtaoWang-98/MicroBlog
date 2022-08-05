<template>
  <el-container>
    <el-aside width="350px" style="background-color: #b2caff">
      <div class="user_info">
        <div style="height: 20px"></div>
        <div class="block" style="height: 180px">
          <el-avatar :size="160" :src=getImgUrl(circleUrl)></el-avatar>
        </div>
        <div class="info">
          <div class="nickname" style="font-size: 30px">{{ nickname }}</div>
          <div class="username" style="font-size: 20px; color: gray">{{ username }}</div>
          <div style="height: 40px"></div>
          <div class="data">
            <el-link :underline="false" style="font-size: 20px">{{ followingNum }}</el-link>
            <span>&emsp;&emsp;</span>
            <el-link :underline="false" style="font-size: 20px">{{ followerNum }}</el-link>
          </div>
          <div class="data" style="font-size: 20px">
            关注
            <span>&emsp;</span>
            粉丝
          </div>
        </div>
      </div>
      <div style="height: 100px"></div>
      <div class="functions">
        <div><el-button type="success" plain v-show="!following" @click="follow">关注</el-button></div>
        <div><el-button type="success" plain v-show="following" @click="unfollow">已关注</el-button></div>
        <div style="height: 40px"></div>
        <div><el-button type="primary" plain @click="sendMessage">私信</el-button></div>
        <div style="height: 40px"></div>
        <div><el-button icon="el-icon-arrow-left" onclick="window.location.href='http://localhost:8080/home'">返回</el-button></div>
      </div>
    </el-aside>
    <el-container>
      <el-main style="background-color: #e5ecf8">
        <div style="overflow:auto">
          <dl class="list">
            <dd v-for="blog in blogList" class="list-item">
              <div style="width: 750px; background-color: #c7d9fc; margin: 0 auto">
                <div class="time" style="height: 20px">{{blog.time}}</div>
                <div style="height: 20px"><el-link :underline="false" href="https://element.eleme.io" icon="el-icon-warning-outline" style="font-size: 10px; color: rgba(255,9,57,0.89); float: right; margin-right: 20px">举报</el-link></div>
                <div style="height: 20px"></div>
                <div class="topic" v-for="topic in blog.topics">
                  <div style="height: 24px; width:300px; font-size: 20px; color: darkblue; margin: 0 auto; text-align: left">#{{topic}}</div>
                </div>
                <div style="height: 10px"></div>
                <div class="content" style="width: 300px; margin: 0 auto; text-align: left">{{blog.content}}</div>
                <div style="height: 20px"></div>
                <div class="pic" v-show="blog.img !== ''">
                  <el-image style="width: 300px; height: 200px" :fit="'scale-down'" :src=getImgUrl(blog.img)></el-image>
                </div>
                <div style="height: 10px"></div>
                <div class="interact" style="height: 24px; width:300px; font-size: 20px; margin: 0 auto">
                  <i class="el-icon-s-promotion"></i>{{blog.repostNum}}
                  <span>&emsp;&emsp;&emsp;</span>
                  <i class="el-icon-star-off" v-show="!blog.collecting" @click="collect(blog.blogId)"></i>
                  <i class="el-icon-star-on" style="color: crimson" v-show="blog.collecting" @click="disCollect(blog.blogId)"></i>
                  {{blog.collectNum}}
                  <span>&emsp;&emsp;&emsp;</span>
                  <i class="el-icon-circle-check" v-show="!blog.liking" @click="like(blog.blogId)"></i>
                  <i class="el-icon-success" style="color: crimson" v-show="blog.liking" @click="dislike(blog.blogId)"></i>
                  {{blog.likeNum}}
                </div>
                <div style="height: 20px"></div>
                <div class="comment" style="height: 40px; width:320px; font-size: 20px; margin: 0 auto">
                  <el-input v-model="comment" placeholder="评论" style="width: 250px"></el-input>
                  <el-button type="primary" size="small" @click="commentSubmit(blog.blogId)">发送</el-button>
                </div>
                <div style="height: 20px"></div>
                <div class="comments" v-for="comment in blog.comments" style=" width: 300px; margin: 0 auto; text-align: left">
                  {{comment}}
                </div>
                <div style="height: 50px"></div>
              </div>
              <el-divider></el-divider>
            </dd>
          </dl>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "PersonalPage",
  data () {
    return {
      circleUrl: "",
      comment: "",
      personId: 0
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/user/homePage?personId=' + this.$route.params.id).then(function (resp) {
      console.log(resp.data.data)
      if(resp.data.data === "USER_NOT_LOGIN") {
        _this.$router.replace({path: '/loginPage'})
      }
      else if(resp.data.data === "USER_ACCOUNT_USE_BY_OTHERS") {
        alert("账号被挤下线！")
        _this.$router.replace({path: '/loginPage'})
      }
      else {
        _this.personId = resp.data.data.personId
        _this.circleUrl = resp.data.data.userImg
        _this.nickname = resp.data.data.nickName
        _this.username = resp.data.data.userName
        _this.followingNum = resp.data.data.followingNum
        _this.followerNum = resp.data.data.followerNum
        _this.groupList = resp.data.data.groups
        _this.blogList = resp.data.data.blogs
        _this.comment = ''
        _this.following = resp.data.data.following
      }
    })
  },
  methods: {
    getImgUrl(src) {
      if(src==='') return
      return require('../../static/' + src)
    },
    follow() {
      axios.post('http://localhost:8181/user/follow?personId=' + this.$route.params.id)
      this.$set(this ,this.following, true)
      location.reload()
    },
    unfollow() {
      axios.post('http://localhost:8181/user/unfollow?personId=' + this.$route.params.id)
      this.$set(this ,this.following, false)
      location.reload()
    },
    like(blogId) {
      axios.post('http://localhost:8181/blog/like?blogId=' + blogId)
      location.reload()
    },
    dislike(blogId) {
      axios.post('http://localhost:8181/blog/dislike?blogId=' + blogId)
      location.reload()
    },
    collect(blogId) {
      axios.post('http://localhost:8181/blog/collect?blogId=' + blogId)
      location.reload()
    },
    disCollect(blogId) {
      axios.post('http://localhost:8181/blog/disCollect?blogId=' + blogId)
      location.reload()
    },
    commentSubmit(blogId) {
      axios.post('http://localhost:8181/blog/comment?blogId=' + blogId + '&content=' + this.comment)
      location.reload()
    },
    sendMessage() {
      window.location.href='http://localhost:8080/messagePage/' + this.personId
    }
  }
}

</script>

<style scoped>

</style>