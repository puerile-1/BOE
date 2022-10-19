<template>
  <div class="home" style="margin: 30px 30px 30px">
      <div class="top">
         <div style="color: white;padding-top: 40px;padding-left: 30px;">设置日程</div>
         <div class="jihua">
            <div style="color: white;padding-left: 30px;padding-top: 20px;" class="celue">计划名称：</div>
            <el-input v-model="planName" placeholder="请输入计划名称" style="padding-top: 20px;width: 220px;"></el-input>
         </div>
         <div class="jihua">
            <div style="color: white;padding-left: 30px;padding-top: 35px;" class="celue">播放策略：</div>
           <el-radio v-model="radio1" label="1" style="padding-top: 46px;">替换</el-radio>
           <el-radio v-model="radio1" label="2" style="padding-top: 46px;">插播</el-radio>
         </div>
         <div class="jihua">
            <div style="color: white;padding-left: 30px;padding-top: 35px;" class="celue">播放模式：</div>
           <el-radio v-model="radio2" label="1" style="padding-top: 46px;">时段播放</el-radio>
           <el-radio v-model="radio2" label="2" style="padding-top: 46px;">持续播放</el-radio>
         </div>
         <div class="jihua">
            <div style="color: white;padding-left: 30px;padding-top: 35px;" class="celue">多屏同步：</div>
           <el-radio v-model="radio3" label="1" style="padding-top: 46px;">打开</el-radio>
           <el-radio v-model="radio3" label="2" style="padding-top: 46px;">关闭</el-radio>
           <div style="color: rgb(170,170,170);font-size:14px; padding-top: 35px;">（打开此功能后会使播放同一计划的设备画面保持统一）</div>
         </div>
         <div class="jihua">
            <div style="color: white;padding-left: 30px;padding-top: 35px;" class="celue">播放事件：</div>
            <div style="padding-top: 30px;">
              <el-date-picker
                  v-model="value1"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  :size="size"
              />

            </div>

         </div>
      </div>
      <div class="mid" style="padding-left: 20px;padding-top: 20px;">
          <div class="left">
            <div id="times" style="margin: 40px 15px 15px;">
            <div class="xunhuan">
                <div style="color: #00FFFF;padding-left:10px; padding-top:10px; font-size:13px;">循环时间段：</div>
                 <div style="padding-left: 120px;padding-top:10px">
                    <el-icon @click="add"><Edit /></el-icon>

                    <el-icon  @click="del" ><DeleteFilled /></el-icon>
                    </div>
            </div>
                 <div style="color:#00FFFF; font-size:13px;padding-left:10px; padding-top:10px;" >
                   {{time}}
                 </div>
                 <div style="color:#00FFFF; font-size:13px;padding-left:10px; padding-top:10px;">
                        循环周期：
                 </div>
                 <div style="color:#00FFFF; font-size:13px;padding-left:10px; padding-top:10px;">
                   {{ userState }}
                 </div>

            </div>
             <div class="tianjiatime" style="margin: 40px 15px 15px;">
                <div style="color: white;" class="add" @click="add">
                  <el-icon><CirclePlus /></el-icon>
                  <span class="addTime">添加时间段</span>
                </div>
             </div>
          </div>
          <div class="right" style="margin-left: 20px;">
            <div id="program">
                <img src="../assets/img/yun.jpg">
                <div class="content">
                  <div class="programName">1111</div>
                  <span class="res">1920*1080</span>
                  <span class="time">5s</span>
                </div>
            </div>
              <div class="tianjiajiemu" style="margin: 20px 20px;" @click="addjie">
                <div style="color: white;" class="add1">
                  <el-icon><CirclePlus /></el-icon>
                </div>
                 <div style="color: white;" class="add2">
                 <br>
                 <span class="addjiemu">添加节目</span>
                 </div>
                </div>
             </div>
      </div>
    <div class="button">
      <el-button type="primary" @click="back" style="color:black; background-color: white">取消</el-button>
      <el-button type="primary" @click="next" style="color:black; background-color: white">选择设备</el-button>
      <el-button type="primary" @click="save">保存</el-button>
    </div>
  </div>
  <el-dialog v-model="dialogVisible" title="设置播放时间段" width="30%">
      <el-form :model="form" label-width="120px">

        <el-form-item label="循环时间段:">
          <el-time-picker
                is-range
                v-model="value2"
                range-separator="至"
                start-placeholder="开始时间"
                end-placeholder="结束时间"
                placeholder="选择时间范围">
            </el-time-picker>

        </el-form-item>
        <el-form-item label="循环周期:">
                <el-select v-model="userState" clearable placeholder=" ">
                <el-option
                    value="每天"
                />
                <el-option
                    value="每周"
                />
                <el-option
                    value="每月"
                />
                </el-select>
        </el-form-item>

      </el-form>
      <template #footer>
    <span class="dialog-footer">
      <el-button @click="dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="addTime(value2,value1)">确认</el-button>
    </span>
      </template>
    </el-dialog>


     <el-dialog v-model="dialogVisible1" title="确认删除此时间段" width="30%">

      <template #footer>
    <span class="dialog-footer">
      <el-button @click="dialogVisible1 = false">取消</el-button>
      <el-button type="primary" @click="save">确认</el-button>
    </span>
      </template>
    </el-dialog>
    <el-dialog v-model="dialogVisible2" title="添加节目" width="45%">
      <div style="">
       <label style="color: black">节目名称：</label>
      <el-input style="width: 200px"
        v-model="name"
        placeholder="请输入节目名称"
        class="input-with-select"
      >
        <template #append>
          <el-button :icon="Search" style="box-shadow:none;border-radius: 0;"/>
        </template>
      </el-input>
       <label style="color: black;padding-left:60px">分辨率：</label>
       <el-select v-model="resolutionId" clearable placeholder=" ">
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
    <el-button type="primary" @click="add" style="margin-left: 650px;background-color:white;color: black">重置</el-button>
    <el-button type="primary" @click="load">查询</el-button>
  </div>
  <br>
   <div class="aaa">
      <el-table
          :data="tableData"
          stripe
          @select="handleSelect"
          ref="multipleTable"
          @select-all="handleSelectAll"
          style="width: 100%; text-align: center">
        <el-table-column type="selection" width="55" ></el-table-column>
        <el-table-column prop="over" label="缩略图" />
        <el-table-column prop="name" label="节目名称"/>
        <el-table-column prop="resolutionId" label="分辨率"/>
        <el-table-column prop="time" label="节目时长"/>
        <el-table-column prop="size" label="节目大小"/>
      </el-table>
    </div>


      <template #footer>
    <span class="dialog-footer">
      <el-button @click="dialogVisible2 = false">取消</el-button>
      <el-button type="primary" @click="addProgram">确认</el-button>
    </span>
      </template>
    </el-dialog>


  <el-dialog v-model="dialogVisible3" title="选择设备" width="70%">
    <div style="padding: 10px 100px 10px;" class="inputDeep">
      <label style="color: black">设备名称：</label>
      <el-input style="width: 200px"
                v-model="equipmentName"
                placeholder="请输入设备名称"
                class="input-with-select"
      >
        <template #append>
          <el-button :icon="search" />
        </template>
      </el-input>
      <label style="color: black;padding-left:100px">所属机构：</label>
      <el-select v-model="institutions" clearable placeholder=" ">
        <el-option
            value="城院罗老师测试"
        />
      </el-select>
      <el-button type="primary" @click="add" style="margin-left: 1050px;background-color:white;color: black">重置</el-button>
      <el-button type="primary" @click="load">查询</el-button>
    </div>
    <div class="aaa">
      <el-table
          :data="tableData2"
          @select="handleSelect2"
          ref="multipleTable2"
          @select-all="handleSelectAll2"
          stripe
          style="width: 100%; text-align: center">
        <el-table-column type="selection" width="55" ></el-table-column>
        <el-table-column prop="equipmentName" label="设备名称"/>
        <el-table-column prop="institutionsId" label="所属机构"/>
        <el-table-column prop="groupId" label="所属分组"/>
        <el-table-column prop="address" label="MAC地址"/>
        <el-table-column prop="resolutionId" label="分辨率"/>
        <el-table-column prop="equipmentState" label="设备状态"/>
        <el-table-column prop="currentPlan" label="当前计划"/>

      </el-table>
    </div>
    <span style="padding-top:50px;margin-left: 1150px">
      <el-button @click="dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="addEquipment">确认</el-button>
    </span>
  </el-dialog>
