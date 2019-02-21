<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Register For Our Site!" />
    </jsp:include>
    <link href="/static/css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../partials/navbar.jsp" />
<div class="container">
    <jsp:include page="../partials/messages.jsp" />
    <h1>Please fill in your information.</h1>
    <form action="/createAdmin" method="post">
        <div class="form-group">
            <label for="desireId">Desired Admin's ID</label>
            <input id="desireId" name="desireId" class="form-control" type="text" value="${desireId}">
        </div>
        <div class="form-group">
            <label for="jediId">Vouching Admin ID</label>
            <input id="jediId" name="jediId" class="form-control" type="text" value="${jediId}">
        </div>
        <div class="form-group">
            <label for="passcode">Desired Admin's Passcode</label>
            <input id="passcode" name="passcode" class="form-control" type="password">
        </div>
        <div class="form-group">
            <label for="confirm_passcode">Confirm Password</label>
            <input id="confirm_passcode" name="confirm_passcode" class="form-control" type="password">
        </div>
        <label for="level">Desired Admin's Level</label>
        <Select class="form-control" name="level" id="level">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
        </Select>
        <input type="submit" class="btn btn-primary btn-block">
    </form>
</div>
</body>
</html>