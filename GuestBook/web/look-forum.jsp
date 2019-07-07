<%--
  Created by IntelliJ IDEA.
  User: 徐仕成
  Date: 2019/7/4
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/forum.css">
    <title>Forum查看</title>

    <script>
        window.onload = function () {
            showTime();
            let toast = "${toast}";
            if (!(toast === "")) {
                layer.msg(toast, {
                    icon: 1,
                    time: 1000,
                    offset: 't'
                });
            }
        };

        function showTime() {
            //创建Date对象
            let today = new Date();
            //分别取出年、月、日、时、分、秒
            let year = today.getFullYear();
            let month = today.getMonth() + 1;
            let day = today.getDate();
            let hours = today.getHours();
            let minutes = today.getMinutes();
            let seconds = today.getSeconds();
            //如果是单个数，则前面补0
            month = month < 10 ? "0" + month : month;
            day = day < 10 ? "0" + day : day;
            hours = hours < 10 ? "0" + hours : hours;
            minutes = minutes < 10 ? "0" + minutes : minutes;
            seconds = seconds < 10 ? "0" + seconds : seconds;

            //构建要输出的字符串
            let str = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;

            //获取id=nowTime的对象
            document.getElementById("nowTime").innerHTML = str;
            //延时器
            window.setTimeout("showTime()", 1000);
        }
    </script>
</head>

<body class="bg-light">
<div class="container-fluid">
    <div class="row row-custom mt-3 justify-content-center align-items-center">
        <div class="col text-right">
            <img src="./img/github.png" alt="Forum">
        </div>
        <div class="col">
            <h4 class="text-muted mt-1">Online Forum</h4>
        </div>
    </div>
    <div class="row row-custom mt-3">
        <div class="col-3">
            <div class="card">
                <img class="card-img-top mx-auto d-block mt-3" src="./img/avatar.png" alt="avatar"
                     style="width: 48px !important;">
                <div class="card-body text-center">
                    <h5 class="card-title mt-n2">${sessionScope.username}</h5>
                    <p class="card-text mt-n1 mb-n2">
                        <small id="nowTime"></small>
                    </p>
                </div>
                <%--<div class="card-footer text-center">--%>
                <%--<a href="#" class="card-link">退出登录</a>--%>
                <%--</div>--%>
            </div>
            <div class="list-group mt-3">
                <a href="forum.jsp" class="list-group-item list-group-item-action">发表</a>
                <a href="LookForumServlet" class="list-group-item list-group-item-action active">查看</a>
                <a href="#" class="list-group-item list-group-item-action">我的回复</a>
                <a href="#" class="list-group-item list-group-item-action">我的留言</a>
            </div>
        </div>
        <div class="col-9">
            <ul class="list-unstyled">
                <c:forEach var="guestBookList" items="${guestBookList}">
                    <li class="media mb-4 bg-white p-3 shadow rounded">
                        <img class="mr-3" src="./img/github.png" alt="头像">
                        <div class="media-body">
                            <div class="row justify-content-between">
                                <div class="col-8">
                                    <h5 class="mt-0 mb-1">${guestBookList.guestbook_title}</h5>
                                </div>
                                <div class="col-4">
                                    <small class="float-right">
                                        <c:if test="${guestBookList.user_name == sessionScope.username}">
                                            <a href="ToModifyForumServlet?guestbook_id=${guestBookList.guestbook_id}">修改</a>
                                            <a href="DeleteForumServlet?guestbook_id=${guestBookList.guestbook_id}"
                                               class="ml-2">删除</a>
                                        </c:if>
                                    </small>
                                </div>
                            </div>

                            <p>
                                <small>${guestBookList.user_name}</small>
                                <small class="float-right">${guestBookList.guestbook_date}</small>
                            </p>
                            <p>${guestBookList.guestbook_content}</p>

                                <%--回复--%>
                            <c:forEach var="replyList" items="${replyList}">
                                <c:if test="${guestBookList.guestbook_id == replyList.guestbook_id}">
                                    <div class="border-top border-light py-1">
                                        <p class="mb-n1">
                                            <small class="text-primary">${replyList.host_user_name}</small>
                                            <small>回复</small>
                                            <small class="text-primary">${replyList.guest_user_name} :</small>
                                            <small class="float-right">${replyList.reply_date}</small>
                                        </p>
                                        <small>${replyList.reply_content}</small>
                                    </div>
                                </c:if>
                            </c:forEach>


                            <form method="post" action="ReplyForumServlet?guestbook_id=${guestBookList.guestbook_id}">

                                <div class="form-row mt-2">
                                    <div class="col-10">
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-sm" name="replyText"
                                                   placeholder="回复..." maxlength="100" required>
                                        </div>
                                    </div>
                                    <div class="col-2">
                                        <button type="submit" class="btn btn-primary btn-sm">回复</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="layer/layer.js"></script>
</body>

</html>
