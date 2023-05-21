<template>
    <div id="BoardQna">
        <the-construction></the-construction>
    </div>
</template>
<script>
import TheConstruction from "../TheConstruction.vue";

export default {
    name: "BoardQna",
    components: {
        TheConstruction,
    },
    created() {
        this.$emit("setTitle", "Q&A");
    },
}
</script>
<style scoped>
</style>