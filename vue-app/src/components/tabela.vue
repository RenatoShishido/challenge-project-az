<template>
  <v-container fluid>
    <v-data-iterator
      :items="items"
      :items-per-page.sync="itemsPerPage"
      :page.sync="page"
      :search="search"
      :sort-by="sortBy.toLowerCase()"
      :sort-desc="sortDesc"
      hide-default-footer
    >
      <template v-slot:header>
        <v-toolbar dark color="grey darken-3" class="mb-1">
          <v-text-field
            v-model="search"
            clearable
            flat
            solo-inverted
            hide-details
            prepend-inner-icon="mdi-magnify"
            label="Search"
          ></v-text-field>
        </v-toolbar>
      </template>

      <template >
        <v-row>
          <v-col cols="12">
            <thead>
              <tr>
                <th class="text-left px-2 subheading font-weight-bold">
                  Name
                </th>
                <th class="text-left px-2 subheading font-weight-bold">
                  Calories
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in items" :key="item.name">
                <td class="px-2">{{ item.name }}</td>
                <td class="px-2">{{ item.calories }}</td>
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
export default {
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
          name: "Frozen Yogurt",
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0,
          sodium: 87,
          calcium: "14%",
          iron: "1%",
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
