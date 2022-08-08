<template>
  <el-container>
    <el-header>
      <div class="followerList">
        <div style="width:200px; margin-left:0">
          <el-button icon="el-icon-arrow-left" onclick="window.location.href='http://localhost:8080/managerPage'">返回</el-button>
        </div>
      </div>
    </el-header>
    <h1 style="font-size: 30px">话题热度趋势</h1>
    <el-main>
      <div id="chart" style="width: 1200px; height: 800px"></div>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "TopicTrend",
  data() {
    return{
      heatData: []
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/manage/topicTrend?topicId=' + this.$route.params.id).then(function (resp) {
      console.log(resp.data)
      if(resp.data.data === "USER_NOT_LOGIN") {
        _this.$router.replace({path: '/loginPage'})
      }
      else if(resp.data.data === "USER_ACCOUNT_USE_BY_OTHERS") {
        alert("账号被挤下线！")
        _this.$router.replace({path: '/loginPage'})
      }
      else {
        _this.heatData = resp.data.data
        let myChart = _this.$echarts.init(document.getElementById('chart'));
        let option = {
          xAxis: {
            type: 'category',
            data: _this.heatData.timeList,
          },
          yAxis: {
            type: 'value',
          },
          series: [
            {
              data: _this.heatData.heatList,
              type: 'line',
            },
          ],
        };
        myChart.setOption(option);
      }
    })
  },
}
</script>

<style scoped>

</style>