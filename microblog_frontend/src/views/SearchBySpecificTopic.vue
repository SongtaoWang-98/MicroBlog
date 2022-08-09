<template>
  <el-container>
    <el-main style="background-color: #e5ecf8">
      <div>
        <div style="width:200px; margin-left:0">
          <el-button icon="el-icon-arrow-left" onclick="window.location.href='http://localhost:8080/home'">返回</el-button>
        </div>
      </div>
      <div style="font-size: 30px">查询结果</div>
      <div style="overflow:auto" >
        <dl class="list">
          <dd v-for="blog in blogList" class="list-item">
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
              <div class="content" style="width: 300px; margin: 0 auto; text-align: left"><p class="xxx">
                <a v-html= "blog.content" ></a></p></div>
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
</template>

<script>
export default {
  name: "SearchBySpecificTopic",
  data () {
    return {
      blogList: null,
      comment: ''
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/search/bySpecificTopic?str=' + this.$route.params.str).then(function (resp) {
      console.log(resp.data.data)
      _this.blogList = resp.data.data.blogs
      _this.comment = ''
    })
  },
  methods: {
    getImgUrl(src) {
      if (src === '') return
      return require('../../static/' + src)
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
    }
  }
}
</script>

<style scoped>

</style>