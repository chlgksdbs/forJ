<template>
    <div id="TheNaverMap">
        <div class="naver_map" ref="map"></div>
    </div>
</template>
<script>
export default {
    name: "TheNaverMap",
    mounted() {
        var naver = window.naver; // window 객체의 naver를 변수로서 선언
        var mapDiv = this.$refs.map;

        // Naver 그린팩토리를 중심점으로 하는 옵션
        var mapOptions = {
            center: new naver.maps.LatLng(37.3595704, 127.105399),
            zoom: 14,
        };

        const map = new naver.maps.Map(mapDiv, mapOptions);
        console.log(map); // 필요 없는 명령어, 그러나 vue에서는 변수를 선언했으면 반드시 사용해야 하므로 사용
    }
}
</script>
<style scoped>
#TheNaverMap {
    width: 57%;
}
.naver_map {
    width: 100%;
    height: 661px;
}   
</style>