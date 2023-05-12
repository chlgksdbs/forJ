<template>
  <div id="ThePageNavigation">
    <ul>
      <li>
        <router-link to="#" class="page_pre_btn">이전</router-link>
      </li>
      <li>
        <router-link to="#" class="page_no">1</router-link>
      </li>
      <li>
        <router-link to="#" class="page_no">2</router-link>
      </li>
      <li>
        <router-link to="#" class="page_no">3</router-link>
      </li>
      <li>
        <router-link to="#" class="page_no">4</router-link>
      </li>
      <li>
        <router-link to="#" class="page_no">5</router-link>
      </li>
      <li>
        <router-link to="#" class="page_next_btn">다음</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'ThePageNavigation',
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
ul {
  list-style: none;
}
ul li {
  display: inline-block;
}
ul li a {
  display: block;
  margin: 0 6px;
}
a {
  text-decoration: none;
  cursor: pointer;
  font-size: 14px;
  color: #222;
}
a.page_no {
  width: 24px;
  height: 24px;
  line-height: 24px;
  border: 1px solid #f0f0f0;
}
a.page_pre_btn::before {
  content: "";
  display: block;
  float: left;
  margin: 5px 2px 0 0;
  transform: rotate(-45deg);
  width: 7px;
  height: 7px;
  border-top: 1px solid #828282;
  border-left: 1px solid #828282;
}
a.page_next_btn::after {
  content: "";
  display: block;
  float: right;
  margin: 5px 0 0 2px;
  transform: rotate(135deg);
  width: 7px;
  height: 7px;
  border-top: 1px solid #828282;
  border-left: 1px solid #828282;
}
</style>