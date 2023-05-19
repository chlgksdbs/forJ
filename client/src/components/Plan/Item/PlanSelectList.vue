<template>
    <div id="PlanSelectList">
        <!-- <div v-if="img"> -->
            <div class="img_box">
                <img :src="img">
            </div>
        <!-- </div>
        <div v-else>
            <div class="btn_box">
                <img src="@/assets/img/icon_nobox_delete.png">
            </div>
        </div> -->
        <div class="text_box">
            <h5>{{ title }}</h5>
        </div>
    </div>
</template>
<script>
export default {
    name: 'PlanSelectList',
    props: {
        img: String,  
        title: String,
    },
}
</script>
<style scoped>
.img_box {
    width: 30%;
    margin: auto 15px auto 0;
}
.img_box img {
  height: 100%;
}
.text_box {
    width: 40%;
    margin: auto 10px auto 15px;
}
.btn_box {
    width: 10%;
    margin: auto;
}
.btn_box img {
    width: 100%;
}
#PlanSelectList {
    margin: 10px auto;
    width: 90%;
    height: 20%;
    border: 1px solid #000;
    display: flex;
    align-items: center;
}
</style>