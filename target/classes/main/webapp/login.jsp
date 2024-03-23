
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = (String)request.getAttribute("message");
%>
<html>
<head>
    <title>Log In</title>
</head>
<body>
LOGIN JSP
<br>
<p><%= message %></p>
<form action="login" method="post">
    <p>Login</p>
    <label>User Name:</label><input type="text" name="userName"/>
    <br/>
    <label>Password:</label><input type="password" name="password"/>
    <br/>
    <input type="submit" value="Log in">
</form>
</body>
</html>
