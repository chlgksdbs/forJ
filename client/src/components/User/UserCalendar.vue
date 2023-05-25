<template>
  <div>
    <h2 class="modifyTitle">나의 여행일정</h2>
    <div class="modifyBox">
      <FullCalendar 
      :options="calendarOptions"
      />
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

import FullCalendar from "@fullcalendar/vue";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";

const memberStore = "memberStore";

export default {
  name: 'UserCalendar',
  components: {
    FullCalendar,
  },
  data() {
    return {
      calendarOptions: {
        plugins: [dayGridPlugin, interactionPlugin],
        initialView: 'dayGridMonth',
        events: [],
      }
    };
  },
  computed: {
    ...mapState(memberStore, ['userInfo']),
  },
  created() {
    axios.get('http://localhost/plan/list/' + this.userInfo.userId)
      .then((resp) => {
        // console.log(resp.data[0]); // 디버깅
        for (let i = 0; i < resp.data.length; i++) {
          this.calendarOptions.events.push({
            'title': resp.data[i].planId + '번째 여행',
            'start': this.dateFormat(resp.data[i].planStartdate, 'start'),
            'end': this.dateFormat(resp.data[i].planEnddate, 'end'),
          });
        }
        // console.log(this.calendarOptions);
      });
  },
  methods: {
    // TODO: 날짜를 형식에 맞게 변환 (yyyy-mm-dd)
    dateFormat(date, val) {
      // yyyy -> 0, 4
      // mm -> 5, 7
      // dd -> 8, 10
      if (val == 'start') return date.substr(0, 10);

      let dd = parseInt(date.substr(8, 10));
      let mm = parseInt(date.substr(5, 7));
      let yy = parseInt(date.substr(0, 4));

      if (dd == 31) {
        if (mm == 12) {
          let newdate = (yy + 1) + '-' + '01-01';
          return newdate;
        }
        else {
          mm += 1;
          if (mm < 10) {
            let newdate = yy + '-0' + mm + '-01';
            return newdate;
          }
          else {
            let newdate = yy + '-' + mm + '-01';
            return newdate;
          }
        }
      }
      else {
        dd += 1;
        if (mm < 10) {
          if (dd < 10) {
            let newdate = yy + '-0' + mm + '-0' + dd;
            return newdate;
          }
          else {
            let newdate = yy + '-0' + mm + '-' + dd;
            return newdate;
          }
        }
        else {
          let newdate = yy + '-' + mm + '-' + dd;
          return newdate;
        }
      }
    },
  },
};
</script>

<style scoped>
.modifyBox{
  margin: 20px 0;
}
</style>