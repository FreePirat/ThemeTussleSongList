<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CREATE SONG</title>
    <style><%@include file="/WEB-INF/css/bootstrap.min.css"%></style>
</head>
<body>
<header>
    <%@ include file="../nav.jsp" %>
</header>
<div class="container">
    <main role="main" class="pb-3">
        <div class="row">
            <div class="col-md-4">
                <form action="create-song" method="post">
                    <div class="form-group">
                        <label class="control-label">Name</label>
                        <input class="form-control" name="songName" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Length</label>
                        <input class="form-control" name="songLength" />
                    </div>
                    <div class="form-group">
                        <input type="submit" value="Create" class="btn btn-primary" />
                    </div>
                </form>
            </div>
        </div>
        <div>
            CREATE SONG!
            <br>
            <a href="songs" method="get">Go Back</a>
        </div>
    </main>
</div>
</body>
</html>
