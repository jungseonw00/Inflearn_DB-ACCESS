drop table member;
create table member (
                     member_id varchar(10),
                     money integer not null default 0,
                     primary key (member_id)
);
insert into member(member_id, money) values ('hi1',10000);
insert into member(member_id, money) values ('hi2',20000);

-- AUTO COMMIT 끄기 --
-- EDIT > PREFERENCES > SQL EXECUTION > NEW CONNECTIONS USE AUTO COMMIT MODE 체크해제 후 편집기에서 SELECT @@autocommit; 실행 후 0이 나오면 성공

-- DB에서 락을 가지고 있어야 데이터를 수정할 수 있다.