<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>
        function checkName() {
            var name = $("#inputEmail3")[0].value;
            var reg = /^[\u4e00-\u9fa5]{1,12}$/;
            var flag = reg.test(name);
            if(!flag){
                $("#nameCheck").html("用户名格式不正确");
            }
            return flag;
        }
        function checkPassword() {
            var password = $("#inputPassword3")[0].value;
            var reg = /^[\w_-]{0,16}$/;
            var flag = reg.test(password);
            if(!flag){
                $("#passwordCheck").html("密码格式不正确");
            }
            return flag;
        }

        $(function () {
            $("form:first").submit(function () {
                return checkPassword() && checkName();
            });
        });


    </script>
</head>
<!--<body style="background: url('photos/image/40c0fccece2f6228181de7912f850c26.jpg')">-->
<body background="images/注册登录.jpg">
<!--<img src="photos/image/banner_1.jpg">-->

<div style="width: 50%;margin: auto;margin-top: 200px">
    <form class="form-horizontal" action="${pageContext.request.contextPath}/userLoginServlet" method="get">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-4 control-label">账号</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="inputEmail3" placeholder="请输入账号" name="username" onblur="checkName(this);">
            </div>
            <span style="color: red" id="nameCheck"></span>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-4 control-label">密码</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" name="password" onblur="checkPassword(this);">
            </div>
            <span style="color: red" id="passwordCheck"></span>
        </div>
        <div class="form-group">
            <label for="inputPassword4" class="col-sm-4 control-label">验证码</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="inputPassword4" placeholder="请输入验证码" name="checkCode">
            </div>
            <img src="${pageContext.request.contextPath}/checkCodeServlet">
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" checked> 记住账号
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-10">
                <button type="submit" class="btn btn-default" >登录</button>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-5">
            </div>
            <span style="color: red">${login_msg}</span>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-10">
                没有账号？<a href="${pageContext.request.contextPath}/userZhuce.jsp">立即注册</a>
            </div>
        </div>
    </form>
</div>


</body>
</html>