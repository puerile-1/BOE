<template>
  <div v-wechat-title="$route.meta.title" class="home">
  </div>
  <div style="margin: 30px 30px 30px;background-color: #292d3e;width: 85%;">
    <div style="display: flex ">
      <div >
        <div id="pmp" style="margin: 30px 30px 30px" >
          <marquee :scrollamount=speed scrolldelay="60"
                   direction= "left" onmouseover="this.stop()"
                   onmouseout="this.start()" :style="[{color:color1},{fontSize:value1*4+'px'},
          {backgroundColor:color2},{lineHeight:value2*4.3+'px'},{paddingTop:position+'px'}]">{{text}}
          </marquee>
        </div>
        <el-input
            v-model="text"
            :autosize="{ minRows: 6}"
            maxlength="300"
            placeholder="请输入公告内容"
            show-word-limit
            type="textarea"
            style="width:840px;margin-left: 30px;background-color: #292d3e;font-size: 16px;"
            ref="getValue"
        />
      </div>
      <div style="margin: 30px 160px 30px;background-color: #232738;width:500px;height: 600px;border-radius: 10px;">
        <div style="margin: 30px 30px 30px;">
          <label style="color:white;padding-right: 10px;">文字颜色:</label>
          <el-color-picker v-model="color1" show-alpha/>
        </div>
        <div class="slider-demo-block" style="width:400px;margin: 30px 30px 30px;">
          <label style="color:white;padding-right: 10px;width:100px;">文字字号:</label>
          <el-slider v-model="value1" show-input size="small"/>
        </div>
        <el-divider />
        <div style="margin: 30px 30px 30px;">
          <label style="color:white;padding-right: 10px;">背景颜色:</label>
          <el-color-picker v-model="color2" show-alpha/>
        </div>
        <div class="slider-demo-block" style="width:400px;margin: 30px 30px 30px;">
          <label style="color:white;padding-right: 10px;width:100px;">背景高度:</label>
          <el-slider v-model="value2" show-input size="small"/>
        </div>
        <el-divider />
        <div class="slider-demo-block" style="width:400px;margin: 30px 30px 30px;">
          <div>
            <label style="color:white;padding-right: 10px;width:160px;">文字位置:</label>
            <el-select v-model="position" style="width:320px">
              <el-option
                  label="上"
                  value="0"
              />
              <el-option
                  label="中"
                  value="160"
              />
              <el-option
                  label="下"
                  value="350"
              />
            </el-select>
            <br>
            <br>
            <label style="color:white;padding-right: 10px;width:160px;">播放速度:</label>
            <el-select v-model="speed" style="width:320px">
              <el-option
                  label="快"
                  value="12"
              />
              <el-option
                  label="中"
                  value="6"
              />
              <el-option
                  label="慢"
                  value="1"
              />
            </el-select>
            <br>
            <br>
            <div class="mb-2 flex items-center text-sm">
              <label style="color:white;padding-right: 10px;width:160px;">播放模式:</label>
              <el-radio-group v-model="radio1" class="ml-4">
                <el-radio label="1" size="large" style="font-size:24px;color:white">时段播放</el-radio>
                <div class="block" v-if="radio1==1">
                  <span class="demonstration">With shortcuts</span>
                  <el-date-picker
                      v-model="value2"
                      type="datetimerange"
                      :shortcuts="shortcuts"
                      range-separator="至"
                      start-placeholder="开始时间"
                      end-placeholder="结束时间"
                  />
                </div>
                <el-radio label="2" size="large" style="font-size:24px;color:white">持续播放</el-radio>
              </el-radio-group>
            </div>
          </div>
        </div>
      </div>

    </div>

    <div class="button" style="display: block">
      <el-button type="primary" @click="back" style="color:black; background-color: white">取消</el-button>
      <el-button type="primary" @click="next" style="color:black; background-color: white">选择设备</el-button>
      <el-button type="primary" @click="publish">发布</el-button>
    </div>

  </div>
</template>

<script>
import request from "@/utils/request";

export default{
  data() {
    return{
      text: '',
      color1: '',
      color2: '',
      value1: '20',
      value2: '20',
      position: "0",
      speed: "12",
      radio1: '2',
      form:{}
    }
  },
  methods: {
    publish(){
      this.form.pubAnnouncementId = 1
      this.form.pubAnnouncementContent = "aaa"
      this.form.pubAnnouncementTextcolor = "green"
      this.form.pubAnnouncementTextsize = 15
      this.form.pubAnnouncementBgcolor = "#ccc"
      this.form.pubAnnouncementBghigh = 15
      request.post("/pubAnnouncement",this.form).then(res => {
        console.log(res)
        if(res.code === 200){
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

  }
}
</script>
<style scoped>
#pmp {
	font-family: "微软雅黑";
	font-size: 40px;
	color: white;
  padding-top: 0px;
	text-decoration: none;
	height: 480px;
	width: 840px;
	border: 25px ridge white;
	background-color: black;
  overflow: hidden;
}
/deep/.el-textarea__inner {
  background-color: #232738;
}
.slider-demo-block {
  display: flex;
  align-items: center;
}
.slider-demo-block .el-slider {
  margin-top: 0;
  margin-left: 12px;
}
.button{
  margin-top: 50px;
  margin-left: 700px;
  width: 300px;
  height: 50px;
  display: flex;
  justify-content: space-between;
}
</style>
