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
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <div class="col-md-6">
            <c:if test="${sessionScope.updateError != null}">Error Try Again</c:if>
            <form action="/editProfile" method="post">
                <div>
                    <label for="username">Username: </label>
                    <input id="username" name="username" type="text" value="${sessionScope.user.username}">
                </div>
                <div>
                    <label for="email">Email: </label>
                    <input id="email" name="email" type="text" value="${sessionScope.user.email}">
                </div>
                <div>
                    <label for="oldpassword">Old Password: </label>
                    <input id="oldpassword" name="oldpassword" type="text" value="">
                </div>
                <div>
                    <label for="newpassword">New Password: </label>
                    <input id="newpassword" name="newpassword" type="text" value="">
                </div>
                <div>
                    <label for="confirmpassword">Confirm Password: </label>
                    <input id="confirmpassword" name="confirmpassword" type="text" value="">
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>
</body>
</html>
