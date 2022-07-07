// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store'

Vue.config.devtools = true

import '@/permission'
Vue.config.productionTip = false
const options = {
    namespace: 'pro__',
    name: 'ls',
    storage: 'local'
}

// Vue.config.productionTip = false

Vue.use(router)
Vue.use(ElementUI)

Vue.directive('title', {
    inserted: function (el, binding) {
        document.title = el.dataset.title
    }
})

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App)
})
