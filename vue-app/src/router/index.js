import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '*',
    name: 'Error',
    component: () => import( '../views/Error.vue')
  },
  {
    path: '/',
    name: 'Listagem',
    component: () => import( '../views/Listagem.vue')
  },
  {
    path: '/administrador',
    name: 'Administrador',
    component: () => import( '../views/Administrador.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
