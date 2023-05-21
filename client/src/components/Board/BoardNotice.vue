<template>
    <div id="BoardNotice">
        <the-construction></the-construction>
    </div>
</template>
<script>
import TheConstruction from "../TheConstruction.vue";

export default {
    name: "BoardNotice",
    components: {
        TheConstruction,
    },
    created() {
        this.$emit("setTitle", "공지사항");
    },
}
</script>
<style scoped>
</style>