<template>
  <div class="home">
    <ListeComponent />
  </div>
</template>

<script>

import ListeComponent from '@/components/ListeComponent.vue'

export default {
  name: 'HomeView',
  components: {

    ListeComponent
  }
}
</script>

<style scoped>
.home{
  text-align: center;
}
</style>
