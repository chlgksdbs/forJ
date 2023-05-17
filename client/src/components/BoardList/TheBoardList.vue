<template>
    <div id="TheBoardList">
        <div align="center">
            <table id="board_table" frame="void">
                <tr id="board_table_top"></tr>
                <tr class="board_table_line">
                    <th id="table_head_board_id">No</th>
                    <th id="table_head_title">제목</th>
                    <th id="table_head_writer">작성자</th>
                    <th id="table_head_write_date">작성일시</th>
                </tr>
                <tr class="board_table_line board_notice">
                    <td><img src="@/assets/img/icon_flag.png" class="icon_flag" /></td>
                    <td>5월 forJ 출석 이벤트</td>
                    <td>admin</td>
                    <td>2023-04-15</td>
                </tr>
                <tr class="board_table_line" v-for="item in items" :key="item.board_id">
                    <td>{{ item.boardId }}</td>
                    <td><router-link :to="'/detail/' + item.boardId">{{ item.title }}</router-link></td>
                    <td>{{ item.writer }}</td>
                    <td>{{ dateFormat(item.writeDate) }}</td>
                </tr>
                <tr class="board_table_line"></tr>
            </table>
        </div>
    </div>
</template>
<script>
import axios from 'axios';

export default {
    name: "TheBoardList",
    data() {
        return {
            items: [],
        }
    },
    created() {
        // axios 비동기 통신으로 server에서 list 가져오기
        axios.get('http://localhost/board/list')
            .then((resp) => {
                // console.log(resp); // 디버깅
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
}
</script>
<style scoped>
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
#TheBoardList {
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
    width: 10%;
}
#table_head_title {
    width: 55%;
}
#table_head_writer {
    width: 15%;
}
#table_head_write_date {
    width: 20%;
}
</style>