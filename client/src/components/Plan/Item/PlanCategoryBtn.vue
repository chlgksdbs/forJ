<template>
    <div class="PlanCategoryBtn">
        <div class="text_box non_click_color" @click="click">
            {{ title }}
        </div>
    </div>
</template>
<script>
export default {
    name: "PlanCategoryBtn",
    data() {
        return {
            flag:'',
        }
    },
    props: {
        title: String,
    },
    methods: {
        click(event) {
            if (event.target.classList.contains("non_click_color")) {
                event.target.classList.remove("non_click_color");
                event.target.classList.add("click_color");
                this.flag = true;
            }
            // } else {
            //     event.target.classList.remove("click_color");
            //     event.target.classList.add("non_click_color");
            // }
            
        }
    }
}
</script>
<style scoped>
.text_box {
    font-size: 80%;
    margin: 10px 7%;
    padding: 5px;
    border-radius: 10px;
    /* width: 18%; */
    /* height: 10%; */
}
.PlanCategoryBtn {
    /* border-radius: 10px; */
    width: 90%;
    height: 10%;
    /* border: 1px solid #40A3FF;
    background-color: #FFF; */
    cursor: pointer;
    /* margin: 10px auto;
    padding: 5px; */
}
.PlanCategoryBtn:hover{
    /* background-color: #40A3FF; */
}
.non_click_color{
    border: 1px solid #40A3FF;
    background-color: #FFF;
}
.click_color {
    background-color: #40A3FF;
}
</style>