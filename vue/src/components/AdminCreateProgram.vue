<template>
    <el-container>
      <el-header style="background: #292d3e">
        <el-button style="margin-left: -20px;margin-top: 14px;width: 140px;border: none;background: none;color: white;font-size: 16px;"
            @click="exit">退出编辑</el-button>
      </el-header>
      <el-container>
        <el-aside width="200px">
        <el-button style="margin-top:40px"
            class="el-icon-plus btn_style"
            @click="addTextHandle('textbox', 'add')"
        > 文本框</el-button>
        <el-button type="primary" class="el-icon-plus btn_style" @click="downLoadImage1" style="margin-top:40px"
          >预 览</el-button>
        <el-button type="primary" class="el-icon-plus btn_style" @click="downLoad"
          >下 载</el-button>
        <el-button type="danger" class="el-icon-plus btn_style" @click="resetCanvas"
          >重 置</el-button>
        <el-button class="el-icon-plus btn_style" @click="imgDraw">
        <input type="file" accept="image/*"
            style="display:none"
            id="uploadfile"
            @change="uploadFile" />上 传</el-button>
        <el-button class="el-icon-edit btn_style"
            @click="initD">绘 制</el-button>
        <el-button
        class="btn-delete btn_style el-icon-delete"
        @click="deleteText">删 除</el-button>
          <el-button
              class="btn-delete btn_style el-icon-delete"
              @click="getMaterial">素材</el-button>
      </el-aside>
      <el-main>
        <div class="content-show">
          <div class="canvas">
            <canvas ref="canvas" id="editorCanvas"></canvas>
          </div>
        </div>
      </el-main>
        <el-aside :width="isCollapsed ? '20px' : '258px'">
        <div class="text-edit">
          <div
            @click="closeSetting"
            class="title-setting"
            :tipTitle="isCollapsed ? '展开设置' : '收起设置'"
          >
            <span class="text-setting">{{title}}</span>
            <i :class="isCollapsed ? 'el-icon-s-unfold' : 'el-icon-close'"/>
          </div>
          <el-form ref="form" >
            <el-form-item label="文本内容：">
              <div id="textBox"  style="width: 100% ">
                <el-input type="textarea"
                    @input="changeText"
                    @focus="changeText"
                    id="in" ref="in"
                    v-model="msg" ></el-input>
              </div>
            </el-form-item>
            <el-form-item label="字体：" label-width="82px">
              <el-select  v-model="fontFamilies.value"
                  placeholder="请选择字体"
                  @change="changeFontFamily">
                <el-option
                  v-for="item in fontFamilies"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="字体颜色：" >
              <el-select v-model="fontColor.value"
              placeholder="请选择字体颜色"
                  @change="changeFontColor"
              >
                <el-option
                  v-for="item in fontColor"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="字体粗细：" >
              <el-select v-model="fontWeight.value"
              placeholder="请选择字体粗细"
                  @change="changefontWeight"
              >
                <el-option
                  v-for="item in fontWeight"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="字体风格：" >
              <el-select v-model="fontStyle.value"
              placeholder="请选择字体风格"
                  @change="changeFontStyle">
                <el-option
                  v-for="item in fontStyle"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="字体大小：" >
              <el-select v-model="fontSizes.value"
              placeholder="选择字体大小"
                  @change="changeFontSize">
                <el-option
                  v-for="item in fontSizes"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="对齐方式：" >
              <el-select v-model="textAlign.value"
                  @change="changeTextAlign"
                  placeholder="选择对齐方式">
                <el-option
                  v-for="item in textAlign"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="背景颜色：" >
              <el-color-picker
                class="color-picker"
                v-model="bgcolor" circle
                size="small"
                show-alpha
                :predefine="predefineColors"
                @change="changeBgColor"
                color-format="hex"
              ></el-color-picker>
            </el-form-item>
          </el-form>
        </div>
      </el-aside>
      </el-container>
    </el-container>
  <div class="material">
    <el-dialog v-model="dialogVisible2" class="color" width="90%"  title="选择素材">

      <div class="item" v-for="(item,index) in tableData" :key="index">
        <div  :class="{'bg':item.displayed}">
          <el-checkbox :style=item.myStyle class="check" v-model="item.checked"></el-checkbox>
        </div>

        <div class="content"  @click="clickItem(index)" @dblclick="goOn(index)">
          <img :src="require('../assets/img/'+item.data)" class="fileImg">
          <div class="title">
            {{item.materialName}}

          </div>
        </div>

      </div>
    </el-dialog>
  </div>
    <div class="dialog">
      <el-dialog v-model="dialogVisible" width="90%" fullscreen center>
         <img :src="imageBase64" alt="" style="margin-left: 240px">
      </el-dialog>
    </div>
