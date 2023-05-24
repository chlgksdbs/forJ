<template>
  <div id="PlanSearchList">
    <div class="img_box">
      <div v-if="img">
        <img class="img_box_img" :src="img">
      </div>
      <div v-else>
        <img class="img_box_no_img" src="@/assets/img/no-photos.png">
      </div>
    </div>
    <div class="text_box">
      <h5>{{ title }}</h5>
    </div>
    <div class="btn_box">
      <img src="@/assets/img/icon_info.png">
      <img src="@/assets/img/icon_nobox_plus.png" @click="addItem" style="cursor: pointer;">
    </div>
  </div>
</template>

<script>
export default {
  name: 'PlanSearchList',
  components: {},
  data() {
    return {
      message: '',
    };
  },
  props: {
    img: String,
    title: String,
    contentId: Number,
    latitude: String,
    longitude: String,
  },
  created() {},
  methods: {
    // 선택목록에 선택한 장소 추가
    addItem() {
      let addInfo = {
        'addImg': this.img,
        'addTitle': this.title,
        'addContentId': this.contentId,
        'addLatitude': this.latitude,
        'addLongitude': this.longitude,
      }
      // console.log(addInfo.addImg);
      // console.log(addInfo.addTitle);
      // console.log(addInfo.addContentId);
      this.$emit("addArea",addInfo);
    }
  },
};
</script>

<style scoped>
.img_box {
  position: relative;
    /* width: 20%;
    height: 95%; */
    width: 100px;
    height:80px;
}
.img_box > div{
  height: 100%;
  width: 100%;
}
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
.text_box {
    width: 40%;
    margin: auto 10px auto 15px;
}
.btn_box {
    width: 10%;
    margin: auto;
}
.btn_box img {
  width:100%;
  margin: 2px 0;
}
#PlanSearchList {
    margin: 10px auto;
    width: 90%;
    height: 20%;
    border: 1px solid #000;
    display: flex;
    align-items: center;
    background-color: #FFF;
}
</style>