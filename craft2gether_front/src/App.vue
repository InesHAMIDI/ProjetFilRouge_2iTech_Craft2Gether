<template>
  <UserComponent />
</template>

<script>
import UserComponent from '@/components/User.vue'
export default {
  name: 'App',
  components: {
    UserComponent
  }
}
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
