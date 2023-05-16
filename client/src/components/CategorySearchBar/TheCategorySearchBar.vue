<template>
    <div id="TheCategorySearchBar">
        <div id="input_search_bar">
            <input type="text" v-model="categoryTitle" @keyup.enter="searchCategoryTitle" placeholder="검색어를 입력하세요." maxlength="45" />
            <img src="@/assets/img/icon_search.png" @click="searchCategoryTitle">
        </div>
    </div>
</template>
<script>
export default {
    name: 'TheCategorySearchBar',
    data() {
        return {
            categoryTitle: '',
        }
    },
    methods: {
        // 검색어 입력 후, 엔터키를 치거나 버튼 누른 후의 작업을 수행하는 메서드
        searchCategoryTitle() {
            console.log(this.categoryTitle);
        },
    },
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
</style>