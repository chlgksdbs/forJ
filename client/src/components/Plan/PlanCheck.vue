<template>
  <div id="check_plan_container">
    <!-- 왼쪽 계획 일정 컨테이너 -->
    <div id="left_plan_list_container">
      <!-- 왼쪽 계획 일정 타이틀 -->
      <div class="left_plan_list_title"><b>여행 일정</b></div>
      <div class="left_plan_list">
        <!-- 왼쪽 계획 일정 카테고리 -->
        <div class="left_plan_list_category">
          <div class="cateDay"><router-link class="cateDayWord" :to="'/plan/check/day/all'">전체보기</router-link></div>
          <!-- <div class="cateDay"><router-link class="cateDayWord" :to="'/plan/check/day/all'">전체보기</router-link></div> -->
            <div v-for="day in setselectInfo[0].period+1" :key="day">
              <div class="cateDay"><router-link class="cateDayWord" :to="'/plan/check/day/' + day ">{{ day }} 일차</router-link></div>
            </div>
        </div>
        <!-- 왼쪽 계획 일정 내용 -->
        <div class="left_plan_list_content">
          <!-- <router-view :selectAreaItems="areaItems"></router-view> -->
          <router-view :selectAreaItems="setselectInfo"></router-view>
        </div>
      </div>
      <div><button class="plan_left_define_btn">일정 확정</button></div>
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
  components: {
  },
  data() {
    return {
      areaItems: [],
    };
  },
  props: {
    // setDatesInfo: Object,
    // setAreaInfo: Array,
    setselectInfo: Array,
  },
  created() {},
  mounted() {

    // console.log(this.setselectInfo);

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

    // 가져온 선택 일자와 여행지 정보중 여행지 정보만 분리해서 담는 메서드
    for (let i = 1; i < this.setselectInfo.length; i++){
      this.areaItems.push(this.setselectInfo[i]);
    }
    // console.log(this.areaItems);
  },
  methods: {
  },
};
</script>

<style scoped>
  #check_plan_container{
    width: 100%;
    display: flex;
    justify-content: center;
  }
  #left_plan_list_container{
    margin: 0 5%;
    /* width: 40%; */
    width: 338px;
    height: 600px;
    background-color: #f5f5f5;
    padding: 10px;
  }
  .left_plan_list_title{
    margin: 10px 15px 15px;
    font-size: 20px;
  }
  .left_plan_list{
    display: flex;
    height: 85%;
  }
  .left_plan_list_category {
    width: 25%;
    height: 600px;
  }
  .cateDay{
    width: 75px;
    height: 20px;
    border-width: 2px 0 2px 2px;
    border: 2px solid #6ab2ed;
    border-radius: 8px;
    padding: 10px 5px;
    margin-bottom: -1px;
    margin-right: -8px;
    text-decoration-line: none;
    background-color: #fff;
  }
  .cateDayWord{
    color: #5b5b5b;
    text-decoration-line: none;
  }
  .cateDay:hover{
    border: 4px solid #6ab2ed;
    background-color: #6ab2ed;
  }
  .cateDay:active{
    border: 4px solid #6ab2ed;
    background-color: #6ab2ed;
  }
  .left_plan_list_content{
    background-color: #fff;
    border: 2px solid #6ab2ed;
    /* width: 75%; */
    width: 250px;
    height: 495px;
    overflow: scroll;
    overflow-x: hidden;
  }
  .plan_left_define_btn {
    color: #6ab2ed;
    font-weight: bold;
    background-color: #fff;
    border: 1px solid #6ab2ed;
    border-radius: 15px;
    width: 70%;
    height: 37px;
    cursor: pointer;
    margin-left: 23%;
}
.plan_left_define_btn:hover {
    color: #fff;
    font-weight: bold;
    background-color: #6ab2ed;
    border: 1px solid #6ab2ed;
}
  #right_plan_map {
    margin: 0 5% 0 0;
    width: 60%;
  }
  .naver_map {
    width: 100%;
    height: 620px;
  }
</style>