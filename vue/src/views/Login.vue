<template>
  <div v-wechat-title="$route.meta.title"  style="width: 100%; height: 100vh; overflow: hidden" class="background">
    <div class="left">
      <span class="banQ">Copyright © 2022 ZUCC移动应用开发实践第14组 版权所有</span>
    </div>
    <div class="right">
      <div class="title">
        <div class="hello">Hello!</div>
        <span class="login">欢迎登录</span>
        <span class="system">BOE商用画屏发布系统</span>
      </div>
      <el-form ref="form" :model="form" size="large" :rules="rules" >
        <!--        输入用户名-->
        <el-form-item prop="username" >
          <el-input  v-model="form.username" class="username">
            <template #prefix>
              <el-icon class="el-input__icon"><user-filled /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <!--        输入密码-->
        <el-form-item prop="password" class="password">
          <el-input v-model="form.password" show-password>
            <template #prefix>
              <el-icon class="el-input__icon"><lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <!--        登录按钮-->
        <el-form-item>
          <el-button style="width: 50%; color: dodgerblue; margin: 0 auto; font-size: 16px" @click="login">登录</el-button>
        </el-form-item>



      </el-form>
    </div>

  </div>
</template>

<script>
import {UserFilled,Lock} from "@element-plus/icons";

import request from "@/utils/request";
import { ref } from 'vue'
export default {
  name: "Login",
  components: {
    UserFilled,
    Lock
  },
  data(){
    return{
      form:{},
      value:ref('') ,

    }
  },
  methods: {
    login(){
      //只有输入才会发出登录请求
      this.$refs['form'].validate((valid) => {
        if (valid) {
          //管理员登录
            request.post("/admin/login", this.form).then(res => {
              if (res.code === 200) {
                this.$message({
                  type: "success",
                  message: "登录成功"
                })
                console.log(res.data)
                this.$cookies.set("data",res.data)
                this.$cookies.set("name","商用画屏发布系统")
                this.$router.push("/admin")//登录成功之后进行页面跳转，跳转到主页
              } else {
                this.$message({
                  type: "error",
                  message: res.msg
                })
              }
            })
        }
      })
    },
    register(){
      this.$router.push("/register")//登录成功之后进行页面跳转，跳转到主页
    }
  }
}
</script>

<style lang="less"  scoped>
.background{
  width:100%;
  height:100%;  /**宽高100%是为了图片铺满屏幕 */

  z-index:-1;
  position: absolute;
  display: flex;
  .left{
    width: 1000px;
    background: url('../assets/img/loginBackground.png');
    margin-bottom: 50px;
    .banQ{
      color: #ccc;
      position: absolute;
      top: 870px;
      left: 250px;
      font-size: 18px;
    }
  }
  .right{
    width: 300px;
    margin: 200px auto;
    .title{
      color: black;
      text-align: left;
      padding: 20px 0;
      .hello{
        font-size: 50px;
        font-weight: 900;
        padding: 20px 0
      }
      .login{
        font-size: 20px;
        color: grey;
      }
      .system{
        font-size: 20px;
        color: dodgerblue;
      }
    }


  }
}
</style>
