<%@ page language="java" import="java.util.*, com.masterserver.core.MasterServer" pageEncoding="utf-8"%>
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
    
    <title>设置服务器自动重启时间</title>
    
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
    <%
    String[] ports = request.getParameter("ports").split(":");
    for(String str :ports){
    	System.out.println(str);
    }
    %>
    <form id="login" action="servlet/Setautorestart" method="post"> 
	设置关闭时间(:分隔)<br>
	每天几点：<input type="text" id="offhour" name="offhour"><br>
	每天几分：<input type="text" id="offmin" name="offmin"><br>
	设置开启时间(:分隔)<br>
	每天几点：<input type="text" id="onhour" name="onhour"><br>
	每天几分：<input type="text" id="onmin" name="onmin"><br>
 	<input type="submit" value="添加" />
 	</form>
 	
  </body>
</html>
