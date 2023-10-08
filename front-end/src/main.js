import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';             //全局引入element
import 'element-ui/lib/theme-chalk/index.css';    //全局引入element的样式
import $ from "jquery";
import 'default-passive-events'
Vue.config.productionTip = false

Vue.use(ElementUI);     //全局注入element
window.jQuery = $;
window.$ = $;
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
