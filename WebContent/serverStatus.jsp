<%@ page language="java" import="java.util.*, com.masterserver.core.MasterServer, com.masterserver.core.MasterServerStatistics" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	if (request.getSession(true).getAttribute("islogin") != null
			&& request.getSession(true).getAttribute("name") != null) {
		if (request.getSession(true).getAttribute("islogin")
				.equals("yes")) {
		} else {
			response.sendRedirect("/masterServerWeb/Index.jsp");
		}
	}else{
			response.sendRedirect("/masterServerWeb/Index.jsp");
	}
	
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>MasterServer控制系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<script language="JavaScript">
		function setaction1() {
			document.forms[0].action = "/masterServerWeb/Action?action=1";
			document.forms[0].submit();
		}
		function setaction2() {
			document.forms[0].action = "/masterServerWeb/Action?action=2";
			document.forms[0].submit();
		}
		function setaction3() {
			document.forms[0].action = "/masterServerWeb/Action?action=3";
			document.forms[0].submit();
		}
		function setaction4() {
			document.forms[0].action = "/masterServerWeb/Action?action=4";
			document.forms[0].submit();
		}
		function setaction5() {
			document.forms[0].action = "/masterServerWeb/Action?action=5";
			document.forms[0].submit();
		}
		function setaction6() {
			document.forms[0].action = "/masterServerWeb/Action?action=6";
			document.forms[0].submit();
		}
		function setaction7() {
			document.forms[0].action = "/masterServerWeb/Action?action=7";
			document.forms[0].submit();
		}
	</script>
	状态：
    <% 
    if(MasterServer.instance.isActivity()){
    	out.write("开启");
    }else{
    	out.write("关闭");
    }
    %>
    <br>
    -------------统计-------------<br>
	当前分钟请求量：<%out.write(MasterServerStatistics.instance.getCurrentMinCount()+"次"); %><br>
	当前小时请求量：<%out.write(MasterServerStatistics.instance.getCurrentHourCount()+"次"); %><br>
	当前天请求量：<%out.write(MasterServerStatistics.instance.getCurrentDayCount()+"次"); %><br>
	<br>
	上一分钟请求量：<%out.write(MasterServerStatistics.instance.getLastMinCount()+"次"); %><br>
	上一小时请求量：<%out.write(MasterServerStatistics.instance.getLastHourCount()+"次"); %><br>
	上一天请求量：<%out.write(MasterServerStatistics.instance.getLastDayCount()+"次"); %><br>
	-----------自动开关------------<br>
	自动开启时间：<%out.write(MasterServer.instance.getOnHour()+":"+MasterServer.instance.getOnMin()); %><br>
	自动关闭时间：<%out.write(MasterServer.instance.getOffHour()+":"+MasterServer.instance.getOffMin()); %><br>
    <form id="serverStatus" action="" method="post">
    	<input type="submit" name="startprotect" value="开启服务" onclick = "setaction1()"/>
    	<input type="submit" name="startprotect" value="关闭服务" onclick = "setaction2()"/>
    	<input type="submit" name="startprotect" value="设置定时开关" onclick = "setaction3()"/>
    </form>
    </body>
</html>
