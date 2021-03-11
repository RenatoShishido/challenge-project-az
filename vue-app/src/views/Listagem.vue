<template>
  <v-main>
    <v-container>
      <v-row>
        <v-col
          md="4"
          sm="12"
          v-for="utensilio in utensilios"
          :key="utensilio.key"
        >
          <Utensilios :utensilio="utensilio" @defaultAction="Adquirir" />
        </v-col>
      </v-row>
    </v-container>
    <div class="text-center py-10">
      <a @click="refresh()">
        <v-pagination
          v-model="page"
          :length="conteudo.totalPages"
          circle
        ></v-pagination>
      </a>
    </div>
  </v-main>
</template>

<script>
import Utensilios from "../components/card-utensilios";
import utensilioService from "../service/utensilioService";
export default {
  name: "Listagem",
  components: {
    Utensilios,
  },
  data: () => ({
    utensilios: [],
    conteudo: [],
    size: 9,
    page: 1,
  }),
  mounted() {
    this.listarUtensilios();
  },
  methods: {
    refresh() {
      this.listarUtensilios();
    },
    async listarUtensilios() {
      try {
        this.conteudo = await utensilioService.listarUtensilios(
          this.size,
          this.page - 1
        );
        const array = [];
        this.conteudo.content.forEach((element) => {
          if (element.adquirido == null) {
            array.push(element);
          }
        });

        this.utensilios = array;
      } catch (error) {
        error;
      }
    },
    async Adquirir(utensilio) {
      try {
        utensilio.adquirido = true;
        await utensilioService.updateUtensilio(utensilio.id, utensilio);
        this.$store.dispatch("snackbar/show", {
          content: "Item adquirido!",
          color: "green",
        });
        this.listarUtensilios();
      } catch (error) {
        error;
        this.$store.dispatch("snackbar/show", {
          content: "Nao foi possivel adquirir este item!",
          color: "error",
        });
      }
    },
  },
};
</script>

<style></style>
