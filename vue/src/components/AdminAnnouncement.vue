<template>
  <div v-wechat-title="$route.meta.title" class="home" style="width:100px;padding: 20px">
     <div style="padding: 30px 100px 30px;background-color: #292d3e;" class="inputDeep">
       <label style="color: white;padding-left:10px">公告状态：</label>
       <el-select v-model="state" clearable placeholder="请选择公告状态">
      <el-option
        value="全部"
      />
      <el-option
        value="待发布"
      />
      <el-option
        value="发布中"
      />
      <el-option
        value="播放中"
      />
      <el-option
        value="发布成功"
      />
      <el-option
        value="发布失败"
      />
      <el-option
        value="已结束"
      />
    </el-select>
     <div style="display: flex;justify-content: flex-end;">
       <el-button type="primary" @click="add" style="margin-left: 650px;background-color:white;color: black">重置</el-button>
       <el-button type="primary" @click="load">查询</el-button>
     </div>

  </div>
    <div style="display: flex;justify-content: flex-end;padding: 20px;margin-top:20px;background-color: #292d3e;">
      <el-button type="primary" @click="create">创建公告</el-button>
    </div>
    <br>
    <!--表格-->
    <div class="aaa">
      <el-table
          :data="tableData"
          stripe
          :header-cell-style="{background:'#232738',color:'white',borderColor:'grey'}"
          :cell-style="{background:'#292d3e',color:'white',borderColor:'grey'}"
          style="width: 100%; text-align: center">
        <el-table-column prop="content" label="公告内容" sortable/>
        <el-table-column prop="startDate" label="播放时间"/>
        <el-table-column prop="state" label="公告状态"/>
        <el-table-column prop="userId" label="作者"/>
        <el-table-column prop="endDate" label="创建时间"/>

        <el-table-column label="操作" width="400">
          <template #default="scope">
            <el-button size="small" type="primary" @click="load">详情</el-button>
            <el-button size="small" type="primary" @click="load">修改</el-button>
            <el-button size="small" type="primary" @click="load">复制</el-button>
            <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.teacherId)">
              <template #reference>
                <el-button size="small" type="danger">删除</el-button>
              </template>
            </el-popconfirm>

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
      state: '',
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
      request.get("/announcement", {
        params:{
          pageNum:this.currentPage4,
          pageSize:this.pageSize4,
          search:this.search
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    create(){
      this.$router.push({ path: '/adminCreateAnnouncement' })
      this.$nextTick()
    },
    save(){
      if(this.form.id){
        request.put("/announcement",this.form).then(res => {
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
        request.post("/announcement",this.form).then(res => {
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
      request.delete("/announcement/" + id).then(res => {
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
