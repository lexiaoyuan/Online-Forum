<%--
  Created by IntelliJ IDEA.
  User: 徐仕成
  Date: 2019/7/6
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>修改留言</title>

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
                <a href="look-forum.jsp" class="list-group-item list-group-item-action">查看</a>
                <a href="modify-forum.jsp" class="list-group-item list-group-item-action active">修改</a>
                <a href="#" class="list-group-item list-group-item-action">我的回复</a>
                <a href="#" class="list-group-item list-group-item-action">我的留言</a>
            </div>
        </div>
        <div class="col-9">
            <h4>修改留言</h4>
            <form action="#" method="post">

                <div class="form-group mt-3">
                    <label for="guestBook_title">标题</label>
                    <input type="text" name="guestBook_title" class="form-control" id="guestBook_title"
                           value="${sessionScope.guestbook_title}" placeholder="请输入标题" required>
                </div>
                <div class="form-group">
                    <label for="guestBook_content">留言</label>
                    <textarea name="guestBook_content" class="form-control" id="guestBook_content" rows="10"
                             placeholder="请输入留言。。。" required>${sessionScope.guestbook_content}
                    </textarea>
                </div>

                <button type="submit" class="btn btn-primary btn-block mb-2">修 改</button>
            </form>
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

