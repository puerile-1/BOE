<template>
  <div v-wechat-title="$route.meta.title" class="home" style="width: 100px;padding: 20px">
     <div style="padding: 30px 100px 30px;background-color: #292d3e;" class="inputDeep">
       <label style="color: white">节目名称：</label>
      <el-input style="width: 200px"
        v-model="name"
        placeholder="请输入节目名称"
        class="input-with-select"
      >
        <template #append>
          <el-button :icon="Search" style="background-color:#232738;box-shadow:none;border-radius: 0;"/>
        </template>
      </el-input>
       <label style="color: white;padding-left:100px">分辨率：</label>
       <el-select v-model="resolutionId" clearable placeholder="请选择分辨率">
      <el-option
        value="1920*1080(横)"
      />
      <el-option
        value="1080*1920(竖)"
      />
      <el-option
        value="3840*2160(横)"
      />
      <el-option
        value="2160*3840(竖)"
      />
    </el-select>
    <label style="color: white;padding-left:100px">节目状态：</label>
       <el-select v-model="state" clearable placeholder="请选择节目状态">
      <el-option
        value="所有"
      />
      <el-option
        value="使用中"
      />
      <el-option
        value="未使用"
      />
      <el-option
        value="已失效"
      />
    </el-select>
     <div style="display: flex;justify-content: flex-end; margin-top: 20px">
       <el-button type="primary" @click="add" style="background-color:white;color: black">重置</el-button>
       <el-button type="primary" @click="load">查询</el-button>
     </div>

  </div>
    <div style="display: flex;justify-content: flex-end;padding: 20px;margin-top:20px;background-color: #292d3e;">
      <el-button type="primary" @click="add">创建节目</el-button>
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
        <el-table-column prop="over" label="缩略图" sortable />
        <el-table-column prop="name" label="节目名称"/>
        <el-table-column prop="resolutionId" label="分辨率"/>
        <el-table-column prop="time" label="节目时长"/>
        <el-table-column prop="size" label="节目大小"/>
        <el-table-column prop="state" label="	节目状态"/>
        <el-table-column prop="userId" label="作者"/>
        <el-table-column prop="updateTime" label="更新时间"/>
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button size="small" type="primary" @click="load">预览</el-button>
            <el-button size="small" type="primary" @click="load">更名</el-button>
            <el-button size="small" type="primary" @click="load">编辑</el-button>
            <br>
            <el-button size="small" @click="handleEdit(scope.row)">复制</el-button>
            <el-button size="small" @click="handleEdit(scope.row)">发布</el-button>
            <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.teacherId)">
              <template #reference>
                <el-button size="small" type="danger">删除</el-button>
              </template>
            </el-popconfirm>
            <br>


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
          <el-form-item label="名称">
            <el-input v-model="form.name" style="width:80%"/>
          </el-form-item>
          <el-form-item label="分辨率" >
            <el-select v-model="form.resolutionId" clearable placeholder="请选择分辨率" style="width:80%">
              <el-option
                  value="1920*1080(横)"
              />
              <el-option
                  value="1080*1920(竖)"
              />
              <el-option
                  value="3840*2160(横)"
              />
              <el-option
                  value="2160*3840(竖)"
              />
            </el-select>
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
      resolutionId: '',
      state: 0,
      value4: '',
       value5: ''
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      request.get("/program", {
        params:{
          pageNum:this.currentPage4,
          pageSize:this.pageSize4,
          name:this.name,
          resolutionId:this.resolutionId,
          state:this.state
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    save(){
      if(this.form.id){
        request.put("/program",this.form).then(res => {
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
        request.post("/program",this.form).then(res => {
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
          this.$router.push({ path: '/adminCreateProgram' })
          this.$nextTick()
        })
      }

    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true

    },
    handleDelete(id) {
      console.log(id)
      request.delete("/program/" + id).then(res => {
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
