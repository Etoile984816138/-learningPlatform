// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from 'entry/player/App'
// import router from './router'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import header from 'commonComp/header/header'
import MuseUI from 'muse-ui'
import 'muse-ui/dist/muse-ui.css'
import 'muse-ui/dist/theme-teal.css'
import VueAwesomeSwiper from 'vue-awesome-swiper'

import audio from 'entry/player/audio'
import page from 'entry/player/page'
import video from 'entry/player/video'

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
    { path: '/audio/:id', component: audio },
    { path: '/page/:id', component: page },
    { path: '/video/:id', component: video }
  ],
  linkActiveClass: 'active'
})

// 挂载到#app上
const app = new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})


