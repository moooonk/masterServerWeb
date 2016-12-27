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
    </body>
</html>
