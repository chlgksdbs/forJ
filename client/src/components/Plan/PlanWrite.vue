<template>
  <div id="PlanWrite">
    <div class="user_plan_content">
      <div id="PlanLeftBar">
        <div class="plan_left_calendar">
            <h3>여행 일정</h3>
            <p>{{ dateFormat(attr[0].dates.start)}} ~ {{ dateFormat(attr[0].dates.end) }} ({{ calcPeriod(attr[0].dates.start, attr[0].dates.end) }}일)</p>
            <button class="plan_left_modify_btn" @click="modifyDate">변경</button>
            <v-calendar
            class="vcalendar"
            :min-date="new Date()"
            :attributes="attr"
            @dayclick="dayClickEvent"
            v-if="openmodal == true" />
        </div>
        <div class="plan_left_select_list">
            <h3>선택 목록</h3>
            <button class="plan_left_delete_btn" @click="deleteAllCard">장소 전체 삭제</button>
            <plan-select-list v-for="selectItem in selectItems" :key="selectItem.no" :img="selectItem.img" :title="selectItem.title"></plan-select-list>
            <button class="plan_left_create_btn">일정 생성</button>
        </div>
      </div>
      <div id="PlanMap">
        <div id="map" class="naver_map" ref="map"></div>
      </div>
      <div id="PlanRightBar">
        <div class="plan_right_search_bar">
            <div id="input_search_bar">
                <input type="text" v-model="categoryTitle" @keyup.enter="searchCategoryTitle" placeholder="검색어를 입력하세요." maxlength="45" />
                <img src="@/assets/img/icon_search.png" @click="searchCategoryTitle">
            </div>
        </div>
        <div class="plan_right_category">
            <plan-category-btn :title="'호텔'" @click.native="setContentTypeId(32)"></plan-category-btn>
            <plan-category-btn :title="'식당'" @click.native="setContentTypeId(39)"></plan-category-btn>
            <plan-category-btn :title="'쇼핑'" @click.native="setContentTypeId(38)"></plan-category-btn>
            <plan-category-btn :title="'여행지'" @click.native="setContentTypeId(12)"></plan-category-btn>
        </div>
        <div v-if="itemList.length">
            <div v-for="item in itemList" :key="item.contentId" class="plan_right_card">
                <plan-search-list :img="item.firstimage" :title="item.title"></plan-search-list>
            </div>
        </div>
        <div v-else class="phrase">
            <div>검색어와 키워드를<br/>선택해주세요.</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import PlanSelectList from "./Item/PlanSelectList.vue";
import PlanCategoryBtn from "./Item/PlanCategoryBtn.vue";
import PlanSearchList from "./Item/PlanSearchList.vue";

