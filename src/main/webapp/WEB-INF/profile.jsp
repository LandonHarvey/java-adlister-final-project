<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <h1>Welcome, ${sessionScope.user.username}!</h1>
    </div>
    <div class="container">
        <h1>Here are all your posted ads!</h1>

        <c:forEach var="ad" items="${userAds}">
            <div class="col-md-6">
                <h2>${ad.title}</h2>
                <p>${ad.description}</p>
                <form action="/edit" method="get">
                    <button name="edit" value="${ad.id}">Edit</button>
                </form>
                <form action="/delete" method="get">
                    <button name="delete" value="${ad.id}">Delete</button>
                </form>
            </div>
        </c:forEach>
    </div>

</body>
</html>
