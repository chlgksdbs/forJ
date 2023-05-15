<template>
  <div id="TheSearchListCard">
    <div class="img_box">
      <img :src="img">
    </div>
    <div class="text_box">
      <h5>{{ title }}</h5>
    </div>
    <div class="btn_box">
      <img src="@/assets/img/icon_info.png">
      <img src="@/assets/img/icon_nobox_plus.png">
    </div>
  </div>
</template>

<script>
export default {
  name: 'TheSearchListCard',
  components: {},
  data() {
    return {
      message: '',
    };
  },
  props: {
    img: String,
    title: String,
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
#TheSearchListCard {
    margin: 10px auto;
    width: 90%;
    height: 20%;
    border: 1px solid #000;
    display: flex;
    align-items: center;
    background-color: #FFF;
}
.img_box {
    width: 20%;
    height: 95%;
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
  width:100%;
  margin: 2px 0;
}
</style>