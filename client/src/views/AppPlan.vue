<template>
    <div id="AppPlan">
        <the-heading></the-heading>
        <!-- <router-view @dateInfo="sendDatesInfo" :setDatesInfo="datesInfo"  @AreaItems="sendAreasInfo" :setAreaInfo="areasInfo"></router-view> -->
        <router-view @selectInfo="sendselectInfo" :setselectInfo="selectInfo"></router-view>
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
    },
    data() {
        return {
            selectInfo: [],
        }
    },
    methods: {
        sendselectInfo(info) {
            this.selectInfo = info;
            // console.log(this.datesInfo.period);
        },
        // sendAreasInfo(info) {
        //     this.areasInfo = info;
        //     console.log(info);
        // },
    },
}
</script>
<style scoped>

</style>