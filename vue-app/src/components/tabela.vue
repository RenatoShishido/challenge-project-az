<template>
  <v-container>
    <v-row v-for="cliente in clientes" :key="cliente.key">
      <v-col cols="2">
        {{ cliente.nome }}
      </v-col>
      <v-col cols="2">
        {{ cliente.telefone }}
      </v-col>
      <v-col cols="5">
        {{ cliente.email }}
      </v-col>
      <v-col cols="1">
        <DialogUpdate
          :typeCliente="cliente"
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
          :typeCliente="cliente"
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
  props: {
    clientes: {},
  },
  components: {
    DialogUpdate,
    Dialog,
  },
  mounted() {
    this.listarClientes();
  },
  methods: {
    async listarClientes() {
      try {
        this.clientes = await clienteService.listarClientes(
          this.size,
          this.page
        );
      } catch (error) {
        error;
      }
    },
    async alterarCliente(content, id) {
      try {
        confirm("Voce tem certeza que deseja alterar esse anuncio");
        await clienteService.updateCliente(id, content);

        this.listarClientes();

        this.$store.dispatch("snackbar/show", {
          content: "Cliente Atualizado com sucesso!",
          color: "green",
        });
      } catch (error) {
        error;
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
