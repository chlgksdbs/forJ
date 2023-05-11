<template>
    <div id="BoardPageView">
        <the-heading></the-heading>
        <the-search-bar></the-search-bar>
        <the-board-list></the-board-list>
    </div>
</template>
<script>
import TheHeading from "@/components/Header/TheHeading.vue";
import TheSearchBar from "@/components/SearchBar/TheSearchBar.vue";
import TheBoardList from "@/components/BoardList/TheBoardList.vue";

export default {
    name: "BoardPageView",
    components: {
        TheHeading,
        TheSearchBar,
        TheBoardList,
    }
}
</script>
<style scoped>
    
</style>