export default {
  name: 'PlanWrite',
  components: {
    PlanSelectList,
    PlanCategoryBtn,
    PlanSearchList,
  },
  data() {
    return {
      openmodal: false,
      selectItems: [
          {
              no: 1,
              title: '삼성화재 유성연수원',
              img: require('@/assets/img/ex_img5.png')
          },
          {
              no: 2,
              title: '유성온천역',
              img: require('@/assets/img/ex_img2.png')
          },
          {
              no: 3,
              title: '충남대학교',
              img: require('@/assets/img/ex_img1.png')
          },
      ],
      attr: [
          {
              highlight: {
                  start: { fillMode: 'outline' },
                  base: { fillMode: 'light' },
                  end: { fillMode: 'outline'},
              },
              dates: {
                start: new Date(),
                end: new Date(),
              },
          },
      ],
      clickCount: 0,
      map: null,
      marker: null,
      categoryTitle: '',
      contentTypeId: 0,
      itemList: [],
      areaList:[],
    };
  },
  created() {},
  methods: {
    // 캘린더 모달창 On, Off하는 메서드
    modifyDate() {
        this.openmodal = !this.openmodal;
    },
    // 날짜 포맷을 변경하는 메서드
    dateFormat(date) {
        let dateFormat = date.getFullYear()
            + '.' + ((date.getMonth() + 1) < 9 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1))
            + '.' + ((date.getDate()) < 9 ? "0" + (date.getDate()) : (date.getDate()));

        return dateFormat;
    },
    // 여행 일정 기간을 계산하는 메서드
    calcPeriod(date1, date2) {
        let period = date2.getTime() - date1.getTime();

        // 여행 도착일이 출발일보다 빠르다면, 설정 초기화
        if (period < 0) {
            alert('여행 출발일과 도착일의 설정이 잘못되었습니다.');
            this.attr[0].dates.start = new Date();
            this.attr[0].dates.end = new Date();
            return "?";
        }
        return Math.floor(period / (1000 * 60 * 60 * 24)) + 1;
    },
    // 캘린더 날짜 변경 이벤트 메서드
    dayClickEvent(date) {
        // 1. clickCount가 0인 경우, startDate를 변경
        if (this.clickCount == 0) {
            this.attr[0].dates.start = date.date;
            this.attr[0].dates.end = date.date;
            this.clickCount++;
        }
        // 2. clickCount가 1인 경우, endDate를 변경 후, clickCount 0으로 변경
        else {
            this.attr[0].dates.end = date.date;
            this.clickCount = 0;
        }
    },
    // 선택 목록에 있는 the-select-list-card를 전체 삭제하는 메서드
    deleteAllCard() {
        this.selectItems.splice(0);
    },
    setContentTypeId(typeid) {
        this.contentTypeId = typeid;
    },
    // 검색어 입력 후, 엔터키를 치거나 버튼 누른 후의 작업을 수행하는 메서드
      searchCategoryTitle() {

        // let areaInfo = {
        //     'addr': '',
        //     'areaCode': '',
        //     'contentId': '',
        //     'contenttypeId': '',
        //     'areaImg': '',
        //     'mapX': '',
        //     'mapY': '',
        //     'sigunguCode': '',
        //     'areaName': '',
        // }
        
        console.log(this.categoryTitle);
        console.log(this.contentTypeId);
            // // 공공api에서 호출
            // const baseUrl = `https://apis.data.go.kr/B551011/KorService1/searchKeyword1`;
            // const serviceKey = `%2BO%2FI7nNhPkcVOh2FuthiaVSbtU9Yvs0HFf0f%2FMd3vSqVsR1UzoM0jbIqd9rAaN7AHHG2S2IpTqcBq1q8aLlkCA%3D%3D`;
            // const pageNo = 1;
            // // const numOfRows = 5;
            // const keyword = `${encodeURI(this.categoryTitle)}`;

            // let url = `${baseUrl}?&pageNo=${pageNo}&MobileOS=ETC&MobileApp=forJ&_type=json&keyword=${keyword}&serviceKey=${serviceKey}`;

            // if (this.contentTypeId != 0) url += `&contentTypeId=${this.contentTypeId}`

            // axios.get(url)
            //     .then((resp) => {
            //         // console.log(resp.da
            //         this.itemList = resp.data.response.body.items.item;
            //         console.log(this.itemList);
            //     })

        // DB에서 키워드 선택에 따른 정보 가져오기
          let url = `http://localhost/area/list/${this.categoryTitle}/${this.contentTypeId}`;
        //   if (this.contentTypeId != 0) url += `/${this.contentTypeId}`
          axios.get(url)
              .then((resp) => {
                  this.itemList = resp.data;
        })

                    // // 객체에 담아 DB에 저장
                    // for (var i in this.itemList) {
                    //     areaInfo.addr = this.itemList[i].addr1;
                    //     areaInfo.areaCode = this.itemList[i].areacode;
                    //     areaInfo.contentId = this.itemList[i].contentid;
                    //     areaInfo.contenttypeId = this.itemList[i].contenttypeid;
                    //     areaInfo.areaImg = this.itemList[i].firstimage;
                    //     areaInfo.mapX = this.itemList[i].mapx;
                    //     areaInfo.mapY = this.itemList[i].mapy;
                    //     areaInfo.sigunguCode = this.itemList[i].sigungucode;
                    //     areaInfo.areaName = this.itemList[i].title;
                    //     axios.post("http://localhost/area/write", areaInfo);
                    // }
            // // 공공api에서 호출
            // const baseUrl = `https://apis.data.go.kr/B551011/KorService1`;
            // const serviceKey = `%2BO%2FI7nNhPkcVOh2FuthiaVSbtU9Yvs0HFf0f%2FMd3vSqVsR1UzoM0jbIqd9rAaN7AHHG2S2IpTqcBq1q8aLlkCA%3D%3D`;
            // const pageNo = 1;
            // const numOfRows = 5;
            // const keyword = window.btoa(encodeURIComponent(this.categoryTitle));

            // let url = `${baseUrl}?serviceKey=${serviceKey}&keyword=${keyword}&pageNo=${pageNo}&numOfRows=${numOfRows}`;
            // if (this.contentTypeId) url += `&contentTypeId=${this.contentTypeId}`;

            // axios.get(url)
            //     .then((resp) => {
            //         console.log(resp);
            // })
    },
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
};
</script>

