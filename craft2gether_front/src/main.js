import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import {axiosInterceptor} from '@/interceptors/axios-request-interceptor'
import { createPinia } from 'pinia'

const app = createApp(App);
const pinia = createPinia()
axios.interceptors.request.use(axiosInterceptor)
app.config.globalProperties.baseUrl = "http://localhost:3000/ctg"
router.beforeEach((to, from, next) => {
    if (to != 'auth' && (localStorage.getItem('token') == null && localStorage.getItem('token') == undefined) ) {
        router.push({name: 'auth'})
    }
    next()
})
app
app.use(router).use(VueAxios, axios).use(pinia).mount('#app');

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap.bundle.min.js"
import "@fortawesome/fontawesome-free/css/all.css"
