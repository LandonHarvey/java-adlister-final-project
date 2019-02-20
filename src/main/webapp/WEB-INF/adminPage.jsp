<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: landonharvey
  Date: 2/20/19
  Time: 9:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
    <title>Admin Page</title>
</head>
<body>
<jsp:include page="/WEB-INF/partials/adminPassword.jsp" />
<c:if test="${sessionScope.passcodeChecked == 1}">
    <div class="container">
        <h1>Here are all reported Posts!</h1>
    </div>
    <div class="container">
        <h1>Here are all reported Comments!</h1>
    </div>
    <div class="container">
        <h1>Here are all Users!</h1>
    </div>
    <div class="container">
        <h1>Here are all Admins</h1>
    </div>
</c:if>
</body>
</html>
