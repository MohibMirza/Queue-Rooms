
<%@page import="b_end.fb_info"%>
<%@page import="b_end.fb_info_json"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
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
 
 <script>

 var fb_id="<%=obj_Client_details.getId() %>";
 var fb_email="<%=obj_Client_details.getEmail() %>";
 var fb_name="<%=obj_Client_details.getUser_name() %>";
 var fb_pic="<%=obj_Client_details.getProfile_picture() %>";
 
 
 var form = $('<form action="' + 'fb_sign_in' + '" method="post">' 
		  + '<input type="text" name="id" value="' + fb_id + '" />' 
		  + '<input type="text" name="name" value="' + fb_name + '" />'
		  + '<input type="text" name="email" value="' + fb_email + '" />'
		  + '<input type="text" name="image" value="' + fb_pic + '" />'
		  +'</form>');
   $('body').append(form);
   console.log(form);
   form.submit();
 
 </script>
 
</body>
</html>