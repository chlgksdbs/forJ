<template>
  <div id="NoticeListItem">
    <div align="center">
            <table id="board_table" frame="void">
                <tr id="board_table_top"></tr>
                <tr class="board_table_line">
                    <th id="table_head_board_id">No</th>
                    <th id="table_head_title">제목</th>
                    <th id="table_head_writer">작성자</th>
                    <th id="table_head_write_date">작성일시</th>
                    <th id="table_head_hit">조회수</th>
                </tr>
                <tr class="board_table_line board_notice" v-for="item in items" :key="item.noticeId">
                    <td>{{ item.noticeId }}</td>
                    <td><router-link :to="'noticeview/' + item.noticeId">{{ item.noticeTitle }}</router-link></td>
                    <td>{{ item.noticeWriter }}</td>
                    <td>{{ dateFormat(item.noticeDate) }}</td>
                    <td>{{ item.noticeHit }}</td>
                </tr>
                <tr class="board_table_line"></tr>
            </table>
        </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "NoticeListItem",
  data() {
        return {
            items: [],
        }
    },
    props: {
        searchItems: Array,
    },
    created() {
        // axios 비동기 통신으로 server에서 notice 가져오기
        axios.get('http://localhost/notice/list')
            .then((resp) => {
                // console.log(resp.data); // 디버깅
                this.items = resp.data;
            });
    },
    methods: {
        // 날짜 포맷을 변경하는 메서드 (시, 분, 초 제외)
        dateFormat(date) {
            let dateFormat = date.substring(0, 10);
            return dateFormat;
        },
    },
    watch: {
        searchItems(arr) {
            if (arr != null) {
                this.items = arr;
                // console.log(this.items); // 디버깅
            }
            else {
                axios.get('http://localhost/notice/list')
                .then((resp) => {
                    // console.log(resp); // 디버깅
                    this.items = resp.data;
                });
            }
        },
    },
}
</script>
<style scoped>
a {
    text-decoration: none;
    color: #000000;
}
th {
    text-align: center;
    padding: 20px;
}
td {
    text-align: center;
    padding: 10px;
}
.icon_flag {
    width: 24px;
}
.board_table_line {
    border-bottom: 1px solid #dadada;
}
.board_notice {
    background-color: #E9E9E9;
}
#NoticeListItem {
    margin: 20px 50px;
}
#board_table {
    width: 100%;
    border-collapse: collapse;
}
#board_table_top {
    border-bottom: 1px solid #000000;
}
#table_head_board_id {
    width: 13%;
}
#table_head_title {
    width: 50%;
}
#table_head_writer {
    width: 12%;
}
#table_head_write_date {
    width: 15%;
}
#table_head_hit {
    width: 10%;
}
</style>