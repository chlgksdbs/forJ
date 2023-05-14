<template>
    <div id="TheCategoryBtn">
        <div class="text_box">
            {{ title }}
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
    width: 16%;
    height: 10%;
    cursor: pointer;
    margin: 10px auto;
    padding: 5px;
}
.text_box {
    font-size: 80%;
}
</style>