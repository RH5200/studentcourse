<template>
  <el-descriptions title="用户信息">
    <el-descriptions-item label="用户名">{{ table.teacherName }}</el-descriptions-item>
    <el-descriptions-item label="学号">{{ table.teacherId }}</el-descriptions-item>
    <el-descriptions-item label="性别">{{ table.sex }}</el-descriptions-item>
    <el-descriptions-item label="手机号">{{ table.teacherPhone }}</el-descriptions-item>
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
      id : null,
      table: {
        teacherId: "",
        teacherName: "",
        teacherPhone: "",
        sex: "",
      }
    }
  },
  methods: {
    findById() {
      var mythis = this;
      mythis.id = localStorage.getItem("id");
      $.ajax({
        url: "http://localhost:8080/teacher/findById",
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
