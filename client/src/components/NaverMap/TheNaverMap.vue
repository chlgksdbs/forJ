<template>
    <div id="TheNaverMap">
        <div class="naver_map" ref="map"></div>
    </div>
</template>
<script>
export default {
    name: "TheNaverMap",
    data() {
        return {
            map: null,
            marker: null,
        }
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