<template>
  <div>
    <el-container>
      <el-header style="background-color: #b3c0d1">
        <div>
          <i class="el-icon-postcard" style="font-size: 3.5vh;margin-top: 1.6vh;font-weight: bolder;margin-left: 35vw">
            学生选课信息管理系统</i>
          <i class="el-icon-user-solid" style="margin-left: 25vw">&nbsp;教师 ：
          </i>
          <span>&nbsp;{{ name }}</span>

          <el-divider direction="vertical" style="color: #333333"></el-divider>
          <el-link class="el-icon-switch-button" style="font-weight: bolder;" @click="exits">退出
          </el-link>
        </div>

      </el-header>
      <el-container>
        <el-aside style="width: 12.5vw;height: 91vh;background-color: #EEF1F6">
          <el-row>
            <el-col :span="12">
              <el-menu
                  :default-active="$route.path"
                  router
                  class="el-menu-vertical-demo"
                  @open="handleOpen"
                  @close="handleClose">
                <el-submenu index="1">
                  <template slot="title">
                    <i class="el-icon-bank-card"></i>
                    <span>教师信息</span>
                  </template>
                  <el-menu-item index="/teacher/teacherInfo"><i class="el-icon-s-promotion"></i>教师主页</el-menu-item>
                </el-submenu>
                <el-submenu index="2">
                  <template slot="title">
                    <i class="el-icon-bank-card"></i>
                    <span>信息编辑</span>
                  </template>
                  <el-menu-item index="/teacher/teacherEdit"><i class="el-icon-s-promotion"></i>修改信息</el-menu-item>
                </el-submenu>
                <el-submenu index="3">
                  <template slot="title">
                    <i class="el-icon-bank-card"></i>
                    <span>课程管理</span>
                  </template>
                  <el-menu-item index="/teacher/teacherCourse"><i class="el-icon-s-promotion"></i>我的课程</el-menu-item>
                </el-submenu>
                <el-submenu index="4">
                  <template slot="title">
                    <i class="el-icon-bank-card"></i>
                    <span>成绩管理</span>
                  </template>
                  <el-menu-item index="/teacher/studentClass"><i class="el-icon-s-promotion"></i>班级年级</el-menu-item>
                </el-submenu>
              </el-menu>
            </el-col>
          </el-row>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>

</template>

<script>
export default {
  name: "admin",
  data() {
    return {
      name: null,
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    load() {
      var mythis = this;
      mythis.name = localStorage.getItem("name");
    },
    exits(){
      var mythis = this;
      mythis.$router.push('/')
    }
  },
  mounted() {
    this.load();
  }
}

</script>

<style scoped>

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 12.4vw;

}

</style>
