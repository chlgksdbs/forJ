<template>
    <div>
        <the-heading></the-heading>
        <div class="user_plan_content">
            <the-plan-left></the-plan-left>
            <the-naver-map></the-naver-map>
            <the-plan-right></the-plan-right>
        </div>
        <the-footer></the-footer>
    </div>
</template>
<script>
import TheHeading from "@/components/Header/TheHeading.vue";
import ThePlanLeft from "@/components/PlanLeftSide/ThePlanLeft.vue";
import TheNaverMap from "@/components/NaverMap/TheNaverMap.vue"
import ThePlanRight from "@/components/PlanRightSide/ThePlanRight.vue";
import TheFooter from "@/components/Footer/TheFooter.vue";

export default {
    name: "UserPlanView",
    components: {
        TheHeading,
        ThePlanLeft,
        TheNaverMap,
        ThePlanRight,
        TheFooter,
    }
}
</script>
<style scoped>
.user_plan_content {
    display: flex;
}
</style>