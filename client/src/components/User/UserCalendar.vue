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
            'start': this.dateFormat(resp.data[i].planStartdate),
            'end': this.dateFormat(resp.data[i].planEnddate),
          });
        }
        console.log(this.calendarOptions);
      });
  },
  methods: {
    // TODO: 날짜를 형식에 맞게 변환 (yyyy-mm-dd)
    dateFormat(date) {
      return date.substr(0, 10);
    },
  },
};
</script>

<style scoped>
.modifyBox{
  margin: 20px 0;
}
</style>