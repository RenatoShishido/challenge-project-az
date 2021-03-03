<template>
  <v-main>
    <v-container fill-height>
      <v-row>
        <v-col cols="12" md="6" xl="6" lg="6">
          <v-row align="center">
            <v-col cols="12">
              <h1 class="d-flex justify-start my-1 display-1">
                Cadastro do Usuario
              </h1>
            </v-col>
            <v-col cols="12" md="6">
              <v-text-field
                v-model="fields.name"
                :rules="defaultRules"
                outlined
                label="Name"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12" md="6">
              <v-text-field
                v-model="fields.telefone"
                :rules="telefoneRules"
                outlined
                label="Telefone"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="fields.email"
                :rules="emailRules"
                outlined
                label="E-mail"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <h1 class="d-flex justify-start mb-2 display-1">
                Cadastro do Utensilio
              </h1>
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="fields.nomeUtensilio"
                outlined
                :rules="defaultRules"
                label="Nome Utensilio"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-textarea
                v-model="fields.descricao"
                outlined
                :rules="defaultRules"
                label="Descricao"
                required
              ></v-textarea>
            </v-col>
            <v-col cols="12" class="d-flex justify-center">
              <Button
                @defaultAction="cadastrarDoacao"
                :typeButton="{
                  color: 'success',
                  text: 'CADASTRAR',
                  class: 'mr-4 white--text',
                  outlined: 'outlined',
                }"
              />
              <Button
                @defaultAction="cleanFields"
                :typeButton="{
                  color: 'red',
                  text: 'LIMPAR',
                  class: 'mr-4 white--text',
                  outlined: 'outlined',
                }"
              />
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12" md="6" xl="6" lg="6">
          <v-row>
            <v-col cols="12">
              <h1 class="d-flex justify-start mb-2 display-1">
                Listagem de Usuarios
              </h1>
            </v-col>
            <v-col cols="12">
              <Table />
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import Table from "../components/tabela";
import Button from "../components/botao";
export default {
  name: "Administrador",
  components: {
    Table,
    Button,
  },
  data: () => ({
    fields: {},
    defaultRules: [(v) => !!v || "E necessario preencher o campo"],
    emailRules: [
      (v) => !!v || "E necessario preencher o campo",
      (v) => /.+@.+\..+/.test(v) || "Email deve ser valido",
    ],
    telefoneRules: [(v) => /([0-9])\w+/g.test(v) || "Apenas numeros"],
  }),
  methods: {
    cleanFields() {
      this.fields = {};
    },
  },
};
</script>
