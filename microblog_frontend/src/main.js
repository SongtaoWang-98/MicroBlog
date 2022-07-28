import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// Vue.use(ElementUI, {size: "small"});

Vue.config.productionTip = false

import axios from 'axios'    //导入axios
axios.defaults.withCredentials = true    //全局设置axios允许携带cookie进行访问
// Vue.prototype.$axios = axios    //设置axios全局标量，之后就可以以this.$axios的形式访问了

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
