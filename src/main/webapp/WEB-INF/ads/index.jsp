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
    <h1>Here Are all the ads!</h1>

    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <div id="borderBox">
                <div id="likeMachine">
                    <span class="glyphicon glyphicon-chevron-up"></span>
                    <span class="glyphicon glyphicon-chevron-down"></span>
                </div>
                <h2><a id="hyper" href="/adIndividual?id=${ad.id}">${ad.title}</a></h2>
                <p>${ad.description}</p>
                <p>${ad.categories}</p>
                <span>Last Edited: <c:out value="${ad.created.toString().split('T')[0]}"></c:out></span>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>