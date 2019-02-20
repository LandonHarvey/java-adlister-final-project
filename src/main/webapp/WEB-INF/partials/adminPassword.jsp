<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: landonharvey
  Date: 2/20/19
  Time: 9:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <c:if test="${sessionScope.passcodeChecked != 1}">
    <div class="container">
        <c:if test="${sessionScope.invalid != null}">Error Try Again</c:if>
        <h1>Please Type Admin Passcode: </h1>
        <form action="/admin" method="POST">
            <div class="form-group">
                <label for="passcode">Password</label>
                <input id="passcode" name="passcode" class="form-control" type="password">
            </div>
            <input type="submit" class="btn btn-primary btn-block" value="Submit">
        </form>
    </div>
    </c:if>
</body>
</html>
