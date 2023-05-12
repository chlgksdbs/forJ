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
                <tr class="board_table_line">
                    <td>54</td>
                    <td>2박 3일 먹방으로 힐링하는 제주도 여행 일정 공유</td>
                    <td>먹깨비</td>
                    <td>2023-05-04</td>
                </tr>
                <tr class="board_table_line">
                    <td>53</td>
                    <td>지방사람의 1박 2일 인천 서울 여행 일정</td>
                    <td>군머꼰대</td>
                    <td>2023-05-03</td>
                </tr>
                <tr class="board_table_line">
                    <td>52</td>
                    <td>2박 3일 부산 커플 여행 일정 공유해요~!!</td>
                    <td>단대복학생</td>
                    <td>2023-05-02</td>
                </tr>
                <tr class="board_table_line">
                    <td>51</td>
                    <td>당일치기 수원 일정 공유합니다~</td>
                    <td>여행가도비</td>
                    <td>2023-05-01</td>
                </tr>
                <tr class="board_table_line">
                    <td>50</td>
                    <td>2박 3일 전주 일정 공유</td>
                    <td>난아이유가아니유</td>
                    <td>2023-05-01</td>
                </tr>
                <tr class="board_table_line">
                    <td>49</td>
                    <td>대전 유성구 1박 2일 일정 공유합니다!!</td>
                    <td>지방유학생</td>
                    <td>2023-04-30</td>
                </tr>
                <tr class="board_table_line">
                    <td>48</td>
                    <td>1박 2일 알찬 제주 서귀포 여행 일정</td>
                    <td>마음만은20대</td>
                    <td>2023-04-30</td>
                </tr>
                <tr class="board_table_line">
                    <td>47</td>
                    <td>일본(교토, 오사카) 4박 5일 여행 일정 공유</td>
                    <td>조민트</td>
                    <td>2023-04-30</td>
                </tr>
                <tr class="board_table_line">
                    <td>46</td>
                    <td>5년차 서울 커플의 데이트 코스 공유~~</td>
                    <td>누구없소</td>
                    <td>2023-04-29</td>
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