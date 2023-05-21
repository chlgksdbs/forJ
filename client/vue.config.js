const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

module.exports = {
  pages: {
    index: { // 1. 설정할 page의 name
      entry: 'src/main.js', // 2. 설정한 page의 entry 파일 위치
      template: 'public/index.html', // 3. 기본 template 파일
      filename: "index.html", // 4. 빌드 결과물로 떨어질 html 파일 이름
      title: '당신만의 여행 플래너, forJ' // 5. page의 title
    }
  }
}
