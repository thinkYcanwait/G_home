<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/templatemo-style.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.singlePageNav.min.js"></script>
    <title>Home</title>
    <script>
        $(function () {
            //通过点击改变文本域提交的地址
            $("#Note").click(function () {
                $(this).removeClass("yellow-btn");
                $("#article").addClass("yellow-btn");
                $("#book").addClass("yellow-btn");
                $("#book_title").attr("hidden", "hidden");
                $("#articleForm").prop("action","/yun/user/addUserNote");
            });
            $("#article").click(function () {
                $(this).removeClass("yellow-btn");
                $("#Note").addClass("yellow-btn")
                $("#book").addClass("yellow-btn");
                $("#book_title").removeAttr("hidden");
                $("#articleForm").prop("action","/yun/user/addUserArticle");
            });
            $("#book").click(function () {
                $(this).removeClass("yellow-btn");
                $("#article").addClass("yellow-btn");
                $("#Note").addClass("yellow-btn");
                $("#book_title").removeAttr("hidden");
                $("#articleForm").prop("action","/yun/user/addUserBook");
            });
            //使文本域支持table缩进
            $("textarea").on(
                'keydown',
                function (e) {
                    if (e.keyCode == 9) {
                        e.preventDefault();
                        var indent = '        ';
                        var start = this.selectionStart;
                        var end = this.selectionEnd;
                        var selected = window.getSelection().toString();
                        selected = indent + selected.replace(/\n/g, '\n' + indent);
                        this.value = this.value.substring(0, start) + selected
                            + this.value.substring(end);
                        this.setSelectionRange(start + indent.length, start
                            + selected.length);
                    }
                });

            //页面一加载就异步加载最新文章
            $.get("loadNewArticle?time="+new Date().getTime(),{},function (data) {
                $(data).each(function (index, element) {
                    var articleename = element.articlename;
                    var username = element.username;
                    var ele = "<li>"+"<a href='/yun/read.html?username="+username+"&articlename="+articleename+"'>"+articleename+"</a>        by:"+username+"</li>";
                    $("#showNewArticle").prepend(ele);
                })
            });

            //加载异步请求退出按钮
            $.get("checkUserLogin",{},function (data) {
                if(data=='true'){
                    $("#loginOut").removeAttr("hidden");
                    $("#userPage").removeAttr("hidden");
                }else{
                    $("#loginIn").removeAttr("hidden");
                }
            });

            $("#loginButton").click(function () {
                window.open("/yun/userLogin.jsp","_self");
            });
            $("#userPage").click(function () {
                window.open("/yun/userPage.html");
            });
            $("#loginOutButton").click(function () {
                window.open("/yun/userLoginOut","_self");
            });

            $("#form-submit").click(function () {
                var subject = $("#subject").val();
                if(subject){
                    $.get("userSubjectSubm",{"subject":subject},function (data) {
                        $("#subjectMsg").html(data);
                    },"text");
                }else {
                    $("#subjectMsg").html("你的内容呢...黑人问号");
                }

            });
        });
    </script>
</head>

<body>
<div class="fixed-header">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">云歌书屋</a>
        </div>
        <nav class="main-menu">
            <ul>
                <li><a href="#home">速写</a></li>
                <li><a href="#services">热门</a></li>
                <li><a href="#clients">最新</a></li>
                <li><a href="#contact">类别</a></li>
                <li><div  id="userPage" hidden="hidden"><button id="userPageButton" class="external">个人空间</button></div></li>
                <li><div id="loginOut" hidden="hidden"><button id="loginOutButton">退出登录</button></div></li>
                <li><div id="loginIn" hidden="hidden"><button id="loginButton">登录</button></div></li>
            </ul>
        </nav>
    </div>
