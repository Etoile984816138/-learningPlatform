// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from 'entry/information/App'
// import router from './router'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'

import MuseUI from 'muse-ui'
import 'muse-ui/dist/muse-ui.css'
import 'muse-ui/dist/theme-teal.css'
import VueAwesomeSwiper from 'vue-awesome-swiper'

import myStudy from 'entry/information/myStudy'
import myHistory from 'entry/information/myHistory'
import myNote from 'entry/information/myNote'
import myMsn from 'entry/information/myMsn'
import myCollection from 'entry/information/myCollection'

Vue.use(MuseUI)
Vue.use(VueAwesomeSwiper)

// 路由配置可看官方文档
Vue.use(VueRouter)
Vue.use(VueResource)

Vue.config.productionTip = false

// 配置路由
const router = new VueRouter({
  routes: [
    // 动态路径参数 以冒号开头
    { path: '/study', component: myStudy },
    { path: '/history', component: myHistory },
    { path: '/note', component: myNote },
    { path: '/msg', component: myMsn },
    { path: '/collection', component: myCollection }
  ]
})

// 挂载到#app上
const app = new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})


