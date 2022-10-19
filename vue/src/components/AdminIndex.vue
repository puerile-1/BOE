<!-- 考试管理功能介绍 -->
<template>
  <div class="home">
    <div class="sheBeiNum" style="margin: 20px 20px 20px">
      <el-card class="box-card" style="background-color: #292d3e;color: white;border: none;">
        <div class="left">
          <img src="../assets/img/shebei_num.png">
        </div>
        <div class="right">
          <div>设备数量</div>
          <div style="font-size:30px">{{shebeinum}}</div>
        </div>
      </el-card>
      <el-card class="box-card" style="margin-left: 20px;background-color: #292d3e;color: white;border: none;">
        <div class="left">
          <img src="../assets/img/shebei_num.png">
        </div>
        <div class="right">
          <div>节目数量</div>
          <div>{{jiemunum}}</div>
        </div>
      </el-card>
      <el-card class="box-card" style="margin-left: 20px;background-color: #292d3e;color: white;border: none;">
        <div class="left">
          <img src="../assets/img/shebei_num.png">
        </div>
        <div class="right">
          <div>计划数量</div>
          <div>{{jihuanum}}</div>
        </div>
      </el-card>
    </div>
    <div class="shebeizhuangtai" style="margin: 20px 20px 20px;color: white;border: none;">
      <el-card style="background-color: #292d3e;color: white;border: none;">
        <div class="left">
          <div class="zhangtai">设备状态</div>
          <div class="shebei">{{shebeinum}}</div>
          <div class="tai">台</div>
          <img src="../assets/img/lan.png" class="lan">
          <router-link :to="{path:'/adminEquipment'}">
            <div class="test2" style="width:350px;height:200px;" >
         <div id="myChart1" style="width:100%;height:200px;float:left;"></div>
        </div>
          </router-link>

        </div>
      </el-card>
      <el-card class="box-card" style="margin-left: 20px;background-color: #292d3e;color: white;border: none;">
        <div class="sbziyuan">设备资源</div>
          <div class="ziyuan">{{ziyuan}}</div>
          <div class="mb">MB</div>
        <img src="../assets/img/lan.png" class="lan2">

         <router-link :to="{path:'/AdminMaterialManage'}">
            <div class="test2" style="width:350px;height:200px;" >
         <div id="myChart2" style="width:100%;height:200px;float:left;"></div>
        </div>
          </router-link>

      </el-card>
      <el-card class="box" style="margin-left: 20px;background-color: #292d3e;color: white;border: none;">
          <div class="boxbody">
             <div class="top">
              <img src="../assets/img/lan.png" class="lan3">
              <div class="fenbu">设备分布</div>
             </div>
      <router-link :to="{path:'/adminEquipment'}">
      <div class="test2" style="width:600px;height:200px;">
          <div class="echart" id="mychart" :style="myChartStyle"></div>
         </div>
      </router-link>

          </div>

      </el-card>
     </div>
  <div class="jihua" style="margin: 20px 20px 20px;color: white;border: none;">
    <el-card class="shenhe" style="background-color: #292d3e;color: white;border: none;">
    <div class="shenhetop">
      <img src="../assets/img/lan.png">
      <div class="tx">计划审核提醒</div>
      <br>
      <br>
       <router-link :to="{path:'/adminPlanCheck'}">
               <el-table
      :data="tableData"
      stripe
      :header-cell-style="{background:'#232738',color:'white',borderColor:'grey'}"
      :cell-style="{background:'#292d3e',color:'white',borderColor:'grey'}"
      style="width: 100%; text-align: center">
        <el-table-column prop="teacherId" label="序号" sortable/>
        <el-table-column prop="teacherName" label="计划名称"/>
        <el-table-column prop="institute" label="计划状态"/>
        <el-table-column prop="sex" label="提交时间"/>
        <el-table-column prop="tel" label="提交人"/>
        <el-table-column label="操作" width="400"></el-table-column>
        </el-table>
       </router-link>

      </div>

      </el-card>
      <el-card class="shijian"  style="margin-left: 20px;background-color: #292d3e;color: white;border: none;">
        <img src="../assets/img/lan.png">
      <div class="sj">事件记录</div>

      </el-card>
    </div>
  </div>

</template>

