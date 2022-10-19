<template>
  <div v-wechat-title="$route.meta.title" class="home" style="width:100px;padding: 20px">
  <div style="padding: 30px 100px 30px;background-color: #292d3e;" class="inputDeep">
    <label style="color: white">分组名称：</label>
      <el-input style="width: 200px"
        v-model="input3"
        placeholder="请输入分组名称"
        class="input-with-select"
      >
        <template #append>
          <el-button :icon="Search" style="background-color:#232738;box-shadow:none;border-radius: 0;"/>
        </template>
      </el-input>
    <label style="color: white;padding-left:100px">所属机构：</label>
    <el-select v-model="value1" clearable placeholder=" ">
      <el-option
        value="城院罗老师测试"
      />
    </el-select>
    <div style="display: flex;justify-content: flex-end;">
      <el-button type="primary" @click="add" style="background-color:white;color: black">重置</el-button>
      <el-button type="primary" @click="add">查询</el-button>
    </div>

  </div>
  <!--    功能-->
  <div style="display: flex;justify-content: flex-end;padding:20px;margin-top:20px;background-color: #292d3e;">
    <el-button type="primary" @click="add">新建分组</el-button>
  </div>
  <!--表格-->
    <div class="aaa" >
      <el-table
          :data="tableData"
          :header-cell-style="{background:'#232738',color:'white',borderColor:'grey'}"
          :cell-style="{background:'#292d3e',color:'white',borderColor:'grey'}"
          stripe
          style="width: 100%; text-align: center;color: #000;"
      >
        <el-table-column prop="groupId" label="分组编号" sortable/>
        <el-table-column prop="groupName" label="分组名称"/>
        <el-table-column prop="institutionsId" label="所属机构"/>
        <el-table-column prop="number" label="设备数量"/>
        <el-table-column prop="describes" label="分组描述"/>

        <el-table-column label="操作">
          <template #default="scope">
            <el-button @click="handleEdit(scope.row)">编辑</el-button>
            <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.adminId)">
              <template #reference>
                <el-button type="danger">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>


  <div>
  <div class="demo-pagination-block">
    <el-pagination
      style="display: flex;justify-content: flex-end;background-color: #292d3e;"
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
    <el-dialog v-model="dialogVisible" title="新建分组" width="60%">
      <el-form :model="form" label-width="120px">
<!--          <el-form-item label="管理员ID">-->
<!--            <el-input v-model="form.adminId" style="width:80%"/>-->
<!--          </el-form-item>-->
        <div style="display: flex;">
           <el-form-item label="分组名称">
          <el-input v-model="form.groupName" style="width:80%"/>
        </el-form-item>
       <label style="padding-left:300px">所属机构：</label>
       <el-select v-model="value1" clearable placeholder=" " >
        <el-option
           value="城院罗老师测试"
         />
      </el-select>
        </div>

        <el-form-item label="分组描述">
          <el-input v-model="form.describe" style="width:50%"/>
        </el-form-item>

         <el-form-item label="设备选择"></el-form-item>
         <div style="border: 1px solid #ccc;">
                <label style="padding-left: 50px;">设备名称：</label>
              <el-input style="width: 200px"
                v-model="input3"
                placeholder="请输入设备名称"
                class="input-with-select"
              >
                <template #append>
                  <el-button :icon="Search" style="box-shadow:none;border-radius: 0;"/>
                </template>
              </el-input>
            <label style="padding-left:100px">分辨率：</label>
            <el-select v-model="value1" clearable placeholder=" ">
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
              <el-button type="primary" @click="add" style="margin-left: 100px;background-color:white;color: black">重置</el-button>
            <el-button type="primary" @click="search">查询</el-button>
            <div>
                      <el-table
                    :data="tableData"
                    stripe

                    style="width: 100%; text-align: center">
                  <el-table-column prop="equipmentName" label="设备名称"/>

                  <el-table-column prop="address" label="MAC地址"/>
                  <el-table-column prop="resolutionId" label="分辨率"/>

                      <el-table-column prop="institutionsId" label="所属机构"/>

                  <el-table-column prop="currentPlan" label="当前计划"/>
                  <el-table-column prop="state" label="设备状态"/>
                </el-table>
            </div>
         </div>
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
      search: '',
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
      request.get("/group", {
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
    save(){
      if(this.form.groupId){
        request.put("/group",this.form).then(res => {
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
        request.post("/group",this.form).then(res => {
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
