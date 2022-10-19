<template>
  <div v-wechat-title="$route.meta.title"  class="material">
    <div class="top">
      <div class="topLeft">
        <label style="width:50px;color: white;margin-right: 10px;font-size: 18px">搜索:</label>
        <el-input style="width: 500px"
                  v-model="search"
                  class="input-with-select">
        </el-input>
      </div>

      <div class="topRight">
        <el-button type="primary" @click="add" style="background-color: white;color: black">重置</el-button>
        <el-button type="primary" @click="load">查询</el-button>
      </div>
    </div>
    <!--    功能-->
    <div class="body">
      <div class="bodyTop">
        <div class="bodyTopLeft">
          <el-radio-group  >
            <el-radio v-model="checked1" label="2" size="large">全部</el-radio>
            <el-radio label="2" size="large">图片</el-radio>
            <el-radio label="2" size="large">视频</el-radio>
            <el-radio label="2" size="large">音频</el-radio>
            <el-radio label="2" size="large">艺术</el-radio>
            <el-radio label="2" size="large">党建</el-radio>
          </el-radio-group>
        </div>
        <div class="bodyTopRight">
          <el-button type="primary"  @click="load">取消选择</el-button>
          <el-button type="primary" style="margin-left: 8px;" @click="selectAll">全选</el-button>
          <el-button type="primary" style="margin-left: 8px;" @click="load">批量删除</el-button>
          <el-button type="primary" style="margin-left: 8px;" @click="addMaterial">新建文件夹</el-button>
          <el-button type="primary" style="margin-left: 8px;" @click="addMaterial">上传素材</el-button>
        </div>
      </div>
      <div class="bodyContent">
<!--        <div class="upload">-->
<!--          <el-upload-->
<!--              v-model:file-list="fileList"-->
<!--              action="http://localhost:8081/material/UploadPic"-->
<!--              list-type="picture-card"-->
<!--              :on-preview="handlePictureCardPreview"-->
<!--              :on-remove="handleRemove"-->
<!--          >-->
<!--            <el-icon><Plus /></el-icon>-->
<!--          </el-upload>-->

