<template>
    <div id="AppPlan">
        <the-heading></the-heading>
        <router-view @dateInfo="sendDatesInfo" :setDatesInfo="datesInfo"></router-view>
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
            datesInfo: {
                'period': 0,
                'start': '',
                'end': '',
            },
        }
    },
    methods: {
        sendDatesInfo(info) {
            this.datesInfo.period = info.period;
            this.datesInfo.start = info.start;
            this.datesInfo.end = info.end;
        }
    }
}
</script>
<style scoped>

</style>