</template>
<script>
  import {
    fontFamilies,
    fontSizes,
    fontColor,
    fontStyle,
    fontWeight,
    backgroundColor,
    textAlign,
  } from "@/utils/fontData";

  import { fabric } from "fabric";
  let editorCanvas = "";

  fabric.Object.prototype.set({
    cornerStrokeColor: "#66b0ef",
    cornerColor: "#60abec",
    cornerStyle: "rectangele",
    cornerSize: 8,
    borderScaleFactor: 2,
    transparentCorners: false,
    borderColor: "#61abe8",
  });

  export default {
    name: "Editor",
    data() {
      return {
        file:'',
        url: 'XXXXXXXX', // base64编码
        imgUrl: '',
        dialogVisible2:false,
        isHide: true,
        checkAll: false,
        isChecked:false,
        isIndeterminate: true,
        fontFamilies,
        fontSizes,
        fontColor,
        fontStyle,
        fontWeight,
        textAlign,
        backgroundColor,
        predefineColors: ["#ffffff", "#FF0000", "#000000","#FFF800","#00FF0A","#FD00FF","#0095FF"],
        checked: true,
        // 模板图片保存数组
        templateImgs: [],
        zoomCounter: 100,
        tipTitle: "",
        imageBase64: "",
        isCollapsed: false,
        backColor: "#eec5c5",
        backGroundStatus: false,
        bgcolor: "#ff0000",
        itemWidth: "230px",
        done: false,
        dialogVisible: false,
        // 文本控件属性
        textStyleData: {
          type: "editText",
          text: "双击编辑文字",
          top: 50,
          left: 50,
          width: 100,
          opacity: 1,
          stroke: "#ffffff",
          strokeWidth: 0,
          textAlign: "left",
          lineHeight: 1,
          charSpacing: 1,
          fontFamily: "hyzktjjkt",
          fontSize: 40,
          fontWeight: "normal",
          fontStyle: "normal",
          fill: "#000000",
          textBackgroundColor: "rgba(0,0,0,0)",
          selectable: true,
        },
        isOpen: false,
        isMove: false,
        src: "",
        msg: "",
        canvas: null,
        templatesTitle: "H5 可视化编辑",
        title: "文本设置",
        showTitle:"100% 预览",
        templateData: {},
        mouseFrom: {},
        mouseTo: {},
        moveCount: 1,
      };
    },
    mounted() {
      this.initeditorCanvas();
    },
    methods: {
      getMaterial(){
        this.dialogVisible2 = true
      },
      downLoadImage1() {
        this.dialogVisible = true
        this.done = true
        let base64URl = editorCanvas.toDataURL({
          formart: 'png',
          multiplier: 1
        })
        this.imageBase64 = base64URl
        this.done = false
      },

      exit(){
        this.$router.push({ path: '/adminReleaseProgram' })
        this.$nextTick()
      },

      saveTemplates() {
        console.log("你点击了模板保存");
        let base64URl = editorCanvas.toDataURL({
          formart: "jpg",
          multiplier: 1,
        });
      },

      addTemplates() {
        console.log("添加模板");
      },

      initD() {
        // 监听鼠标按下
        const obj = editorCanvas.getActiveObject();
        editorCanvas.on("mouse:down", (options) => {
          // 记录当前鼠标的起点坐标
          if(!obj) {
            this.mouseFrom.x = options.e.clientX - editorCanvas._offset.left;
            this.mouseFrom.y = options.e.clientY - editorCanvas._offset.top;
          }
        });
        // 监听鼠标移动
        editorCanvas.on("mouse:move", (options) => {
          // 记录当前鼠标移动终点坐标
          if (!obj) {
            this.mouseTo.x = options.e.clientX - editorCanvas._offset.left
            this.mouseTo.y = options.e.clientY - editorCanvas._offset.top
            this.drawRect();
          }
        });
        editorCanvas.on("mouse:up", (options) => {
          if (!obj) {
            this.mouseFrom.x = options.e.clientX - editorCanvas._offset.left;
            this.mouseFrom.y = options.e.clientY - editorCanvas._offset.top;
            this.doDrawing = false;
            this.canvasObject = null;
            this.mouseFrom = {};
            this.mouseTo = {}
          }
        });
        editorCanvas.on("selection:created",(option) => {
          if (option) {
            this.doDrawing = false;
          }
        })
      },
      getTransformedPosX(x) {
        let zoom = Number(editorCanvas.getZoom())
        return (x - editorCanvas.viewportTransform[4]) / zoom;
      },
      getTransformedPosY(y) {
        let zoom = Number(editorCanvas.getZoom())
        return (y - editorCanvas.viewportTransform[5]) / zoom;
      },
      // 绘制矩形
      drawRect() {
        // 计算矩形长宽
        let left = this.getTransformedPosX(this.mouseFrom.x);
        let top = this.getTransformedPosY(this.mouseFrom.y);
        let width = this.mouseTo.x - this.mouseFrom.x;
        let height = this.mouseTo.y - this.mouseFrom.y;
        const canvasObject = new fabric.Rect({
          left: left,
          top: top,
          width: width,
          height: height,
          fill: "#d70202",
          strokeWidth: 2,
        });
        editorCanvas.add(canvasObject);
      },

      // 初始化模板编辑画布
      initeditorCanvas() {
        editorCanvas = new fabric.Canvas("editorCanvas", {
          devicePixelRatio: true,
          width: "1400",
          height: "800",
          originX: "center",
          originY: "center",
          backgroundColor: "#232738",
          transparentCorners: false,
        });
        editorCanvas.preserveObjectStacking = true;
      },

      // 收起文本设置
      closeSetting() {
        this.isCollapsed = !this.isCollapsed;
      },
      // 载入图片
      imgDraw() {
        document.getElementById("uploadfile").click();
      },
      uploadFile(e) {
        editorCanvas.isDrawingMode = false;
        let file = e.target.files[0];
        let reader = new FileReader();
        reader.onload = (e) => {
          let data = e.target.result;
          fabric.Image.fromURL(data, (img) => {
            editorCanvas.add(img).renderAll();
          });
        };
        reader.readAsDataURL(file);
        e.target.value = "";
      },
      base64ImgtoFile (dataurl, filename = 'file') {
        const arr = dataurl.split(',')
        const mime = arr[0].match(/:(.*?);/)[1]
        const suffix = mime.split('/')[1]
        const bstr = atob(arr[1])
        let n = bstr.length
        const u8arr = new Uint8Array(n)
        while (n--) {
          u8arr[n] = bstr.charCodeAt(n)
        }
        return new File([u8arr], `${filename}.${suffix}`, {
          type: mime
        })
      },

      // 下载图片
      downLoad() {
        this.done = true;
        const dataURL = editorCanvas.toDataURL({
          width: editorCanvas.width,
          height: editorCanvas.height,
          left: 0,
          top: 0,
          format: "png",
        });
        const link = document.createElement("a");
        link.download = "图片.png";
        link.href = dataURL;
        console.log(dataURL);
        this.url = dataURL;
        let file = new File([], "", undefined)
        file = this.base64ImgtoFile(this.url)
        this.imgUrl = window.webkitURL.createObjectURL(file) || window.URL.createObjectURL(file) // imgUrl图片网络路径
        console.log(this.imgUrl);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
      },

      // 清空画布
      resetCanvas() {
        let children = editorCanvas.getObjects();
        if (children.length > 0) {
          editorCanvas.remove(...children);
        }
        editorCanvas.setBackgroundColor("#fff");
      },
      // 缩放
      zoomIt(factor) {
        let zoomCounter = this.zoomCounter;
        let cWidth = editorCanvas.width;
        let cHeight = editorCanvas.height;

        /* 同步缩小 */
        if (factor < 1 && zoomCounter > 0) {
          this.zoomCounter -= 20;
          editorCanvas.setWidth(cWidth * factor);
          editorCanvas.setHeight(cHeight * factor);

          const objects = editorCanvas.getObjects();
          for (let i in objects) {
            let scaleX = objects[i].scaleX;
            let scaleY = objects[i].scaleY;
            let left = objects[i].left;
            let top = objects[i].top;
            let tempScaleX = scaleX * factor;
            let tempScaleY = scaleY * factor;
            let tempLeft = left * factor;
            let tempTop = top * factor;
            objects[i].scaleX = tempScaleX;
            objects[i].scaleY = tempScaleY;
            objects[i].left = tempLeft;
            objects[i].top = tempTop;
            objects[i].setCoords();
            let zoomPoint = new fabric.Point(
              editorCanvas.width / 2,
              editorCanvas.height / 2
            );
            editorCanvas.zoomToPoint(zoomPoint, factor);
            editorCanvas.renderAll();
            editorCanvas.calcOffset();
          }
        }

        /* 同步放大 */
        if (factor > 1 && zoomCounter < 100) {
          this.zoomCounter += 20;
          editorCanvas.setWidth(cWidth * factor);
          editorCanvas.setHeight(cHeight * factor);
          const objects = editorCanvas.getObjects();
          for (let i in objects) {
            let scaleX = objects[i].scaleX;
            let scaleY = objects[i].scaleY;
            let left = objects[i].left;
            let top = objects[i].top;
            let tempScaleX = scaleX * factor;
            let tempScaleY = scaleY * factor;
            let tempLeft = left * factor;
            let tempTop = top * factor;
            objects[i].scaleX = tempScaleX;
            objects[i].scaleY = tempScaleY;
            objects[i].left = tempLeft;
            objects[i].top = tempTop;
            objects[i].setCoords();
          }
          let zoomPoint = new fabric.Point(
            editorCanvas.width / 2,
            editorCanvas.height / 2
          );
          editorCanvas.zoomToPoint(zoomPoint, factor);
          editorCanvas.renderAll();
          editorCanvas.calcOffset();
        } else {
          return;
        }
      },

      // 删除当前鼠标活动控件
      deleteText() {
        const obj = editorCanvas.getActiveObject();
        if (obj) {
          editorCanvas.remove(obj);
        }
        editorCanvas.renderAll();
      },

      // 更改字体大小
      changeFontSize(value) {
        let mfontSize = value;
        const obj = editorCanvas.getActiveObject();
        if (obj) {
          obj.set({
            fontSize: mfontSize,
          });
          editorCanvas.renderAll();
        }
        this.templateData.fontSize = mfontSize;
      },

      // 背景颜色
      changeBgColor(value) {
        let mbgColor = value;
        const obj = editorCanvas;
        if (obj) {
          obj.set({
            backgroundColor: mbgColor,
          });
          editorCanvas.renderAll();
        }
        this.templateData.bgColor = mbgColor;
      },

      // 字体颜色
      changeFontColor(value) {
        let mfontColor = value;
        const obj = editorCanvas.getActiveObject();
        if (obj) {
          obj.set({
            fill: mfontColor,
          });
          editorCanvas.renderAll();
        }
        this.templateData.textColor = mfontColor;
      },

      // 对齐方式
      changeTextAlign(value) {
        let mtextAlign = value;
        const obj = editorCanvas.getActiveObject();
        if (obj) {
          obj.set("textAlign", mtextAlign);
          obj.centerH();
          editorCanvas.renderAll();
        }
        this.templateData.horizontalAlign = mtextAlign;
      },

      // 字体粗细
      changefontWeight(value) {
        let mfontWeight = value;
        const obj = editorCanvas.getActiveObject();
        if (obj) {
          obj.set("fontWeight", mfontWeight);
          editorCanvas.renderAll();
        }
        this.templateData.fontWeight = mfontWeight;
      },

      // 字体风格
      changeFontStyle(value) {
        let mfontStyle = value;
        const obj = editorCanvas.getActiveObject();
        if (obj) {
          obj.set("fontStyle", mfontStyle);
          editorCanvas.renderAll();
        }
        this.templateData.fontStyle = mfontStyle;
      },

      // 字体
      changeFontFamily(value) {
        let mfontFamily = value;
        const obj = editorCanvas.getActiveObject();
        if (obj) {
          obj.set("fontFamily", mfontFamily);
          editorCanvas.renderAll();
        }
        this.templateData.fontFamily = mfontFamily;
      },

      // 设置模板画布尺寸
      chooseTemplateSize(value) {
        let mtemplateSzie = value;
        console.log(mtemplateSzie);
        let mwidth = mtemplateSzie.width;
        let mheight = mtemplateSzie.height;
        editorCanvas.setHeight(mheight);
        editorCanvas.setWidth(mwidth);
        editorCanvas.renderAll();
        this.templateData.screenSize = mtemplateSzie;
        this.templateData.screenHeight = mheight;
        this.templateData.screenWidth = mwidth;
      },

      // 文本域输入监听
      changeText() {
        const obj = editorCanvas.getActiveObject();
        let inputText = this.msg;
        if (inputText != null) {
          if (obj) {
            obj.set("text", inputText);
            editorCanvas.renderAll();
          }
        }
        this.templateData.text = inputText;
      },

      //添加文本框
      addTextHandle(actions, action) {
        switch (actions) {
          case "textbox":
            this.addTextObjectHandle(action);
            break;
          case "image":
            this.addImageObjectHandle(action);
            break;
        }
      },

      addTextObjectHandle(action) {
        let textBox;
        let currentOptionCss;
        if (action == "add") {
          currentOptionCss = this.textStyleData;
          textBox = new fabric.Textbox(currentOptionCss.text || "", currentOptionCss);
          editorCanvas.add(textBox).setActiveObject(textBox);
        }
      },

      // 图片预览
      downLoadImage() {
        let base64URl = editorCanvas.toDataURL({
          formart: "png",
          multiplier: 1,
        });
        this.imageBase64 = base64URl;
        this.done = false;
      },
    },

    components: {},
    created() {},
  };