</div>
<div class="container">
    <section class="col-md-12 content" id="home">
        <div class="col-lg-6 col-md-6 content-item">
            <img src="images/1.jpg" alt="Image" class="tm-image">
        </div>
        <div class="col-lg-6 col-md-6 content-item content-item-1 background">
            <button id="note" class="btn normal-btn" type="button" style="margin-bottom: 5px;">便签</button>
            <button id="article" class="btn normal-btn yellow-btn" type="button" style="margin-bottom: 5px;">文章</button>
            <button id="book" class="btn normal-btn yellow-btn" type="button" style="margin-bottom: 5px;">连载</button>
            <form id="articleForm" action="/yun/user/addUserNote" method="post">
                <div style="margin-bottom: 3px"><input hidden type="text" id="book_title" value="无题大作" name="articleTitle"></div>
                <textarea class="form-control" rows="12" name="writeArea" required></textarea>
                <button type="submit" class="btn btn-smart dark-blue-btn" style="margin-top: 5px;">发布</button>
                <button type="button" class="btn btn-smart dark-blue-bordered-btn" style="margin-top: 5px;">保存</button>
            </form>
        </div>
    </section>

    <section class="col-md-12 content padding" id="services">
        <div class="col-lg-6 col-md-6 col-md-push-6 content-item">
            <img src="images/2.jpg" alt="Image" class="tm-image">
        </div>
        <div class="col-lg-6 col-md-6 col-md-pull-6 content-item background flexbox">
            <h2 class="section-title">最热文章</h2>
            <p class="section-text">无论何时何地，你的光芒总是那么夺目...</p>
            <ul class="dark-blue-text">
                <li>撒哈拉的故事</li>
                <li>小王子</li>
                <li>送你一匹马</li>
                <li>闲情偶寄</li>
            </ul>
            <p>阳光，草地，还有风...</p>
            <p>吹过你的额头...</p>
        </div>

    </section>

    <section class="col-md-12 content" id="clients">
        <div class="col-lg-6 col-md-6 content-item">
            <img src="images/3.jpg" alt="Image" class="tm-image">
        </div>
        <div class="col-lg-6 col-md-6 content-item background flexbox">
            <h2  class="section-title">最新发布</h2>
            <p class="section-text">守三五更的月，打晌午时的更...</p>
            <ul id="showNewArticle" class="dark-blue-text">
            </ul>
            <div>
                <button id="showNewArticlePage" type="button" class="btn dark-blue-bordered-btn normal-btn">文章</button>
                <button id="showNewBookPage" type="button" class="btn green-btn normal-btn">连载</button>
            </div>
        </div>
    </section>


    <section class="col-md-12 content" id="contact">
        <div class="col-lg-6 col-md-6 col-md-push-6 content-item">
            <img src="images/4.jpg" alt="Image" class="tm-image">
        </div>
        <div class="col-lg-6 col-md-6 col-md-pull-6 content-item background flexbox">
            <h2 class="section-title">联系站主</h2>
            <p class="margin-b-25">幕后程序猿并不知道要把小站做成啥样，你可以...</p>

            <!--<div class="row"> -->
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="form-group contact-field">
                        <input name="subject" type="text" class="form-control" id="subject"
                               placeholder="留下意见" required>
                    </div>
                </div>
                <div id="subjectMsg" style="color:green;margin-left: 13px"></div>
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="form-group margin-b-0">
                        <button id="form-submit" class="btn no-bg btn-contact">提交</button>
                    </div>
                </div>
        </div>

    </section>

    <footer class="col-md-12 content" id="externals">
        <div class="col-lg-6 col-md-6 last">
            <img src="images/5.png" alt="Image" class="tm-image">
        </div>
        <div class="col-lg-6 col-md-6 background last about-text-container">
            <h2 class="section-title">关于本站</h2>
            <p class="about-text" style="color:green;">就选你的意见再好，想法再有创意，那又怎样，站主还是没时间来做...</p>
            <p class="about-text" style="color: pink">是站主就了不起呀，就可以为所欲为吗...</p>
            <p class="about-text" style="color: green">sorry，是站主就是可以为所欲为...</p>
        </div>
    </footer>

</div>

<div class="text-center footer">
    <div class="container">Copyright &copy; 2016.Company name All rights reserved.<a target="_blank"
                                                                                     href="http://www.freemoban.com/">www.freemoban.com</a>
    </div>
</div>
<script>

    // Check scroll position and add/remove background to navbar
    function checkScrollPosition() {
        if ($(window).scrollTop() > 50) {
            $(".fixed-header").addClass("scroll");
        } else {
            $(".fixed-header").removeClass("scroll");
        }
    }

    $(document).ready(function () {
        // Single page nav
        $('.fixed-header').singlePageNav({
            offset: 59,
            filter: ':not(.external)',
            updateHash: true
        });

        checkScrollPosition();

        // nav bar
        $('.navbar-toggle').click(function () {
            $('.main-menu').toggleClass('show');
        });

        $('.main-menu a').click(function () {
            $('.main-menu').removeClass('show');
        });
    });

    $(window).on("scroll", function () {
        checkScrollPosition();
    });
</script>
</body>
</html>