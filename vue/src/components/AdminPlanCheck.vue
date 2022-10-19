<template>
  <div v-wechat-title="$route.meta.title" class="home" style="padding: 20px">
     <div style="padding: 30px 100px 30px;background-color: #292d3e;" class="inputDeep">
       <label style="color: white">计划名称：</label>
      <el-input style="width: 200px"
        v-model="name"
        placeholder="请输入计划名称"
        class="input-with-select"
      >
        <template #append>
          <el-button :icon="Search" style="background-color:#232738;box-shadow:none;border-radius: 0;"/>
        </template>
      </el-input>
    <el-button type="primary" @click="add" style="margin-left: 900px;">重置</el-button>
    <el-button type="primary" @click="load" style="background-color:white;color: black">查询</el-button>
  </div>
    <!--表格-->
    <div class="aaa">
      <el-table
          :data="tableData"
          stripe
          :header-cell-style="{background:'#232738',color:'white',borderColor:'grey'}"
          :cell-style="{background:'#292d3e',color:'white',borderColor:'grey'}"
          style="width: 100%; text-align: center">
        <el-table-column prop="over" label="缩略图" />
        <el-table-column prop="name" label="计划名称"/>
        <el-table-column prop="model" label="使用设备数"/>
        <el-table-column prop="userId" label="作者"/>
        <el-table-column prop="cardId" label="更新日期"/>
        <el-table-column label="操作" width="400">
          <template #default="scope">
            <el-button size="small" type="primary" @click="load">预览</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>


    <div style="margin: 10px 0;">
      <!--      分页-->
      <el-pagination
          v-model:currentPage="currentPage4"
          v-model:page-size="pageSize4"
          :page-sizes="[5, 10, 15, 20]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"/>
      <!--弹窗-->
      <el-dialog v-model="dialogVisible" title="提示" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="教师姓名">
            <el-input v-model="form.teacherName" style="width:80%"/>
          </el-form-item>
          <el-form-item label="学院">
            <el-input v-model="form.institute" style="width:80%"/>
          </el-form-item>
          <el-form-item label="性别">
            <el-input v-model="form.sex" style="width:80%"/>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="form.tel" style="width:80%"/>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.email" style="width:80%"/>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="form.pwd" style="width:80%"/>
          </el-form-item>
          <el-form-item label="卡号">
            <el-input v-model="form.cardId" style="width:80%"/>
          </el-form-item>
          <el-form-item label="类型">
            <el-input v-model="form.type" style="width:80%"/>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确认</el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src


import request from "@/utils/request";
import { ArrowLeft } from '@element-plus/icons-vue'
import { Search } from '@element-plus/icons-vue'
export default {
  name: 'ExamSearch',
  components: {
    ArrowLeft
  },
  data() {
    return {
      search: '',
      currentPage4: 1,
      pageSize4: 10,
      total: 0,
      dialogVisible:false,
      form:{},
      tableData: [],
      Search,
      name: '',
      value2: '',
      value3: '',
      value4: '',
       value5: ''
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      request.get("/plan", {
        params:{
          pageNum:this.currentPage4,
          pageSize:this.pageSize4,
          name:this.name
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    save(){
      if(this.form.teacherId){
        request.put("/teacher",this.form).then(res => {
          console.log(res)
          if(res.code === '0'){
            this.$message({
              type:"success",
              message:"更新成功"
            })
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
          this.load()//刷新表格的数据
          this.dialogVisible = false  //关闭弹窗
        })
      }
      else{
        // console.log(this.form)
        request.post("/teacher",this.form).then(res => {
          console.log(res)
          if(res.code === '0'){
            this.$message({
              type:"success",
              message:"新增成功"
            })
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
          this.load()//刷新表格的数据
          this.dialogVisible = false  //关闭弹窗
        })
      }

    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true

    },
    handleDelete(id) {
      console.log(id)
      request.delete("/teacher/" + id).then(res => {
        console.log(res)
        if(res.code === '0'){
          this.$message({
            type:"success",
            message:"删除成功"
          })
        }else{
          this.$message({
            type:"error",
            message:res.msg
          })
        }
        this.load()//刷新表格的数剧
      })
    },
    add() {
      this.dialogVisible = true
      this.form = {}
    },
    handleSizeChange(pageSize) {//改变当前每页的个数
      this.pageSize4 = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {//改变当前页码
      this.currentPage4 = pageNum
      this.load()
    }

  }
}
</script>
<style scoped>
.aaa >>> .el-table__row>td{
  /* 去除表格线 */
  border: none;
}

.aaa >>> .el-table th.is-leaf {
  /* 去除上边框 */
  border: none;
}
.aaa >>> .el-table::before{
  /* 去除下边框 */
  border: none;
}

</style>
