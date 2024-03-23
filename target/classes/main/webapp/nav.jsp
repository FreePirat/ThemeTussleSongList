<%
    String currentURL = request.getRequestURL().toString();
    String[] urlParts = currentURL.split("/");
    String currentHost1 = urlParts[2];
    String currentHost2 = urlParts[3];
%>
<nav class="navbar navbar-expand-sm navbar-toggleable-sm navbar-light bg-white border-bottom box-shadow mb-3">
    <div class="container-fluid">
        <a href="http://<%=currentHost1%>/<%=currentHost2%>/home" class="navbar-brand" >Home</a>
        <div class="navbar-collapse collapse d-sm-inline-flex justify-content-between">
            <ul class="navbar-nav flex-grow-1">
                <li class="nav-item">
                    <a class="nav-link text-dark" href="songs">Manage Songs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="playlist">Playlist</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
