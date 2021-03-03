<template>
  <div>
    <v-app v-if="showError">
      <Error/>
    </v-app>
    <v-app v-else>
      <Nav/>
      <Router-view/>
      <Footer/>
    </v-app>
  </div>
</template>

<script>
import Error from "./views/Error"
import Nav from "./components/navBar"
import Footer from "./components/footer"
export default {
  name: "App",
  components: {
    Error,
    Nav,
    Footer,
  },
  computed: {
    showError() {
      return this.$route.name == "Error";
    },
  },
};
</script>
<style>
a {
  text-decoration: none;
}
</style>
