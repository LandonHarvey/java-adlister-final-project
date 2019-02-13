<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
    <link href="/static/css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
        <div class="container">
            <div id="titleProfile">
                <h1>Welcome, ${sessionScope.user.username}!</h1>
                <form action="/editProfile" method="get">
                    <button class="btn btn-success" name="editProfile" value="${sessionScope.user}">Edit Profile</button>
                </form>
            </div>
        </div>
        <div class="container">
            <h1>Here are all your posted ads!</h1>
            <c:forEach var="ad" items="${userAds}">
                <div class="col-md-6">
                    <div id="borderBox">
                        <h2>${ad.title}</h2>
                        <p>${ad.description}</p>
                        <p>${ad.categories}</p>
                        <div class="buttonChange">
                            <form action="/edit" method="get" id="spacing">
                                <button class="btn btn-success" name="edit" value="${ad.id}">Edit</button>
                            </form>
                            <form action="/delete" method="get">
                                <button class="btn btn-warning" name="delete" value="${ad.id}">Delete</button>
                            </form>
                            <input type="hidden" name="adId" value="${sessionScope.user.id}">
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
</body>
</html>
