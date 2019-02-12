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
    <link href="/static/css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Here is your Ad!</h1>
<%--<c:forEach var="ad" items="${ads}">--%>
    <div class="col-md-6">
        <div id="borderBox">
            <h2>${ad.title}</h2>
            <p>${ad.description}</p>
            <p>${ad.categories}</p>
            <span>Created By: <c:out value="${user.username}"> </c:out></span><br>
            <span> Last Edited: <c:out value="${ad.created.toString().split('T')[0]}"></c:out></span>
        </div>
    </div>
<%--</c:forEach>--%>
</div>
<script type="text/javascript" src="/static/js/ad.js"></script>
</body>
</html>
