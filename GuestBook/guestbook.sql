create table user_info (
user_name varchar2(20) primary key,
user_pwd varchar2(20));

create table guestbook (
guestbook_id number(10, 0) primary key,
user_name varchar2(20) not null references user_info(user_name),
guestbook_title varchar2(100) not null,
guestbook_content varchar2(2000) not null,
guestbook_date date not null);

create table reply (
reply_id number(10, 0) primary key,
guestbook_id number(10, 0) not null references guestbook(guestbook_id),
user_name varchar2(20) not null references user_info(user_name),
reply_content varchar2(2000) not null,
reply_date date not null);

alter table user_info modify user_pwd not null;

create user hr identified by hr default tablespace users;
grant connect,resource,create view to hr;
connect hr/hr @guestbook

connect system/admin @guestbook;

insert into user_info values('admin', 'admin');
commit;