</script>

<style scoped>
  * {
    margin: 0;
    padding: 0;
  }
  body {
    font-family: 'PingFang SC', "Helvetica Neue", Helvetica, "microsoft yahei", arial, STHeiTi, sans-serif;
  }

  a {
    text-decoration: none
  }

  .el-aside {
    background-color: #232738;
    color: #333;
    text-align: center;
  }

  .el-main {
    background-color: #333;
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  .el-main .content-show {
    display: flex;
    flex-direction: column;
  }

  .text-edit h6 {
    height: 40px;
    color: black;
  }

  .text-edit .el-form {
    margin: 15px;
  }


  .btn-delete {
    min-height: 36px;
  }

  .template-content {
    padding: 0px;
    margin: 0px;
  }

  ton-box:hover {
    color: white;
    opacity: 1;
  }

  .templateContent {
    width: 100%;
    padding: 0px;
    background-color: #fff;
  }

  .template-upload {
    width: 256px;
    height: 130px;
    border: 2px solid #adadad;
    margin: 10px auto;
    line-height: 130px;
    text-align: center;
    cursor: pointer;
  }
  .template-img {
    width: 300px;
    height: 130px;
    margin: 0px auto;
    line-height: 130px;
    text-align: center;
    cursor: pointer;
  }

  #editorCanvas {
    width: 100%;
    display: block;
    margin: 15px auto;
    height: 100%;
  }

  .text-setting {
    text-align: center;
    width: 80px;
    font-size: 18px;
    font-weight: 500;
    margin-right:170px;
  }

  .text-edit h4,
  .side-right h4,
  .show h1 {
    text-align: center;
    font-size: 16px;
    color: #585858;
    font-weight: normal;
    margin: 15px auto;
  }

  .show h1 {
    text-align: center;
    color: #585858;
    padding: 15px;
    font-weight: bold;
    font-size: 24px;
    margin: 15px auto;
  }

  .show .show-img .imgBase64 {
    margin: 30px auto 0px auto;
    box-shadow: 0 0 25px #cac6c6;
  }

  .title-setting {
    text-align: center;
    font-size: 16px;
    margin: 20px auto;
    color: white;
  }

  .content-show {
    position: relative;
    height: 947px;
    width: 1000px;
  }

  .content-show .canvas {
    position: absolute;
    top: 1%;
    left: 1%;
  }

  .content-show .canvas #editorCanvas {
    margin: 20px 20px 20px;
    background-color: #292d3e;
  }

  .btn-zoom {
    display: inline-block;
    line-height: 1;
    height: 40px;
    width: 104px;
    white-space: nowrap;
    -webkit-appearance: none;
    text-align: center;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    outline: 0;
    margin: 0;
    -webkit-transition: 0.1s;
    transition: 0.1s;
    font-weight: 500;
    font-size: 14px;
    border-radius: 4px;
    padding: 12px 20px;
    color: #fff;
    background-color: #6A60E3;
    border-color: #6A60E3;
  }

  .btn-download,
  .btn-save,
  .btn-load,
  .btn-reset {
    display: inline-block;
    line-height: 1;
    height: 40px;
    width: 104px;
    white-space: nowrap;
    -webkit-appearance: none;
    text-align: center;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    outline: 0;
    margin: 0;
    -webkit-transition: 0.1s;
    transition: 0.1s;
    font-weight: 500;
    padding: 12px 20px;
    font-size: 14px;
    border-radius: 4px;
    color: #fff;
    background-color: #6A60E3;
    margin: 10px 0px 0px 10px;
    border-color: #6A60E3;
  }

  .el-form-item {
    margin: 15px auto;
  }
  .el-form {
    height: 70px;
    line-height: 70px;
  }

  .el-header {
    height: 60px;
  }

  .el-form--inline .el-form-item__content {
    margin: 0;
    padding: 0;
    height: 60px;
  }

  .btn_style {
    display: inline-block;
    line-height: 40px;
    white-space: nowrap;
    -webkit-appearance: none;
    text-align: center;
    font-weight: 500;width: 60px;
    height:60px;
    margin:5px 5px 5px 5px;
    border: none;
    border-radius: 0;
    background-color: gray;
    color: white;
    font-size: 16px;
  }
  .color{
    --el-dialog-bg-color:white
  }


</style>
<style lang="less" scoped>
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
</style>
