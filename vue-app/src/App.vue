<template>
  <div>
    <v-app v-if="showError">
      <Error/>
    </v-app>
    <v-app v-else>
      <SnackBar/>
      <Nav/>
      <Router-view/>
      <Footer/>
    </v-app>
  </div>
</template>

<script>
import SnackBar from "./components/snackBar"
import Error from "./views/Error"
import Nav from "./components/navBar"
import Footer from "./components/footer"
export default {
  name: "App",
  components: {
    Error,
    Nav,
    Footer,
    SnackBar
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
