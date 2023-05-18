<template>
    <div id="PlanCategoryBtn">
        <div class="text_box">
            <b>{{ title }}</b>
        </div>
    </div>
</template>
<script>
export default {
    name: "PlanCategoryBtn",
    props: {
        title: String,
    }
}
</script>
<style scoped>
.text_box {
    font-size: 80%;
}
#PlanCategoryBtn {
    border: 1px solid #40A3FF;
    border-radius: 10px;
    width: 18%;
    height: 10%;
    cursor: pointer;
    margin: 10px auto;
    padding: 5px;
    background-color: #FFF;
}
#PlanCategoryBtn:hover{
    background-color: #40A3FF;
}
</style>