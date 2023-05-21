<template>
    <div id="TheConstruction">
        <div class="construct_wrap">
            <div class="construct_title">
                <img src="@/assets/img/page_construction.png" width="100px">
                <h1>홈페이지 오픈 준비중입니다.</h1>
            </div>
            <div class="construct_content">
                더 나은 서비스를 제공해 드리기 위해
                <br>
                최선을 다하겠습니다.
            </div>
            <div class="construct_email">
                <span class="material-symbols-outlined" style="margin-right: 5px;">
                    mail
                </span>
                chlgksdbs98@naver.com
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: "TheConstruction",
}
</script>
<style scoped>
.construct_wrap {
    width: 424px;
    margin: 0;
    padding: 50px 82px 60px;
    border: none;
    border-top: 4px solid #000;
    border-bottom: 2px solid #000;
}
.construct_content {
    color: #888;
}
.construct_email {
    margin-top: 30px;
    color: #a9a9a9;
    display: flex;
    justify-content: center;
}
#TheConstruction {
    display: flex;
    justify-content: center;
    margin: 100px 0;
}
</style>