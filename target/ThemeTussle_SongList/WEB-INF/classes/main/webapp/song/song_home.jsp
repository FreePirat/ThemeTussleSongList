
<%@ page import="java.util.List" %>
<%@ page import="main.models.Song" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Song> songs = (List<Song>) request.getAttribute("songs");
%>
<html>
<head>
    <style>
        <%@include file="/WEB-INF/css/bootstrap.min.css" %>
    </style>
    <title>Songs</title>
</head>
<body>
<header>
    <%@ include file="../nav.jsp" %>
</header>

<a href="create-song">Add Song</a>
<% if (songs != null && !songs.isEmpty()) { %>
<table class="table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Length</th>
        <th>Manage</th>
    </tr>
    </thead>
    <tbody>
    <% for (Song song : songs) { %>
    <tr>
        <td>
            <%= song.getName() %>
        </td>
        <td>
            <%= song.getLength()%>
        </td>
        <td>
            <a href="detail-song?id=<%=song.getId()%>">Details</a>
            | <a href="edit-song?id=<%=song.getId()%>">Edit</a>
            | <a href="delete-song?id=<%=song.getId()%>">Delete</a>
            | <a href="add-to-playlist?id=<%=song.getId()%>">ADD TO PLAYLIST</a>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<% } else { %>
<p>No Songs Found</p>
<% } %>
</body>
</html>