<style scoped>
input {
    position: absolute;
    border: none;
    width: 60%;
    top: 50%;
    left: 45%;
    transform: translate( -50%, -50% );
}
input:focus {
    outline: none;
}
input::placeholder {
    text-align: center;
}
img {
    position: absolute;
    width: 20px;
    cursor: pointer;
    top: 50%;
    right: 5%;
    transform: translate( -50%, -50% );
}
.user_plan_content {
    display: flex;
    justify-content: center;
}
.vcalendar {
    margin: 10px;
    z-index: 9999999;
}
.plan_left_calendar {
    width: 70%;
    height: 25%;
    background-color: #FFFFFF;
    border: 1px solid #40A3FF;
    margin: 10px auto;
    padding: 15px;
}
.plan_left_calendar_phrase{
    font-size: 15px;
    color: #0085FF;
}
.plan_left_select_list {
    width: 70%;
    height: 60%;
    background-color: #FFFFFF;
    border: 1px solid #40A3FF;
    margin: 10px auto;
    padding: 15px;
    overflow: auto;
}
/* 추후에 modify 버튼과 create 버튼을 하나의 component로 작성 예정 */
.plan_left_modify_btn {
    color: #FFF;
    font-weight: bold;
    background-color: #0085FF;
    border: 1px solid #0085FF;
    border-radius: 15px;
    width: 70%;
    height: 20%;
    cursor: pointer;
}
.plan_left_delete_btn {
    margin: 3px;
    color: #FFF;
    font-weight: bold;
    background-color: #EF5C53;
    border: 1px solid #EF5C53;
    border-radius: 15px;
    width: 90%;
    height: 7%;
    cursor: pointer;
}
.plan_left_create_btn {
    color: #FFF;
    font-weight: bold;
    background-color: #0085FF;
    border: 1px solid #0085FF;
    border-radius: 15px;
    width: 90%;
    height: 7%;
    cursor: pointer;
}
.naver_map {
    width: 100%;
    height: 661px;
}   
.plan_right_category {
    display: flex;
}
.phrase{
    display: inline-block;
    font-size: 15px;
    color: #0085FF;
    margin: 70% 25% 30%;
}
#PlanLeftBar {
    margin: auto;
    background-color: #F5F5F5;
    width: 15%;
    height: 661px;
}
#PlanMap {
    width: 57%;
}
#input_search_bar {
    margin: 5px;
    position: relative;
    width: 95%;
    height: 41px;
    border: 1px solid #000;
    background-color: #FFF;
}
#PlanRightBar {
    margin: auto;
    background-color: #F5F5F5;
    width: 20%;
    height: 661px;
    overflow: auto;
}
</style>