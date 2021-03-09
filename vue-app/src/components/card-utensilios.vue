<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-card max-width="344" height="300">
          <v-card-text>
            <p class="display-1 text--primary text-capitalize">
              {{ utensilio.nome }}
            </p>
            <p class="body-2">{{ utensilio.email }}</p>
            <div class="text--primary text-justify body-1">
              {{ utensilio.descricao }}
            </div>
          </v-card-text>
          <v-card-actions class="d-flex justify-center">
            <v-btn text color="teal accent-4" @click="reveal = true">
              Adquirir
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: {
    utensilio: {},
  },
  data: () => ({}),
};
</script>

<style></style>