<!--          <el-dialog v-model="dialogVisible1">-->
<!--            <img w-full :src="dialogImageUrl" alt="Preview Image" />-->
<!--          </el-dialog>-->
<!--        </div>-->

        <div class="item" v-for="(item,index) in tableData" :key="index">
          <div  :class="{'bg':item.displayed}">
            <el-checkbox :style=item.myStyle class="check" v-model="item.checked"></el-checkbox>
          </div>

          <div class="content"  @click="clickItem(index)" @dblclick="goOn(index)">
            <img :src="require('../assets/img/yun.jpg')" class="fileImg">
            <div class="title">
              {{item.materialName}}

            </div>
          </div>

        </div>
      </div>

    </div>
    <el-dialog v-model="dialogVisible8" title="提示" width="30%">
              <div class="upload">
                <el-upload
                    v-model:file-list="fileList"
                    action="http://localhost:8080/material/UploadPic"
                    list-type="picture-card"
                    :on-preview="handlePictureCardPreview"
                    :on-remove="handleRemove"
                >
                  <el-icon><Plus /></el-icon>
                </el-upload>

                <el-dialog v-model="dialogVisible1">
                  <img w-full :src="dialogImageUrl" alt="Preview Image" />
                </el-dialog>
              </div>
    </el-dialog>
    <!--表格-->
    <div class="aaa" >

      <!--弹窗-->
      <el-dialog v-model="dialogVisible" title="提示" width="30%">
        <el-form :model="form" label-width="120px">
          <!--          <el-form-item label="管理员ID">-->
          <!--            <el-input v-model="form.adminId" style="width:80%"/>-->
          <!--          </el-form-item>-->
          <el-form-item label="文件夹名称">
            <el-input v-model="form.fileName" style="width:80%"/>
          </el-form-item>
          <el-form-item label="所属类别">
            <el-select v-model="form.category" clearable style="width:80%">
              <el-option
                  value="图片"
              />
              <el-option
                  value="视频"
              />
              <el-option
                  value="音频"
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
import { Search } from '@element-plus/icons-vue'
import { ArrowLeft } from '@element-plus/icons-vue'
import {ref} from "vue";
export default {
  name: 'adminManage',
  components: {
    ArrowLeft
  },
  data() {
    return {
      fileList:[{
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
      }],
      dialogVisible8:false,
      search:'',
      currentPage4: 1,
      pageSize4: 10,
      total: 0,
      dialogVisible:false,
      form:{},
      tableData: [],
      value1: '/src/assets/img/yun.jpg',
      value2: '',
      value3: '',
      dialogImageUrl:'',
      dialogVisible1:false,

      fileName:"zzz"
    }
  },
  created() {
    this.load()
  },
  methods: {

    clickItem(index){
      if(this.tableData[index].displayed===false)
        this.tableData[index].displayed=true
      else
        this.tableData[index].displayed=false
      if(this.tableData[index].myStyle==="display: none;")
        this.tableData[index].myStyle="display: block;"
      else
        this.tableData[index].myStyle="display: none;"
      if(this.tableData[index].checked===false)
        this.tableData[index].checked=true
      else
        this.tableData[index].checked=false
    },
    selectAll(){
      for (let i = 0; i < this.tableData.length; i++) {
        this.tableData[i].displayed = true
        this.tableData[i].myStyle = "display: block;"
        this.tableData[i].checked = true
      }

    },
    load(){

      this.fileName = this.$route.query.name
      console.log(this.fileName)
      request.get("/material", {
        params:{
          pageNum:this.currentPage4,
          pageSize:this.pageSize4,
          fileName:this.fileName
        }
      }).then(res => {
        console.log(res.data)
        this.tableData = res.data.records
        console.log(this.tableData)
        this.total = res.data.total
      })
    },

    save(){
      if(this.form.fileId){
        request.put("/file",this.form).then(res => {
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
        request.post("/file",this.form).then(res => {
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
    addMaterial(){
      this.dialogVisible8 = true
    },
    handleDelete(id) {
      console.log(id)
      request.delete("/file/" + id).then(res => {
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
    },
    handlePictureCardPreview(uploadFile){
      console.log(uploadFile.url)
      this.dialogImageUrl = uploadFile.url

      this.dialogVisible1 = true
    }
  }
}
</script>
<style lang="less" scoped>
.material{
  width: 100%;
  padding: 20px;
  .top{
    padding: 20px;
    background-color: #292d3e;
    display: flex;
    align-items: center;
    .topLeft{
      margin-left: 50px;
    }
    .topRight{
      margin-left: 700px;
    }
  }
  .body{
    width: 100%;
    margin-top: 20px;
    padding-top: 30px;
    padding-bottom: 15px;
    background-color: #292d3e;
    .bodyTop{
      width: 100%;
      display: flex;
      align-items: center;
      .bodyTopLeft{
        margin-left: 50px;
        width: 600px;
      }
      .bodyTopRight{
        width: 600px;
        margin-left: 250px;
      }
    }
    .bodyContent{
      width: 100%;
      height: 600px;
      display: flex;
      .upload{
        width: 90%;
        height: 90%;
        margin: 80px;
      }
      .item{
        width: 150px;
        height: 180px;

        margin: 40px 40px 40px 80px;
        .bg{

          position: absolute;
          width: 190px;
          height: 200px;
          border: 1px solid dodgerblue;
          z-index: 0;
          .check{
            margin-left: 160px;
            margin-top: 15px;
          }
        }

        .content{
          position: relative;
          z-index: 1;

          .fileImg{
            width: 90%;
            margin-left: 25px;
            margin-top: 50px;
          }
          .title{
            width: 90%;
            color: white;
            font-size: 20px;
            text-align: center;
            margin-left: 25px;
            margin-top: 20px;

          }
        }

      }

    }
  }


}




</style>
