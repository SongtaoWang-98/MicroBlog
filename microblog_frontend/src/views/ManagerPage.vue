<template>
  <el-container>
    <el-header>
      <div>
        <div style="width:200px; margin: 0 auto">
          <h1 style="font-size: 30px">管理功能</h1>
        </div>
      </div>
    </el-header>
    <el-main>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="用户管理" name="first">用户管理</el-tab-pane>
        <el-tab-pane label="创作统计" name="second">创作统计</el-tab-pane>
        <el-tab-pane label="博文管理" name="third">博文管理</el-tab-pane>
        <el-tab-pane label="热度统计" name="fourth">热度统计</el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "ManagerPage",
  data() {
    return {
      activeName: "second"
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    }
  }
}
</script>

<style scoped>

</style>