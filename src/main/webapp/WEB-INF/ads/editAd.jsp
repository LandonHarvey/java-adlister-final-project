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
<div class="col-md-6">
    <jsp:include page="../partials/messages.jsp" />
        <form action="/edit" method="post">
            <div>
            <label for="title">Title: </label>
            <input id="title" name="title" type="text" value="${updatedAd.title}">
                <small id="titleRemain"><span id="titleLength">50</span> characters remaining</small>
            </div>
            <div>
            <label for="description">Description: </label>
                <textarea id="description" name="description" class="form-control">${updatedAd.description}</textarea>
                <small id="desRemain"><span id="desLength">5000</span> characters remaining</small>
            <input type="hidden" name="adId" value="${editAd}">
            </div>
            <div>
                <label for="categories">Categories</label>
                <select name="categories" id="categories" name="categories" multiple>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.getId()}"> <c:out value="${category.getName()}"></c:out></option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit"></button>
        </form>
    </div>
</div>

<script type="text/javascript" src="/static/js/create.js"></script>
</body>
</html>
