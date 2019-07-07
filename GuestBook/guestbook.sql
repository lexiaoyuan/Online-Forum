create table user_info (
user_name varchar2(20) primary key,
user_pwd varchar2(20) not null);

create table guestbook (
guestbook_id number(10, 0) primary key,
user_name varchar2(20) not null references user_info(user_name),
guestbook_title varchar2(100) not null,
guestbook_content varchar2(2000) not null,
guestbook_date date not null);

create table reply (
reply_id number(10, 0) primary key,
guestbook_id number(10, 0) not null references guestbook(guestbook_id),
host_user_name varchar2(20) not null references user_info(user_name),
guest_user_name varchar2(20) not null references user_info(user_name),
reply_content varchar2(100) not null,
reply_date date not null);

alter table user_info modify user_pwd not null;

create user hr identified by hr default tablespace users;
grant connect,resource,create view to hr;
connect hr/hr @guestbook

connect system/admin @guestbook;

insert into user_info values('admin', 'admin');
commit;

select * from guestbook;

select * from user_info;

select * from reply;

select table_name from user_tables;

insert into user_info values('lexiaoyuan', 'lexiaoyuan');commit;

create sequence guestbook_id_seq start with 2019070400 increment by 1 nocache;

insert into guestbook values(guestbook_id_seq.nextval, 'admin', 'testTitle1', 'testContent1', SYSDATE);commit;

alter session set nls_date_format = 'YYYY-MM-DD HH24:MI:SS';

insert into guestbook values(guestbook_id_seq.nextval, 'lexiaoyuan', 'testTitle2', 'testContent2', SYSDATE);commit;

drop sequence guestbook_id_seq;

delete from guestbook;

delete from guestbook where guestbook_id=2019070432;

alter table reply modify reply_content varchar2(100);

drop table reply cascade constraints;

create sequence reply_id_seq start with 2019070600 increment by 1 nocache;

select * from reply order by guestbook_id;

select * from guestbook order by guestbook_id desc;

select * from reply order by guestbook_id desc;

alter sequence guestbook_id_seq nocache;

alter sequence reply_id_seq nocache;
