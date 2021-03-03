<template>
  <v-main>
    <v-container>
      <v-row>
        <v-col col=3> 
          <Utensilios/>
        </v-col>
        <v-col col=3> 
          <Utensilios/>
        </v-col>
        <v-col col=3> 
          <Utensilios/>
        </v-col>
        <v-col col=3> 
          <Utensilios/>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import Utensilios from "../components/card-utensilios"
export default {
  name: "Listagem",
  components: {
    Utensilios
  },
  data: () => ({
    
  }),
  methods: {
    async Adquirir() {

    },
    async TirarSolicitacao() {
      
    }
  }
};
</script>

<style></style>
