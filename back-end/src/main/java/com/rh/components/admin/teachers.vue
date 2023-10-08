<template>
  <div style="margin-top: 3vh;position: relative;z-index: 1">
    <div>
      <el-card :hidden="hiddenCard"
               style=" border-radius: 4px;width: 40vw;position: absolute;z-index:9999;margin-top: 20vh;margin-left: 18vw">
        <el-form :model="teacherForm" :rules="rules" status-icon ref="teacherForm" label-width="10vw"
                 class="demo-ruleForm"
                 label-position="right">
          <el-form-item label="职工号:" prop="teacherId">
            <el-input type="text" v-model="teacherForm.teacherId" autocomplete="off"
                      style="width: 15vw;margin-right: 300px" disabled="disabled"></el-input>
          </el-form-item>
          <el-form-item label="教师名:" prop="teacherName">
            <el-input type="text" v-model="teacherForm.teacherName" autocomplete="off"
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-form-item label="性别:" prop="sex">
            <el-select v-model="teacherForm.sex" autocomplete="off" style="width: 15vw;margin-right: 300px">
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

          <el-form-item label="联系方式:" prop="teacherPhone">
            <el-input type="text" v-model="teacherForm.teacherPhone" placeholder="输入该教师联系方式:" autocomplete="off"
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
    </div>
    <div>
      <el-card :hidden="hiddenForm"
               style=" border-radius: 4px;width: 40vw;position: absolute;z-index:9999;margin-top: 20vh;margin-left: 18vw">
        <el-form :model="teacherForm1" :rules="rules1" status-icon ref="teacherForm1" label-width="10vw"
                 class="demo-ruleForm"
                 label-position="right">
          <el-form-item label="职工号:" prop="teacherId">
            <el-input type="text" v-model="teacherForm1.teacherId" autocomplete="off"
                      style="width: 15vw;margin-right: 300px" ></el-input>
          </el-form-item>
          <el-form-item label="教师名:" prop="teacherName">
            <el-input type="text" v-model="teacherForm1.teacherName" autocomplete="off"
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-form-item label="性别:" prop="sex">
            <el-select v-model="teacherForm1.sex" autocomplete="off" style="width: 15vw;margin-right: 300px">
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

          <el-form-item label="联系方式:" prop="teacherPhone">
            <el-input type="text" v-model="teacherForm1.teacherPhone" placeholder="输入该教师联系方式:" autocomplete="off"
                      max="100" min="0"
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-button type="success" plain class="edit_button"
                     @click="sure_add()" style="margin-right: 3vw">
            确定
          </el-button>
          <el-button type="info" plain class="edit_button"
                     @click="close_add()" style="margin-right: 3vw">
            取消
          </el-button>
        </el-form>
      </el-card>
    </div>
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <el-form-item label="职工号">
        <el-input v-model="form.query1" placeholder="请输入职工号"></el-input>
      </el-form-item>
      <el-form-item label="教师名">
        <el-input v-model="form.query2" placeholder="请教师名"></el-input>
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
    <el-card style=" border-radius: 4px;margin-top: 1.5vh;margin-left: 3.5vw">
      <el-table
          :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
          border
          style="width: 100%;margin-left: 7.5vw">
        <el-table-column
            prop="teacherId"
            label="职工号"
            width="180">
        </el-table-column>
        <el-table-column
            prop="teacherName"
            label="教师名"
            width="180">
        </el-table-column>
        <el-table-column
            prop="sex"
            label="性别"
            width="180">
        </el-table-column>
        <el-table-column
            prop="teacherPhone"
            label="联系方式"
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
                title="确定删除该教师吗？"
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
          style="margin-top: 1vh;margin-left: -16vw"
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
  name: "teachers",
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
      hiddenForm:true,
      teacherForm: {
        teacherId: 0,
        teacherName: "",
        sex: "",
        teacherPhone: ""
      },
      teacherForm1: {
        teacherId: 0,
        teacherName: "",
        sex: "",
        teacherPhone: ""
      },
      pagesize: 10,//默认分页每页数据量
      currentPage: 1,//默认展示第一页数据
      form: {
        query1: '',
        query2: '',
        value: true,
      },
      tableData: [],
      rules: {
        teacherPhone: [
          {validator: checkMobile, trigger: 'blur'}
        ]
      },
      rules1: {
        teacherPhone: [
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
          teacherId: mythis.form.query1,
          teacherName: mythis.form.query2
        }
        $.ajax({
          url: "http://localhost:8080/teacher/findTeacher",
          type: "post",
          contentType: 'application/json;',
          data: JSON.stringify(formInfo),
          success: function (response) {
            mythis.tableData = response.data;
          }
        })
      }
    },
    handleSizeChange: function (val) {
      this.pagesize = val;
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage;
    },

    edit(row) {
      var mythis = this;
      mythis.teacherForm = row;
      console.log(mythis.teacherForm)
      console.log(row);
      mythis.hiddenCard = false;
    },
    check_teacherForm() {
      var result;
      this.$refs["teacherForm"].validate((valid) => {
        if (valid) {
          result = true;
        } else {
          result = false;
        }
      })
      return result;
    },
    check_teacherForm1() {
      var result;
      this.$refs["teacherForm1"].validate((valid) => {
        if (valid) {
          result = true;
        } else {
          result = false;
        }
      })
      return result;
    },
    edit_student() {
      var mythis = this;
      var type = this.check_teacherForm();
      if (type === false) {
        return
      }
      $.ajax({
        url: "http://localhost:8080/teacher/edit",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(mythis.teacherForm),
        success: function (response) {
          console.log(response)
          mythis.$message({
            type: response.code === 200 ? "success" : "error",
            message: response.msg
          })
          mythis.search();
          mythis.hiddenCard = true;
        },
      })
    },
    close_student() {
      var mythis = this;
      mythis.hiddenCard = true;
    },
    add() {
      var mythis = this;
      mythis.hiddenForm = false;
    },
    sure_add() {
      var mythis = this;
      $.ajax({
        url: "http://localhost:8080/teacher/add",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(mythis.teacherForm1),
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
      var type = this.check_teacherForm1();
      if (type === false) {
        return
      }
      $.ajax({
        url: "http://localhost:8080/teacher/del",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(row.teacherId),
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
      this.search()
    }
  }

</script>

<style scoped>

</style>
