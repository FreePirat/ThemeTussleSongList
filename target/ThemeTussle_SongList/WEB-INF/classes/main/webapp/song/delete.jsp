<%@ page import="main.models.Song" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Song song = (Song) request.getAttribute("song");
%>
<html>
<head>
    <title>Delete Song</title>
    <style>
        <%@include file="/WEB-INF/css/bootstrap.min.css" %>
        <%@include file="/WEB-INF/css/styles.css" %>
    </style>
</head>
<body>
<header>
    <%@ include file="../nav.jsp" %>
</header>
<div class="container">
    <main role="main" class="pb-3">
        <h1>Delete</h1>
        <h3>Are you sure you want to delete this?</h3>
        <div>
            <dl class="row">
                <dt class="col-sm-2">
                    Name
                </dt>
                <dd class = "col-sm-10">
                    <%=song.getName()%>
                </dd>
                <dt class="col-sm-2">
                    Length
                </dt>
                <dd class = "col-sm-10">
                    <%=song.getLength()%>
                </dd>
            </dl>
            <form action="delete-song" method="post">
                <input type="hidden" name="id" value="<%=song.getId()%>">
                <div class="form-group">
                    <input type="submit" value="Delete" class="btn btn-primary"/>
                </div>
            </form>
            <div>
                <a href="songs" method="get">Go Back</a>
            </div>
        </div>
    </main>
</div>
</body>
</html>
