<%@ page import="main.models.Song" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = (String) request.getAttribute("message");
    Song song = (Song) request.getAttribute("song");
%>
<html>
<head>
    <title>EDIT</title>
    <style><%@include file="/WEB-INF/css/bootstrap.min.css"%></style>
</head>
<body>
<header>
    <%@ include file="../nav.jsp" %>
</header>
<div class="container">
    <main role="main" class="pb-3">
        <% if(message != null) { %>
        <p><%= message%></p>
        <% } %>
        <div class="row">
            <div class="col-md-4">
                <form action="edit-song" method="post">
                    <input type="hidden" name="songId" value="<%= song.getId()%>">
                    <div class="form-group">
                        <label class="control-label">Name</label>
                        <input class="form-control" name="songName" value="<%= song.getName()%>" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Price</label>
                        <input class="form-control" name="songLength" value="<%= song.getLength()%>" />
                    </div>
                    <div class="form-group">
                        <input type="submit" value="Update" class="btn btn-primary" />
                    </div>
                </form>
            </div>
        </div>
        <div>
            <a href="songs" method="get">Go Back</a>
        </div>
    </main>
</div>
</body>
</html>
