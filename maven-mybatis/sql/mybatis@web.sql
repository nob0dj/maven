--student테이블 생성
-- drop table student
create table student(
    id number,
    name varchar2(100) not null,
    tel char(11) not null,
    created_at date default sysdate,
    constraints pk_student_id primary key(id)
);
create sequence seq_student_id;

select * from student order by no desc;

--delete from student where name = '마동석';

insert into student values (seq_student_id.nextval, '홍길동', '01012341234', default);
insert into student values (seq_student_id.nextval, '신사임당', '01022223333', default);
insert into student values (seq_student_id.nextval, '이순신', '01033334444', default);


--mybatis 동적쿼리
--select * from student where no = #{no}
--1. 컬럼명/테이블명 식별자에 대한 동적처리 ${}
--2. 조건식 if, choose
--3. in 조건절 동적처리 where dept_code in ('D5', 'D6', 'D7')





-- web계정에서 sh계정 데이터 접근 처리
-- web계정
select * from sh.employee;
select * from sh.department;
select * from sh.job;

---------------------------------------
-- sh계정
---------------------------------------
--접속계정변경
grant all on sh.employee to web;
grant select on sh.department to web;
grant select on sh.job to web;
---------------------------------------

--동의어 Synonym 생성
--sh.employee emp
--관리자로부터 create synonym 권한 먼저 부여받을 것
create synonym emp for sh.employee;
create synonym dept for sh.department;
create synonym job for sh.job;

----------------------------------------
--관리자계정 system
----------------------------------------
grant create synonym to web;
----------------------------------------

select * from emp;
select * from dept;
select * from job;
