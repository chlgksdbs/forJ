<template>
    <div id="TheCategorySearchBar">
        <div id="input_search_bar">
            <input type="text" placeholder="검색어를 입력하세요." maxlength="45" />
            <img src="@/assets/img/icon_search.png">
        </div>
    </div>
</template>
<script>
export default {
    name: 'TheCategorySearchBar',
}
</script>
<style>
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