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
            <plan-select-list v-for="selectItem in selectItems" :key="selectItem.contentId" :img="selectItem.img" :title="selectItem.title" :contentId="selectItem.contentId"  @deleteId="deleteOneCard"></plan-select-list>
            <!-- <router-link to="/plan/check"><button v-if="selectItems.length" class="plan_left_create_btn" @click="sendDates, sendItems">일정 생성</button></router-link> -->
            <router-link to="/plan/check"><button v-if="selectItems.length" class="plan_left_create_btn" @click="sendSelectInfo">일정 생성</button></router-link>
        </div>
      </div>
      <div id="PlanMap">
        <div id="map" class="naver_map" ref="map"></div>
      </div>
      <div id="PlanRightBar">
        <div class="plan_right_sido">
            <select class="sido_select_bar" v-model="sidocode">
                <option value="" selected>지역을 선택해주세요.</option>
                <option class="sido_select_option" v-for="sido in sidos" :key="sido.code" :value="sido.code">{{ sido.name }}</option>
            </select>
        </div>
        <div class="plan_right_category">
            <div class="PlanCategoryBtn">
                <div v-if="contentTypeId==32" class="text_box click_color" @click="setContentTypeId(32)">호텔</div>
                <div v-else class="text_box non_click_color" @click="setContentTypeId(32)">호텔</div>
                <div v-if="contentTypeId==39" class="text_box click_color" @click="setContentTypeId(39)">식당</div>
                <div v-else class="text_box non_click_color" @click="setContentTypeId(39)">식당</div>
                <div v-if="contentTypeId==38" class="text_box click_color" @click="setContentTypeId(38)">쇼핑</div>
                <div v-else class="text_box non_click_color" @click="setContentTypeId(38)">쇼핑</div>
                <div v-if="contentTypeId==12" class="text_box click_color" @click="setContentTypeId(12)">여행지</div>
                <div v-else class="text_box non_click_color" @click="setContentTypeId(12)">여행지</div>
            </div>
        </div>
        <div class="plan_right_search_bar">
            <div id="input_search_bar">
                <input type="text" v-model="categoryTitle" @keyup.enter="searchCategoryTitle" placeholder="검색어를 입력하세요." maxlength="45" />
                <img src="@/assets/img/icon_search.png" @click="searchCategoryTitle">
            </div>
        </div>
        <div v-if="itemList.length">
            <div v-for="item in itemList" :key="item.contentId" class="plan_right_card">
                <plan-search-list :img="item.areaImg" :title="item.title" :contentId="item.contentId" @addArea="setSelectList"></plan-search-list>
            </div>
        </div>
        <div v-else class="phrase">
            <div>지역과 키워드 버튼을</div><div>선택해주세요.</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import PlanSelectList from "./Item/PlanSelectList.vue";
import PlanSearchList from "./Item/PlanSearchList.vue";

