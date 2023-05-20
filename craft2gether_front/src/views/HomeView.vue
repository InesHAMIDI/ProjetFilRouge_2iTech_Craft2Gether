<template>
  <div class="home">
    <HeaderComponent />
    <ListeComponent />
    <FooterComponent />
  </div>
</template>

<script>
import HeaderComponent from '@/components/HeaderComponent.vue'
import FooterComponent from '@/components/FooterComponent.vue'
import ListeComponent from '@/components/ListeComponent.vue'

export default {
  name: 'HomeView',
  components: {
    HeaderComponent,
    FooterComponent,
    ListeComponent
  }
}
</script>
