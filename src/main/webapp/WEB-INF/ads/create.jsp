<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <jsp:include page="../partials/messages.jsp" />
        <h1>Create a new Ad</h1>
        <form action="/ads/create" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input id="title" name="title" class="form-control" type="text" value="${sessionScope.title}">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control">${sessionScope.description}</textarea>
            </div>
            <div>
                <label for="categories">Description</label>
                <select name="categories" id="categories" name="categories" multiple>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.getId()}"> <c:out value="${category.getName()}"></c:out></option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" class="btn btn-block btn-primary">
        </form>
    </div>
</body>
</html>
