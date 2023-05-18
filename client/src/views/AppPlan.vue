<template>
    <div id="AppPlan">
        <the-heading></the-heading>
        <router-view></router-view>
        <the-footer></the-footer>
    </div>
</template>
<script>
import TheHeading from "@/components/TheHeading.vue";
import TheFooter from "@/components/TheFooter.vue";

export default {
    name: "AppPlan",
    components: {
        TheHeading,
        TheFooter,
    }
}
</script>
<style scoped>

</style>