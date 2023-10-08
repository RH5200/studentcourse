<template>
  <div style="margin-top: 3vh">
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <el-form-item label="课程号">
        <el-input v-model="form.query1" placeholder="课程号"></el-input>
      </el-form-item>
      <el-form-item label="课程名">
        <el-input v-model="form.query2" placeholder="课程名"></el-input>
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
          style="width: 100%;margin-left: 9vw">
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
            prop="term"
            label="学期"
            width="180">
        </el-table-column>
        <el-table-column
            label="操作"
            width="180">
          <template slot-scope="scope">
            <el-popconfirm
                title="确定选择该课程吗？"
                confirm-button-text='取消'
                cancel-button-text='确定'
                cancel-button-type="Primary"
                confirm-button-type="Danger"
                @cancel="sure(scope.row)"
            >
              <el-button
                  icon="el-icon-check"
                  style="margin-left: 2vw"
                  size="mini"
                  type="primary"
                  slot="reference"
              >开设
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

    <el-card :hidden="hiddenCard"
             style=" border-radius: 4px;width: 40vw;position: fixed;margin-top: -70vh;margin-left: 15vw">
      <el-form :model="teacherForm"  status-icon ref="teacherForm" label-width="10vw"
               class="demo-ruleForm"
               label-position="right">
        <el-form-item>

        </el-form-item>
        <el-form-item label="教师:" prop="sex">
          <el-select v-model="teacherForm.teacherId"  placeholder="请选择教师:"  filterable autocomplete="off" style="width: 15vw;margin-right: 300px">
            <el-option
                v-for="item in teacherData"
                :label="item.teacherId + item.teacherName"
                :value="item.teacherId"
                :key="item.teacherName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-button type="success" plain class="edit_button"
                   @click="edit" style="margin-right: 3vw">
          确定
        </el-button>
        <el-button type="info" plain class="edit_button"
                   @click="close" style="margin-right: 3vw">
          取消
        </el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "creatCourse",
  data() {
    return {
      hiddenCard: true,
      pagesize: 10,//默认分页每页数据量
      currentPage: 1,//默认展示第一页数据
      teacherForm: {
        teacherId: '',
      },
      form: {
        query1: '',
        query2: '',
        value: true,
      },
      tableData: [],
      teacherData:[],
    }
  },
  methods: {
    search() {
      var mythis = this;
      if (mythis.form.value) {
        var formInfo = {
          courseId:mythis.form.query1,
          courseName: mythis.form.query2,
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
    teacherSearch() {
      var mythis = this;
      if (mythis.form.value) {
        $.ajax({
          url: "http://localhost:8080/teacher/findAll",
          type: "post",
          contentType: 'application/json;',
          success: function (response) {
            mythis.teacherData = response.data;
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
    sure(row){
      var mythis = this;
      mythis.hiddenCard = false;
      localStorage.setItem("courseId",row.courseId)
      localStorage.setItem("term", row.term)
    },
    edit() {
      var mythis = this;
      var formInfo = {
        teacherId: mythis.teacherForm.teacherId,
        courseId: localStorage.getItem("courseId"),
        term: localStorage.getItem("term"),
      }
      $.ajax({
        url: "http://localhost:8080/courseTeacher/addCourse",
        type: "post",
        contentType: 'application/json;',
        data: JSON.stringify(formInfo),
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
    close() {
      var mythis = this;
      mythis.hiddenCard = true;
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
    this.teacherSearch()
  }
}
</script>

<style scoped>

</style>
