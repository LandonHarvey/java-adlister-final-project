<%--
  Created by IntelliJ IDEA.
  User: landonharvey
  Date: 2/5/19
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Here is your Ad!</h1>
<%--<c:forEach var="ad" items="${ads}">--%>
    <div class="col-md-6">
    <c:out value="${user.username}"> </c:out>
    <h2>${ad.title}</h2>
    <p>${ad.description}</p>
    </div>
<%--</c:forEach>--%>
</div>
</body>
</html>
