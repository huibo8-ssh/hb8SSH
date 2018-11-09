<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYpE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta content="IE=11.0000" http-equiv="X-Ua-Compatible">
		<meta name="GENERaTOR" content="MSHTML 11.00.9600.17496">
		<title>登录页面</title> 
		<script src="${basePath}js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<link href="${basePath}css/login.css" type="text/css" rel="stylesheet" /> 
		<script type="text/javascript">
		$(function(){
			//得到焦点
			$("#password").focus(function(){
				$("#left_hand").animate({
					left: "150",
					top: " -38"
				},{step: function(){
					if(parseInt($("#left_hand").css("left"))>140){
						$("#left_hand").attr("class","left_hand");
					}
				}}, 2000);
				$("#right_hand").animate({
					right: "-64",
					top: "-38px"
				},{step: function(){
					if(parseInt($("#right_hand").css("right"))> -70){
						$("#right_hand").attr("class","right_hand");
					}
				}}, 2000);
			});
			//失去焦点
			$("#password").blur(function(){
				$("#left_hand").attr("class","initial_left_hand");
				$("#left_hand").attr("style","left:100px;top:-12px;");
				$("#right_hand").attr("class","initial_right_hand");
				$("#right_hand").attr("style","right:-112px;top:-12px");
			});

			//登录按钮点击事件
			$("#bt-login").click(function(){
				$("#ff")[0].submit();
			});
		});
		</script>
	</head>
	<body>
		<div class="top_div"></div>
		<div id="bgdiv">
			<div id="cat">
				<div class="tou"></div>
				<div class="initial_left_hand" id="left_hand"></div>
				<div class="initial_right_hand" id="right_hand"></div>
			</div>
			<form action="${basePath}user/login.do" method="post" id="ff">
				<p id="p-userName">
    				<span class="u_logo"></span> 
    				<input class="ipt" type="text" placeholder="请输入用户名或邮箱" name="userName">
    			</p>
    			<p style="position:relative;">
    				<span class="p_logo"></span> 
    				<input class="ipt" id="password" type="password" placeholder="请输入密码" name="userPass">
    			</p>
    			<p id="loginError">${errorMsg}</p>
    			<div id="bt-div">
    				<p style="margin: 0px 35px 20px 45px;">
    					<span style="float: left;">
    						<a style="color: rgb(204, 204, 204);" href="#">忘记密码?</a>
    					</span> 
    					<span style="float: right;">
    						<a id="bt-reg" href="#">注册</a>
    						<a id="bt-login" href="javascript:void(0);">登录</a> 
    					</span>
    				</p>
    			</div>
			</form>
		</div>
	</body>
</HTML>