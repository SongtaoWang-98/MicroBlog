<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="可见范围">
        <el-radio-group v-model="form.scope">
          <el-radio label="PUBLIC">公开可见</el-radio>
          <el-radio label="FOLLOW">关注者可见</el-radio>
          <el-radio label="FRIENDS">好友可见</el-radio>
          <el-radio label="PRIVATE">仅自己可见</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="博文正文">
        <el-input type="textarea" v-model="form.content" maxlength="140" minlength="1" show-word-limit></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="showPics">添加图片</el-button>
      </el-form-item>
      <div>
        <el-image style="width: 300px; height: 300px" :src="getImgUrl(picUrl)" :fit="'scale-down'" v-if="form.picId !== 0"></el-image>
      </div>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
    <div style="width: 1000px; margin: 0 auto" v-if="Visible">
      <span v-for="photo in photos" style="height: 250px; width: 250px; box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04); margin: 0 auto">
      <el-image
          style="width: 200px; height: 200px" @click="choosePic(photo.photoId, photo.url)"
          :src="getImgUrl(photo.url)"
          :fit="'scale-down'"></el-image>
    </span>
    </div>
  </div>
</template>

<script>
export default {
  name: "PublishPage",
  data() {
    return {
      photos:[],
      Visible:false,
      picUrl:'',
      form: {
        scope: "PUBLIC",
        picId: 0,
        content:""
      }
    }
  },
  methods: {
    onSubmit() {
      axios({
        method:"post",
        url:"http://localhost:8181/blog/publish",
        data:this.form
      }).then(res=>{
        console.log(res)
        if(res.data.code === "-1") alert(res.data.message)
        else window.location.href='http://localhost:8080/home'
      })
    },
    getImgUrl(src) {
      if(src==='') return
      return require('../../static/' + src)
    },
    showPics() {
      this.Visible = true
    },
    choosePic(pic, url) {
      this.Visible = false
      this.form.picId = pic
      this.picUrl = url
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/photo/photoList').then(function (resp) {
      console.log(resp)
      _this.photos = resp.data.data.photos
    })
  },
}
</script>

<style scoped>

</style>