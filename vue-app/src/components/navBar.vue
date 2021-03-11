<template>
  <div>
    <v-app-bar app color="grey">
      <v-app-bar-nav-icon
        v-if="isMobile"
        @click.stop="drawer = !drawer"
      ></v-app-bar-nav-icon>
      <v-row v-if="!isMobile">
        <v-col col="12" class="d-flex justify-end">
          <router-link
            v-for="item in items"
            :key="item.key"
            :to="item.route"
            class="white--text headline mx-10"
            >{{ item.text }}</router-link
          >
        </v-col>
      </v-row>
    </v-app-bar>
    <v-navigation-drawer v-if="isMobile" v-model="drawer" app left>
      <v-list>
        <v-subheader class="title py-5">Sistema Doador</v-subheader>
        <v-list-item v-for="item in items" :key="item.key" :to="item.route">
          <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title v-text="item.text"></v-list-item-title>
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
      items: [
        {
          text: "UTENSILIOS PARA DOACAO",
          icon: "mdi-account",
          route: "/",
        },
        {
          text: "ADMINISTRADOR",
          icon: "mdi-flag",
          route: "/administrador",
        },
      ],
    };
  },
  computed: {
    isMobile() {
      return this.$vuetify.breakpoint.smAndDown;
    },
  },
};
</script>

<style></style>
