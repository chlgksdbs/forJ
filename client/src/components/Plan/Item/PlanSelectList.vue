<template>
    <div id="PlanSelectList">
        <div class="img_box">
            <!-- <div v-if="img">
                <img class="img_box_img" :src="img">
            </div>
            <div v-else>
                <img class="img_box_no_img" src="@/assets/img/no-photos.png">
            </div> -->
            <div>
                <img class="img_box_img" :src="img">
            </div>
        </div>
        <div class="text_box">
            <h5>{{ title }}</h5>
        </div>
        <div class="btn_box">
            <img src="@/assets/img/icon_nobox_delete.png">
        </div>
    </div>
</template>
<script>
export default {
    name: 'PlanSelectList',
    props: {
        img: String,  
        title: String,
        contentId: Number,
    },
    created() {
        console.log(this.img);
        console.log(this.title);
        console.log(this.contentId);
    }
}
</script>
<style scoped>

.img_box {
  position: relative;
    width: 50%;
    height:80px;
}
/* .img_box > div{
  height: 100%;
  width: 100%;
} */
.img_box_img{
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
}
.img_box_no_img{
  position: absolute;
  top: 0;
  left: 0;
  height: 45%;
  width: 45%;
  padding: 23%;
}
/* .img_box {
    width: 30%;
    margin: auto 15px auto 0;
}
.img_box img {
  height: 100%;
} */
.text_box {
    width: 40%;
    margin: auto 5px auto 5px;
}
.btn_box {
    width: 10%;
    margin: 1%;
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