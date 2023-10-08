<template>
  <el-descriptions title="用户信息">
    <el-descriptions-item label="用户名">{{ table.studentName }}</el-descriptions-item>
    <el-descriptions-item label="学号">{{ table.studentId }}</el-descriptions-item>
    <el-descriptions-item label="性别">{{ table.sex }}</el-descriptions-item>
    <el-descriptions-item label="手机号">{{ table.studentPhone }}</el-descriptions-item>
    <el-descriptions-item label="班级">{{ table.className }}</el-descriptions-item>
    <el-descriptions-item label="年级">{{ table.gradeName }}</el-descriptions-item>
    <el-descriptions-item label="学院">{{ table.departmentName }}</el-descriptions-item>
    <el-descriptions-item label="学校">
      <el-tag size="small">xxxxx学院</el-tag>
    </el-descriptions-item>
  </el-descriptions>
</template>

<script>
export default {
  name: "studentInfo",
  data() {
    return {
      id :null,
      table: {
        studentId: "",
        studentName: "",
        studentPhone: "",
        sex: "",
      }
    }
  },
  methods: {
    findById() {
      var mythis = this;
      mythis.id = localStorage.getItem("id");
      $.ajax({
        url: "http://localhost:8080/student/findById",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(mythis.id),
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
  },
  mounted() {
    this.findById()
  },
}
</script>

<style scoped>

</style>
