<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/static/css/navbar.css" rel="stylesheet" type="text/css">
<c:if test="${empty sessionScope.user}">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/ads">Adlister</a>
            <a class="navbar-brand" href="/ads/create">Create Ad</a>
        </div>
        <form class="form-inline navbar-left mt-2" action="/search" method="get">
            <input class="form-control mr-sm-2" type="search" name="Search" placeholder="Search By Ad Title:">
        </form>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/login">Login</a></li>
            <li><a href="/register">Register</a></li>
            <c:if test="${sessionScope.user != null}"><li><a href="/logout">Logout</a></li></c:if>
        </ul>
    </div>
</nav>
</c:if>

<c:if test="${!empty sessionScope.user}">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/ads">Adlister</a>
            <a class="navbar-brand" href="/ads/create">Create Ad</a>
        </div>
        <form class="form-inline navbar-left mt-2" action="/search" method="get">
            <input class="form-control mr-sm-2" type="search" name="Search" placeholder="Search By Ad Title:">
        </form>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/profile">Profile</a></li>
            <c:if test="${!empty sessionScope.admin}"><li><a href="/admin">Admin Portal</a></li></c:if>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </div>
    </div>
</nav>
</c:if>