export default {
  name: 'PlanWrite',
  components: {
    PlanSelectList,
    PlanSearchList,
  },
  data() {
    return {
        openmodal: false,
        selectItems: [],
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
        areaList: [],
        sidocode: '',
        sidos: [
            {
                code: 1,
                name: '서울'
            },
            {
                code: 2,
                name: '인천'
            },
            {
                code: 3,
                name: '대전'
            },
            {
                code: 4,
                name: '대구'
            },
            {
                code: 5,
                name: '광주'
            },
            {
                code: 6,
                name: '부산'
            },
            {
                code: 7,
                name: '울산'
            },
            {
                code: 8,
                name: '세종틀별자치시'
            },
            {
                code: 31,
                name: '경기도'
            },
            {
                code: 32,
                name: '강원도'
            },
            {
                code: 39,
                name: '제주도'
            },
        ]
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
        alert("선택 장소가 모두 삭제되었습니다.");
      },
      // 선택 목록에 있는 the-select-list-card를 개별 삭제하는 메서드
      deleteOneCard(delTitle) {
          for (let i = 0; i < this.selectItems.length; i++){
              if (this.selectItems[i].title == delTitle) {
                  this.selectItems.splice(i, 1);
                  i--;
            }
          }
          alert(delTitle + "이 삭제되었습니다.");
      },
      // 찾을 contentTypeId 결정
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
        
        // console.log(this.categoryTitle);
        // console.log(this.contentTypeId);
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
        if (this.sidocode == '') {
            alert("지역을 선택해주세요.");
        }else if (this.contentTypeId == 0) {
            alert("키워드 버튼을 선택해주세요.");
        }else {
            let keyword = '';
            if (this.categoryTitle == '') keyword = 'null';
            else keyword = this.categoryTitle;
            let url = `http://localhost/area/list/${this.contentTypeId}/${this.sidocode}/${keyword}`;
            // console.log(url);
          axios.get(url)
              .then((resp) => {
                  this.itemList = resp.data;
                //   console.log(this.itemList);
        })
        }

          

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
      // 서치목록에서 추가 클릭한 장소를 선택목록에 추가하는 메서드
      setSelectList(item) {
          let selectItem = {
              'contentId': item.addContentId,
              'title': item.addTitle,
              'img': item.addImg,
        }
          this.selectItems.push(selectItem);
        //   console.log(this.selectItems);
      },
      // PlanCheck페이지에서 선택한 여행일자와 여행지 정보를 알기위해 부모로 보내는 메서드
      sendSelectInfo() {
          let dateInfo = {
              'period': Math.abs((this.attr[0].dates.end - this.attr[0].dates.start) / (1000 * 60 * 60 * 24)),
              'start': this.attr[0].dates.start,
              'end': this.attr[0].dates.end,
          }
        //   console.log(dateInfo);
          let selectInfo = [];
          selectInfo.push(dateInfo);
          for (let i = 0; i < this.selectItems.length; i++){
              selectInfo.push(this.selectItems[i]);
          }
        //   console.log(selectInfo);
          this.$emit("selectInfo", selectInfo);
      },
      // PlanCheck페이지에서 선택한 여행지 정보를 알기위해 부모로 보내는 메서드
    //   sendItems() {
    //       this.$emit("AreaItems", this.selectItems);
    //       console.log(this.selectItems);
    //   }
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
    width: 75%;
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
    width: 75%;
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
    background-color: #6ab2ed;
    border: 1px solid #6ab2ed;
    border-radius: 15px;
    width: 70%;
    height: 20%;
    cursor: pointer;
}
.plan_left_delete_btn {
    margin: 3px;
    color: #f37d71;
    font-weight: bold;
    background-color: #fff;
    border: 1px solid #f37d71;
    border-radius: 15px;
    width: 90%;
    height: 7%;
    cursor: pointer;
}
.plan_left_delete_btn:hover{
    color: #fff;
    background-color: #f37d71;
    border: 1px solid #f37d71;
}
.plan_left_create_btn {
    color: #6ab2ed;
    font-weight: bold;
    background-color: #fff;
    border: 1px solid #6ab2ed;
    border-radius: 15px;
    width: 90%;
    height: 7%;
    cursor: pointer;
}
.plan_left_create_btn:hover{
    color: #fff;
    background-color: #6ab2ed;
    border: 1px solid #6ab2ed;
}
.naver_map {
    width: 100%;
    height: 661px;
}   
.plan_right_category {
    display: flex;
}
.plan_right_sido{
    width: 100%;
}
.sido_select_bar{
    width: 95%;
    height: 25px;
    margin: 5px 0 10px;
}
.text_box {
    font-size: 80%;
    margin: 0 2%;
    padding: 1%;
    border-radius: 10px;
    width: 20%;
    height: 13%;
}
.text_box:hover{
    background-color: #40A3FF;
}
.PlanCategoryBtn {
    display: flex;
    justify-content: space-between;
    /* border-radius: 10px; */
    width: 100%;
    height: 15%;
    /* border: 1px solid #40A3FF;
    background-color: #FFF; */
    cursor: pointer;
    /* margin: 10px auto;
    padding: 5px; */
}

.non_click_color{
    border: 1px solid #40A3FF;
    background-color: #FFF;
}
.click_color {
    background-color: #40A3FF;
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
    width: 20%;
    height: 661px;
}
#PlanMap {
    width: 52%;
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