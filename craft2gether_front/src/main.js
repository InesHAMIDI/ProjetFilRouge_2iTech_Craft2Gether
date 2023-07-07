import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import {axiosInterceptor} from '@/interceptors/axios-request-interceptor'
import { createPinia } from 'pinia'
import { useConnection } from '@/connexion/CheckConnection'

const app = createApp(App);
const pinia = createPinia()
axios.interceptors.request.use(axiosInterceptor)
app.config.globalProperties.baseUrl = "http://localhost:3000/ctg"
app.use(pinia)

 router.beforeEach((to, from, next) => {
    const auth = useConnection()
   if (to.name !== 'auth' && !auth.isConnected) next({ name: 'auth' })
   else next() })

app.use(router).use(VueAxios, axios).mount('#app');
app.provide('global', 'injections')

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap.bundle.min.js"
import "@fortawesome/fontawesome-free/css/all.css"