</template>

<script>
import {ref} from "vue";
import request from "@/utils/request";

export default {
  data() {
    return {
      tableData:[],
      tableData2:[],
      planName: '',
      radio1: '1',
      radio2: '1',
      radio3: '1',
      value1: '',
      value2: '',
      dialogVisible:false,
      dialogVisible1:false,
      dialogVisible2:false,
      dialogVisible3:false,
      userState: '',
      name: '',
      equipmentName:'',
      resolutionId: '',
      institutions:'',
      groupId:1,
      address:'',
      resolution:'',
      equipmentState:'',
      state:1,
      system_update:'',
      currentPlan:'',
      time:'',
      form:{},
      stateArr:[],
      equipmentArr:[]
    }
  },
  created() {
    this.load()
    this.load2()
  },
  methods: {
    load2(){
      request.get("/equipment", {
        params:{
          pageNum:this.currentPage4,
          pageSize:this.pageSize4,
          name:this.name,
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
        this.tableData2 = res.data.records
        this.total = res.data.total
      })
    },
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
        console.log(res.data.records)
        this.tableData = res.data.records

      })
    },
    save(){
      this.updateTime = ""
      this.over = "yun.jpg"
      this.form.name=this.planName
      this.form.stateId = this.state
      this.form.model = parseInt(this.radio2)
      this.form.date = this.value1[0].getFullYear()+"."+this.value1[0].getMonth()+"."+this.value1[0].getDate()+" "+this.value2[0].getHours()+':'+this.value2[0].getMinutes()
      this.form.userId = this.$cookies.get("data").adminName
      this.form.reviewId = this.$cookies.get("data").adminName
      if(this.radio3==="1"){
        this.form.synchronous = true
      }
      if(this.radio3==="0"){
        this.form.synchronous = false
      }
      this.form.programId = this.stateArr[0].id
      this.form.equipmentId = this.equipmentArr[0].equipmentId
      console.log(this.form)
      if(this.form.programId){
        request.post("/plan",this.form).then(res => {
          console.log(res)
          if(res.code === '200'){
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
    },
    back(){
      this.$router.push("/adminPlanList")
    },
    next(){
      this.dialogVisible3=true
      this.load2()
    },
    addProgram() {
      let times = document.getElementById("program")
      times.style.display="block"
      this.dialogVisible2 = false


    },
    addEquipment(){
      this.dialogVisible3 = false
    },
    handleSelect(selection, row) {
      this.stateArr[0] = row
      console.log(this.stateArr[0].id)
    },
    handleSelect2(selection, row) {
      this.equipmentArr[0] = row
      console.log(this.equipmentArr[0].equipmentId)
    },
     add(){
      this.dialogVisible = true
      this.form = {}
       console.log(this.value1)
    },
    del(){
      this.dialogVisible1 = true
      this.form = {}
    },
    addjie(){
      this.dialogVisible2 = true
      this.form = {}
    },
    addTime(value,value1){
       console.log( value,value1)
      console.log(value1[0].getMonth())
       let times = document.getElementById("times")
       times.style.display="block"
      this.dialogVisible = false
      this.time=value1[0].getFullYear()+"."+value1[0].getMonth()+"."+value1[0].getDate()+" "+value[0].getHours()+':'+value[0].getMinutes()+'至'+
      value1[1].getFullYear()+"."+value1[1].getMonth()+"."+value1[1].getDate()+" "+value[1].getHours()+':'+value[1].getMinutes()
       console.log(this.time)
    }


  }
}
</script>

<style lang="less" scoped>
  .home{
    height: 900px;
    width:900px;
    .top{
        width:850px;
        height: 350px;
        .jihua{
            display: flex;

        }
    }
    .mid{
        width:100%;
        height:400px;
        display: flex;
         .left{
              width:300px;
              height:400px;
              border: 1px solid #ccc;
              #times{
                display: none;
                width:260px;
                height:120px;
                border: 1px solid #ccc;
                .xunhuan{
                    display: flex;
                }
              }

              .tianjiatime{
                    display: flex;
                    width:260px;
                    height:120px;
                   border: 2px dashed #ccc;
                   .add{
                      margin: auto;
                     display: flex;
                     justify-content: center;
                     align-items: center;
                     .addTime{
                       margin-left: 5px;
                     }
                   }
              }

         }
         .right{
              width:1200px;
              height:400px;
              border: 1px solid #ccc;
              display: flex;
              justify-content: center;
              align-items: center;
              #program{
                display: none;
                width:200px;
                height: 300px;
                border: 1px dashed #ccc;

                img{
                  width: 80%;
                  height: 80%;
                  margin-left: 20px;
                }
                .content{
                  .programName{
                    color: white;
                    margin-left: 20px;
                    margin-top: 5px;

                  }
                  .res{
                    color: white;
                    margin-left: 20px;
                  }
                  .time{
                    color: white;
                    margin-left: 10px;
                  }

                }
              }
             .tianjiajiemu{

                    width:200px;
                    height: 300px;
                   border: 2px dashed #ccc;
                   .add1{
                     margin-top: 90px;
                    margin-left: 90px;
                   }
                   .add2{
                      margin-left: 70px;

                   }

             }
         }
    }
    .button{
      margin-top: 50px;
      margin-left: 700px;
      width: 300px;
      height: 50px;
      display: flex;
      justify-content: space-between;
    }
  }
</style>
