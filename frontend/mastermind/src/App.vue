<template>
    <component v-bind:is="layout"></component>
</template>

<script>
import AppLayout from './layouts/AdminLayout'
import SimpleLayout from './layouts/SimpleLayout'
export default {
  computed: {
    layout () {
      return 'app-layout'
    }
  },
  components: {
    'app-layout': AppLayout,
    'simple-layout': SimpleLayout
    // define as many layouts you want for the application
  }
}
</script>
