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
                <v-container>
                  <v-row align="center">
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
                        v-mask="'(##) #####-####'"
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
                </v-container>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header class="title">
                Cadastro de Utensilio
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-container>
                  <v-row align="center">
                    <v-col cols="12">
                      <v-text-field
                        v-model="fields_utensilio.nome"
                        outlined
                        :rules="defaultRules"
                        label="Nome Utensilio"
                        required
                      ></v-text-field>
                      <v-text-field
                        v-model="fields_utensilio.cliente_id"
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
                </v-container>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header class="title">
                Listagem de Usuarios
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-container>
                  <v-row align="center">
                    <v-col cols="12">
                      <Table
                        :clientes="clientes"
                        @alterarCliente="alterarCliente"
                        @removerCliente="removerCliente"
                      />
                      <div class="text-center py-10">
                        <a @click="listarClientes()">
                          <v-pagination
                            v-model="page"
                            :length="conteudo.totalPages"
                            circle
                          ></v-pagination>
                        </a>
                      </div>
                    </v-col>
                  </v-row>
                </v-container>
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
    clientes: {},
    conteudo: {},
    size: 2,
    page: 1,
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
  mounted() {
    this.listarClientes();
  },
  methods: {
    async listarClientes() {
      try {
        this.conteudo = await clienteService.listarClientes(this.size, this.page - 1);
        this.clientes = this.conteudo.content
      } catch (error) {
        error;
      }
    },
    async cadastrarCliente() {
      try {
        await clienteService.criarCliente(this.fields_cliente);
        this.$store.dispatch("snackbar/show", {
          content: "Cliente gravado com sucesso!",
          color: "green",
        });

        this.fields_cliente = {};
        this.panel = 1;
        this.listarClientes();
      } catch (error) {
        console.log();
        this.$store.dispatch("snackbar/show", {
          content: error.message,
          color: "error",
        });
        this.fields_cliente = {};
      }
    },
    async cadastrarUtensilio() {
      try {
        await utensilioService.criarUtensilio(this.fields_utensilio);
        this.fields_utensilio = {};
        this.$store.dispatch("snackbar/show", {
          content: "Utensilio cadastrado!",
          color: "green",
        });
      } catch (error) {
        this.$store.dispatch("snackbar/show", {
          content: error.message,
          color: "error",
        });
        this.fields_utensilio = {};
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
