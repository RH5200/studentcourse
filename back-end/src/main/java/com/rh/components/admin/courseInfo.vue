<template>
  <div style="margin-top: 3vh">
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <el-form-item label="课程名">
        <el-input v-model="form.query1" placeholder="课程名"></el-input>
      </el-form-item>
      <el-form-item label="教师名">
        <el-input v-model="form.query2" placeholder="教师名"></el-input>
      </el-form-item>
      <el-form-item label="教师职工号">
        <el-input v-model="form.query3" placeholder="教师职工号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-switch
            v-model="form.value"
            active-color="#13ce66"
            active-text="启用查询">
        </el-switch>
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
            width="180">
        </el-table-column>
        <el-table-column
            prop="courseName"
            label="课程名"
            width="180">
        </el-table-column>
        <el-table-column
            prop="teacherId"
            label="教师号"
            width="180">
        </el-table-column>
        <el-table-column
            prop="teacherName"
            label="教师名"
            width="180">
        </el-table-column>
        <el-table-column
            prop="courseCredit"
            label="学分"
            width="180">
        </el-table-column>
        <el-table-column
            prop="term"
            label="学期"
            width="180">
        </el-table-column>
        <el-table-column
            label="操作"
            width="280">
          <template slot-scope="scope">
            <el-popconfirm
                title="确定取消开设该课程吗？"
                confirm-button-text='取消'
                cancel-button-text='确定'
                cancel-button-type="Primary"
                confirm-button-type="Danger"
                @cancel="del(scope.row)"
            >
              <el-button
                  icon="el-icon-close"
                  style="margin-left: 2vw"
                  size="mini"
                  type="danger"
                  slot="reference"
              >取消开设
              </el-button>
            </el-popconfirm>
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
  </div>
</template>

<script>
export default {
  name: "courseInfo",
  data() {
    return {
      pagesize: 10,//默认分页每页数据量
      currentPage: 1,//默认展示第一页数据
      form: {
        query1: '',
        query2: '',
        query3: '',
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
          courseName: mythis.form.query1,
          teacherId: mythis.form.query3,
          teacherName: mythis.form.query2
        }
        $.ajax({
          url: "http://localhost:8080/courseTeacher/findInfo",
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
    del(row){
      console.log(row)
      var mythis = this;
      $.ajax({
        url: "http://localhost:8080/courseTeacher/del",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(row.ctid),
        success: function (response) {
          mythis.$message({
            type: response.code === 200 ? "success" : "error",
            message: response.msg
          })
          mythis.search();
        }
      })
    },
    chooseCourse(row) {
      var mythis = this;
      var formChoose = {
        studentId: localStorage.getItem("id"),
        courseId: row.courseId,
        teacherId: row.teacherId,
        term: row.term,
      }
      $.ajax({
        url: "http://localhost:8080/score/insertScore",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(formChoose),
        success: function (response) {
          mythis.$message({
            type: response.code === 200 ? "success" : "error",
            message: response.msg
          })
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
    query3() {
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
