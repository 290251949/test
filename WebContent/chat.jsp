<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.entity.User"%>
<%@ page import="com.dao.FriendDao"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聊天</title>
<%
		User user = null;
		if (session.getAttribute("user") != null) {
			user = (User) session.getAttribute("user");
		}
	%>
<script type="text/javascript" src="JS/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="JS/jquery.nicescroll.js"></script>
<script>
    $(document).ready(function(){
	   window.setInterval(function(){
		   $.post("sendmsg",{
			   aim:"receive",
			   name:"<%=user.getUsername()%>"
		   },function(data){
			   //alert(data);
			   var topname=$("#chatname").text();
			   //alert(topname);
			   $(data).find("message").each(function(){
				   var message=$(this);
				   var from=message.find("from").text();
				   var to=message.find("to").text();
				   var msg=message.find("msg").text();
				   var isRead=message.find("isRead").text();
				   //alert(from+to+msg+isRead);
				   $(".friend").each(function(){
					   var friendname=$(this).find(".friendname").find("name").text();
					   var friendhead=$(this).find(".friendhead").find("img").attr("src").substring(6);
					   if(from==friendname){
                          if(topname==from){
							   getRecord(friendname,friendhead);
							   //$(this).find(".msgamount").hide();
						   }
						   else{
						       $(this).find(".msgamount").show();
						   }
					   }
				   });
			   })
		   });
	   }, 1000);
    });
    
   function getRecord(friendname,friendhead){
	   //alert(friendname);
	   $.post("queryrecord",{
		   name1:"<%=user.getUsername()%>",
		   name2:friendname
	   },function(data){
		   //alert(data);
		   $(".friend").each(function(){
					   var friendname1=$(this).find(".friendname").find("name").text();
					   if(friendname1==friendname){
						   $(this).find(".msgamount").hide();
					   }
				   });
		   $("#defaultroom").hide();
		   $("#chatroom").show();
		   $("#chatname").text(friendname);
		   $("#msgpanel").empty();
		   $(data).find("message").each(function(){
			   var message=$(this);
			   var from=message.find("from").text();
			   var to=message.find("to").text();
			   var msg=message.find("msg").text();
			   var isRead=message.find("isRead").text();
			   //alert(from+to+msg+isRead);
			   if(from=="<%=user.getUsername()%>"){
				   addElementRight(msg);
				   gotobuttom();
			   }else{
				   addElementLeft(msg,friendhead);
				   gotobuttom();
			   }
		   })
	   });
   }
   
   function gotobuttom(){
	    var div = document.getElementById("msgpanel");
		div.scrollTop = div.scrollHeight;
   }
   
   function sendmsg(){
	  var msg=$("#textArea").val();
	  var thefrom="<%=user.getUsername()%>" ;
	  var theto=$("#chatname").text();
	  //alert(theto);
	  //alert(msg);
	  //dom中添加结点
	  addElementRight(msg);
	  
	  //向服务端发送消息
	  $.post("sendmsg",{
		  aim:"send",
		  from:thefrom,
		  to:theto,
		  data:msg
	  },function(){
		  //alert("发送成功");
	  });
	  $("#textArea").val("");
	  gotobuttom();
   }
   
   function addElementRight(msg){
	   var element="<div class='msgrow'><div class='msg2'><div class='msghead2'><img alt='' src='image/msgpointright.png' width='15px' height='35px'> <img alt='' src='image/<%=user.getHead()%>' width='50px' height='50px'></div><div class='msgtext2'><div class='msg'>"+msg+"</div></div></div></div>";
		  $("#msgpanel").append(element);
   }
   
   function addElementLeft(msg,head){
	   var element="<div class='msgrow'><div class='msg1'><div class='msghead'><img src='image/"+head+"' width='50px' height='50px'><img src='image/msgpoint.png' width='15px' height='35px'></div><div class='msgtext'><div class='msg'>"+msg+"</div></div></div></div>";
	   $("#msgpanel").append(element);
   }
</script>

<style type="text/css">
body {
	margin: 0 0;
	padding: 0 0;
	font-family: Microsoft YaHei;
	background-image: url("image/bg.png");
	background-size: cover;
}

#content {
	position: relative;
	top: 120px;
	width: 900px;
	height: 600px;
	margin: 0 auto;
}

#nav {
	position: relative;
	width: 250px;
	height: 600px;
	background-color: black;
	border-radius: 5px 0 0 5px;
	float: left;
}

#user {
	width: 250px;
	height: 150px;
}

#userhead {
	width: 60px;
	height: 60px;
	padding: 10px 10px;
	float: left;
}

#username {
	float: left;
	width: 150px;
	height: 60px;
	color: white;
	padding: 10px 10px;
}

#friends {
	width: 250px;
	height: 450px;
	border-radius: 0 0 0 5px;
	background-color: #333;
}

.friend {
	width: 248px;
	height: 80px;
	border: 1px solid #695d5d;
}

