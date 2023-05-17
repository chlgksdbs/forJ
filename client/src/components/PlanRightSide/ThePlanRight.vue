<template>
    <div id="ThePlanRight">
        <div class="plan_right_search_bar">
            <div id="input_search_bar">
                <input type="text" v-model="categoryTitle" @keyup.enter="searchCategoryTitle" placeholder="검색어를 입력하세요." maxlength="45" />
                <img src="@/assets/img/icon_search.png" @click="searchCategoryTitle">
            </div>
        </div>
        <div class="plan_right_category">
            <the-category-btn :title="'호텔'" @click.native="setContentTypeId(32)"></the-category-btn>
            <the-category-btn :title="'식당'" @click.native="setContentTypeId(39)"></the-category-btn>
            <the-category-btn :title="'쇼핑'" @click.native="setContentTypeId(8)"></the-category-btn>
            <the-category-btn :title="'여행지'" @click.native="setContentTypeId(12)"></the-category-btn>
        </div>
        <div class="plan_right_card">
            <the-search-list-card :img="require('@/assets/img/ex_img1.png')" :title="'충남대학교'"></the-search-list-card>
            <the-search-list-card :img="require('@/assets/img/ex_img2.png')" :title="'유성온천역'"></the-search-list-card>
            <the-search-list-card :img="require('@/assets/img/ex_img3.png')" :title="'대전월드컵경기장'"></the-search-list-card>
            <the-search-list-card :img="require('@/assets/img/ex_img4.png')" :title="'한밭대학교'"></the-search-list-card>
            <the-search-list-card :img="require('@/assets/img/ex_img5.png')" :title="'삼성화재 유성연수원'"></the-search-list-card>
            <the-search-list-card :img="require('@/assets/img/ex_img6.png')" :title="'에이트'"></the-search-list-card>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
// import TheCategorySearchBar from "../CategorySearchBar/TheCategorySearchBar.vue";
import TheCategoryBtn from "../CategoryBtn/TheCategoryBtn.vue";
import TheSearchListCard from "../SearchListCard/TheSearchListCard.vue";

export default {
    name: "ThePlanRight",
    components: {
        // TheCategorySearchBar,
        TheCategoryBtn,
        TheSearchListCard,
    },
    data() {
        return {
            categoryTitle: '',
            contentTypeId: 0,
            itemList: [],
        }
    },
    methods: {
        setContentTypeId(typeid) {
            this.contentTypeId = typeid;
        },
        // 검색어 입력 후, 엔터키를 치거나 버튼 누른 후의 작업을 수행하는 메서드
        searchCategoryTitle() {
            console.log(this.categoryTitle);
            console.log(this.contentTypeId);
            try {
                // 디비에서 정보 검색
                // 공공api에서 호출
                const baseUrl = `https://apis.data.go.kr/B551011/KorService1/searchKeyword1`;
                const serviceKey = `%2BO%2FI7nNhPkcVOh2FuthiaVSbtU9Yvs0HFf0f%2FMd3vSqVsR1UzoM0jbIqd9rAaN7AHHG2S2IpTqcBq1q8aLlkCA%3D%3D`;
                const pageNo = 1;
                const numOfRows = 5;
                const keyword = `${encodeURI(this.categoryTitle)}`;

                let url = `${baseUrl}?numOfRows=${numOfRows}&pageNo=${pageNo}&MobileOS=ETC&MobileApp=forJ&_type=json&keyword=${keyword}&contentTypeId=${this.contentTypeId}&serviceKey=${serviceKey}`;

                axios.get(url)
                    .then((resp) => {
                        console.log(resp.data);
                        this.itemList = resp.data.response.body.items.item;
                        console.log(this.itemList);
                })
            } catch {
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
            }
        },
    }
}
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
#input_search_bar {
    margin: 5px;
    position: relative;
    width: 95%;
    height: 41px;
    border: 1px solid #000;
    background-color: #FFF;
}
#ThePlanRight {
    margin: auto;
    background-color: #F5F5F5;
    width: 20%;
    height: 661px;
    overflow: auto;
}
.plan_right_category {
    display: flex;
}
</style>