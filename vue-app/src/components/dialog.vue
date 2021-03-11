<template>
  <v-row>
    <v-dialog v-model="dialog" max-width="290">
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
        <v-card-text class="title text-justify py-4 px-4">
          Voce tem certeza que deseja remover este anuncio de doacao?
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="red darken-1" text @click="dialog = false">
            Discordo
          </v-btn>
          <v-btn color="green darken-1" text @click="defaultAction">
            Concordo
          </v-btn>
        </v-card-actions>
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
    };
  },
  methods: {
      defaultAction() {
      this.$emit("defaultAction", this.typeCliente.email);
      this.dialog = false
    },
  }
};
</script>

<style></style>
