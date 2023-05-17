<template>
    <div id="BoardPageView">
        <the-heading></the-heading>
        <the-search-bar></the-search-bar>
        <the-board-list></the-board-list>
        <div class="post_area">
            <router-link to="/write">글작성</router-link>
        </div>
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
.post_area {
    margin: 0 65px 10px 0;
    text-align: right;
}
a {
    text-decoration: none;
    color: #000;
    border: 1px solid #000;
    border-radius: 10px;
    padding: 10px 15px;
}
</style>