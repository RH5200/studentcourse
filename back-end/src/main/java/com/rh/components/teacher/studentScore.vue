<template>
  <div style="margin-top: 3vh">
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <el-form-item label="学号">
        <el-input v-model="form.query1" placeholder="学号"></el-input>
      </el-form-item>
      <el-form-item label="学生名">
        <el-input v-model="form.query2" placeholder="学生名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-switch
            v-model="form.value"
            active-color="#13ce66"
            active-text="启用查询">
        </el-switch>
      </el-form-item>
      <el-form-item >
        <el-button  icon="el-icon-refresh-left" style="margin-right: -23vw" @click="back()">
          返回
        </el-button>
      </el-form-item>
    </el-form>
    <el-card style=" border-radius: 4px;margin-top: 1.5vh">
      <el-table
          :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
          border
          style="width: 100%;margin-left: 2vw">
        <el-table-column
            prop="courseId"
            label="课程号"
            width="140">
        </el-table-column>
        <el-table-column
            prop="courseName"
            label="课程名"
            width="180">
        </el-table-column>
        <el-table-column
            prop="studentName"
            label="学生名"
            width="140">
        </el-table-column>
        <el-table-column
            prop="studentId"
            label="学号"
            width="140">
        </el-table-column>
        <el-table-column
            prop="score"
            label="成绩"
            width="140">
        </el-table-column>
        <el-table-column
            prop="className"
            label="班级"
            width="180">
        </el-table-column>
        <el-table-column
            prop="gradeName"
            label="年级"
            width="140">
        </el-table-column>
        <el-table-column
            label="操作"
            width="140">
          <template slot-scope="scope">
            <el-button
                icon="el-icon-edit"
                style="margin-left: 2vw"
                size="mini"
                type="danger"
                slot="reference"
                @click="editScore(scope.row)"
            >修改成绩
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          style="margin-top: 1vh"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[10, 20, 30, 40, 50]"
          :page-size="pagesize"
          :total="tableData.length"
          layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
    </el-card>

    <el-card :hidden="hiddenCard"
             style=" border-radius: 4px;width: 40vw;position: fixed;margin-top: -80vh;margin-left: 20vw">
      <el-form :model="scoreForm" :rules="rules" status-icon ref="scoreForm" label-width="10vw" class="demo-ruleForm"
               label-position="right">
        <el-form-item label="姓名:" prop="studentName">
          <el-input type="text" v-model="scoreForm.studentName" autocomplete="off"
                    style="width: 15vw;margin-right: 300px" disabled="disabled"></el-input>
        </el-form-item>
        <el-form-item label="学号:" prop="studentId">
          <el-input type="text" v-model="scoreForm.studentId" autocomplete="off"
                    style="width: 15vw;margin-right: 300px" disabled="disabled"></el-input>
        </el-form-item>
        <el-form-item label="课程名:" prop="courseName">
          <el-input type="text" v-model="scoreForm.courseName" autocomplete="off"
                    style="width: 15vw;margin-right: 300px" disabled="disabled"></el-input>
        </el-form-item>
        <el-form-item label="输入该学生成绩:" prop="score">
          <el-input type="number" v-model="scoreForm.score" placeholder="输入该学生成绩:" autocomplete="off"
                    max="100" min="0"
                    style="width: 15vw;margin-right: 300px"></el-input>
        </el-form-item>
        <el-button type="success" plain class="edit_button"
                   @click="edit_score" style="margin-right: 3vw">
          确定
        </el-button>
        <el-button type="info" plain class="edit_button"
                   @click="close_score" style="margin-right: 3vw">
          取消
        </el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "studentScore",
  data() {
    var validateScore = (rule, value, callback) => {
      if (isNaN(value)) {
        callback(new Error('请输入数字'));
      } else if (value < 0 || value > 100) {
        callback(new Error('请输入1 - 100内的数字'));
      } else if (value * 100 % 1 > 0) {
        callback(new Error('最多保留两位小数'));
      } else {
        callback();
      }
    };
    return {
      hiddenCard: true,
      scoreForm: {
        studentName: '',
        studentId: '',
        courseName: '',
        score: ""
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
        score: [
          {validator: validateScore, trigger: 'blur'}
        ]
      },
    }
  },
  methods: {
    check_scoreForm() {
      var result;
      this.$refs["scoreForm"].validate((valid) => {
        if (valid) {
          result = true;
        } else {
          result = false;
        }
      })
      return result;
    },
    search() {
      var mythis = this;
      if (mythis.form.value) {
        var formInfo = {
          teacherId: localStorage.getItem("id"),
          classId : localStorage.getItem("classId"),
          studentId: mythis.form.query1,
          studentName: mythis.form.query2,
        }
        console.log(formInfo)
        $.ajax({
          url: "http://localhost:8080/score/studentScore",
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
    editScore(row) {
      var mythis = this;
      mythis.scoreForm = row;
      console.log(mythis.scoreForm)
      console.log(row);
      mythis.hiddenCard = false;
    },
    edit_score() {
      var mythis = this;
      var type = this.check_scoreForm();
      if (type === false) {
        return
      }
      if (mythis.form.value) {
        var formInfo1 = {
          sctid: mythis.scoreForm.sctid,
          score: mythis.scoreForm.score,
        }
        $.ajax({
          url: "http://localhost:8080/score/editScore",
          type: "post",
          contentType: 'application/json;',
          data: JSON.stringify(formInfo1),
          success: function (response) {
            mythis.$message({
              type: response.code === 200 ? "success" : "error",
              message: response.msg
            })
            mythis.hiddenCard = true;
            mythis.search();
          }
        })
      }
    },
    close_score() {
      var mythis = this;
      mythis.hiddenCard = true;
    },
    back(){
      var mythis = this;
      mythis.$router.push('/teacher/studentClass')
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
      if (this.form.query3) {
        params.query3 = this.form.query3
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


