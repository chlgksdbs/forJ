<template>
    <div id="TheHeading">
        <div id="header_left"></div>
        <div id="header_center">
            <img src="@/assets/img/logo.png">
        </div>
        <div id="header_right">
            <router-link to="#">마이페이지</router-link>
            <router-link to="#">로그아웃</router-link>
        </div>
    </div>
</template>
<script>
export default {
    name: "TheHeading",
}
</script>
<style scoped>
img {
    width: 250px;
}
a {
    text-decoration: none;
    color: #888;
    margin: 0 10px;
}
#TheHeading {
    display: flex;
}
#header_left {
    flex: 1;
}
#header_center {
    flex: 3;
    text-align: center;
}
#header_right {
    flex: 1;
    text-align: center;
    margin: auto;
}
</style>