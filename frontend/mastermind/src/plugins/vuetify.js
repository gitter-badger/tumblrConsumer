import Vue from 'vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import theme from './theme'

Vue.use(Vuetify, {
    iconfont: 'mdi',
    theme
  });
const opts = {iconfont: 'mdi',
                theme};
export default new Vuetify(opts);