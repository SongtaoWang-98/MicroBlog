<template>
  <div class="home">
    <el-button>{{msg}}</el-button>
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: {
  },
  data() {
    return{
      msg: "hello nonono"
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/home').then(function (resp) {
      _this.msg = resp.data.data
    })
  }
}
</script>
