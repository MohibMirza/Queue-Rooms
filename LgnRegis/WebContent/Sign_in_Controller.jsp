
<%@page import="b_end.fb_info"%>
<%@page import="b_end.fb_info_json"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <%
 String access_token=(String)request.getParameter("access_token");
 
 fb_info_json client_info=new fb_info_json();
 fb_info obj_Client_details= client_info.call_me(access_token);

 %>
 
 Name : <%=obj_Client_details.getUser_name() %><br>
 Email : <%=obj_Client_details.getEmail() %><br>
 id : <%=obj_Client_details.getId() %><br>
 Profile Picture : <%=obj_Client_details.getProfile_picture() %><br>
 
 <img src="<%=obj_Client_details.getProfile_picture() %>"></img>
 
</body>
</html>