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
    <link href="/static/css/base.css" rel="stylesheet" type="text/css">
    <title>Search</title>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div id="searchContainer">
<div>
    <div id="searchSpecial" class="col-md-5">
        <h1>Ad Results!</h1>
        <c:choose>
            <c:when test="${ads != null}">
        <c:forEach var="ad" items="${ads}">
            <div class="col-md-12">
                <div class="borderBox2">
                    <h2><a id="hyper" href="/adIndividual?id=${ad.id}">${ad.title}</a></h2>
                    <p>${ad.description}</p>
                    <p>${ad.categories}</p>
                    <span>Last Edited: <c:out value="${ad.created.toString().split('T')[0]}"></c:out></span>
                </div>
            </div>
        </c:forEach>
            </c:when>
            <c:when test="${catSearch != null}">
                <c:forEach var="cat" items="${catSearch}">
                    <div class="col-md-12">
                        <div class="borderBox2">
                            <h2><a id="hyper2" href="/adIndividual?id=${cat.id}">${cat.title}</a></h2>
                             <p>${cat.description}</p>
                             <p>${cat.categories}</p>
                            <span>Last Edited: <c:out value="${cat.created.toString().split('T')[0]}"></c:out></span>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>
    </div>
</div>
<div class="col-md-5">
    <div>
        <h1>Category Search</h1>
        <hr>
        <form name="catCheck" action="/search" method="post">
            <c:forEach var="category" items="${categories}">
                <input type="checkbox" name="categoryname" value="${category.getId()}" onclick="chkcontrol(${category.getId()})">${category.getName()}<br>
            </c:forEach>
            <input type="submit" class="btn btn-block btn-primary">
        </form>
    </div>
</div>
</div>
<script type="text/javascript" src="/static/js/categorySearch.js"></script>
</body>
</html>
