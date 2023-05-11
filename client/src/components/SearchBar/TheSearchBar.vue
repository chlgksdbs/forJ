<template>
    <div id="TheSearchBar" align="right">
        <div id="input_search_bar">
            <input type="text" placeholder="검색어를 입력하세요." />
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
    border: none;
    margin: auto;
    width: 160px;
    height: 35px;
}
input:focus {
    outline: none;
}
#input_search_bar {
    width: 335px;
    height: 41px;
    border: 1px solid #dadada;
    border-radius: 10px;
}
</style>