<template>
  <div id="PlanSelectList">
      <div class="img_box">
          <div v-if="img">
              <img class="img_box_img" :src="img">
          </div>
          <div v-else>
              <img class="img_box_no_img" src="@/assets/img/no-photos.png">
          </div>
      </div>
      <div class="text_box">
          <h5>{{ title }}</h5>
      </div>
      <div class="btn_box">
          <div class="material-symbols-outlined" style="cursor: pointer;" @click="setCost">monetization_on</div>
      </div>
      <div ref="PlanCost" class="PlanCost">
        <div style="font-size: 11px; text-align: left; margin: 5% 10% 2%;">예상경비</div>
        <input type="text" class="cost_input" v-model="cost" placeholder="예상경비를 작성하세요."/>원
        <div class="inputEnd" @click="storeCost">완료</div>
      </div>
      <!-- <div ref="PlanTime" class="PlanTime">
        <div style="font-size: 11px; text-align: left; margin: 15px 15px 5px;">예상시간</div>
        <input type="text" class="time_input" v-model="time" placeholder="예상소요시간를 작성하세요."/>
        <div class="inputEnd" @click="storeTime">완료</div>
      </div> -->
  </div>
</template>
<script>

export default {
  name: 'PlanSelectList',
  props: {
      img: String,  
      title: String,
      contentId: Number,
  },
  data() {
    return {
      cost: 0,
      // costList: [],
    }
  },
  methods: {
    //   // 삭제하고 싶은 장소의 title 값을 부모로 보내는 메서드
    // deleteOne() {
    //       this.$emit("deleteId", this.title);
    // },
    // 예상 경비 작성 블럭을 보이게하는 메서드
    setCost() {
      this.$refs.PlanCost.style.display = 'block';
    },
    // // 각 장소별 예상 시간을 작성 및 저장하는 메서드 
    // setTime() {
    //   document.querySelector("#PlanTime").style.display = 'block';
    // },
    // 각 장소별 예상 경비를 작성 및 저장하는 메서드 
    storeCost() {
      // 중복 방지. 이미 있는 contentId면 값만 변경
      // let flag = 0;
      // for (let i = 0; i < this.costList.length; i++){
      //   if (this.costList[i].contentId == this.contentId) {
      //     this.costList[i].cost = this.cost;
      //     flag = 1;
      //     break;
      //   }
      // }
      // if (flag == 0) {
      //   let list = {
      //     'contentId': this.contentId,
      //     'cost': this.cost,
      //   }
      //   this.costList.push(list);
      // }
      // console.log(this.costList);

      let list = {
        'contentId': this.contentId,
        'cost': this.cost,
      }
      // 부모한테 보내야됨
      this.$emit("storeCost", list);

      // 다시 안보이게
      this.$refs.PlanCost.style.display = 'none';
    },
  }
}
</script>
<style scoped>
#PlanSelectList {
  position: relative;
  margin: 10px auto;
  width: 90%;
  height: 20%;
  border: 1px solid #000;
  display: flex;
  align-items: center;
}
.PlanCost{
  display: none;
  position: absolute;
  margin: 5px auto;
  width: 100%;
  height: 100%;
  border: 1px solid #000;
  align-items: center;
  background-color: #f5f5f5;
}
.cost_input{
  width: 70%;
  height: 15px;
  margin: auto;
}
.inputEnd{
  /* width: 27px; */
  /* height: 27px; */
  border: 2px solid #6ab2ed;
  border-radius: 50px;
  /* font-size: 13px; */
  display: inline-block;
  text-align: right;
  /* margin-right: 10px 15px 5px; */
  background-color: #6ab2ed;
  color: #fFF;
  font-size: 70%;
  margin: 2% 23% 2% 77%;
  padding: 1%;
  width: 12%;
  height: 18%;
  cursor: pointer;
}
/* .PlanTime{
  display: none;
  position: absolute;
  margin: 5px auto;
  width: 100%;
  height: 100%;
  border: 1px solid #000;
  align-items: center;
  background-color: #f5f5f5;
} */
/* .time_input{
  width: 80%;
  margin: auto;
} */
.img_box {
position: relative;
  width: 50%;
  height:80px;
}
.img_box_img{
position: absolute;
top: 0;
left: 0;
height: 100%;
width: 100%;
}
.img_box_no_img{
position: absolute;
top: 0;
left: 0;
height: 45%;
width: 45%;
padding: 23%;
}
.text_box {
  width: 40%;
  margin: auto 5px auto 5px;
}
.material-symbols-outlined{
  margin: 5px 0;
}
.btn_box {
  width: 10%;
  margin: 1%;
}
.btn_box img {
  width: 100%;
}
</style>