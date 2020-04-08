import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from '@/plugins/vuetify' // path to vuetify export

Vue.config.productionTip = false

import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'
//import Progress from '@/vue-multiple-progress'
//Vue.component('VmProgress', Progress)

new Vue({
  router,
  vuetify,
  render: h => h(App),
}).$mount('#app')
