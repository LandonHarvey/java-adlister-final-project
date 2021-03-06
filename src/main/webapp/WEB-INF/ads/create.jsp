<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
    <link href="/static/css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container" id="createAd">
        <jsp:include page="../partials/messages.jsp" />
        <h1 id="createH">Create a new Ad</h1>
        <form action="/ads/create" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input id="title" name="title" class="form-control" type="text" minlength="5" maxlength="50" value="${sessionScope.title}">
                <small id="titleRemain"><span id="titleLength">50</span> characters remaining</small>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" maxlength="5000">${sessionScope.description}</textarea>
                <small id="desRemain"><span id="desLength">5000</span> characters remaining</small>
            </div>
            <div>
                <label for="categories">Categories</label>
                <select class="form-control" name="categories" id="categories" multiple>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.getId()}"> <c:out value="${category.getName()}"></c:out></option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" class="btn btn-block btn-primary">
        </form>
    </div>

<script type="text/javascript" src="/static/js/create.js"></script>
</body>
</html>
