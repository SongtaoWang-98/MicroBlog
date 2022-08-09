<template>
  <el-container>
    <el-aside width="350px" style="background-color: #b2caff">
      <div class="user_info">
        <div style="height: 20px"></div>
        <div class="block" style="height: 180px">
          <el-avatar :size="160" :src=getImgUrl(circleUrl) onclick="window.location.href='http://localhost:8080/myInfoPage'"></el-avatar>
        </div>
        <div class="info">
          <div class="nickname" style="font-size: 30px">{{ nickname }}</div>
          <div class="username" style="font-size: 20px; color: gray">{{ username }}</div>
          <div style="height: 40px"></div>
          <div class="data">
            <el-link :underline="false" href="http://localhost:8080/followingList" style="font-size: 20px">{{ followingNum }}</el-link>
            <span>&emsp;&emsp;</span>
            <el-link :underline="false" href="http://localhost:8080/followerList" style="font-size: 20px">{{ followerNum }}</el-link>
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
        <div><el-button type="success" plain icon="el-icon-camera-solid" onclick="window.location.href='http://localhost:8080/photoPage'">我的相册</el-button></div>
        <div style="height: 40px"></div>
        <div><el-button type="primary" plain icon="el-icon-s-promotion" onclick="window.location.href='http://localhost:8080/messageList'">我的私信</el-button></div>
        <div style="height: 40px"></div>
        <div><el-button type="info" plain icon="el-icon-s-data" onclick="window.location.href='http://localhost:8080/myStatPage'">我的数据</el-button></div>
        <div style="height: 40px"></div>
        <div><el-button type="danger" plain icon="el-icon-star-on" onclick="window.location.href='http://localhost:8080/myCollections'">我的收藏</el-button></div>
        <div style="height: 40px"></div>
        <div><el-button plain @click="logout">退出登录</el-button></div>
        <div style="height: 40px"></div>
      </div>
    </el-aside>
    <el-container>
      <el-header style="height: 100px; background-color: #99b7f8; ">
        <div style="height: 30px"></div>
        <div class="group">
          <el-link :underline="false" href="http://localhost:8080/home" style="font-size: 28px; color: crimson">热门</el-link>
          <span>&emsp;&emsp;&emsp;</span>
          <el-link :underline="false" href="http://localhost:8080/home/new" style="font-size: 28px; color: darkorange">最新</el-link>
          <span>&emsp;&emsp;&emsp;</span>
          <el-link :underline="false" href="http://localhost:8080/home/friends" style="font-size: 28px; color: #171418">好友圈</el-link>
          <span>&emsp;&emsp;&emsp;</span>
          <el-dropdown>
          <el-button style="font-size: 20px; background-color: #f3e9eb; border: 0">
            分组查看<em class="el-icon-arrow-down el-icon--right"></em>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-for="group in groupList" key="index" @click.native="showByGroup(group.id)">{{ group.name }}</el-dropdown-item>
          </el-dropdown-menu>
          </el-dropdown>
          <span>&emsp;&emsp;&emsp;</span>
          <el-button type="warning" round icon="el-icon-edit" size ="large" onclick="window.location.href='http://localhost:8080/publishPage'">发布新博文</el-button>
        </div>
      </el-header>
      <el-main style="background-color: #e5ecf8">
        <div class="infinite-list-wrapper" style="overflow:auto">
          <dl
              class="list"
              v-infinite-scroll="load"
              infinite-scroll-disabled="disabled">
            <dd v-for="(blog, index) in blogList" class="list-item">
              <div style="width: 750px; background-color: #c7d9fc; margin: 0 auto">
                <div class="time" style="height: 20px">{{blog.time}}</div>
                <div style="height: 20px"><el-link :underline="false" href="https://element.eleme.io" icon="el-icon-warning-outline" style="font-size: 10px; color: rgba(255,9,57,0.89); float: right; margin-right: 20px">举报</el-link></div>
                <div class="block" style="height: 80px">
                  <el-avatar :size="60" :src=getImgUrl(blog.publisherImg) @click.native="showPersonalPage(blog.publisherId)"></el-avatar>
                </div>
                <div class="info">
                  <div class="nickname" style="font-size: 20px">{{ blog.publisherName }}</div>
                </div>
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
                  <em class="el-icon-s-promotion"></em>{{blog.repostNum}}
                  <span>&emsp;&emsp;&emsp;</span>
                  <em class="el-icon-star-off" v-show="!blog.collecting" @click="collect(blog.blogId)"></em>
                  <em class="el-icon-star-on" style="color: crimson" v-show="blog.collecting" @click="disCollect(blog.blogId)"></em>
                  {{blog.collectNum}}
                  <span>&emsp;&emsp;&emsp;</span>
                  <em class="el-icon-circle-check" v-show="!blog.liking" @click="like(blog.blogId)"></em>
                  <em class="el-icon-success" style="color: crimson" v-show="blog.liking" @click="dislike(blog.blogId)"></em>
                  {{blog.likeNum}}
                </div>
                <div style="height: 20px"></div>
                <div class="comment" style="height: 40px; width:320px; font-size: 20px; margin: 0 auto">
                  <el-input v-model="commentData[index]" placeholder="评论" style="width: 250px"></el-input>
                  <el-button type="primary" size="small" @click="commentSubmit(blog.blogId, commentData[index])">发送</el-button>
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
          <p>没有更多了</p>
        </div>
      </el-main>
    </el-container>
    <el-aside width="500px" style="background-color: #faf1e7">
      <div style="height: 100px; background-color: #faf1e7"></div>
      <div class="search" style="height: 150px">
        <div style="height: 60px; font-size: 40px; color: rgba(58,58,70,0.89)">搜一搜</div>
        <div style="height: 50px">
          <el-input
              placeholder="请输入内容"
              v-model="input"
              clearable style="width: 200px">
          </el-input>
          <el-button type="primary" @click="search()">GO</el-button>
        </div>
        <div style="height: 40px">
          <el-radio-group v-model="radio">
            <el-radio :label="1">博文</el-radio>
            <el-radio :label="2">话题</el-radio>
            <el-radio :label="3">用户</el-radio>
          </el-radio-group>
        </div>
      </div>
      <div style="height: 50px"></div>
      <div class="topics" style="height: 200px">
        <div style="height: 60px; font-size: 40px; color: rgba(58,58,70,0.89)">热门话题</div>
        <div style="height: 80px"></div>
        <div v-for="topic in topicList" key="index" style="font-size: 28px; color: rgba(255,9,57,0.89)" @click="searchHotTopic(topic)">
          <div style="width: 300px; height: 80px; margin: 0 auto; text-align: left">#{{topic}}</div></div>
      </div>
    </el-aside>
  </el-container>
