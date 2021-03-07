<template>
  <v-container>
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
                <th class="text-left px-2 subtitle font-weight-bold">
                  Nome
                </th>
                <th class="text-center px-2 subtitle font-weight-bold">
                  Email
                </th>
                <th class="text-left px-2 subtitle font-weight-bold">
                  Utensilio
                </th>
                <th class="text-left px-2 subtitle font-weight-bold">
                  Descricao
                </th>
                <th class="text-left px-2 subtitle font-weight-bold">
                  Alterar
                </th>
                <th class="text-left px-2 subtitle font-weight-bold">
                  Deletar
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in items" :key="item.name">
                <td class="px-2">{{ item.name }}</td>
                <td class="px-2">{{ item.email }}</td>
                <td class="px-2">{{ item.utensilio }}</td>
                <td class="px-2">{{ item.descricao }}</td>
                <td class="px-2">
                  <Button
                    @defaultAction="cleanFields"
                    :typeButton="{
                      color: 'green',
                      fab: 'fab',
                      icon: 'mdi-pencil',
                      outlined: 'outlined',
                    }"
                  />
                </td>
                <td cols="5" class="px-2">
                  <Dialog
                    :typeButton="{
                      color: 'red',
                      fab: 'fab',
                      icon: 'mdi-delete',
                      outlined: 'outlined',
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
          <v-menu offset-y>
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
    Dialog,
  },
  data() {
    return {
      search: "",
      filter: {},
      page: 1,
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
      ],
    };
  },
};
</script>
