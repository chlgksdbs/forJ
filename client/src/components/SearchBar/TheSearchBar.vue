<template>
    <div id="TheSearchBar" align="right">
        <div id="input_search_bar">
            <input type="text" placeholder="검색어를 입력하세요." maxlength="45" />
            <img src="@/assets/img/icon_search.png">
        </div>
    </div>
</template>
<script>
export default {
    name: "TheSearchBar",
}
</script>
<style scoped>
input {
    position: absolute;
    border: none;
    width: 180px;
    top: 50%;
    left: 50%;
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
    position: relative;
    width: 300px;
    height: 41px;
    border: 1px solid #dadada;
    border-radius: 10px;
}
</style>