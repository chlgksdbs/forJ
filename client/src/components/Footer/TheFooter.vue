<template>
  <div id="TheFooter">
    <div id="footer_line">
      <hr>
    </div>
    <div>
      <router-link to="#" class="footer_text">이용약관</router-link>
      <router-link to="#" class="footer_text">개인정보 처리방침</router-link>
      <router-link to="#" class="footer_text">문의</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TheFooter',
  components: {},
  data() {
    return {
      message: '',
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
.footer_text {
  text-decoration: none;
  font-size: 14px;
  color: #888;
}
.footer_text+.footer_text::before {
  margin: 0 10px;
  content: " | ";
  color: #dadada;
}
#TheFooter {
  margin: 0 0 40px 0;
}
#footer_line {
  margin: 30px 0;
}
</style>