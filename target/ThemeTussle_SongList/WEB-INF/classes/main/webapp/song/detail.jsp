<%@ page import="main.models.Song" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Song song = (Song) request.getAttribute("song");
%>
<html>
<head>
    <style><%@include file="/WEB-INF/css/bootstrap.min.css"%>
        <%@include file="/WEB-INF/css/styles.css" %>
    </style>

    <title>Song Details</title>
</head>
<body>
<header>
    <%@ include file="../nav.jsp" %>
</header>
<div class="container">
    <main role="main" class="pb-3">
        <h1>Details</h1>
        <div>
            <h3>Song</h3>
            <hr/>
            <dl class="row">
                <dt class="col-sm-2">
                    Name
                </dt>
                <dd class="col-sm-10">
                    <%=song.getName()%>
                </dd>
                <dt class="col-sm-2">
                    Length
                </dt>
                <dd class="col-sm-10">
                    <%=song.getLength()%>
                </dd>
            </dl>

            <div>
                <a href="songs" method="get">Go Back</a>
            </div>
        </div>
    </main>
</div>
</body>
</html>