<template>
    <div id="MainPageView">
        <the-heading></the-heading>
        <the-carousel></the-carousel>
        <h1>나만의 여행 일정을 만들고 공유</h1>
        <the-card></the-card>
        <the-footer></the-footer>
    </div>
</template>
<script>
import TheHeading from "@/components/Header/TheHeading.vue";
import TheCarousel from "@/components/Carousel/TheCarousel.vue";
import TheCard from "@/components/Card/TheCard.vue";
import TheFooter from "@/components/Footer/TheFooter.vue";

export default {
    name: "MainPageView",
    components: {
        TheHeading,
        TheCarousel,
        TheCard,
        TheFooter
    },
}
</script>
<style>
    
</style>