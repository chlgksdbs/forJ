<template>
    <div id="BoardPageView">
        <the-heading></the-heading>
        <the-search-bar></the-search-bar>
        <the-board-list></the-board-list>
        <the-page-navigation></the-page-navigation>
        <the-footer></the-footer>
    </div>
</template>
<script>
import TheHeading from "@/components/Header/TheHeading.vue";
import TheSearchBar from "@/components/SearchBar/TheSearchBar.vue";
import TheBoardList from "@/components/BoardList/TheBoardList.vue";
import ThePageNavigation from "@/components/PageNavigation/ThePageNavigation.vue";
import TheFooter from "@/components/Footer/TheFooter.vue";

export default {
    name: "BoardPageView",
    components: {
        TheHeading,
        TheSearchBar,
        TheBoardList,
        TheFooter,
        ThePageNavigation,
    }
}
</script>
<style scoped>
    
</style>