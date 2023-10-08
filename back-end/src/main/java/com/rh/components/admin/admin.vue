<template>
  <div>
    <el-container>
      <el-header style="background-color: #b3c0d1">
        <div>
          <i class="el-icon-postcard" style="font-size: 3.5vh;margin-top: 1.6vh;font-weight: bolder;margin-left: 35vw">
            学生选课信息管理系统</i>
          <i class="el-icon-user-solid" style="margin-left: 25vw">&nbsp;管理员 ：
          </i>
          <span>&nbsp;{{name}}</span>

          <el-divider direction="vertical" style="color: #333333"></el-divider>
          <el-link  class="el-icon-switch-button" style="font-weight: bolder;" @click="exits">退出
          </el-link>
        </div>

      </el-header>
      <el-container>
        <el-aside style="width: 12.5vw;height: 91vh;background-color: #EEF1F6" >
          <el-row>
            <el-col :span="12">
              <el-menu
                  router
                  :default-active="$route.path"
                  class="el-menu-vertical-demo"
                  @open="handleOpen"
                  @close="handleClose">
                <el-submenu index="1">
                  <template slot="title">
                    <i class="el-icon-s-unfold"></i>
                    <span>个人信息</span>
                  </template>
                  <el-menu-item index="/admin/adminInfo"><i class="el-icon-s-promotion"></i>查看信息</el-menu-item>
                </el-submenu>
                <el-submenu index="2">
                  <template slot="title">
                    <i class="el-icon-s-unfold"></i>
                    <span >信息编辑</span>
                  </template>
                  <el-menu-item index="/admin/adminEdit"><i class="el-icon-s-promotion"></i>修改信息</el-menu-item>
                </el-submenu>
                <el-submenu index="3">
                  <template slot="title">
                    <i class="el-icon-s-unfold"></i>
                    <span >学生管理</span>
                  </template>
                  <el-menu-item index="/admin/students"><i class="el-icon-s-promotion"></i>学生信息</el-menu-item>
                </el-submenu>
                <el-submenu index="4">
                  <template slot="title">
                    <i class="el-icon-s-unfold"></i>
                    <span >教师管理</span>
                  </template>
                  <el-menu-item index="/admin/teachers"><i class="el-icon-s-promotion"></i>教师信息</el-menu-item>
                </el-submenu>
                <el-submenu index="5">
                  <template slot="title">
                    <i class="el-icon-s-unfold"></i>
                    <span >课程管理</span>
                  </template>
                  <el-menu-item index="/admin/courses" ><i class="el-icon-s-promotion"></i>课程信息</el-menu-item>
                </el-submenu>

                <el-submenu index="6">
                  <template slot="title">
                    <i class="el-icon-s-unfold"></i>
                    <span>课表管理</span>
                  </template>
                  <el-menu-item index="/admin/creatCourse"><i class="el-icon-s-promotion"></i>开设课程</el-menu-item>
                  <el-menu-item index="/admin/courseInfo"><i class="el-icon-s-promotion"></i>教师课程</el-menu-item>
                </el-submenu>
                <el-submenu index="7">
                  <template slot="title">
                    <i class="el-icon-s-unfold"></i>
                    <span >成绩管理</span>
                  </template>
                  <el-menu-item index="/admin/studentsClass" ><i class="el-icon-s-promotion"></i>学生成绩</el-menu-item>
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
      name : null,
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    load(){
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
