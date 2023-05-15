<template>
    <div id="TheCategoryBtn">
        <div class="text_box">
            <b>{{ title }}</b>
        </div>
    </div>
</template>
<script>
export default {
    name: "TheCategoryBtn",
    props: {
        title: String,
    }
}
</script>
<style scoped>
#TheCategoryBtn {
    border: 1px solid #40A3FF;
    border-radius: 10px;
    width: 18%;
    height: 10%;
    cursor: pointer;
    margin: 10px auto;
    padding: 5px;
    background-color: #FFF;
}
.text_box {
    font-size: 80%;
}
</style>