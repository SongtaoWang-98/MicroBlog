<template>
  <el-container>
    <el-header><div style="font-size: 30px">数据统计</div></el-header>
    <el-main>
      <el-row>
        <el-col :span="6">
          <div>&emsp;</div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple">创作数据</div>
          <div class="grid-content bg-purple">{{ blogNum }}</div>
        </el-col>
        <el-col :span="4"><div>&emsp;</div></el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple">创作总热度</div>
          <div class="grid-content bg-purple">{{ heat }}</div>
        </el-col>
        <el-col :span="6">
          <div>&emsp;</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="5"><div>&emsp;</div></el-col>
        <el-col :span="2">
          <div class="grid-content bg-purple">总评论数</div>
          <div class="grid-content bg-purple">{{ commentNum }}</div>
        </el-col>
        <el-col :span="2"><div>&emsp;</div></el-col>
        <el-col :span="2">
          <div class="grid-content bg-purple-light">总点赞数</div>
          <div class="grid-content bg-purple-light">{{likeNum}}</div>
        </el-col>
        <el-col :span="2"><div>&emsp;</div></el-col>
        <el-col :span="2">
          <div class="grid-content bg-purple">总转发数</div>
          <div class="grid-content bg-purple">{{repostNum}}</div>
        </el-col>
        <el-col :span="2"><div>&emsp;</div></el-col>
        <el-col :span="2">
          <div class="grid-content bg-purple-light">总收藏数</div>
          <div class="grid-content bg-purple-light">{{collectNum}}</div>
        </el-col>
        <el-col :span="5"><div>&emsp;</div></el-col>
      </el-row>
      <el-divider></el-divider>
      <div style="font-size: 30px">最热博文</div>
      <div style="width: 750px; background-color: #c7d9fc; margin: 0 auto">
        <div style="height: 20px">{{blog.time}}</div>
        <div style="height: 20px"><el-link :underline="false" href="https://element.eleme.io" icon="el-icon-warning-outline" style="font-size: 10px; color: rgba(255,9,57,0.89); float: right; margin-right: 20px">举报</el-link></div>
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
        <div style="height: 20px"></div>
        <div class="comment" style="height: 40px; width:320px; font-size: 20px; margin: 0 auto">
          <el-input v-model="comment" placeholder="评论" style="width: 250px"></el-input>
          <el-button type="primary" size="small">发送</el-button>
        </div>
        <div style="height: 20px"></div>
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
      circleUrl: "",
      blog:null
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/user/personalStat').then(function (resp) {
      console.log(resp.data.data)
      _this.blogNum = resp.data.data.blogNum
      _this.heat = resp.data.data.heat
      _this.commentNum = resp.data.data.commentNum
      _this.likeNum = resp.data.data.likeNum
      _this.repostNum = resp.data.data.repostNum
      _this.collectNum = resp.data.data.collectNum
      // _this.blog = resp.data.data.hottestBlog
      _this.comment = ''
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
.el-row {
  margin-bottom: 20px;
  &:last-child {
   margin-bottom: 0;
 }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>