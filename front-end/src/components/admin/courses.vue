<template>
  <div style="margin-top: 3vh;position: relative;z-index: 1">
    <div>
      <el-card :hidden="hiddenCard"
               style=" border-radius: 4px;width: 40vw;position: absolute;z-index:9999;margin-top: 22vh;margin-left: 17vw">
        <el-form :model="courseForm"  status-icon ref="courseForm" label-width="10vw"
                 class="demo-ruleForm"
                 label-position="right">
          <el-form-item label="课程号号:" prop="courseId" >
            <el-input type="text" v-model="courseForm.courseId" autocomplete="off"
                      style="width: 15vw;margin-right: 300px" disabled="disabled"></el-input>
          </el-form-item>
          <el-form-item label="课程名:" prop="courseName">
            <el-input type="text" v-model="courseForm.courseName" autocomplete="off"
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-form-item label="学分:" prop="courseCredit">
            <el-select v-model="courseForm.courseCredit" autocomplete="off" style="width: 15vw;margin-right: 300px">
              <el-option
                  label="0.5"
                  value="0.5">
              </el-option>
              <el-option
                  label="1"
                  value="1">
              </el-option>
              <el-option
                  label="1.5"
                  value="1.5">
              </el-option>
              <el-option
                  label="2"
                  value="2">
              </el-option>
              <el-option
                  label="2.5"
                  value="2.5">
              </el-option>
              <el-option
                  label="3"
                  value="3">
              </el-option>
            </el-select>
          </el-form-item>
          <el-button type="success" plain class="edit_button"
                     @click="edit_course" style="margin-right: 3vw">
            确定
          </el-button>
          <el-button type="info" plain class="edit_button"
                     @click="close_course" style="margin-right: 3vw">
            取消
          </el-button>
        </el-form>
      </el-card>
    </div>
    <div>
      <el-card :hidden="hiddenForm"
               style=" border-radius: 4px;width: 40vw;position: absolute;z-index:9999;margin-top: 22vh;margin-left: 17vw">
        <el-form :model="courseForm1"  status-icon ref="courseForm1" label-width="10vw"
                 class="demo-ruleForm"
                 label-position="right">
          <el-form-item label="课程名" prop="courseName">
            <el-input type="text" v-model="courseForm1.courseName" autocomplete="off"
                      style="width: 15vw;margin-right: 300px"></el-input>
          </el-form-item>
          <el-form-item label="学分:" prop="courseCredit">
            <el-select v-model="courseForm1.courseCredit" autocomplete="off" style="width: 15vw;margin-right: 300px">
              <el-option
                  label="0.5"
                  value="0.5">
              </el-option>
              <el-option
                  label="1"
                  value="1">
              </el-option>
              <el-option
                  label="1.5"
                  value="1.5">
              </el-option>
              <el-option
                  label="2"
                  value="2">
              </el-option>
              <el-option
                  label="2.5"
                  value="2.5">
              </el-option>
              <el-option
                  label="3"
                  value="3">
              </el-option>
            </el-select>
          </el-form-item>
          <el-button type="success" plain class="edit_button"
                     @click="sure_add" style="margin-right: 3vw">
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
        <el-input v-model="form.query1" placeholder="请输入课程号"></el-input>
      </el-form-item>
      <el-form-item label="学生名">
        <el-input v-model="form.query2" placeholder="请输入课程名"></el-input>
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
    <el-card style=" border-radius: 4px;margin-top: 1vh;margin-left: 3.5vw">
      <el-table
          :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
          border
          style="width: 100%;margin-left: 7.5vw">
        <el-table-column
            prop="courseId"
            label="课程号"
            width="180">
        </el-table-column>
        <el-table-column
            prop="courseName"
            label="课程名"
            width="180">
        </el-table-column>
        <el-table-column
            prop="courseCredit"
            label="学分"
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
                title="确定删除该课表吗？"
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
  name: "courses",
  data() {
    return {
      hiddenCard: true,
      hiddenForm: true,
      courseForm: {
        courseName: '',
        courseId: '',
        courseCredit: '',
      },
      courseForm1: {
        courseName: '',
        courseCredit: '',
      },
      pagesize: 10,//默认分页每页数据量
      currentPage: 1,//默认展示第一页数据
      form: {
        query1: '',
        query2: '',
        value: true,
      },
      tableData: [],
    }
  },
  methods: {
    search() {
      var mythis = this;
      if (mythis.form.value) {
        var formInfo = {
          courseId: mythis.form.query1,
          courseName: mythis.form.query2
        }
        $.ajax({
          url: "http://localhost:8080/course/findCourse",
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
    check_courseForm() {
      var result;
      this.$refs["courseForm"].validate((valid) => {
        if (valid) {
          result = true;
        } else {
          result = false;
        }
      })
      return result;
    },

    edit(row) {
      var mythis = this;
      mythis.courseForm = row;
      console.log(mythis.courseForm)
      console.log(row);
      mythis.hiddenCard = false;
    },
    edit_course() {
      var mythis = this;
      var type = this.check_courseForm();
      if (type === false) {
        return
      }
      $.ajax({
        url: "http://localhost:8080/course/edit",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(mythis.courseForm),
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
    close_course() {
      var mythis = this;
      mythis.hiddenCard = true;
    },
    del(row) {
      var mythis = this;
      $.ajax({
        url: "http://localhost:8080/course/del",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(row.courseId),
        success: function (response) {
          console.log(response)
          mythis.$message({
            type: response.code === 200 ? "success" : "error",
          })
          mythis.search();
        }
      })
    },
    add() {
      var mythis = this;
      mythis.hiddenForm = false;
    },
    sure_add() {
      var mythis = this;
      $.ajax({
        url: "http://localhost:8080/course/add",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(mythis.courseForm1),
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
