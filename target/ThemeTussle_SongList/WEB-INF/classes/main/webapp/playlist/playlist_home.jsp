
<%@ page import="java.util.List" %>
<%@ page import="main.models.PlaylistItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<PlaylistItem> items = (List<PlaylistItem>) request.getAttribute("items");
%>
<html>
<head>
    <style>
        <%@include file="/WEB-INF/css/bootstrap.min.css" %>
    </style>
    <title>MY PLAYLIST</title>
</head>
<body>
<header>
    <%@ include file="../nav.jsp" %>
</header>

<% if (items != null && !items.isEmpty()) { %>
<table class="table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Length</th>
        <th>Manage</th>
    </tr>
    </thead>
    <tbody>
    <% for (PlaylistItem pi : items) { %>
    <tr>
        <td>
            <%= pi.getSong().getName() %>
        </td>
        <td>
            <%= pi.getSong().getLength() %>
        </td>
        <td>
            <a href="delete-from-playlist?id=<%=pi.getId()%>">Delete From Playlist</a>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<% } else { %>
<p>No Items Found</p>
<% } %>
</body>
</html>