.friendhead {
	width: 60px;
	height: 60px;
	padding: 10px 10px;
	float: left;
}

.friendname {
	float: left;
	width: 110px;
	height: 60px;
	color: white;
	padding: 10px 10px;
	font-size: 14px;
}

.msgamount {
	background-image: url("image/msgamount.png");
	background-size: 28px 28px;
	background-repeat: no-repeat;
	margin: 10px 0px;
	width: 28px;
	height: 50px;
	float: left;
	text-align: center;
	display: none;
}
#defaultroom{
    position: relative;
	width: 650px;
	height: 600px;
	background-color: #fbfcf6;
	float: left;
	border-radius: 0 5px 5px 0;
	text-align: center;
	line-height: 600px;
	color:#ddacac;
}

#chatroom {
	position: relative;
	width: 650px;
	height: 600px;
	background-color: white;
	float: left;
	border-radius: 0 5px 5px 0;
	display:none;
}

#top {
	width: 650px;
	height: 50px
}

#chatname {
	width: 600px;
	height: 49px;
	border-bottom: 1px solid #d3d3e2;
	margin: 0 auto;
	text-align: center;
	line-height: 49px;
	color: #888585;
}

#center {
	width: 650px;
	height: 399px;
	border-bottom: 1px solid #d3d3e2;
}

#msgpanel {
	width: 600px;
	height: 399px;
	margin: 0 auto;
}

.msgrow {
	width: 590px;
	min-height: 60px;
	padding-top: 10px;
}

.msg1 {
	overflow: hidden;
}

.msg2 {
	overflow: hidden;
}

.msghead {
	float: left;
}

.msghead2 {
	float: right;
}

.msgtext {
	float: left;
	border: 1px solid #968d8d;
	border-radius: 5px;
	margin-top: 15px;
	min-height: 30px;
}

.msgtext2 {
	float: right;
	border: 1px solid #968d8d;
	border-radius: 5px;
	margin-top: 15px;
	min-height: 30px;
}

.msg {
	padding: 5px 5px;
	font-size: 14px;
	max-width: 240px;
}

#buttom {
	width: 650px;
	height: 150px;
}

#textArea {
	width: 624px;
	height: 106px;
	margin: 20px 20px;
	border: none;
	resize: none;
	font-family: Microsoft YaHei;
	font-size: 13px;
}
</style>
</head>
<body>
	<div id="content">
		<div id="nav">
			<div id="user">
				<div id="userhead">
					<img alt="" src="image/<%=user.getHead()%>" width="60px"
						height="60px">
				</div>
				<div id="username"><%=user.getUsername()%><br>&nbsp&nbsp<span style="font-size: 12px"><%=user.getLabel()%></span>
				</div>
			</div>
			<div id="friends">
				<%
					FriendDao fd = new FriendDao();
					ArrayList<User> list = fd.queryUser(user);
					int i=0;
					for (User friend : list) {
				%>
				<div class="friend">
					<div class="friendhead">
						<img src="image/<%=friend.getHead()%>" width="60px" height="60px">
					</div>
					<div class="friendname">
						<name><%=friend.getUsername()%></name><br>&nbsp&nbsp<span
							style="font-size: 12px"><%=friend.getLabel()%></span>
					</div>
					<div class="msgamount"></div>
					<script type="text/javascript">
					$(".friend").eq(<%=i%>).click(function(){
						$(".friend").css("background-color","#333");
						$(this).css("background-color","#f3b5c7");
						getRecord("<%=friend.getUsername()%>","<%=friend.getHead()%>");
					});
					</script>
				</div>
				<%
					i++;}
				%>
			</div>
		</div>
		<div id="defaultroom">
		你还没有选择好友,请选择好友进行聊天。
		</div>
		<div id="chatroom">
			<div id="top">
				<div id="chatname"></div>
			</div>
			<div id="center">
				<div id="msgpanel">
					<!-- 通过在JS中添加结点 增加内容-->
				</div>
				<script>
				$("#msgpanel").niceScroll({
				    cursorcolor: "#ccc",//#CC0071 光标颜色
				    cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
				    touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
				    cursorwidth: "5px", //像素光标的宽度
				    cursorborder: "0", // 游标边框css定义
				    cursorborderradius: "5px",//以像素为光标边界半径
				    autohidemode: true //是否隐藏滚动条
				});
				</script>
			</div>
			<div id="buttom">
				<textarea rows="" cols="" id="textArea"></textarea>
				<script type="text/javascript">
					document.getElementById("textArea").onkeydown = function(e) {
						if (e.keyCode == 13 && e.ctrlKey) {
							document.getElementById("textArea").value += "\n";
						} else if (e.keyCode == 13) {
							// 避免回车键换行
							e.preventDefault();
							// 下面写你的发送消息的代码
							sendmsg();
						}
					}
				</script>
			</div>
		</div>
	</div>
</body>
</html>