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
        <el-tab-pane label="用户管理" name="first">
          <el-table
              :data="userList"
              style="width: 100%">
            <el-table-column
                prop="userId"
                label="用户id"
                width="180">
            </el-table-column>
            <el-table-column
                prop="nickName"
                label="昵称"
                width="180">
            </el-table-column>
            <el-table-column
                prop="userName"
                label="用户名">
            </el-table-column>
            <el-table-column
                prop="state"
                label="状态">
            </el-table-column>
            <el-table-column
                prop="lastTime"
                label="上次登录时间">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <div v-show="scope.row.state === 'NORMAL'">
                  <el-button type="danger" size="min"  plain @click="ban(scope.row.userId)">封禁</el-button>
                  <el-button type="primary" size="min" plain disabled>解封</el-button>
                </div>
                <div v-show="scope.row.state === 'BANNED'">
                  <el-button type="danger" size="min"  plain disabled>封禁</el-button>
                  <el-button type="primary" size="min" plain @click="unban(scope.row.userId)">解封</el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="创作统计" name="second">
          <div style="width: 600px; height: 400px; margin: 0 auto">
            <el-card class="box-card">
              <div class="text item">
                总用户数：{{statData.userNum}}
              </div>
            </el-card>
            <el-card class="box-card">
              <div class="text item">
                总博文数：{{statData.blogNum}}
              </div>
            </el-card>
            <el-card class="box-card">
              <div class="text item">
                总话题数：{{statData.topicNum}}
              </div>
            </el-card>
          </div>
          <div id="gender" style="width: 600px; height: 400px; margin: 0 auto"></div>
          <div id="generation" style="width: 600px; height: 400px; margin: 0 auto"></div>
        </el-tab-pane>
        <el-tab-pane label="博文管理" name="third">
          <div>
            <el-table
                :data="blogList"
                style="width: 100%">
              <el-table-column
                  prop="blogId"
                  label="博文id">
              </el-table-column>
              <el-table-column
                  prop="publisherName"
                  label="发布者"
                  width="180">
              </el-table-column>
              <el-table-column
                  prop="time"
                  label="发布时间"
                  width="180">
              </el-table-column>
              <el-table-column
                  prop="scope"
                  label="可见范围">
              </el-table-column>
              <el-table-column
                  prop="content"
                  label="博文内容"
                  width="200">
              </el-table-column>
              <el-table-column
                  prop="likeNum"
                  label="点赞数">
              </el-table-column>
              <el-table-column
                  prop="collectNum"
                  label="收藏数">
              </el-table-column>
              <el-table-column
                  prop="forwardNum"
                  label="转发数">
              </el-table-column>
              <el-table-column
                  prop="state"
                  label="状态">
              </el-table-column>
              <el-table-column
                  prop="heat"
                  label="热度">
              </el-table-column>
              <el-table-column
                  label="是否删除"
                  prop="deleted">
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <div v-show="scope.row.deleted === ' '">
                    <el-button type="danger" size="min"  plain @click="del(scope.row.blogId)">删除</el-button>
                  </div>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-tab-pane>
        <el-tab-pane label="热度统计" name="fourth">
          <el-table
              :data="topicList"
              style="width: 100%">
            <el-table-column
                prop="topicId"
                label="话题id"
                width="180">
            </el-table-column>
            <el-table-column
                prop="topicName"
                label="话题名"
                width="180">
            </el-table-column>
            <el-table-column
                prop="heat"
                label="热度">
            </el-table-column>
            <el-table-column
                prop="blogNum"
                label="博文数量">
            </el-table-column>
            <el-table-column
                label="操作"
                width="280">
              <template slot-scope="scope">
                <el-button type="primary" size="min" plain @click="showTrend(scope.row.topicId)">显示热度</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "ManagerPage",
  data() {
    return {
      activeName: "first",
      userList:[],
      statData:[],
      blogList:[],
      topicList:[]
    }
  },
  mounted() {
    this.showCharts()
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/manage/userList').then(function (resp) {
      console.log(resp.data)
      if(resp.data.data === "USER_NOT_LOGIN") {
        _this.$router.replace({path: '/loginPage'})
      }
      else if(resp.data.data === "USER_ACCOUNT_USE_BY_OTHERS") {
        alert("账号被挤下线！")
        _this.$router.replace({path: '/loginPage'})
      }
      else {
        _this.userList = resp.data.data.userList
      }
    })
  },
  methods: {
    handleClick() {
      console.log(this.activeName)
      if(this.activeName === "first") {
        const _this = this
        axios.get('http://localhost:8181/manage/userList').then(function (resp) {
          console.log(resp.data)
          if(resp.data.data === "USER_NOT_LOGIN") {
            _this.$router.replace({path: '/loginPage'})
          }
          else if(resp.data.data === "USER_ACCOUNT_USE_BY_OTHERS") {
            alert("账号被挤下线！")
            _this.$router.replace({path: '/loginPage'})
          }
          else {
            _this.userList = resp.data.data.userList
          }
        })
      }
      else if(this.activeName === "second") {
        this.showCharts()
      }
      else if(this.activeName === "third") {
        const _this = this
        axios.get('http://localhost:8181/manage/blogList').then(function (resp) {
          console.log(resp.data)
          if(resp.data.data === "USER_NOT_LOGIN") {
            _this.$router.replace({path: '/loginPage'})
          }
          else if(resp.data.data === "USER_ACCOUNT_USE_BY_OTHERS") {
            alert("账号被挤下线！")
            _this.$router.replace({path: '/loginPage'})
          }
          else {
            _this.blogList = resp.data.data.blogList
          }
        })
      }
      else {
        const _this = this
        axios.get('http://localhost:8181/manage/topicList').then(function (resp) {
          console.log(resp.data)
          if(resp.data.data === "USER_NOT_LOGIN") {
            _this.$router.replace({path: '/loginPage'})
          }
          else if(resp.data.data === "USER_ACCOUNT_USE_BY_OTHERS") {
            alert("账号被挤下线！")
            _this.$router.replace({path: '/loginPage'})
          }
          else {
            _this.topicList = resp.data.data.topicList
          }
        })
      }
    },
    ban(userId) {
      axios.post('http://localhost:8181/manage/ban?userId=' + userId)
      location.reload()
    },
    unban(userId) {
      axios.post('http://localhost:8181/manage/unban?userId=' + userId)
      location.reload()
    },
    del(blogId) {
      axios.post('http://localhost:8181/manage/deleteBlog?blogId=' + blogId)
      location.reload()
    },
    showTrend(topicId) {
      window.location.href='http://localhost:8080/topicTrend/' + topicId
    },
    showCharts() {
      const _this = this
      axios.get('http://localhost:8181/manage/statistics').then(function (resp) {
        console.log(resp.data)
        if(resp.data.data === "USER_NOT_LOGIN") {
          _this.$router.replace({path: '/loginPage'})
        }
        else if(resp.data.data === "USER_ACCOUNT_USE_BY_OTHERS") {
          alert("账号被挤下线！")
          _this.$router.replace({path: '/loginPage'})
        }
        else {
          _this.statData = resp.data.data
          let genderChart = _this.$echarts.init(document.getElementById('gender'))
          let option = {
            title: {
              text: '男女比统计'
            },
            series: [{
              name: "人数",
              type: 'pie',
              radius:'55%',
              // roseType: "angle",
              data: [
                {value:_this.statData.maleNum, name:'男'},
                {value:_this.statData.femaleNum, name:'女'},
                {value:_this.statData.unknownGenderNum, name:'未知'},
              ]
            }]
          }
          genderChart.setOption(option)

          let geneChart = _this.$echarts.init(document.getElementById('generation'))
          let option2 = {
            title: {
              text: '年龄统计'
            },
            tooltip: {},
            legend: {
              data:['人数']
            },
            xAxis: {
              data: ["<70后", "80后", "90后", "00后", ">10后", "未知"]
            },
            yAxis: {},
            series: [{
              name: "人数",
              type: 'bar',
              data: [_this.statData.b70sNum, _this.statData.g80sNum, _this.statData.g90sNum,
                _this.statData.g00sNum, _this.statData.a10sNum, _this.statData.unknownBirthdayNum]
            }]
          }
          geneChart.setOption(option2)
        }
      })
    }
  }
}
</script>

<style scoped>
.text {
  font-size: 28px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
}

</style>