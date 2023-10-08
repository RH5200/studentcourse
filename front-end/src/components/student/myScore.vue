<template>
  <div style="margin-top: 3vh">
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
            prop="term"
            label="学期"
            width="180">
        </el-table-column>
        <el-table-column
            prop="courseCredit"
            label="学分"
            width="180">
        </el-table-column>
        <el-table-column
            prop="score"
            label="成绩"
            width="180"
        >
        </el-table-column>
      </el-table>
      <el-pagination
          style="margin-bottom: 1vh; "
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10]"
          :page-size="pagesize"
          :total="tableData.length"
          layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "myScore",
  data() {
    return {
      pagesize:5,//默认分页每页数据量
      currentPage:1,//默认展示第一页数据
      form: {
        query1: '',
        query2: '',
        query3: '',
        value:true,
      },
      tableData: [],
    }
  },
  methods: {
    search() {
      var mythis = this;
      if(mythis.form.value){
        $.ajax({
          url: "http://localhost:8080/score/findScore",
          type: "post",
          contentType: 'application/json;',
          data: JSON.stringify(localStorage.getItem("id")),
          success: function (response) {
            mythis.tableData = response.data;
          }
        })
      }
    },
    handleSizeChange: function(val) {
      this.pagesize = val;
    },
    handleCurrentChange: function(currentPage) {
      this.currentPage = currentPage;
    },
    exitCourse(row){
      var mythis = this
      $.ajax({
        url: "http://localhost:8080/score/del",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(row.sctid),
        success: function (response) {
          mythis.$message({
            type: response.code === 200 ? "success" : "error",
            message: response.msg
          })
          mythis.search();
        }
      })
    }
  },
  mounted() {
    this.search()
  }
}
</script>

<style scoped>

</style>
