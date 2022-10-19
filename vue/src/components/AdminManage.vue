<template>
  <div v-wechat-title="$route.meta.title" class="home" style="width:100px;padding: 20px">
  <div style="padding: 10px 100px 10px;background-color: #292d3e;" class="inputDeep">
    <label style="color: white">用户名：</label>
      <el-input style="width: 200px"
        v-model="adminName"
        placeholder="请输入账户名称"
        class="input-with-select"
      >
        <template #append>
          <el-button :icon="Search" style="background-color:#232738;box-shadow:none;border-radius: 0;"/>
        </template>
      </el-input>
    <label style="color: white;padding-left:100px">所属机构：</label>
    <el-select v-model="institutionsId" clearable placeholder=" ">
      <el-option
        value="城院罗老师测试"
      />
    </el-select>
    <label style="color: white;padding-left:100px">所属角色：</label>
    <el-select v-model="role" clearable placeholder=" ">
      <el-option
        value="罗老师"
      />
    </el-select>
    <label style="color: white;padding-left:10px">状态：</label>
    <el-select v-model="userState" clearable placeholder=" " style="padding:10px">
      <el-option
        value="所有"
      />
      <el-option
        value="启用"
      />
      <el-option
        value="停用"
      />
    </el-select>
    <div style="display: flex;justify-content: flex-end;padding-top: 30px;padding-bottom: 15px;">
      <el-button type="primary" @click="add" style="background-color: white;color: black">重置</el-button>
      <el-button type="primary" @click="load">查询</el-button>
    </div>
  </div>
  <!--    功能-->
  <div style="display: flex;justify-content: flex-end;padding: 20px;margin-top:20px;background-color: #292d3e;">
    <el-button type="primary" @click="add">新建账户</el-button>
  </div>
  <!--表格-->
    <div class="aaa" >
      <el-table

          :data="tableData"
          :header-cell-style="{background:'#232738',color:'white',borderColor:'grey'}"
          :cell-style="{background:'#292d3e',color:'white',borderColor:'grey'}"
          stripe
          style="width: 100%; text-align: center;color: #fff;"
      >
        <el-table-column prop="adminId" label="用户编号" sortable/>
        <el-table-column prop="adminName" label="用户名"/>
        <el-table-column prop="pwd" label="密码"/>
        <el-table-column prop="institutionsId" label="所属机构编号"/>
        <el-table-column prop="userState" label="用户状态"/>
        <el-table-column prop="role" label="所属角色"/>
        <el-table-column prop="trueName" label="真实姓名"/>
        <el-table-column prop="tel" label="电话"/>
        <el-table-column prop="email" label="电子邮箱"/>



        <el-table-column label="操作" width="150px">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.adminId)">
              <template #reference>
                <el-button size="small" type="danger">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>


  <div>
  <div class="demo-pagination-block">
    <el-pagination
      style="display: flex;justify-content: flex-end;background-color: #292d3e; margin-top:20px"
      v-model:currentPage="currentPage4"
      v-model:page-size="pageSize4"
      :page-sizes="[10, 20, 50, 100]"
      :small="small"
      :disabled="disabled"
      :pager-style="{background:'#292d3e'}"
      layout="total, prev, pager, next, sizes, jumper"
      :total="10"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
    <!--弹窗-->
    <el-dialog v-model="dialogVisible" title="提示" width="30%">
      <el-form :model="form" label-width="120px">
<!--          <el-form-item label="管理员ID">-->
<!--            <el-input v-model="form.adminId" style="width:80%"/>-->
<!--          </el-form-item>-->
        <el-form-item label="用户名">
          <el-input v-model="form.adminName" style="width:80%"/>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.pwd" style="width:80%"/>
        </el-form-item>

        <el-form-item label="真实姓名">
          <el-input v-model="form.trueName" style="width:80%"/>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.tel" style="width:80%"/>
        </el-form-item>
        <el-form-item label="电子邮箱">
          <el-input v-model="form.email" style="width:80%"/>
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
import { Search } from '@element-plus/icons-vue'
import { ArrowLeft } from '@element-plus/icons-vue'
export default {
  name: 'adminManage',
  components: {
    ArrowLeft
  },
  data() {
    return {
      institutionsId:'',
      adminName:'',
      userState:'',
      role:'',
      currentPage4: 1,
      pageSize4: 10,
      total: 0,
      dialogVisible:false,
      form:{},
      tableData: [],
      value1: '',
      value2: '',
      value3: '',
      Search
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      console.log(this.adminName)
      request.get("/admin", {
        params:{
          pageNum:this.currentPage4,
          pageSize:this.pageSize4,
          institutionsId:this.institutionsId,
          adminName:this.adminName,
          userState:this.userState,
          role:this.role
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },

    save(){
      if(this.form.adminId){
        request.put("/admin",this.form).then(res => {
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
        request.post("/admin",this.form).then(res => {
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
    add(){
      this.dialogVisible = true
      this.form = {}
    },

    handleDelete(id) {
      console.log(id)
      request.delete("/admin/" + id).then(res => {
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

.el-input__wrapper {
  border: none !important;
  box-shadow: none !important;
}
.el-input__inner {
  border: none !important;
  box-shadow: none !important;
}
.el-select {
  --el-select-input-focus-border-color: transparent;
}
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
