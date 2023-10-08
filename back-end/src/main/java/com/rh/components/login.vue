<template>
  <div class="login">
    <el-container>
      <el-header style="background-color: #b3c0d1">
        <div style="font-size: 30px;margin-top: 15px;font-weight: bolder">
          <i class="el-icon-postcard"></i>
          学生选课信息管理系统
        </div>
      </el-header>
      <el-main>
        <div id="register">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <div style="text-align: center">登录</div>
            </div>
            <el-form id="form" ref="form" :model="form" :rules="rules">
              <br/>
              <div class="demo-input-suffix">
                <el-form-item prop="id" style="margin-left: 1.5vw">
                  账号：
                  <el-input style="width: 14vw" type="text" placeholder="  请输入学号或教职工号"
                            v-model="form.id"></el-input>
                </el-form-item>
              </div>
              <el-form-item prop="password" style="margin-left: 1.5vw">
                密码：
                <el-input style="width: 14vw" type="password" placeholder="  请输入密码" v-model="form.password"
                          show-password></el-input>
              </el-form-item>
              <el-form-item label="用户类型" prop="type">
                <el-radio-group v-model="form.type">
                  <el-radio label="student" value="student">学生</el-radio>
                  <el-radio label="teacher" value="teacher">老师</el-radio>
                  <el-radio label="admin" value="admin">管理员</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-button type="success" style="float: left;margin-left: 4.3vw" plain class="login_button"
                         @click="enter">
                登录
              </el-button>
              <el-button type="danger" style="float: right;margin-right: 3.5vw" plain class="register_button"
                         @click="reset">
                重置
              </el-button>
              <br><br><br>

            </el-form>
          </el-card>
        </div>
      </el-main>
      <el-footer style="margin-bottom: 25px;">
        <h3 style="text-align: center;margin: auto">
          Student Course Selection Information Management System
        </h3>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'login',
  data() {
    return {
      form: {
        id: null,
        password: null,
        type: null,
      },
      rules: {
        id: [
          {
            required: true, message: "用户名不能为空", trigger: "blur",
          }
        ],
        password: [
          {
            required: true, message: "密码不能为空", trigger: "blur",
          }
        ],
        type: [
          {
            required: true, message: '请选择', trigger: 'change'
          }
        ],
      },
      dataTable: [],
    }
  },
  methods: {
    register_check() {
      var result;
      this.$refs['form'].validate((valid) => {
        if (valid) {
          result = true;
        } else {
          result = false;
        }
      })
      return result;
    },
    enter() {
      var type = this.register_check();
      var mythis = this;
      if (type === false) {
        return
      }
      if (mythis.form.type === "student") {
        var formInfo = {
          studentId: mythis.form.id,
          password: mythis.form.password,
        }
        $.ajax({
          url: "http://localhost:8080/student/login",
          type: "post",
          contentType: 'application/json;',
          data: JSON.stringify(formInfo),
          success: function (response) {
            console.log(response)
            mythis.$message({
              type: response.code === 200 ? "success" : "error",
              message: response.msg
            })
            if (response.code === 200) {
              localStorage.setItem("id", response.data.studentId)
              localStorage.setItem("name", response.data.studentName)
              setTimeout(() => {
                mythis.$router.push ('/student/studentInfo');
              }, 1200);
            }
          }

        })
      } else if (mythis.form.type === "teacher") {
        var formInfo1 = {
          teacherId: mythis.form.id,
          password: mythis.form.password,
        }
        $.ajax({
          url: "http://localhost:8080/teacher/login",
          type: "post",
          contentType: 'application/json;',
          data: JSON.stringify(formInfo1),
          success: function (response) {
            console.log(response)
            mythis.$message({
              type: response.code === 200 ? "success" : "error",
              message: response.msg
            })
            if (response.code === 200) {
              localStorage.setItem("id", response.data.teacherId)
              localStorage.setItem("name", response.data.teacherName)
              setTimeout(() => {
                mythis.$router.push ('/teacher/teacherInfo');
              }, 1200);
            }
          }
        })
      } else if (mythis.form.type === "admin") {
        var formInfo2 = {
          adminId: mythis.form.id,
          password: mythis.form.password,
        }
        $.ajax({
          url: "http://localhost:8080/admin/login",
          type: "post",
          contentType: 'application/json;',
          data: JSON.stringify(formInfo2),
          success: function (response) {
            console.log(response)
            mythis.$message({
              type: response.code === 200 ? "success" : "error",
              message: response.msg
            })
            if (response.code === 200) {
              localStorage.setItem("id", response.data.adminId)
              localStorage.setItem("name", response.data.adminName)
              setTimeout(() => {
                mythis.$router.push ('/admin/adminInfo');
              }, 1200);
            }
          }
        })
      }
    },
    reset: function () {
      var mythis = this;
      mythis.form.id = ''
      mythis.form.password = ''
      mythis.form.type = ""
    },
  },
}
</script>
<style scoped>
.box-card {
  width: 400px;
  height: 380px;
  margin: auto;
  margin-top: 16vh;
  border: 0;
  border-radius: 20px;
  background-color: aliceblue;

}
.login {
  height: 100%;
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background-color: aliceblue;
}

.el-container {
  width: 100%;
  min-height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.el-main {
  height: 100%;
  overflow: hidden;
}

.el-footer {
  display: flex;
  height: 4vh;
  width: 100%;
}

.el-input_inner {
  border-radius: 10px;
}

</style>



