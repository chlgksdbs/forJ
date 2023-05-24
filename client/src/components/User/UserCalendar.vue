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
import FullCalendar from "@fullcalendar/vue";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";

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
        events: [
          {
            title: '전주 여행',
            start: '2023-05-21',
          },
          {
            title: '제주 여행',
            start: '2023-05-29',
            end: '2023-06-01',
          },
        ],
      }
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
.modifyBox{
  margin: 20px 0;
}
</style>