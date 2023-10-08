<template>

  <div style="margin-top: 3vh;position: relative;z-index:1;">
    <dv>
      <el-card :hidden="hiddenCard"
               style=" border-radius: 4px;width: 40vw;position: absolute;z-index:9999;margin-top: 10vh;margin-left: 17vw">
        <el-form :model="studentForm" :rules="rules" status-icon ref="studentForm" label-width="10vw"
                 class="demo-ruleForm"
                 label-position="right">
          <el-form-item label="学号:" prop="studentId">
            <el-input type="text" v-model="studentForm.studentId" autocomplete="off"
                      style="width: 15vw;margin-right: 300px" disabled="disabled"></el-input>
          </el-form-item>
          <el-form-item label="学生名:" prop="studentName">
            <el-input type="text" v-model="studentForm.studentName" autocomplete="off"
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-form-item label="密码:" prop="password">
            <el-input type="text" v-model="studentForm.password" autocomplete="off"
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-form-item label="性别:" prop="sex">
            <el-select v-model="studentForm.sex" autocomplete="off" style="width: 15vw;margin-right: 300px">
              <el-option
                  label="男"
                  value="男">
              </el-option>
              <el-option
                  label="女"
                  value="女">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="班级:" prop="className">
            <el-select v-model="studentForm.classId" placeholder="请选择班级:" filterable autocomplete="off"
                       style="width: 15vw;margin-right: 300px">
              <el-option
                  v-for="item in classData"
                  :label="item.className"
                  :value="item.classId"
                  :key="item.className">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="年级:" prop="gradeName">
            <el-select v-model="studentForm.gradeId" placeholder="请选择年级:" filterable autocomplete="off"
                       style="width: 15vw;margin-right: 300px">
              <el-option
                  v-for="item in gradeData"
                  :label="item.gradeName"
                  :value="item.gradeId"
                  :key="item.gradeName">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="学院:" prop="sex">
            <el-select v-model="studentForm.departmentId" placeholder="请选择班级:" filterable autocomplete="off"
                       style="width: 15vw;margin-right: 300px">
              <el-option
                  v-for="item in departmentData"
                  :label="item.departmentName"
                  :value="item.departmentId"
                  :key="item.departmentName">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="联系方式:" prop="studentPhone">
            <el-input type="text" v-model="studentForm.studentPhone" placeholder="输入该学生成绩:" autocomplete="off"
                      max="100" min="0"
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-button type="success" plain class="edit_button"
                     @click="edit_student" style="margin-right: 3vw">
            确定
          </el-button>
          <el-button type="info" plain class="edit_button"
                     @click="close_student" style="margin-right: 3vw">
            取消
          </el-button>
        </el-form>
      </el-card>
    </dv>
    <div>
      <el-card :hidden="hiddenForm"
               style=" border-radius: 4px;width: 40vw;position: fixed;z-index:9999;margin-top: 10vh;margin-left: 17vw">
        <el-form :model="studentForm1" :rules="rules" status-icon ref="studentForm1" label-width="10vw"
                 class="demo-ruleForm"
                 label-position="right">
          <el-form-item label="学号:" prop="studentId">
            <el-input type="text" v-model="studentForm1.studentId" autocomplete="off"
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-form-item label="学生名:" prop="studentName">
            <el-input type="text" v-model="studentForm1.studentName" autocomplete="off"
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-form-item label="密码:" prop="password">
            <el-input type="text" v-model="studentForm1.password" autocomplete="off"
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-form-item label="性别:" prop="sex">
            <el-select v-model="studentForm1.sex" autocomplete="off" style="width: 15vw;margin-right: 300px">
              <el-option
                  label="男"
                  value="男">
              </el-option>
              <el-option
                  label="女"
                  value="女">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="联系方式:" prop="studentPhone">
            <el-input type="text" v-model="studentForm1.studentPhone" placeholder="输入该学生联系方式:"
                      autocomplete="off"
                      max="100" min="0"
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-form-item label="班级:" prop="className">
            <el-select v-model="studentForm1.classId" placeholder="请选择班级:" filterable autocomplete="off"
                       style="width: 15vw;margin-right: 300px">
              <el-option
                  v-for="item in classData"
                  :label="item.className"
                  :value="item.classId"
                  :key="item.className">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="年级:" prop="gradeName">
            <el-select v-model="studentForm1.gradeId" placeholder="请选择年级:" filterable autocomplete="off"
                       style="width: 15vw;margin-right: 300px">
              <el-option
                  v-for="item in gradeData"
                  :label="item.gradeName"
                  :value="item.gradeId"
                  :key="item.gradeName">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="学院:" prop="sex">
            <el-select v-model="studentForm1.departmentId" placeholder="请选择班级:" filterable autocomplete="off"
                       style="width: 15vw;margin-right: 300px">
              <el-option
                  v-for="item in departmentData"
                  :label="item.departmentName"
                  :value="item.departmentId"
                  :key="item.departmentName">
              </el-option>
            </el-select>
          </el-form-item>
          <el-button type="success" plain class="edit_button"
                     @click="edit_add" style="margin-right: 3vw">
            确定
          </el-button>
          <el-button type="info" plain class="edit_button"
                     @click="close_add" style="margin-right: 3vw">
            取消
          </el-button>
        </el-form>
      </el-card>
    </div>
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <el-form-item label="学号">
        <el-input v-model="form.query1" placeholder="请输入学号"></el-input>
      </el-form-item>
      <el-form-item label="学生名">
        <el-input v-model="form.query2" placeholder="请输入学生名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-switch
            v-model="form.value"
            active-color="#13ce66"
            active-text="启用查询">
        </el-switch>
      </el-form-item>
      <el-form-item style="margin-left: 2vw">
        <el-button type="primary" icon="el-icon-circle-plus-outline" @click="add">
          添加
        </el-button>
      </el-form-item>
    </el-form>
    <el-card style=" border-radius: 4px;margin-top: 1.5vh;margin-left: 0.5vw">
      <el-table
          :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
          border
          style="width: 100%;margin-left: 3.5vw">
        <el-table-column
            prop="studentId"
            label="学号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="studentName"
            label="学生名"
            width="100">
        </el-table-column>
        <el-table-column
            prop="password"
            label="学生密码"
            width="140">
        </el-table-column>
        <el-table-column
            prop="sex"
            label="性别"
            width="60">
        </el-table-column>
        <el-table-column
            prop="studentPhone"
            label="联系方式"
            width="160">
        </el-table-column>
        <el-table-column
            prop="gradeName"
            label="年级"
            width="100">
        </el-table-column>
        <el-table-column
            prop="className"
            label="班级"
            width="180">
        </el-table-column>
        <el-table-column
            prop="departmentName"
            label="学院"
            width="180">
        </el-table-column>
        <el-table-column
            label="操作"
            width="280">
          <template slot-scope="scope">
            <el-button
                @click="edit(scope.row)"
                icon="el-icon-edit"
                style="margin-left: 2vw"
                size="mini"
                type="warning"
                slot="reference"
            >编辑
            </el-button>
            <el-popconfirm
                title="确定删除该学生吗？"
                confirm-button-text='取消'
                cancel-button-text='确定'
                cancel-button-type="Primary"
                confirm-button-type="Danger"
                @cancel="del(scope.row)"
            >
              <el-button
                  icon="el-icon-delete"
                  style="margin-left: 2vw"
                  size="mini"
                  type="danger"
                  slot="reference"
              >删除
              </el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          style="margin-top: 1vh;margin-left: -16VW"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[10, 20, 30, 40, 50]"
          :page-size="pagesize"
          :total="tableData.length"
          layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "students",
  data() {
    var checkMobile = (rules, value, cb) => {
      const regMobile = /^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\d{8}$/

      if (regMobile.test(value)) {
        return cb()
      }
      cb(new Error('请输入合法的手机号'))
    };
    return {
      hiddenCard: true,
      hiddenForm: true,
      studentForm: {
        studentName: '',
        studentId: '',
        studentPhone: '',
        sex: ""
      },
      studentForm1: {
        studentName: '',
        studentId: '',
        studentPhone: '',
        sex: ""
      },
      pagesize: 10,//默认分页每页数据量
      currentPage: 1,//默认展示第一页数据
      form: {
        query1: '',
        query2: '',
        value: true,
      },
      tableData: [],
      gradeData:[],
      classData:[],
      departmentData:[],
      rules: {
        studentPhone: [
          {validator: checkMobile, trigger: 'blur'}
        ]
      },
    }
  },
  methods: {
    search() {
      var mythis = this;
      if (mythis.form.value) {
        var formInfo = {
          studentId: mythis.form.query1,
          studentName: mythis.form.query2
        }
        $.ajax({
          url: "http://localhost:8080/student/findStudent",
          type: "post",
          contentType: 'application/json;',
          data: JSON.stringify(formInfo),
          success: function (response) {
            mythis.tableData = response.data;
          }
        })
      }
    },
    gradeSearch() {
      var mythis = this;
        $.ajax({
          url: "http://localhost:8080/grade/findGrade",
          type: "post",
          contentType: 'application/json;',
          success: function (response) {
            mythis.gradeData = response.data;
          }
        })
    },
    classSearch() {
      var mythis = this;
        $.ajax({
          url: "http://localhost:8080/class/findClass",
          type: "post",
          contentType: 'application/json;',
          success: function (response) {
            mythis.classData = response.data;
          }
        })
    },
    departmentSearch() {
      var mythis = this;
        $.ajax({
          url: "http://localhost:8080/department/findDepartment",
          type: "post",
          contentType: 'application/json;',
          success: function (response) {
            mythis. departmentData = response.data;
          }
        })
    },
    handleSizeChange: function (val) {
      this.pagesize = val;
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage;
    },
    check_studentForm() {
      var result;
      this.$refs["studentForm"].validate((valid) => {
        if (valid) {
          result = true;
        } else {
          result = false;
        }
      })
      return result;
    },
    check_studentForm1() {
      var result;
      this.$refs["studentForm1"].validate((valid) => {
        if (valid) {
          result = true;
        } else {
          result = false;
        }
      })
      return result;
    },
    add() {
      var mythis = this;
      mythis.hiddenForm = false;
    },

    edit(row) {
      var mythis = this;
      mythis.studentForm = row;
      console.log(mythis.studentForm)
      console.log(row);
      mythis.hiddenCard = false;
    },
    edit_student() {
      var mythis = this;
      var type = this.check_studentForm();
      if (type === false) {
        return
      }
      $.ajax({
        url: "http://localhost:8080/student/edit",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(mythis.studentForm),
        success: function (response) {
          console.log(response)
          mythis.$message({
            type: response.code === 200 ? "success" : "error",
            message: response.msg
          })
          mythis.search();
          mythis.hiddenCard = true;
        }
      })

    },
    close_student() {
      var mythis = this;
      mythis.hiddenCard = true;
    },
    edit_add() {
      var mythis = this;
      var type = this.check_studentForm1();
      if (type === false) {
        return
      }
      $.ajax({
        url: "http://localhost:8080/student/add",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(mythis.studentForm1),
        success: function (response) {
          console.log(response)
          mythis.$message({
            type: response.code === 200 ? "success" : "error",
            message: response.msg
          })
          mythis.search();
          mythis.hiddenForm = true;
        }
      })

    },
    close_add() {
      var mythis = this;
      mythis.hiddenForm = true;
    },
    del(row) {
      var mythis = this;
      $.ajax({
        url: "http://localhost:8080/student/del",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(row.studentId),
        success: function (response) {
          console.log(response)
          mythis.$message({
            type: response.code === 200 ? "success" : "error",
          })
          mythis.search();
        }
      })
    }
  },
  computed: {
    searchParams() {
      let params = {}
      if (this.form.query1) {
        params.query1 = this.form.query1
      }
      if (this.form.query2) {
        params.query2 = this.form.query2
      }
      return params
    }
  },
  watch: {
    query1() {
      this.search()
    },
    query2() {
      this.search()
    },
    searchParams() {
      this.search()
    }
  },
  mounted() {
    this.search();
    this.gradeSearch();
    this.classSearch();
    this.departmentSearch();
  }
}

</script>

<style scoped>
</style>
