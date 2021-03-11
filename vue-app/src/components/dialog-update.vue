<template>
  <v-row>
    <v-dialog v-model="dialog" max-width="600px">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          v-bind="attrs"
          v-on="on"
          :fab="typeButton.fab"
          :outlined="typeButton.outlined"
          :color="typeButton.color"
          :class="typeButton.class"
        >
          <div v-if="!typeButton.icon">
            {{ typeButton.text }}
          </div>
          <div v-else>
            <v-icon>
              {{ typeButton.icon }}
            </v-icon>
          </div>
        </v-btn>
      </template>
      <v-card>
        <v-container>
          <h1 class="d-flex justify-center display-1 my-4">Atualizar Cliente</h1>
          <v-text-field
            v-model="typeCliente.nome"
            outlined
            label="Nome"
            required
          ></v-text-field>
          <v-text-field
            v-model="typeCliente.telefone"
            v-mask="'(##) #####-####'"
            outlined
            label="Telefone"
            required
          ></v-text-field>
          <div>
          </div>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" text @click="defaultAction()">
              Atualizar
            </v-btn>
          </v-card-actions>
        </v-container>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  props: {
    typeButton: {},
    typeCliente: {},
  },
  data() {
    return {
      dialog: false,
      fields: {},
    };
  },
  methods: {
    defaultAction() {
      this.$emit("defaultAction", this.typeCliente, this.typeCliente.email);
      this.dialog = false
    },
  }
};
</script>

<style></style>
