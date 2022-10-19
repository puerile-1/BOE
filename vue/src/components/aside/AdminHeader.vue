<template xmlns:src="http://www.w3.org/1999/xhtml">
  <div style="height: 60px; line-height: 60px; display: flex;background-color: #292d3e">
    <img style="height: 44px;width: 60px;margin-top: 12px;margin-left: 50px;background-color: #fff"  src="../../assets/img/BOE.png">
    <div style="width: 200px; padding-left: 10px;font-size: 20px; font-weight: bold; color: #fff">{{ this.$cookies.get("name") }}</div>
    <div style="flex: 1"></div>
    <div style="width: 100px;text-align: center; padding-top: 15px" >
      <el-dropdown>
      <span class="el-dropdown-link">
        {{ this.$cookies.get("data").adminName }}
        <el-icon class="el-icon--right">
          <arrow-down />
        </el-icon>
      </span>
      <template #dropdown>
        <el-dropdown-menu>
<!--          <el-dropdown-item>个人中心</el-dropdown-item>-->
          <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { ArrowDown } from '@element-plus/icons'
export default {
  name: "AdminHeader",
  components: {
    ArrowDown
  },
  methods:{
    logout(){
      this.$cookies.remove("data")
      this.$router.push("/login")
    }
  }
}
</script>

<style scoped>

</style>
