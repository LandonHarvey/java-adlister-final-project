<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Report Page" />
    </jsp:include>
    <link href="/static/css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <c:if test="${type.equals('user')}">
            <h1>User Reported: </h1>
            <c:if test="${admin.level == 3}">
                <form action="/delete" method="get">
                    <button class="btn btn-warning" name="deleteUser" value="${Report.id}">Delete User</button>
                </form>
            </c:if>
        </c:if>
        <c:if test="${type.equals('comment')}">
            <h1>Comment Reported:</h1>
            <form action="/delete" method="get">
                <button class="btn btn-warning" name="deleteComment" value="${Report.id}">Delete Comment</button>
            </form>
        </c:if>
        <c:if test="${type.equals('ad')}">
            <h1>Post Reported: </h1>
            <form action="/delete" method="get">
                <button class="btn btn-warning" name="delete" value="${Report.id}">Delete Post</button>
            </form>
        </c:if>

        <h3><a href="/profile?id=${Report.username}">Reported By: ${Report.username}</a></h3>
        <h3>Offense: ${Report.offense_name}</h3>
        <h3>Description of Incident: ${Report.description}</h3>
        <h3><a href="/profile?id=${Report.offender}">User Reported: ${Report.offender}</a></h3>
        <c:if test="${type.equals('ad')}">
            <h3><a href="/adIndividual?id=${Report.id}">Reported Content: ${Report.title}</a></h3>
        </c:if>
        <c:if test="${type.equals('comment')}">
            <h3>Reported Content: ${Report.title}</h3>
        </c:if>
        <h3>Report Created at: ${Report.created.toString().split(' ')[0]}</h3>
    </div>
</body>
</html>
