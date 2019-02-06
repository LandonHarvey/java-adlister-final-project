<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: landonharvey
  Date: 2/6/19
  Time: 2:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="col-md-6">
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
            <button type="submit"></button>
        </form>
    </div>
</body>
</html>
