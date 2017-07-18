<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script type="text/javascript" src="JS/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#sub").click(function(){
		//alert("你好");
		$.post("loginroom",{
			username:$(".textstyle").eq(0).val(),
			password:$(".textstyle").eq(1).val(),
		},function(data){
			if(data=="yes"){
				window.location.href="chat.jsp";
			}
			else{
				$("#point").show();
				$(".textstyle").eq(0).focus();
				$(".textstyle").eq(1).val("");
				
			}
		});
	});
});
</script>
<style type="text/css">
body {
	margin: 0 0;
	padding: 0 0;
	font-family: Microsoft YaHei;
}

#content {
	position: relative;
	top: 100px;
	width: 700px;
	height: 1000px;
	margin: 0 auto;
	width: 700px;
}

#left {
	width: 600px;
	position: relative;
	float: left;
	height: 1000px
}

#right {
	width: 100px;
	position: relative;
	float: left;
	height: 1000px;
}

#logo {
	width: 120px;
	margin: 0 auto;
}

#logoword {
	font-size: 28px;
	color: #655d5d;
	width: 600px;
	text-align: center;
	line-height: 60px;
}

#form {
	width: 220px;
	position: relative;
	left: 190px;
	font-size: 20px;
	color: #5c5f07;
	float: left;
}

#point {
	position: relative;
	width: 100px;
	float: left;
	height: 60px;
	left: 200px;
	display: none;
}

#pointimage {
	float: left;
	width: 30px
}

#pointword {
	width: 70px;
	float: left;
}

.textstyle {
	border-radius: 8px;
	border: 1px solid rgb(180, 180, 180);
}

#subdiv {
	width: 210px;
	margin: 0 auto;
}

#sub {
	margin-top: 20px;
	border: 1px solid rgb(180, 180, 180);
	border-radius: 8px; /* 圆角边框 */
	background-color: white;
	color: rgb(180, 180, 180);
	width: 210px;
	height: 50px;
}
</style>
</head>
<body>
	<div id="content">
		<div id="left">
			<div id="logo">
				<img src="image/logo.png" width="120px" height="130px">
			</div>
			<div id="logoword">Online Chat</div>
			<div id="form">
				<table>
					<tr>
						<td>用户名</td>
						<td><input type="text" class="textstyle"></td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input type="password" class="textstyle"></td>
					</tr>
				</table>
			</div>
			<div id="point">
				<div id="pointimage">
					<img src="image/point.jpg">
				</div>
				<div id="pointword">用户名或密码不正确！</div>
			</div>
			<div id="subdiv">
				<input type="button" value="登录" id="sub">
			</div>
			<div id="right"></div>
		</div>
	</div>
</body>
</html>