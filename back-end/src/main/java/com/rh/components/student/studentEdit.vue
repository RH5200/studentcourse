<template>
  <el-tabs v-model="activeName" type="card" >
    <el-tab-pane label="修改密码" name="first">
      <el-card style=" border-radius: 4px;width: 40vw">
        <el-form :model="form" status-icon :rules="rules" ref="form" label-width="10vw" class="demo-ruleForm"
                 label-position="right">
          <el-form-item label="输入密码:" prop="password">
            <el-input type="password" v-model="form.password" placeholder="请输入密码" autocomplete="off" show-password
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-form-item label="确认密码:" prop="newPassword">
            <el-input type="newPassword" v-model="form.newPassword" placeholder="请确认密码" autocomplete="off"
                      show-password
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-button type="info" plain class="edit_button"
                     @click="edit_password" style="margin-right: 3vw">
            确认修改
          </el-button>
        </el-form>
      </el-card>
    </el-tab-pane>
    <el-tab-pane label="修改手机号" name="second">
      <el-card style=" border-radius: 4px;width: 40vw">
        <el-form :model="form1" status-icon :rules="rules1" ref="form1" label-width="10vw" class="demo-ruleForm" label-position="right">
          <el-form-item label="输入手机号:" prop="mobile">
            <el-input v-model="form1.mobile" placeholder="请输入手机号" autocomplete="off"
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-form-item label="输入用户密码:" prop="password">
            <el-input type="password" v-model="form1.password" placeholder="请输入密码核对用户信息" autocomplete="off"
                      show-password
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-button type="info" plain class="adit_phone"
                     @click="edit_phone" style="margin-right: 3vw">
            确认修改手机号
          </el-button>
        </el-form>
      </el-card>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
export default {
  name: "edit",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.form.newPassword !== '') {
          this.$refs.form.validateField('newPassword');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.form.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    var checkMobile = (rules, value, cb) => {
      const regMobile = /^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\d{8}$/

      if (regMobile.test(value)) {
        return cb()
      }
      cb(new Error('请输入合法的手机号'))
    };
    var validatePass1 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      }
      callback();
    };

    return {
      activeName: 'first',
      form: {
        password: "",
        newPassword: "",
      },
      form1: {
        mobile: "",
        password: "",
      },
      rules: {
        password: [
          {validator: validatePass, trigger: 'blur'}
        ],
        newPassword: [
          {validator: validatePass2, trigger: 'blur'}
        ],
      },
      rules1: {
        mobile: [
          {validator: checkMobile, trigger: 'blur'}
        ],
        password: [
          {validator: validatePass1, trigger: 'blur'}
        ]
      },
    }
  },
  methods: {
    check_password() {
      var result;
      this.$refs["form"].validate((valid) => {
        if (valid) {
          result = true;
        } else {
          result = false;
        }
      })
      return result;
    },
    check_phone() {
      var result;
      this.$refs["form1"].validate((valid) => {
        if (valid) {
          result = true;
        } else {
          result = false;
        }
      })
      return result;
    },
    edit_password() {
      var mythis = this;
      var type = this.check_password();
      if (type === false) {
        return
      }
      var formInfo1 = {
        studentId: localStorage.getItem("id"),
        password: mythis.form.password,
      }
      $.ajax({
        url: "http://localhost:8080/student/edit",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(formInfo1),
        success: function (response) {
          console.log(response)
          mythis.$message({
            type: response.code === 200 ? "success" : "error",
            message: response.msg
          })
          mythis.table = response.data;
        }
      })
    },
    edit_phone() {
      var mythis = this;
      var type = this.check_phone();
      if (type === false) {
        return
      }
      var formInfo2 = {
        studentId: localStorage.getItem("id"),
        password: mythis.form1.password,
      }
      $.ajax({
        url: "http://localhost:8080/student/login",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(formInfo2),
        success: function (response) {
          if (response.code === 200) {
            var formInfo3 = {
              studentId: localStorage.getItem("id"),
              studentPhone: mythis.form1.mobile,
            }
            $.ajax({
              url: "http://localhost:8080/student/edit",
              type: "post",
              contentType: 'application/json;',
              data: JSON.stringify(formInfo3),
              success: function (response) {
                console.log(response)
                mythis.$message({
                  type: response.code === 200 ? "success" : "error",
                  message: response.msg
                })
              }
            })
          } else {
            mythis.$message({
              message: '密码错误,手机号更改失败!',
              type: 'error'
            });
          }
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