<script>
import * as echarts from 'echarts'
export default {
  name: 'test2',
  name: "ExamFunction",
  data () {
    return {
      shebeinum:3,
      jiemunum:2,
      jihuanum:42,
      ziyuan:5.03,
           myChart1: '',
           opinionData1: [
           {"itemStyle":"#grey","name":"离线","value":1},
           {"itemStyle":"#green","name":"播放","value":1},
           {"itemStyle":"#FFFF00","name":"空闲","value":1},
           ],
           myChart2: '',
           opinionData2: [
           {"itemStyle":"#FFFF33","name":"图片","value":1},
           {"itemStyle":"#33FFFF","name":"视频","value":0},
           {"itemStyle":"#B94FFF","name":"音频","value":0},
           ],
        xData: ["罗老师","徐","王"], //横坐标
         yData: [1,2,5], //数据
         myChartStyle: { float: "left", width: "100%", height: "220px" } //图表样式
    }
  },
  mounted: function () {
          this.drawLine();
          this.initEcharts();
       },
       methods: {
        initEcharts() {
      // 基本柱状图
      const option = {
        xAxis: {
          data: this.xData
        },
        yAxis: {},
        series: [
          {
            type: "bar", //形状为柱状图
            data: this.yData
          }
        ]
      };
      const myChart = echarts.init(document.getElementById("mychart"));
      myChart.setOption(option);
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
          async  drawLine () {
           this.myChart1 = echarts.init(document.getElementById('myChart1'))
           this.myChart2 = echarts.init(document.getElementById('myChart2'))
           this.myChart1.setOption({

             grid: { containLabel: true },
             tooltip: {
               trigger: 'item',
               formatter: '{a} <br/>{b} : {d}%'
             },
             color: ['#808080', 'green', '#FFFF00'],
            legend: {
               orient: 'vertical',
               icon: 'circle',
               align: 'left',
               x: 'right',
               y: 'bottom',
               left:'260',
               top:'80',
               textStyle:{
                color: 'white',//字体颜色
                fontSize: 13
           },
               data: ['离线', '播放', '空闲']
             },
             series: [
               {
                 name: '设备状态',
                 type: 'pie',
                 radius: ['50%', '70%'],
                 avoidLabelOverlap: false,
                 center: ['40%', '50%'],
                 itemStyle: {
                   emphasis: {
                     shadowBlur: 10,
                     shadowOffsetX: 0,
                     shadowColor: 'rgba(0, 0, 0, 0.5)'
                   },
                   color: function (params) {
                     // 自定义颜色
                     var colorList = ['#808080', 'green', '#FFFF00']
                     return colorList[params.dataIndex]
                   }
                 },
                 data: this.opinionData1
               }
             ]
           })
           this.myChart2.setOption({

             grid: { containLabel: true },
             tooltip: {
               trigger: 'item',
               formatter: '{a} <br/>{b} : {d}%'
             },
             color: ['#FFFF33', '#33FFFF', '#B94FFF'],
            legend: {
               orient: 'vertical',
               icon: 'circle',
               align: 'left',
               x: 'right',
               y: 'bottom',
               left:'260',
               top:'80',
               textStyle:{
                color: 'white',//字体颜色
                fontSize: 13,//字体大小
           },
               data: ['图片', '视频', '音频']
             },
             series: [
               {
                 name: '设备状态',
                 type: 'pie',
                 radius: ['50%', '70%'],
                 avoidLabelOverlap: false,
                 center: ['40%', '50%'],
                 itemStyle: {
                   emphasis: {
                     shadowBlur: 10,
                     shadowOffsetX: 0,
                     shadowColor: 'rgba(0, 0, 0, 0.5)'
                   },
                   color: function (params) {
                     // 自定义颜色
                     var colorList = ['#FFFF33', '#33FFFF', '#B94FFF']
                     return colorList[params.dataIndex]
                   }
                 },
                 data: this.opinionData2
               }
             ]
           })
         },
     }
}
</script>

<style lang="less" scoped>
.home{
  width: 100px;
  .sheBeiNum{
    width: 95%;

    display: flex;
    .el-card ::v-deep .el-card__body{
      width: 500px;
      height: 100px;
      display: flex;
      align-items: center;
      .left{
        width: 80px;
        img{

          width: 80px;
          height: 80px;
        }
      }
      .right{
        width: 80px;
        margin-left: 30px;
      }
    }

  }
 .shebeizhuangtai{
  width: 95%;
  display: flex;
  .el-card ::v-deep .el-card__body{
      width: 450px;
      height: 300px;
      display: flex;
      align-items: center;
    }
    .shebei{
     position:absolute;
    left: 373px;
    top: 320px;
    font-size: 25px;
 }
 .tai{
  position:absolute;
    left: 370px;
    top: 350px;
    font-size: 23px;
 }
 .zhangtai{
         position:absolute;
         left: 249px;
          top: 220px;
          font-size: 18px;
      }
 .lan{
   position:absolute;
    left: 237px;
    top: 222px;
 }
 .sbziyuan{
   position:absolute;
    left: 719px;
    top: 220px;
    font-size: 18px;
 }
 .lan2{
  position:absolute;
    left: 707px;
    top: 222px;
 }
 .ziyuan{
   position:absolute;
   left: 824px;
    top: 320px;
    font-size: 25px;
 }
 .mb{
  position:absolute;
  left: 834px;
    top: 350px;
    font-size: 20px;
 }
 .box{
   width: 600px;
   height: 300px;
      .boxbody{
        width: 100%;
        height: 300px;
        .top{
        width: 600px;
        height: 50px;
        .fenbu{
          position:absolute;
          left: 1197px;
          top: 220px;
          font-size: 18px;
        }
        .lan3{
           position:absolute;
           left: 1185px;
           top: 222px;
        }
      }

      }
 }
}
   .jihua{
    width: 95%;
     display: flex;
     .shenhe{
        width: 925px;
        height: 300px;
         display: flex;
        .shenhetop{
            width: 925px;
            height: 40px;
        }
        .tx{
            position:absolute;
           left: 252px;
            top: 543px;
            font-size: 18px;
        }
     }
     .shijian{
      width: 600px;
      height: 400px;
      .sj{
        position:absolute;
           left: 1198px;
            top: 543px;
            font-size: 18px;
      }
     }
 }
}
</style>

