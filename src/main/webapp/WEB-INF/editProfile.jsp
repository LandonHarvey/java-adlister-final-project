<%--
  Created by IntelliJ IDEA.
  User: landonharvey
  Date: 2/7/19
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
    <link href="/static/css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container"  id="createAd">
        <jsp:include page="partials/messages.jsp" />
        <h1 id="createH">Edit Profile: </h1>
            <label>Upload Profile Picture</label>
            <button onclick="openUp()" value="open" id="open">Open Picker</button>
            <button onclick="closeUp()" value="close" id="close">Close Picker</button>
            <div id="inline" class="picker-content"></div>
            <div id="content"></div>
            <form action="/editProfile" method="post">
                <div class="form-group">
                    <label for="username">Username: </label>
                    <input class="form-control" id="username" name="username" type="text" value="${sessionScope.username}">
                </div>
                <div class="form-group">
                    <label for="email">Email: </label>
                    <input class="form-control" id="email" name="email" type="text" value="${sessionScope.email}">
                </div>
                <div class="form-group">
                    <label for="oldpassword">Old Password: </label>
                    <input class="form-control" id="oldpassword" name="oldpassword" type="text" value="${sessionScope.oldpassword}">
                </div>
                <div class="form-group">
                    <label for="newpassword">New Password: </label>
                    <input class="form-control" id="newpassword" name="newpassword" type="text" value="${sessionScope.newpassword}">
                </div>
                <div class="form-group">
                    <label for="confirmpassword">Confirm Password: </label>
                    <input class="form-control" id="confirmpassword" name="confirmpassword" type="text" value="${sessionScope.confirmpassword}">
                </div>
                <button type="submit" class="btn btn-block btn-primary">Submit</button>
            </form>
    </div>
<script src="//static.filestackapi.com/filestack-js/1.x.x/filestack.min.js"></script>
<script type="text/javascript" src="/static/js/filestack.js"></script>
</body>
</html>
