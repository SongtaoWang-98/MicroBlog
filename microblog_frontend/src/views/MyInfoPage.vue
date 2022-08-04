<template>
  <div>
    <div class="block" style="height: 180px">
      <el-avatar :size="160" :src=getImgUrl(picUrl) @click.native="showPhotos"></el-avatar>
      <div>点击替换头像</div>
      <div style="width: 1000px; margin: 0 auto" v-if="Visible">
        <span v-for="photo in photos" style="height: 250px; width: 250px; box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04); margin: 0 auto">
          <el-image
            style="width: 200px; height: 200px" @click="choosePic(photo.photoId, photo.url)"
            :src="getImgUrl(photo.url)"
            :fit="'scale-down'"></el-image>
        </span>
      </div>
    </div>
    <div v-if="Visible !== true">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="真实姓名">
          <el-col :span="5">
            <el-input v-model="form.realName"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="form.gender" placeholder="选择">
            <el-option label="男" value="MALE"></el-option>
            <el-option label="女" value="FEMALE"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="生日">
          <el-col :span="5">
            <el-date-picker type="date" placeholder="选择日期" v-model="form.birthday" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="公司">
          <el-input v-model="form.company"></el-input>
        </el-form-item>
        <el-form-item label="工作">
          <el-input v-model="form.job"></el-input>
        </el-form-item>
        <el-form-item label="大学">
          <el-input v-model="form.university"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.telNumber"></el-input>
        </el-form-item>
        <el-form-item label="爱好">
          <el-input v-model="form.hobby"></el-input>
        </el-form-item>
        <el-form-item label="所在省份">
          <el-input v-model="form.province"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确认修改</el-button>
          <el-button onclick="window.location.href='http://localhost:8080/home'">返回主页</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyInfoPage",
  data() {
    return {
      picUrl:'',
      Visible:false,
      photos:[],
      form: {
        picId:0,
        realName: "",
        gender:"",
        birthday:"",
        company:"",
        job:"",
        university:"",
        telNumber:"",
        hobby:"",
        province:""
      }
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/user/detailedInfo').then(function (resp) {
      console.log(resp.data.data)
      if(resp.data.data === "USER_NOT_LOGIN") {
        _this.$router.replace({path: '/loginPage'})
      }
      else{
        _this.picUrl = resp.data.data.picUrl
        _this.photos = resp.data.data.photos
        _this.form.picId = resp.data.data.picId
        _this.form.realName = resp.data.data.realName
        _this.form.gender = resp.data.data.gender
        _this.form.birthday = resp.data.data.birthday
        _this.form.company = resp.data.data.company
        _this.form.job = resp.data.data.job
        _this.form.university = resp.data.data.university
        _this.form.telNumber = resp.data.data.telNumber
        _this.form.hobby = resp.data.data.hobby
        _this.form.province = resp.data.data.province
      }
    })
  },
  methods: {
    onSubmit() {
      console.log('submit!')
      axios({
        method:"post",
        url:"http://localhost:8181/user/updateDetailedInfo",
        data:this.form
      })
    },
    getImgUrl(src) {
      if(src==='') return
      return require('../../static/' + src)
    },
    showPhotos() {
      this.Visible = true
    },
    choosePic(pic, url) {
      this.Visible = false
      this.form.picId = pic
      this.picUrl = url
    }
  }
}
</script>

<style scoped>

</style>