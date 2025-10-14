/*
DDL : create, drop, alter
DML : insert, select, update, delete
DCL : grant, revoke, rollback
*/

use springweb2;

drop table if exists employee;
create table employee(
	id int,
    name varchar(50),
    dept varchar(30)
);

# 기존 테이블 필드 추가
alter table employee add column age int default 10;
alter table employee add column date date;

# 기존 테이블 필드 정보 수정
alter table employee modify column dept longtext;

# 기존 테이블 필드명 수정
alter table employee change column name nickname varchar(100);

# 기존 테이블 필드명 삭제
alter table employee drop column date;

# 특정 테이블 필드 확인
show columns from employee;

# 현재 데이터베이스 내 모든 테이블 확인
show tables;

# 제약조건 추가
alter table employee add constraint primary key(id);
alter table employee add constraint employee_name unique(name);

# 제약조건 삭제
alter table employee drop primary key;
# alter table employee drop foreign key fkName;
alter table employee drop constraint employee_id;

# 제약조건 확인
select * from information_schema.table_constraints;
select * from information_schema.table_constraints where table_schema = "springweb2";
select * from information_schema.table_constraints where table_schema = "springweb2" and table_name = "employee";

select * from employee;