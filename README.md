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

![image](./img/user_info表结构.png)

- 表2-3 guestbook表结构及其约束
![image](./img/guestbook表结构.png)

- 表2-4 reply表结构及其约束
![image](./img/reply表结构.png)

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
![image](./img/查看留言.png)
图4-4 查看留言界面

#### 4.4修改留言界面设计
> 每个用户在查看留言页面可以看到在自己发表的留言的右上角有一个修改链接，点击修改，可跳转至修改留言界面，在原有内容上进行修改，点击修改按钮即可。每个用户只能修改自己的留言，不是自己发表的留言不会出现修改链接，无法修改。修改留言界面如图4-5所示。

### 5.网上论坛主要功能实现
#### 5.1网上论坛设计概述
> 本系统采用`JSP`+`Servlet`+`JavaBean`+`DAO`+`JDBC`+`Oracle`+`Bootstrap`+`Layer`+`HTML`+`CSS`+
`JavaScript`+`EL`+`JSTL`等技术开发，使用`IntelliJ IDEA`、`Tomcat`、 `Database Configuration Assistant`、`sqldeveloper`、`Chrome`、`Sublime Text`等工具进行开发。
使用的开发环境以及开发工具的版本如表5-1所示。

- 表5-1 开发环境以及开发工具
![image](./img/开发工具及环境.png)
表5-1 开发环境以及开发工具

- 架构图如图5-1所示：
![image](./img/架构图.png)
图5-1 架构图

#### 5.2系统目录结构
- 系统的目录结构如下：
```
//java代码部分
.src
│  db.properties  //连接数据库的配置文件
│
├─beans
│      GuestBook.java  //对应guestbook表，包含该表中字段的set和get方法
│      Reply.java  //对应reply表，包含该表中字段的set和get方法
│      UserInfo.java  //对应user_info表，包含表中字段的set和get方法
│
├─dao
│      GuestBook_dao.java   //进行留言的增删改查
│      Register_dao.java    //添加用户
│      Reply_dao.java   //进行回复的添加和查找显示
│      SearchGuestBook_dao.java   //通过guestbook_id查找留言，修改留言是用到
│
├─dbc
│      JdbcUtil.java   //数据库的连接和释放资源工具
│
└─servlets
        DeleteForumServlet.java   //实现删除留言操作
        ForumServlet.java   //实现发表留言操作
        LoginServlet.java   //实现登录操作
        LookForumServlet.java   //实现查看留言操作
        ModifyForumServlet.java   //实现修改留言操作
        RegisterServlet.java   //实现注册操作
        ReplyForumServlet.java   //实现回复操作
        ToModifyForumServlet.java  //协助实现修改留言操作
//web部分的目录结构
.web
│  forum.jsp   //发表留言页面
│  index.jsp   //默认入口页面、登录页面
│  look-forum.jsp   //查看留言页面
│  modify-forum.jsp   //修改留言页面
│  register.jsp   //注册页面
│
├─css
│      forum.css   //发表留言页面css样式
│      login.css  //登录、注册页面样式
│
├─img
│      avatar.png  //头像
│      github.png   //logo
│
├─layer   //layer组件库
│  
│
└─WEB-INF
    │  web.xml   //Servlet的配置
    │
    └─lib
            jstl.jar  //jstl的jar包
            standard.jar  //jstl的jar包
```
### 5.3注册功能实现
> 用户在注册页面输入用户名和密码、确认密码后，点击注册按钮，即可注册。本系统中每个用户的用户名是唯一的，不能相同，在注册时如果用户名相同，会提示该用户已注册，用户需要重新输入用户名。如果用户名为被注册过，点击注册按钮后会提示注册成功，并跳转至登录页面。



