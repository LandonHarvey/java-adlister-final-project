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
            </div>
            <div>
            <label for="description">Description: </label>
            <input id="description" name="description" type="text" value="${updatedAd.description}">
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
</body>
</html>