</template>

<script>
export default {
  name: 'Home',
  components: {
  },
  data () {
    return {
      circleUrl: "",
      nickname:"Cheryl",
      username:"Cheryl@yeah.net",
      followingNum:234,
      followerNum:3028,
      groupList:["朋友","同学","默认分组"],
      radio: '1',
      input:'',
      topicList:["天气真好","猫猫狗狗","发工资"],
      blogList:[],
      commentData:[]
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/home/friends').then(function (resp) {
      if(resp.data.data === "USER_NOT_LOGIN") {
        _this.$router.replace({path: '/loginPage'})
      }
      else if(resp.data.data === "USER_ACCOUNT_USE_BY_OTHERS") {
        alert("账号被挤下线！")
        _this.$router.replace({path: '/loginPage'})
      }
      else {
        console.log(resp.data.data)
        _this.circleUrl = resp.data.data.userImg
        _this.nickname = resp.data.data.nickName
        _this.username = resp.data.data.userName
        _this.followingNum = resp.data.data.followingNum
        _this.followerNum = resp.data.data.followerNum
        _this.groupList = resp.data.data.groups
        _this.blogList = resp.data.data.blogs
        _this.topicList = resp.data.data.topics
        _this.radio = 1
        _this.input = ''
        _this.commentData = []
      }
    })
  },
  computed: {
    noMore () {
      return this.count >= 20
    },
    disabled () {
      return this.loading || this.noMore
    }
  },
  methods: {
    load () {
      this.loading = true
      setTimeout(() => {
        this.count += 2
        this.loading = false
      }, 2000)
    },
    getImgUrl(src) {
      if(src==='') return
      return require('../../static/' + src)
    },
    showByGroup(id) {
      window.location.href='http://localhost:8080/home/group/' + id
    },
    showPersonalPage(id) {
      window.location.href='http://localhost:8080/personalPage/' + id
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
    commentSubmit(blogId, str) {
      axios.post('http://localhost:8181/blog/comment?blogId=' + blogId + '&content=' + str)
      location.reload()
    },
    logout() {
      axios.post('http://localhost:8181/logout')
      location.reload()
    },
    search() {
      if(this.radio === 1) window.location.href=('http://localhost:8080/searchByContent/' + this.input)
      else if(this.radio === 2) window.location.href=('http://localhost:8080/searchByTopic/' + this.input)
      else window.location.href=('http://localhost:8080/searchByUsername/' + this.input)
    },
    searchHotTopic(str) {
      window.location.href=('http://localhost:8080/searchBySpecificTopic/' + str)
    }
  }
}
</script>

<style>
body > .el-container {
  margin-bottom: 140px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.el-dropdown {
  vertical-align: top;
}
.el-dropdown + .el-dropdown {
  margin-left: 15px;
}
.el-icon-arrow-down {
  font-size: 12px;
}

</style>