<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>注册</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        var nameflag;
        $(function () {
            $("#img1").click(function () {
                $(this).attr("src", "${pageContext.request.contextPath}/checkCodeServlet?time=" + new Date().getTime());
            });

            $("form:first").submit(function () {
                return  checkPassword() && nameCheck();
            });
        });

        function checkPassword() {
            var password = $("#inputPassword3")[0].value;
            var reg = /^[\w_-]{6,16}$/;
            var flag = reg.test(password);
            if (!flag) {
                $("#passwordCheck").html("密码格式不正确");
            } else {
                $("#passwordCheck").html("");
            }
            return flag;
        }

        //该方法用于获取回调函数的返回值
        function addBoolean(rs) {
            nameflag = rs;
        }


        function nameCheck() {
            var username = $("#inputEmail3")[0].value;
            var reg = /^[\u4e00-\u9fa5]{1,12}$/;
            var flag = reg.test(username);
            if (!flag) {
                $("#nameCheck_div").css("color", "red");
                $("#nameCheck_div").html("用户名格式不正确");
                return false;
            } else {
                //这种方法不能直接使用this，需要传递参数，因为这里的this是代表方法对象，而不是事件源对象
                /*$.get("userNameCheckedSercvlet", {username: username}, function (data) {
                    if (data.nameExists) {
                        addBoolean(data.nameExists);
                        $("#nameCheck_div").css("color", "red");
                        $("#nameCheck_div").html(data.nameMsg);
                        return !data.nameExists;
                    } else {
                        addBoolean(data.nameExists);
                        $("#nameCheck_div").css("color", "green");
                        $("#nameCheck_div").html(data.nameMsg);
                        return !data.nameExists;
                    }
                }, "json");*/
                $.ajax({
                    type:"get",
                    url:"userNameCheckedSercvlet",
                    async:false,
                    data:"username="+username,
                    dataType:"json",
                    success:function (data) {
                        if (data.nameExists) {
                            addBoolean(data.nameExists);
                            $("#nameCheck_div").css("color", "red");
                            $("#nameCheck_div").html(data.nameMsg);
                        } else {
                            addBoolean(data.nameExists);
                            $("#nameCheck_div").css("color", "green");
                            $("#nameCheck_div").html(data.nameMsg);
                        }
                    }
                });
                if (nameflag == true) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    </script>

</head>
<!--<body style="background: url('photos/image/40c0fccece2f6228181de7912f850c26.jpg')">-->
<body background="images/注册登录.jpg">
<!--<img src="photos/image/banner_1.jpg">-->

<div style="width: 50%;margin: auto;margin-top: 200px">
    <form class="form-horizontal" action="${pageContext.request.contextPath}/userZhuceServlet" method="post">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-4 control-label">账号</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="inputEmail3" placeholder="请输入账号" name="username"
                       onblur="nameCheck();">
            </div>
            <div class="col-sm-3" id="nameCheck_div"></div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-4 control-label">密码</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" name="password"
                       onblur="checkPassword()">
            </div>
            <span style="color: red" id="passwordCheck"></span>
        </div>
        <div class="form-group">
            <label for="inputPassword6" class="col-sm-4 control-label">手机号</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="inputPassword6" placeholder="请输入手机号" name="tel">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword7" class="col-sm-4 control-label">邮箱</label>
            <div class="col-sm-5">
                <input type="email" class="form-control" id="inputPassword7" placeholder="请输入邮箱" name="email">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword8" class="col-sm-4 control-label">验证码</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="inputPassword8" placeholder="请输入验证码" name="checkCode">
            </div>
            <img id="img1" src="${pageContext.request.contextPath}/checkCodeServlet">
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-10">
                <button type="submit" class="btn btn-default">注册</button>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-5">
            </div>
            <span style="color:red;">${zhuce_msg}</span>
        </div>
    </form>
    <div class="form-group">
        <div class="col-sm-offset-4 col-sm-10">
            已有账号？<a href="${pageContext.request.contextPath}/userLogin.jsp">立即登录</a>
        </div>
    </div>
</div>
</body>
</html>