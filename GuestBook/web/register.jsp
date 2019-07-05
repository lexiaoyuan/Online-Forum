<%--
  Created by IntelliJ IDEA.
  User: 徐仕成
  Date: 2019/7/2
  Time: 23:27
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/login.css">
    <title>Forum注册</title>

    <script>
        window.onload = function () {
            let toast = "${toast}";
            if (!(toast === "")) {
                layer.msg('${toast}', {
                    icon: 2,
                    time: 1000,
                    offset: 't'
                });
            }
        }
    </script>
</head>

<body class="bg-light">
<div class="container-fluid">
    <div class="row">
        <div class="col text-center py-4 mt-2">
            <img class="img-fluid" src="./img/github.png" alt="GitHub">
        </div>
    </div>
    <div class="row">
        <div class="col text-center">
            <h4 class="text-muted font-weight-light mt-1">注册一个你的账号</h4>
        </div>
    </div>
    <div class="row row-custom">
        <div class="col">
            <div class="bg-white p-4 mt-3 border rounded">
                <form method="post" action="RegisterServlet">
                    <div class="form-group">
                        <label for="usernameInput" class="text-dark">用户名</label>
                        <input type="text" class="form-control form-control-sm" id="usernameInput" name="username" placeholder="请输入用户名" required>
                    </div>
                    <div class="form-group">
                        <label for="userpwdInput" class="text-dark d-block">密码</label>
                        <input type="password" class="form-control form-control-sm" id="userpwdInput" name="userpwd" placeholder="请输入密码" required>
                    </div>
                    <div class="form-group">
                        <label for="userpwdConfirmInput" class="text-dark d-block">确认密码</label>
                        <input type="password" class="form-control form-control-sm" id="userpwdConfirmInput" name="userpwdConfirm" placeholder="请确认密码" required>
                    </div>
                    <button type="submit" class="btn rounded-sm btn-primary-custom btn-block btn-sm text-white mt-4">注册</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="layer/layer.js"></script>
</body>

</html>