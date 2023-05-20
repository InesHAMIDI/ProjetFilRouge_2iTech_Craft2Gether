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
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

button{
   background-color:antiquewhite; 
}

button:hover{
    background-color: #111;
}

.header, .footer{
  background-color: #717171;
  color: #81acff;
}

i {
  color:white;
  font-size: 30px;
}
</style>
