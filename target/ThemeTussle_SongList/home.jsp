
<%@ page import="main.dao.UserDAO" %>
<%@ page import="main.dao.UserLoginDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="main.models.User" %>
<%@ page import="main.models.UserLogin" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
      String sessionToken = (String)request.getSession().getAttribute("token");
      System.out.println("HOME JSP token: " + sessionToken);
      User sessionUser = null;
      List<UserLogin> ulList = null;

      if(sessionToken != null){
          UserLoginDAO ulDAO = new UserLoginDAO();
          ulDAO.makeConnection();
            ulList = ulDAO.findAll();
          ulDAO.closeConnection();

          UserDAO uDAO = new UserDAO();
          uDAO.makeConnection();
          System.out.println("HOME JSP: FINDING USER!");
          System.out.println("HOME JSP: USER LOGIN LIST: " + ulList);

          for(UserLogin ul : ulList){
              if(ul.getLoginToken().equals(sessionToken)){
                  System.out.println("HOME JSP: FOUND USER!");
                  sessionUser = uDAO.findByID(ul.getUserId());
              }
          }
          uDAO.closeConnection();
      }
%>
<!DOCTYPE html>
<html>
<br/>
<% if(sessionUser == null) {%>
<% response.sendRedirect("login"); %>
<% } else { %> <!-- IF LOGGED IN -->
<head>
    <style>
        <%@include file="/WEB-INF/css/bootstrap.min.css" %>
        <%@include file="/WEB-INF/css/styles.css" %>
    </style>
    <title>Home</title>
</head>
<body>
<header>
    <%@ include file="nav.jsp" %>
</header>
<p>Currently logged in as : <%= sessionUser.getUserName()%> </p>
<a href="logout">Log Out</a>
<% } %>
</body>
</html>