<template>
  <div v-wechat-title="$route.meta.title" class="home" style="width:100px;padding: 20px; height: calc(100vh - 50px)">
  <div style="padding: 10px 100px 10px;background-color: #292d3e;" class="inputDeep">
    <div style="display: flex; margin: 10px">
      <label style="color: white">设备名称：</label>
      <el-input style="width: 180px"
                v-model="equipmentName"
                placeholder="请输入设备名称"
                class="input-with-select"
      >
        <template #append>
          <el-button :icon="Search" style="background-color:#232738;box-shadow:none;border-radius: 0;"/>
        </template>
      </el-input>
      <label style="color: white;padding-left:100px">所属机构：</label>
      <el-select v-model="institutions" clearable placeholder=" ">
        <el-option
            value="城院罗老师测试"
        />
      </el-select>
      <label style="color: white;padding-left:100px">所属分组：</label>
      <el-select v-model="groupId" clearable placeholder=" ">
        <el-option
            value="罗老师"
        />
      </el-select>

    </div>
    <div style="display:flex; margin: 10px">
      <label style="color: white;">MAC地址：</label>
      <el-input style="width: 200px"
                v-model="address"
                placeholder="请输入MAC地址"
                class="input-with-select"
      >
        <template #append>
          <el-button :icon="Search" />
        </template>
      </el-input>
      <label style="color: white;padding-left:100px">分辨率：</label>
      <el-input style="width: 200px"
                v-model="resolution"
                placeholder="请输入分辨率"
                class="input-with-select"
      >
        <template #append>
          <el-button :icon="Search" style="background-color:#232738;box-shadow:none;border-radius: 0;"/>
        </template>
      </el-input>
      <label style="color: white;padding-left:100px">设备状态：</label>
      <el-select v-model="equipmentState" clearable placeholder=" ">
        <el-option
            value="播放"
        />
        <el-option
            value="空闲"
        />
        <el-option
            value="离线"
        />
      </el-select>
    </div>
    <div style="display: flex;">
      <label style="color: white;">系统版本：</label>
      <el-select v-model="system_update" clearable placeholder=" ">
        <el-option
            value="已是最新"
        />
        <el-option
            value="升级"
        />
        <el-option
            value="有新版本"
        />
      </el-select>
      <label style="color: white;padding-left:100px">当前计划：</label>
      <el-select v-model="currentPlan" clearable placeholder=" ">
        <el-option
            value="-"
        />
      </el-select>


      <label style="color: white;padding-left: 100px">SN：</label>
      <el-input style="width: 200px"
                v-model="input3"
                placeholder="请输入SN号"
                class="input-with-select"
      >
        <template #append>
          <el-button :icon="Search" style="background-color:#232738;box-shadow:none;border-radius: 0;"/>
        </template>
      </el-input>
    </div>

    <div style="display: flex;justify-content: flex-end; margin: 10px">
      <el-button type="primary" @click="add" style="background-color:white;color: black">重置</el-button>
      <el-button type="primary" @click="load">查询</el-button>
    </div>


  </div>
    <div style="display: flex;justify-content: flex-end;margin-top:20px;padding-right: 20px;padding-top: 20px;padding-bottom: 15px;background-color: #292d3e;">
      <el-button type="primary" style="" @click="load">批量导入</el-button>
      <el-button type="primary" style="margin-left: 8px;background-color: white;color: black" @click="load">模板下载</el-button>
      <el-button type="primary" style="margin-left: 8px;background-color: white;color: black" @click="load">批量删除</el-button>
      <el-button type="primary" style="margin-left: 8px;background-color: white;color: black" @click="load">批量控制</el-button>
      <el-button type="primary" style="margin-left: 8px;background-color: white;color: black" @click="load">数据导出</el-button>
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
        <el-table-column prop="equipmentId" label="设备编号" sortable/>
        <el-table-column prop="equipmentName" label="设备名称"/>
        <el-table-column prop="institutionsId" label="所属机构"/>
        <el-table-column prop="groupId" label="所属分组"/>
        <el-table-column prop="address" label="MAC地址"/>
        <el-table-column prop="resolutionId" label="分辨率"/>
        <el-table-column prop="state" label="设备状态"/>
        <el-table-column prop="system_update" label="系统升级"/>
        <el-table-column prop="currentPlan" label="当前计划"/>
        <el-table-column label="操作" width="400">
          <template #default="scope">
            <el-button size="small" type="primary" @click="load">详情</el-button>
            <el-button size="small" type="primary" @click="load">控制</el-button>
            <el-button size="small" type="primary" @click="load">刷新</el-button>
            <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
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
      <el-dialog v-model="dialogVisible" title="提示" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="设备名称">
            <el-input v-model="form.equipmentName" style="width:80%"/>
          </el-form-item>
          <el-form-item label="所属分组">
            <el-input v-model="form.groupId" style="width:80%"/>
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
      equipmentName:'',
      institutions:'',
      groupId:1,
      address:'',
      resolution:'',
      equipmentState:'',
      system_update:'',
      currentPlan:'',
      value1: '',
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
      request.get("/equipment", {
        params:{
          pageNum:this.currentPage4,
          pageSize:this.pageSize4,
          equipmentName:this.equipmentName,
          institutions:this.institutions,
          groupId:this.groupId,
          address:this.address,
          resolution:this.resolution,
          equipmentState:this.equipmentState,
          system_update:this.system_update,
          currentPlan:this.currentPlan,

        }
      }).then(res => {
        console.log(res.data.records)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    save(){
      if(this.form.equipmentId){
        request.put("/equipment",this.form).then(res => {
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
        request.post("/equipment",this.form).then(res => {
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
      request.delete("/equipment/" + id).then(res => {
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
