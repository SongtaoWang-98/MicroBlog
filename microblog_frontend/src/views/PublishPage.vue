<template>
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
      <el-button>添加图片</el-button>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">立即创建</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "PublishPage",
  data() {
    return {
      form: {
        scope: "PUBLIC",
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
      })
      window.location.href='http://localhost:8080/home'
    }
  }
}
</script>

<style scoped>

</style>