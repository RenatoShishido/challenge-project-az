<template>
  <v-btn
    :fab="typeButton.fab"
    :outlined="typeButton.outlined"
    :color="typeButton.color"
    :class="typeButton.class"
    @click="defaultAction()"
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

<script>
export default {
  props: {
    typeButton: {},
  },
  methods: {
    defaultAction() {
      this.$emit("defaultAction");
    },
  },
};
</script>

<style></style>
