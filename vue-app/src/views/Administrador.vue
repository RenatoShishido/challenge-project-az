<template>
  <v-main>
    <v-container fill-height>
      <v-row>
        <v-col>
          <v-expansion-panels focusable inset hover v-model="panel">
            <v-expansion-panel>
              <v-expansion-panel-header class="title">
                Cadastro de Pessoa
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-row align="center">
                  <v-col cols="12">
                    <h1 class="d-flex justify-start my-1 display-1">
                      Cadastro do Usuario
                    </h1>
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-text-field
                      v-model="fields_cliente.nome"
                      :rules="defaultRules"
                      outlined
                      label="Name"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-text-field
                      v-model="fields_cliente.telefone"
                      :rules="telefoneRules"
                      outlined
                      label="Telefone"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field
                      v-model="fields_cliente.email"
                      :rules="emailRules"
                      outlined
                      label="E-mail"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" class="d-flex justify-center">
                    <Button
                      @defaultAction="cadastrarCliente"
                      :typeButton="{
                        color: 'green',
                        text: 'Cadastrar',
                        class: 'mr-4 white--text',
                        outlined: 'outlined',
                      }"
                    />
                    <Button
                      @defaultAction="fields_cliente = {}"
                      :typeButton="{
                        color: 'red',
                        text: 'LIMPAR',
                        class: 'mr-4 white--text',
                        outlined: 'outlined',
                      }"
                    />
                  </v-col>
                </v-row>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header class="title">
                Cadastro de Utensilio
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-row>
                  <v-col cols="12">
                    <h1 class="d-flex justify-start mb-2 display-1">
                      Cadastro do Utensilio
                    </h1>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field
                      v-model="fields_utensilio.nome"
                      outlined
                      :rules="defaultRules"
                      label="Nome Utensilio"
                      required
                    ></v-text-field>
                    <v-text-field
                      v-model="fields_utensilio.email"
                      outlined
                      :rules="emailRules"
                      label="Email de Cadastro"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-textarea
                      v-model="fields_utensilio.descricao"
                      outlined
                      :rules="defaultRules"
                      label="Descricao"
                      required
                    ></v-textarea>
                  </v-col>
                  <v-col cols="12" class="d-flex justify-center">
                    <Button
                      @defaultAction="cadastrarUtensilio"
                      :typeButton="{
                        color: 'success',
                        text: 'CADASTRAR',
                        class: 'mr-4 white--text',
                        outlined: 'outlined',
                      }"
                    />
                    <Button
                      @defaultAction="fields_utensilio = {}"
                      :typeButton="{
                        color: 'red',
                        text: 'LIMPAR',
                        class: 'mr-4 white--text',
                        outlined: 'outlined',
                      }"
                    />
                  </v-col>
                </v-row>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header class="title">
                Listagem de Usuarios
              </v-expansion-panel-header>
              <v-expansion-panel-content>
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
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import Table from "../components/tabela";
import Button from "../components/botao";
import clienteService from "../service/clienteService";
import utensilioService from "../service/utensilioService";

export default {
  name: "Administrador",
  components: {
    Table,
    Button,
  },
  data: () => ({
    fields_cliente: {},
    fields_utensilio: {},
    panel: [],
    defaultRules: [(v) => !!v || "E necessario preencher o campo"],
    emailRules: [
      (v) => !!v || "E necessario preencher o campo",
      (v) => /.+@.+\..+/.test(v) || "Email deve ser valido",
    ],
    telefoneRules: [(v) => /([0-9])\w+/g.test(v) || "Apenas numeros"],
  }),
  methods: {
    async cadastrarCliente() {
      try {
         await clienteService.listarClienteId(this.fields_cliente.email)
         .then((response => {
           response
           this.$store.dispatch("snackbar/show", {
            content: "Email ja cadastrado !",
            color: "error",
          });
           this.fields_cliente = {};
         }))
         .catch(async err => {
           err
           await clienteService.criarCliente(this.fields_cliente);
             this.$store.dispatch("snackbar/show", {
            content: "Cliente gravado com sucesso!",
            color: "green",
          });
           this.fields_cliente = {};
         })
        
      } catch (error) {
        this.$store.dispatch("snackbar/show", {
          content: "Erro ao tentar gravar cliente!",
          color: "error",
        });
        this.fields_cliente = {};
      }
    },
    async cadastrarUtensilio() {
      try {
        await clienteService.listarClienteId(this.fields_utensilio.email)
        .then(async response => {
          response
          await utensilioService.criarUtensilio(this.fields_utensilio);
          this.fields_utensilio = {};
          this.$store.dispatch("snackbar/show", {
            content: "Utensilio cadastrado!",
            color: "green",
          });
        })
        .catch(err => {
          err
          this.fields_utensilio = {};
          this.$store.dispatch("snackbar/show", {
            content: "Email nao cadastrado!",
            color: "error",
          });
        })
      } catch (error) {
        this.$store.dispatch("snackbar/show", {
          content: "Erro ao tentar gravar utensilio!",
          color: "error",
        });
        this.fields_utensilio = {};
      }
    },
  },
};
</script>
