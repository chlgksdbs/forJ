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
                <tr class="board_table_line">
                    <td>1</td>
                    <td>하하</td>
                    <td>한윤</td>
                    <td>오늘</td>
                </tr>
                <tr class="board_table_line">
                    <td>2</td>
                    <td>하하</td>
                    <td>한윤</td>
                    <td>오늘</td>
                </tr>
                <tr class="board_table_line">
                    <td>3</td>
                    <td>하하</td>
                    <td>한윤</td>
                    <td>오늘</td>
                </tr>
                <tr class="board_table_line">
                    <td>4</td>
                    <td>하하</td>
                    <td>한윤</td>
                    <td>오늘</td>
                </tr>
                <tr class="board_table_line">
                    <td>5</td>
                    <td>하하</td>
                    <td>한윤</td>
                    <td>오늘</td>
                </tr>
                <tr class="board_table_line">
                    <td>6</td>
                    <td>하하</td>
                    <td>한윤</td>
                    <td>오늘</td>
                </tr>
                <tr class="board_table_line">
                    <td>7</td>
                    <td>하하</td>
                    <td>한윤</td>
                    <td>오늘</td>
                </tr>
                <tr class="board_table_line">
                    <td>8</td>
                    <td>하하</td>
                    <td>한윤</td>
                    <td>오늘</td>
                </tr>
                <tr class="board_table_line">
                    <td>9</td>
                    <td>하하</td>
                    <td>한윤</td>
                    <td>오늘</td>
                </tr>
                <tr class="board_table_line">
                    <td>10</td>
                    <td>하하</td>
                    <td>한윤</td>
                    <td>오늘</td>
                </tr>
                <tr class="board_table_line"></tr>
            </table>
        </div>
    </div>
</template>
<script>
export default {
    name: "TheBoardList",
}
</script>
<style scoped>
th, td {
    text-align: center;
    padding: 10px;
}
.board_table_line {
    border-bottom: 1px solid #dadada;
}
#TheBoardList {
    margin: 20px 50px;
}
#board_table {
    width: 100%;
    table-layout: fixed;
    border-collapse: collapse;
}
#board_table_top {
    border-bottom: 1px solid #000000;
}
#table_head_board_id {
    width: 10%;
}
#table_head_title {
    width: 50%;
}
#table_head_writer {
    width: 25%;
}
#table_head_write_date {
    width: 15%;
}
</style>