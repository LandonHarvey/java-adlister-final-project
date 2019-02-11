<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: landonharvey
  Date: 2/6/19
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
    <%--<script src=“${pageContext.request.contextPath}/static/js/search.js></script>--%>
    <title>Search</title>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Ad Results!</h1>
    <c:choose>
        <c:when test="${ads != null}">
    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <h2>${ad.title}</h2>
            <p>${ad.description}</p>
            <p>${ad.categories}</p>
        </div>
    </c:forEach>
        </c:when>
        <c:when test="${catSearch != null}">
            <c:forEach var="cat" items="${catSearch}">
                <h2>${cat.title}</h2>
                <p>${cat.description}</p>
                <p>${cat.categories}</p>
            </c:forEach>
        </c:when>
    </c:choose>
        <div class="col-md-6">
            <form name="catCheck" action="/search" method="post">
                <c:forEach var="category" items="${categories}">
                    <input type="checkbox" name="categoryname" value="${category.getId()}" onclick="chkcontrol(${category.getId()})">${category.getName()}<br>
                </c:forEach>
                <input type="submit" class="btn btn-block btn-primary">
            </form>
        </div>
</div>
<script type="text/javascript" src="/static/js/categorySearch.js"></script>
</body>
</html>
