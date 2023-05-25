use forj;

insert into forj_user(user_id, user_pw, user_name, user_nickname, user_gender, user_phone, user_email, user_authority)
values
('admin', 'admin', '관리자', '관리자', '남자', '010-0000-0000', 'admin@forj.com', 'admin');

insert into forj_user(user_id, user_pw, user_name, user_nickname, user_gender, user_phone, user_email)
values
('zosunny', '1234', '해린', '이쁘니', '여자', '010-0000-0000', 'zosunny@forj.com'),
('chlgksdbs', '1234', '한윤', '귀요미', '남자', '010-0000-0000', 'chlgksdbs@forj.com');

insert into forj_board (title, writer, write_date, content)
values
('2박 3일 먹방으로 힐링하는 제주도 여행 일정 공유', 'zosunny', now(), '2박 3일 제주도'),
('지방사람의 1박 2일 인천 서울 여행 일정', 'admin', now(), '1박 2일 인천'),
('2박 3일 부산 커플 여행 일정 공유해요~!!', 'chlgksdbs', now(), '2박 3일 부산'),
('5년차 서울 커플의 데이트 코스 공유~~', 'chlgksdbs', now(), '## 글 내용

글 작성화면 중, 내용 작성란 입니다.
😁😁😁
<hr>');

insert into forj_notice (notice_title, notice_writer, notice_date, notice_content)
values ('5월 forJ 출석 이벤트', 'admin', now(), '5월 forJ 출석 이벤트 관련 공지사항'),
('forJ 이용 수칙', 'admin', now(), 'forJ 이용 수칙 관련 공지사항');
