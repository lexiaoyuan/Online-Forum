### 垃圾的网上论坛
> 一个使用JSP+Servlet+JavaBean+DAO+JDBC+Oracle开发的垃圾的网上论坛
### 1.网上论坛需求分析
> 这里主要实现网上论坛的两个基本模块，即登录注册模块和留言板模块。登录注册模块主要包括用户登录、注册、忘记密码，留言板模块主要包括发表留言、回复留言、修改留言、删除留言、显示留言等功能。

- 用例图如图1-1所示。
![image](./img/useCase.png)
图1-1 网上论坛用例图
### 2.数据库设计
#### 2.1数据库概念结构设计
> 通过对网上论坛中数据及数据处理过程的分析，抽象出用户信息（user_info）、留言信息（guestbook）、回复信息（reply）3个实体，ER图如图2-1所示。

![image](./img/ER.png)
图2-1 网上论坛ER图
#### 2.2数据库逻辑结构设计
> 根据网上论坛ER图，设计出该系统的9个关系表，分别为user_info（用户信息）、guestbook（留言信息）、reply（回复信息）。表结构及其约束情况见表2-2至表2-4。

- 表2-2 user_info表结构及其约束

| 字段名 | 数据类型 | 长度 | 约束 | 说明 |
| --- | --- | --- | --- | --- | --- |
|user_name|varchar2|20|primary key|用户名|
|user_pwd|varchar2|20|not null|	用户密码|

- 表2-3 guestbook表结构及其约束

| 字段名 | 数据类型 | 长度 | 约束 | 说明 |
| --- | --- | --- | --- | --- | --- |
|guestbook_id|number|10|primary key|留言的id|
|user_name|varchar2|20|foreign key|用户名|
|guestbook_title|varchar2|100|not null|留言的标题|
|guestbook_content|varchar2|2000|not null|留言的内容|
|guestbook_date|date||not null|留言的时间|

- 表2-4 reply表结构及其约束

| 字段名 | 数据类型 | 长度 | 约束 | 说明 |
| --- | --- | --- | --- | --- | --- |
|reply_id|number|10|primary key|回复的id|
|guestbook_id|number|10|foreign key|留言的id|
|host_user_name|varchar2|20|foreign key|执行回复操作的用户名|
|guest_user_name|varchar2|20|foreign key|发留言的用户名|
|reply_content|varchar2|100|not null|回复的内容|
|reply_date|date||not null|回复的时间|

- 表之间的关系图见图2-2：
![image](./img/GUESTBOOKSQL.png)
图2-2 表之间的关系图

#### 2.3序列设计
> 为了方便产生留言的id、回复的id，在数据库中分别用下列序列产生相应编号。
- guestbook_id_seq：产生留言的id，起始值为2019070400，步长为1，不缓存，不循环。
- reply_id_seq：产生回复的id，起始值为2019070600，步长为1，不缓存，不循环。

### 3.网上论坛数据库实现
#### 3.1表的创建
```sql
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
```
#### 3.2序列的创建
```sql
create sequence guestbook_id_seq start with 2019070400 increment by 1 nocache;
create sequence reply_id_seq start with 2019070600 increment by 1 nocache;
```
### 4.网上论坛主界面设计
#### 4.1登录与注册界面设计
> 用户需要登录过后才可以使用发表留言等其他功能。用户登录界面如图4-1所示。用户在登录之前需要先进行注册，注册界面如图4-2所示。

![image](./img/login.png)
图4-1 用户登录界面

![image](./img/register.png)
 图4-2 用户注册界面
 #### 4.2 发表留言界面设计
> 登录成功后跳转到发表留言页面，在发表留言页面输入留言标题和内容，点击发表按钮，即可发表留言。发表留言界面如图4-3所示。
![image](./img/forum.png)
图4-3 发表留言界面
#### 4.3查看留言界面设计
> 每一个用户都可以查看到所有的留言，并且也可以回复所有的留言，但是只能修改和删除自己发表的留言。查看留言部分界面如图4-4所示。
