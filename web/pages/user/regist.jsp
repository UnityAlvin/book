<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%-- 静态包含Base标签、CSS样式、jQuery文件 --%>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function () {
            $("#sub_btn").click(function () {
                var name = $("#username").val();
                var nameReg = /^\w{5,12}$/;

                if (!nameReg.test(name)) {
                    $("span.errorMsg").text("用户名不合法");
                    return false;
                }


                var pwd = $("#password").val();
                var pwdReg = /^\w{5,12}$/;

                if (!pwdReg.test(pwd)) {
                    $("span.errorMsg").text("密码不合法");
                    return false;
                }

                var rePwd = $("#repwd").val();

                if (pwd != rePwd) {
                    $("span.errorMsg").text("两次密码输入不一致");
                    return false;
                }

                var email = $("#email").val();
                var emailReg = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;

                if (!emailReg.test(email)) {
                    $("span.errorMsg").text("邮箱不合法");
                    return false;
                }

                var code = $.trim($("#code").val());


                if (code == null || code == "") {
                    $("span.errorMsg").text("验证码不能为空");
                    return false;
                }

                $("span.errorMsg").text("");
            })

            //切换验证码，因为浏览器缓存，需要在切换验证码图片路径的后面加上一个可变的参数，
            //保证每一次浏览器缓存的验证码不同
            $("#code_img").click(function () {
                this.src = "${basePath}kaptcha.jpg?d=" + new Date();
            })

            $("#username").blur(function () {
                var username = this.value;

                $.getJSON("userServlet","action=ajaxExistsUsername&username="+username,function (data) {
                    if(data.existsUsername){
                        $("span.errorMsg").text("用户名已存在");
                    }else {
                        $("span.errorMsg").text("");
                    }
                })
            })
        })
    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
    <div id="login_header">
        <img class="logo_img" alt="" src="static/img/logo.gif">
    </div>

    <div class="login_banner">

        <div id="l_content">
            <span class="login_word">欢迎注册</span>
        </div>

        <div id="content">
            <div class="login_form">
                <div class="login_box">
                    <div class="tit">
                        <h1>注册尚硅谷会员</h1>
                        <span class="errorMsg">${ requestScope.msg }</span>
                    </div>
                    <div class="form">
                        <form action="userServlet" method="post">
                            <input type="hidden" name="action" value="regist">
                            <label>用户名称：</label>
                            <input class="itxt" type="text" placeholder="请输入用户名"
                                   autocomplete="off" tabindex="1" name="username" id="username"
                                   value="${ requestScope.username }"/>
                            <br/>
                            <br/>
                            <label>用户密码：</label>
                            <input class="itxt" type="password" placeholder="请输入密码"
                                   autocomplete="off" tabindex="1" name="password" id="password"/>
                            <br/>
                            <br/>
                            <label>确认密码：</label>
                            <input class="itxt" type="password" placeholder="确认密码"
                                   autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
                            <br/>
                            <br/>
                            <label>电子邮件：</label>
                            <input class="itxt" type="text" placeholder="请输入邮箱地址"
                                   autocomplete="off" tabindex="1" name="email" id="email"
                                   value="${ requestScope.email }"/>
                            <br/>
                            <br/>
                            <label>验证码：</label>
                            <input class="itxt" type="text" style="width: 90px;" id="code" name="code" />
                            <img alt="" src="kaptcha.jpg"  style="float: right; margin-right: 40px;width: 150px;" id="code_img">
                            <br/>
                            <br/>
                            <input type="submit" value="注册" id="sub_btn"/>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <%--静态包含页脚内容--%>
    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>