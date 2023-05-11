<template>
    <div id="BoardPageView">
        <the-heading></the-heading>
        <the-board-list></the-board-list>
    </div>
</template>
<script>
import TheHeading from "@/components/Header/TheHeading.vue";
import TheBoardList from "@/components/BoardList/TheBoardList.vue";

export default {
    name: "BoardPageView",
    components: {
        TheHeading,
        TheBoardList,
    }
}
</script>
<style scoped>
    
</style>