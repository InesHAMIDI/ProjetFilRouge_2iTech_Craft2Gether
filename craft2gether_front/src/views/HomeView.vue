<template>
  <div class="home">
    <MenuNav />
    <ListeComponent />
    <FooterComponent />
  </div>
</template>

<script>
import MenuNav from '@/components/MenuNav.vue'
import FooterComponent from '@/components/FooterComponent.vue'
import ListeComponent from '@/components/ListeComponent.vue'

export default {
  name: 'HomeView',
  components: {
    MenuNav,
    FooterComponent,
    ListeComponent
  }
}
</script>
