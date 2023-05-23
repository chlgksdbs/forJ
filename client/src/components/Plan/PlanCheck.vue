<template>
  <div id="check_plan_container">
    <!-- 왼쪽 계획 일정 컨테이너 -->
    <div id="left_plan_list_container">
      <!-- 왼쪽 계획 일정 카테고리 -->
      <div class="left_plan_list_category">
        <ui>
          <!-- <router-link to="/plan/check/day"><li v-for="day in setDatesInfo" :key="day.start">{{ day.period }}</li></router-link> -->
          <div v-for="day in setDatesInfo.period" :key="day">
            <router-link :to="'/plan/check/day/' + day">{{ day }} 일차</router-link>
          </div>
        </ui>
      </div>
      <!-- 왼쪽 계획 일정 내용 -->
      <div class="left_plan_list_content">
        <router-view></router-view>
      </div>
    </div>
    <!-- 일정 경로 맵 -->
    <div id="right_plan_map">
      <div id="map" class="naver_map" ref="map"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PlanCheck',
  components: {},
  data() {
    return {
      // message: '',
    };
  },
  props: {
    setDatesInfo : Object,
  },
  created() {
   },
  mounted() {
    var naver = window.naver; // window 객체의 naver를 변수로서 선언
    var mapDiv = this.$refs.map;

    // Naver 그린팩토리를 중심점으로 하는 옵션
    var mapOptions = {
        center: new naver.maps.LatLng(37.3595704, 127.105399),
        zoom: 14,
    };

    this.map = new naver.maps.Map(mapDiv, mapOptions);
    this.marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(37.3595704, 127.105399),
        map: this.map,
    });

    // 지도 클릭 이벤트 핸들러
    naver.maps.Event.addListener(this.map, 'click', (e) => {
        const latlng = new naver.maps.LatLng(e.coord.y, e.coord.x);
        this.marker.setPosition(latlng); // 마커 위치 변경
    });
  },
  methods: {},
};
</script>

<style scoped>
  #check_plan_container{
    width: 100%;
    display: flex;
    justify-content: center;
  }
  #left_plan_list_container{
    display: flex;
    margin: 0 5%;
    width: 35%;
    height: 661px;
  }
  .left_plan_list_category {
    background-color: #ffd9d9;
    width: 25%;
    height: 661px;
  }
  .left_plan_list_content{
    background-color: #afa8a8;
    width: 75%;
    height: 661px;
  }
  #right_plan_map {
    margin: 0 5% 0 0;
    width: 60%;
  }
  .naver_map {
    width: 100%;
    height: 661px;
  }
</style>