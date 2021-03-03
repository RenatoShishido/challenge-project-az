<template>
  <v-container fluid>
    <v-data-iterator
      :items="items"
      :items-per-page.sync="itemsPerPage"
      :page.sync="page"
      :search="search"
      hide-default-footer
    >
      <template v-slot:header>
        <v-text-field
          v-model="search"
          clearable
          color="grey darken-3"
          solo-inverted
          hide-details
          prepend-inner-icon="mdi-magnify"
          label="Search"
        ></v-text-field>
      </template>

      <template>
        <v-row class=" mt-4">
          <v-col cols="12">
            <thead>
              <tr>
                <th cols="2" class="text-left px-2 title font-weight-bold">
                  Nome
                </th>
                <th cols="3" class="text-center px-2 title font-weight-bold">
                  Email
                </th>
                <th cols="1" class="text-left px-2 title font-weight-bold">
                  Utensilio
                </th>
                <th cols="5" class="text-left px-2 title font-weight-bold">
                  Descricao
                </th>
                <th cols="5" class="text-left px-2 title font-weight-bold">
                  Alterar
                </th>
                <th cols="5" class="text-left px-2 title font-weight-bold">
                  Deletar
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in items" :key="item.name">
                <td cols="2" class="px-2">{{ item.name }}</td>
                <td cols="3" class="px-2">{{ item.email }}</td>
                <td cols="1" class="px-2">{{ item.utensilio }}</td>
                <td cols="5" class="px-2">{{ item.descricao }}</td>
                <td cols="5" class="px-2">
                  <Button
                    @defaultAction="cleanFields"
                    :typeButton="{
                      color: 'green',
                      fab: 'fab',
                      icon: 'mdi-pencil',
                      outlined: 'outlined'
                    }"
                  />
                </td>
                <td cols="5" class="px-2">
                  <Dialog
                  :typeButton="{
                      color: 'red',
                      fab: 'fab',
                      icon: 'mdi-delete',
                      outlined: 'outlined'
                    }"
                  />

                </td>
              </tr>
            </tbody>
          </v-col>
        </v-row>
      </template>

      <template v-slot:footer>
        <v-row class="mt-2" align="center" justify="center">
          <span class="grey--text">Items per page</span>
          <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                dark
                text
                color="primary"
                class="ml-2"
                v-bind="attrs"
                v-on="on"
              >
                {{ itemsPerPage }}
                <v-icon>mdi-chevron-down</v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(number, index) in itemsPerPageArray"
                :key="index"
                @click="updateItemsPerPage(number)"
              >
                <v-list-item-title>{{ number }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <v-spacer></v-spacer>

          <span
            class="mr-4
            grey--text"
          >
            Page {{ page }} of {{ numberOfPages }}
          </span>
          <v-btn
            fab
            dark
            color="grey darken-3"
            class="mr-1"
            @click="formerPage"
          >
            <v-icon>mdi-chevron-left</v-icon>
          </v-btn>
          <v-btn fab dark color="grey darken-3" class="ml-1" @click="nextPage">
            <v-icon>mdi-chevron-right</v-icon>
          </v-btn>
        </v-row>
      </template>
    </v-data-iterator>
  </v-container>
</template>
<script>
import Button from "./botao";
import Dialog from "./dialog";
export default {
  components: {
    Button,
    Dialog
  },
  data() {
    return {
      itemsPerPageArray: [4, 8, 12],
      search: "",
      filter: {},
      sortDesc: false,
      page: 1,
      itemsPerPage: 4,
      sortBy: "name",
      items: [
        {
          name: "Renato Tomio",
          email: "renato.re2012@hotmail.com",
          utensilio: "Panela de Pressao",
          descricao: "Panela com pouco uso bem conservada pronta para usar",
        },
        {
          name: "Renato Tomio",
          email: "renato.re2012@hotmail.com",
          utensilio: "Panela de Pressao",
          descricao: "Panela com pouco uso bem conservada pronta para usar",
        },
        {
          name: "Renato Tomio",
          email: "renato.re2012@hotmail.com",
          utensilio: "Panela de Pressao",
          descricao: "Panela com pouco uso bem conservada pronta para usar",
        },
        {
          name: "Renato Tomio",
          email: "renato.re2012@hotmail.com",
          utensilio: "Panela de Pressao",
          descricao: "Panela com pouco uso bem conservada pronta para usar",
        },
        {
          name: "Renato Tomio",
          email: "renato.re2012@hotmail.com",
          utensilio: "Panela de Pressao",
          descricao: "Panela com pouco uso bem conservada pronta para usar",
        },
        {
          name: "Renato Tomio",
          email: "renato.re2012@hotmail.com",
          utensilio: "Panela de Pressao",
          descricao: "Panela com pouco uso bem conservada pronta para usar",
        },
        {
          name: "Renato Tomio",
          email: "renato.re2012@hotmail.com",
          utensilio: "Panela de Pressao",
          descricao: "Panela com pouco uso bem conservada pronta para usar",
        },
        {
          name: "Renato Tomio",
          email: "renato.re2012@hotmail.com",
          utensilio: "Panela de Pressao",
          descricao: "Panela com pouco uso bem conservada pronta para usar",
        },
        {
          name: "Renato Tomio",
          email: "renato.re2012@hotmail.com",
          utensilio: "Panela de Pressao",
          descricao: "Panela com pouco uso bem conservada pronta para usar",
        },
      ],
    };
  },
  computed: {
    numberOfPages() {
      return Math.ceil(this.items.length / this.itemsPerPage);
    },
    filteredKeys() {
      return this.keys.filter((key) => key !== "Name");
    },
  },
  methods: {
    nextPage() {
      if (this.page + 1 <= this.numberOfPages) this.page += 1;
    },
    formerPage() {
      if (this.page - 1 >= 1) this.page -= 1;
    },
    updateItemsPerPage(number) {
      this.itemsPerPage = number;
    },
  },
};
</script>
