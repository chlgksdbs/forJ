<template>
  <div>
    <h2 class="modifyTitle">내가 좋아요 한 글</h2>
    <div class="modifyBox">
    </div>
  </div>
</template>

<script>
export default {
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
a {
    text-decoration: none;
    color: #A1A1A1;
}
th {
    text-align: center;
    padding: 5px;
}
td {
    text-align: center;
    padding: 5px;
}
.modifyBox {
  margin: 20px 0;
}
</style>