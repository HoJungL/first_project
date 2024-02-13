insert into article( title, content)
values ('aaaa', '1111');
insert into article(title, content)
values ('bbbb', '2222');
insert into article(title, content)
values ('cccc', '3333');
-- article 테이블에 데이터 추가
insert into article(title, content)
values ('your best movie?', 'go');
insert into article(title, content)
values ('your soul food?', 'gogo');
insert into article(title, content)
values ('your hobby?', 'gogogo');
-- 4번 게시글의 댓글 추가
insert into comment(article_id, nickname, body)
values (4, 'Park', 'good will hunting');

insert into comment(article_id, nickname, body)
values (4, 'Kim', 'i am sam');

insert into comment(article_id, nickname, body)
values (4, 'Choi', 'escape');

-- 5번 게시글의 댓글추가
insert into comment(article_id, nickname, body)
values (5, 'Park', 'chicken');

insert into comment(article_id, nickname, body)
values (5, 'Kim', '샤브샤브');

insert into comment(article_id, nickname, body)
values (5, 'Choi', 'sushi');

-- 6번 게시글의 댓글추가
insert into comment(article_id, nickname, body)
values (6, 'Park', 'jogging');

insert into comment(article_id, nickname, body)
values (6, 'Kim', 'youtube');

insert into comment(article_id, nickname, body)
values (6, 'Choi', 'reading');
