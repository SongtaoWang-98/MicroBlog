<template>
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
</template>

<script>
export default {
  name: "MyInfoPage",
  data() {
    return {
      form: {
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
      _this.form.realName = resp.data.data.realName
      _this.form.gender = resp.data.data.gender
      _this.form.birthday = resp.data.data.birthday
      _this.form.company = resp.data.data.company
      _this.form.job = resp.data.data.job
      _this.form.university = resp.data.data.university
      _this.form.telNumber = resp.data.data.telNumber
      _this.form.hobby = resp.data.data.hobby
      _this.form.province = resp.data.data.province
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
      // axios.post('http://localhost:8181/user/updateDetailedInfo', data:this.form)
    }
  }
}
</script>

<style scoped>

</style>