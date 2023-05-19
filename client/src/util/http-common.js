// http 요청에 사용되는 공통 module을 create하는 파일
// memberStore.js의 actions 요청에 대해 미리 세팅

import axios from "axios";

export default axios.create({
  baseURL: "http://localhost",
  headers: {
    // 파일 업로드의 경우는 제외
    // Frontend -> Backend로 보내는 data 형식
    "Content-Type": "application/json;charset=utf-8",
  },
});
