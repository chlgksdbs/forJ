<template>
  <div>
    <h2 class="modifyTitle">나의 여행일정</h2>
    <div class="modifyBox">
      <FullCalendar :options="calendarOptions"></FullCalendar>
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
        initialView: 'dayGridMonth'
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