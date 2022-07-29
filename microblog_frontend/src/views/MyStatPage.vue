<template>
  <el-container>
    <el-header>
      <div style="width:200px; margin-left:0">
        <el-button icon="el-icon-arrow-left" onclick="window.location.href='http://localhost:8080/home'">返回</el-button>
      </div>
      <div style="font-size: 40px">数据统计</div>
    </el-header>
    <div style="height: 40px"></div>
    <el-main>
      <el-row>
        <el-col :span="6">
          <div>&emsp;</div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple-dark" style="font-size: 30px">创作数据</div>
          <div class="grid-content bg-purple-dark" style="font-size: 40px; color: #911008">{{ blogNum }}</div>
        </el-col>
        <el-col :span="4"><div>&emsp;</div></el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple-dark" style="font-size: 30px">创作总热度</div>
          <div class="grid-content bg-purple-dark" style="font-size: 40px; color: #911008">{{ heat }}</div>
        </el-col>
        <el-col :span="6">
          <div>&emsp;</div>
        </el-col>
      </el-row>
      <div style="height: 70px"></div>
      <el-row>
        <el-col :span="5"><div>&emsp;</div></el-col>
        <el-col :span="2">
          <div class="grid-content bg-purple" style="font-size: 30px">总评论数</div>
          <div class="grid-content bg-purple" style="font-size: 40px; color: #911008">{{ commentNum }}</div>
        </el-col>
        <el-col :span="2"><div>&emsp;</div></el-col>
        <el-col :span="2">
          <div class="grid-content bg-purple-light" style="font-size: 30px">总点赞数</div>
          <div class="grid-content bg-purple-light" style="font-size: 40px; color: #911008">{{likeNum}}</div>
        </el-col>
        <el-col :span="2"><div>&emsp;</div></el-col>
        <el-col :span="2">
          <div class="grid-content bg-purple" style="font-size: 30px">总转发数</div>
          <div class="grid-content bg-purple" style="font-size: 40px; color: #911008">{{repostNum}}</div>
        </el-col>
        <el-col :span="2"><div>&emsp;</div></el-col>
        <el-col :span="2">
          <div class="grid-content bg-purple-light" style="font-size: 30px">总收藏数</div>
          <div class="grid-content bg-purple-light" style="font-size: 40px; color: #911008">{{collectNum}}</div>
        </el-col>
        <el-col :span="5"><div>&emsp;</div></el-col>
      </el-row>
      <div style="height: 80px"></div>
      <el-divider></el-divider>
      <div style="font-size: 40px">最热博文</div>
      <div style="height: 40px"></div>
      <div style="width: 750px; background-color: #c7d9fc; margin: 0 auto">
        <div style="height: 20px">{{blog.time}}</div>
        <div style="height: 20px"></div>
        <div class="topic" v-for="topic in blog.topics">
          <div style="height: 24px; width:300px; font-size: 20px; color: darkblue; margin: 0 auto; text-align: left">#{{topic}}</div>
        </div>
        <div style="height: 10px"></div>
        <div class="content" style="width: 300px; margin: 0 auto; text-align: left">{{blog.content}}</div>
        <div style="height: 20px"></div>
        <div class="pic" v-show="blog.img !== ''">
          <el-image style="width: 300px; height: 200px" :fit="scale-down" :src=getImgUrl(blog.img)></el-image>
        </div>
        <div style="height: 10px"></div>
        <div class="interact" style="height: 24px; width:300px; font-size: 20px; margin: 0 auto">
          <i class="el-icon-s-promotion"></i>{{blog.repostNum}}
          <span>&emsp;&emsp;&emsp;</span>
          <i class="el-icon-star-off"></i>{{blog.collectNum}}
          <span>&emsp;&emsp;&emsp;</span>
          <i class="el-icon-circle-check"></i>{{blog.likeNum}}
        </div>
        <div style="height: 40px"></div>
        <div class="comments" v-for="comment in blog.comments" style=" width: 300px; margin: 0 auto; text-align: left">
          {{comment}}
        </div>
        <div style="height: 50px"></div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "MyStatPage",
  data () {
    return {
      blogNum: 10,
      collectNum: 3,
      heat: 10,
      commentNum: 5,
      likeNum: 5,
      repostNum: 0,
      blog: []
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/user/personalStat').then(function (resp) {
      console.log(resp.data.data)
      if(resp.data.data === "USER_NOT_LOGIN") {
        _this.$router.replace({path: '/loginPage'})
      }
      else {
        _this.blogNum = resp.data.data.blogNum
        _this.heat = resp.data.data.heat
        _this.commentNum = resp.data.data.commentNum
        _this.likeNum = resp.data.data.likeNum
        _this.repostNum = resp.data.data.repostNum
        _this.collectNum = resp.data.data.collectNum
        _this.blog = resp.data.data.hottestBlog
      }
    })
  },
  methods: {
    getImgUrl(src) {
      if(src==='') return
      return require('../../static/' + src)
    }
  }
}
</script>

<style>
.bg-purple-dark {
  background: #b785ea;
}
.bg-purple {
  background: #63a5f1;
}
.bg-purple-light {
  background: #7cf1ed;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>