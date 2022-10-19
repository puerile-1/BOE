<template>
  <div v-wechat-title="$route.meta.title">
    <el-menu
        style="width: 200px; height:calc(100vh - 50px);border: 0px"
        router
        active-text-color="#ffd04b"
        background-color="#292d3e"
        text-color="#fff"
        class="el-menu-vertical-demo">
      <el-menu-item index="adminIndex">
        <template #title>
          <el-icon><House /></el-icon>
          <span>首页概览</span>
        </template>

      </el-menu-item>
      <el-sub-menu index="2">
        <template #title>
          <el-icon><postcard /></el-icon>
          <span>素材管理</span>
        </template>
        <el-menu-item index="AdminMaterialManage">素材列表</el-menu-item>
        </el-sub-menu>
      <el-sub-menu index="3">
        <template #title>
          <el-icon><DataLine /></el-icon>
          <span>节目制作与发布</span>
        </template>
        <el-menu-item index="adminReleaseProgram">发布节目</el-menu-item>
        <el-menu-item index="adminAnnouncement">发布公告</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="4" >
        <template #title>
          <el-icon><tickets /></el-icon>
          <span>计划管理</span>
        </template>
        <el-menu-item index="adminPlanList">计划列表</el-menu-item>
        <el-menu-item index="adminPlanCheck">计划审核</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="5">
        <template #title>
          <el-icon><Odometer /></el-icon>
          <span>设备管理</span>
        </template>
        <el-menu-item index="adminEquipment">设备列表</el-menu-item>
        <el-menu-item index="adminGrouping">分组管理</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="6">
        <template #title>
          <el-icon><user /></el-icon>
          <span>系统管理</span>
        </template>
        <el-menu-item index="AdminManage">账户管理</el-menu-item>
      </el-sub-menu>

    </el-menu>
  </div>
</template>

<script>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
export default {
  name: "adminAside"
}
</script>

<style scoped>

</style>
