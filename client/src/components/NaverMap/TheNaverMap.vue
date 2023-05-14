<template>
    <div id="TheNaverMap">
        <h3>Naver Map</h3>
        <div class="naver_map" ref="map">

        </div>
    </div>
</template>
<script>
export default {
    name: "TheNaverMap",
    mounted() {
        let naver = window.naver;
        var mapDiv = this.$refs.map;

        const map = new naver.maps.Map(mapDiv);
        console.log(map);
    }
}
</script>
<style scoped>
#TheNaverMap {
    width: 50%;
}
.naver_map {
    width: 100%;
    height: 400px;
}   
</style>