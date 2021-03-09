<template>
  <v-container>
    <v-row v-for="item in items" :key="item.key">
      <v-col md="2">
        {{ item.nome }}
      </v-col>
      <v-col md="2">
        {{ item.telefone }}
      </v-col>
      <v-col md="5">
        {{ item.email }}
      </v-col>
      <v-col md="1">
        <DialogUpdate
          :typeCliente="item"
          @defaultAction="alterarCliente"
          :typeButton="{
            color: 'green',
            fab: 'fab',
            icon: 'mdi-pencil',
            outlined: 'outlined',
          }"
        />
      </v-col>
      <v-col md="1">
        <Dialog
          :typeCliente="item"
          @defaultAction="removerCliente"
          :typeButton="{
            color: 'red',
            fab: 'fab',
            icon: 'mdi-delete',
            outlined: 'outlined',
          }"
        />
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import DialogUpdate from "./dialog-update.vue";
import Dialog from "./dialog";
import clienteService from "../service/clienteService";
export default {
  components: {
    DialogUpdate,
    Dialog,
  },
  data() {
    return {
      search: "",
      filter: {},
      page: 1,
      sortBy: "name",
      items: [],
    };
  },
  mounted() {
    this.listarClientes();
  },
  methods: {
    async listarClientes() {
      try {
        this.items = await clienteService.listarClientes();
      } catch (error) {
        error;
        this.$store.dispatch("snackbar/show", {
          content: "Erro ao listar cliente!",
          color: "error",
        });
      }
    },
    async alterarCliente(content, id) {
      try {
        await clienteService.updateCliente(id, content);

        this.listarClientes();

        this.$store.dispatch("snackbar/show", {
          content: "Cliente Atualizado com sucesso!",
          color: "green",
        });
      } catch (error) {
        error;
        this.$store.dispatch("snackbar/show", {
          content: "Erro ao alterar cliente!",
          color: "error",
        });
      }
    },
    async removerCliente(id) {
      try {
        await clienteService.removeCliente(id);

        this.listarClientes();

        this.$store.dispatch("snackbar/show", {
          content: "Cliente removido com sucesso!",
          color: "green",
        });
      } catch (error) {
        error;
        this.$store.dispatch("snackbar/show", {
          content: "Erro ao remover cliente!",
          color: "error",
        });
      }
    },
  },
};
</script>
