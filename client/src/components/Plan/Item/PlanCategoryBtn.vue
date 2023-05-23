<template>
    <div class="PlanCategoryBtn">
        <div id="text_box" class="non_click_color" @click="click">
            {{ title }}
        </div>
    </div>
</template>
<script>
export default {
    name: "PlanCategoryBtn",
    data() {
        return {
            // flag:'',
        }
    },
    props: {
        title: String,
        checkClick: String,
    },
    watch: {
        // checkClick(value) {
        //     if (value == true) {
        //         document.getElementById("text_box").classList.replace("non_click_color", "click_color");
        //      }else {
        //         document.getElementById("text_box").classList.replace("click_color", "non_click_color");
        //     }
        // },
    },
    methods: {
        click() {
            if (this.checkClick == true) {
                document.getElementById("text_box").classList.replace("non_click_color", "click_color");
             }else {
                document.getElementById("text_box").classList.replace("click_color", "non_click_color");
            }
        },
        // click(event) {
        //     if (this.checkClick===32 && event.target.classList.contains("non_click_color")) {
        //         event.target.classList.remove("non_click_color");
        //         event.target.classList.add("click_color");
        //         this.flag = true;
        //     }else {
        //         event.target.classList.remove("click_color");
        //         event.target.classList.add("non_click_color");
        //     }

        //     if (this.checkClick===39 && event.target.classList.contains("non_click_color")) {
        //         event.target.classList.remove("non_click_color");
        //         event.target.classList.add("click_color");
        //         this.flag = true;
        //     }else {
        //         event.target.classList.remove("click_color");
        //         event.target.classList.add("non_click_color");
        //     }

        //     if (this.checkClick===38 && event.target.classList.contains("non_click_color")) {
        //         event.target.classList.remove("non_click_color");
        //         event.target.classList.add("click_color");
        //         this.flag = true;
        //     }else {
        //         event.target.classList.remove("click_color");
        //         event.target.classList.add("non_click_color");
        //     }

        //     if (this.checkClick===12 && event.target.classList.contains("non_click_color")) {
        //         event.target.classList.remove("non_click_color");
        //         event.target.classList.add("click_color");
        //         this.flag = true;
        //     }else {
        //         event.target.classList.remove("click_color");
        //         event.target.classList.add("non_click_color");
        //     }


        // }
        // isClick(event) {
            // if (this.checkClick===true) {
            //     event.target.classList.remove("non_click_color");
            //     event.target.classList.add("click_color");
            // }else {
            //     event.target.classList.remove("click_color");
            //     event.target.classList.add("non_click_color");
            // }
             
        // }
    },
}
</script>
<style scoped>
#text_box {
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