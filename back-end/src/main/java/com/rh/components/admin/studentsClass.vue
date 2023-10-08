<template>
  <div>
    <el-card style=" border-radius: 4px;margin-top: 1.5vh">
      <el-table
          :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
          border
          style="width: 100%;margin-left: 15vw">
        <el-table-column
            prop="classId"
            label="班级号"
            width="180">
        </el-table-column>
        <el-table-column
            prop="className"
            label="班级名"
            width="180">
        </el-table-column>
        <el-table-column
            prop="gradeName"
            label="年级名"
            width="180">
        </el-table-column>
        <el-table-column
            label="操作"
            width="180">
          <template slot-scope="scope">
            <el-button
                icon="el-icon-date"
                style="margin-left: 2vw"
                size="mini"
                type="danger"
                slot="reference"
                @click="selectClass(scope.row)"
            >查看
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
  </div>
</template>

<script>
export default {
  name: "studentClass",
  data() {

    return {
      hiddenCard: true,
      classForm: {
        classId: '',
        className: '',
        gradeName: ''
      },
      pagesize: 10,//默认分页每页数据量
      currentPage: 1,//默认展示第一页数据
      tableData: [],
    }
  },
  methods: {
    search() {
      var mythis = this;
      $.ajax({
        url: "http://localhost:8080/score/studentClass",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(0),
        success: function (response) {
          mythis.tableData = response.data;
        }
      })
    },
    handleSizeChange: function (val) {
      this.pagesize = val;
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage;
    },
    selectClass(row) {
      var mythis = this;
      localStorage.setItem("classId",row.classId);
      mythis.$router.push ('/admin/studentsScore');
    },
  },
  mounted() {
    this.search()
  }
}
</script>

<style scoped>

</style>
