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
}

button {
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
  font-size: 35px;
}

p{
  font-size: 17px;
}
</style>
