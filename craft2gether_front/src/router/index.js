import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ListeDetailsView from '../views/ListeDetailsView.vue'
import BlocDetailView from '../views/BlocDetailView.vue'


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    alias:["/listes", "/accueil", "/home"]
  },

  {
    path: '/listes/:id',
    name: 'liste-details',
    component: ListeDetailsView
  },

  {
    path: '/blocs/:id',
    name: 'bloc-details',
    component: BlocDetailView
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
