<template>
  <el-descriptions title="用户信息">
    <el-descriptions-item label="用户名">{{ table.adminName }}</el-descriptions-item>
    <el-descriptions-item label="学号">{{ table.adminId }}</el-descriptions-item>
    <el-descriptions-item label="手机号">{{ table.adminPhone }}</el-descriptions-item>
    <el-descriptions-item label="学校">
      <el-tag size="small">贵州师范学院</el-tag>
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
        adminId: "",
        adminName: "",
        adminPhone: "",
        sex: "",
      }
    }
  },
  methods: {
    findById() {
      var mythis = this;
      mythis.id = localStorage.getItem("id");
      $.ajax({
        url: "http://localhost:8080/admin/findById",
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
