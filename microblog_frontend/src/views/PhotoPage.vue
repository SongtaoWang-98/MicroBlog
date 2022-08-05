<template>
  <el-container>
    <el-header>
      <div>
        <div style="width:200px; margin-left:0">
          <el-button icon="el-icon-arrow-left" onclick="window.location.href='http://localhost:8080/home'">返回</el-button>
        </div>
      </div>
    </el-header>
    <h1 style="font-size: 30px">我的相册</h1>
    <el-main>
      <div class="demo-image">
        <div class="block" v-for="photo in photos" style="height: 600px; width: 600px; box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04); margin: 0 auto">
          <el-image
              style="width: 500px; height: 500px"
              :src="getImgUrl(photo.url)"
              :fit="'scale-down'"></el-image>
        </div>
      </div>
      <div style="height: 50px"></div>
      <div>
        <el-upload
            class="upload-demo"
            name="picFile"
            :with-credentials="true"
            drag
            action="http://localhost:8181/photo/add">
          <em class="el-icon-upload"></em>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传jpg/png文件</div>
        </el-upload>
      </div>
      <div style="height: 30px"></div>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "PhotoPage",
  data() {
    return {
      photos:[]
    };
  },
  methods: {
    getImgUrl(src) {
      if(src==='') return
      return require('../../static/' + src)
    },
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/photo/photoList').then(function (resp) {
      console.log(resp.data)
      if(resp.data.data === "USER_NOT_LOGIN") {
        _this.$router.replace({path: '/loginPage'})
      }
      else if(resp.data.data === "USER_ACCOUNT_USE_BY_OTHERS") {
        alert("账号被挤下线！")
        _this.$router.replace({path: '/loginPage'})
      }
      else {
        _this.photos = resp.data.data.photos
      }
    })
  },
}
</script>

<style scoped>

</style>