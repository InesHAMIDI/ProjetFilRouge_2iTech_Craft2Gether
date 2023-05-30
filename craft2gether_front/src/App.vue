<template>
  <HeaderComponent />
  <FooterComponent />

  <router-view />
</template>

<script>
import HeaderComponent from '@/components/HeaderComponent.vue'
import FooterComponent from '@/components/FooterComponent.vue'

export default {
  components:{
    HeaderComponent,
    FooterComponent
  }
}

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  align-items: center;
  justify-content: center;
}

.header, .footer{
  background-color: #717171;
  color: #81acff;
}

i {
  color:white;
  font-size: 35px;
}

p{
  font-size: 17px;
}

button{
  background-color: transparent;
  box-shadow: none;
  padding: 0;
  border: none;
  background: none;
}
</style>
