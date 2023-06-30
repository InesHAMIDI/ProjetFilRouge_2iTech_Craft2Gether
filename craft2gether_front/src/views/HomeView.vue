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
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
}
</style>
