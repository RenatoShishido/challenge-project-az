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
export default {
  props: {
    clientes: {},
  },
  components: {
    DialogUpdate,
    Dialog,
  },
  methods: {
    async alterarCliente(content, id) {
      this.$emit("alterarCliente", content, id)
    },
    async removerCliente(id) {
      this.$emit("removerCliente", id)
    },
  },
};
</script>
