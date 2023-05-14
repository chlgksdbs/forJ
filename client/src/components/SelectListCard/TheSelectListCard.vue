<template>
    <div id="TheSelectListCard">
        <div class="img_box">
            <img :src="img">
        </div>
        <div class="text_box">
            <h5>{{ title }}</h5>
        </div>
        <div class="delete_btn_box">

        </div>
    </div>
</template>
<script>
export default {
    name: 'TheSelectListCard',
    props: {
        title: String,
        img: String,  
    },
}
</script>
<style scoped>
#TheSelectListCard {
    margin: 10px auto;
    width: 90%;
    height: 20%;
    border: 1px solid #000;
    display: flex;
    justify-content: center;
}
</style>