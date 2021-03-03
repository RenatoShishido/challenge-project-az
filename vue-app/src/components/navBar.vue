<template>
  <div>
    <v-app-bar app color="grey">
      <v-app-bar-nav-icon
        v-if="isMobile"
        @click.stop="drawer = !drawer"
      ></v-app-bar-nav-icon>
      <v-row v-if="!isMobile">
        <v-col col="12" class="d-flex justify-end">
          <router-link to="/" class="white--text headline"
            >UTENSILIOS PARA DOACAO</router-link
          >
          <router-link to="/administrador" class="white--text headline  mx-10"
            >ADMINISTRADOR</router-link
          >
        </v-col>
      </v-row>
    </v-app-bar>
    <v-navigation-drawer v-if="isMobile" v-model="drawer" app left>
      <v-list>
        <v-subheader class="title py-5">Sistema Doador</v-subheader>
        <v-list-item to="/" >
          <v-list-item-icon>
            <v-icon>mdi-account</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title >UTENSILIOS</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item to="/administrador">
          <v-list-item-icon>
            <v-icon>mdi-flag</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title >ADMINISTRADOR</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      drawer: false,
    };
  },
  computed: {
    isMobile() {
      return this.$vuetify.breakpoint.smAndDown;
    },
    isAdministrador() {
      return this.$route.name == "Administrador";
    },
  },
};
</script>

<style></style>
