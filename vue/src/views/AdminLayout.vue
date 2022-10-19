<template>
  <div style="background-color: #232738;width: 100%">
    <!--    头部-->
    <AdminHeader />
    <!--    主体-->
    <div style="width:100%;display: flex">
      <!--      侧边栏-->
      <AdminAside />
      <!--      内容区域home-->
      <router-view style="flex: 1"/>
    </div>
  </div>
</template>

<script>

import AdminHeader from "@/components/aside/AdminHeader";
import AdminAside from "@/components/aside/AdminAside";

export default {
  name: "AdminLayout",
  components:{
    AdminAside,
    AdminHeader
  }
}
</script>

<style scoped>

</style>
