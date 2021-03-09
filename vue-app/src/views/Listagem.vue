<template>
  <v-main>
    <v-container>
      <v-row>
        <v-col md=4 sm=12 v-for="utensilio in utensilios" :key="utensilio.key"> 
          <Utensilios :utensilio="utensilio"/>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import Utensilios from "../components/card-utensilios"
import utensilioService from "../service/utensilioService"
export default {
  name: "Listagem",
  components: {
    Utensilios
  },
  data: () => ({
    utensilios: []
  }),
  mounted() {
    utensilioService.listarUtensilios().then((response) => {
      this.utensilios = response
      console.log(response);
    })
  },
  methods: {
    async Adquirir() {

    },
    async TirarSolicitacao() {
      
    }
  }
};
</script>

<style></style>
