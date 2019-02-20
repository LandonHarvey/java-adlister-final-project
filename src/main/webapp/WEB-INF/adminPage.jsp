    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Admin Page" />
    </jsp:include>
    <link href="/static/css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/partials/adminPassword.jsp" />
<c:if test="${sessionScope.passcodeChecked == 1}">
    <div class="container">
        <h1>Here are all reported Posts!</h1>
        <div class="adminReports">
            <h3>Username |</h3>
            <h3>Offense |</h3>
            <h3>Description |</h3>
            <h3>Offender |</h3>
            <h3>Title |</h3>
            <h3>TimeStamp |</h3>
            <h3>Actions</h3>
        </div>
        <c:forEach  var="report" items="${postsReports}" >
                <div class="adminReports">
                    <h3>${report.username}</h3>
                    <h3>${report.offense_name}</h3>
                    <h3>${report.description}</h3>
                    <h3>${report.offender}</h3>
                    <h3>${report.title}</h3>
                    <h3>${report.created.toString().split(' ')[0]}</h3>
                    <div class="buttonChange">
                        <form action="/report" method="get">
                            <input type="hidden" name="type" value="ad">
                            <button class="btn btn-info" name="inspect" value="${report.id}">Inspect</button>
                        </form>
                        <form action="/delete" method="get">
                            <button class="btn btn-warning" name="deleteReport" value="${report.id}">Delete</button>
                        </form>
                        <input type="hidden" name="adId" value="${sessionScope.user.id}">
                    </div>
                </div>
        </c:forEach>
    </div>
    <div class="container">
        <h1>Here are all reported Comments!</h1>
        <div class="adminReports">
                <h3>Username |</h3>
                <h3>Offense |</h3>
                <h3>Description |</h3>
                <h3>Offender |</h3>
                <h3>Title |</h3>
                <h3>TimeStamp |</h3>
                <h3>Actions</h3>
        </div>
        <c:forEach  var="report" items="${commentReports}" >
                <div class="adminReports">
                    <h3>${report.username}</h3>
                    <h3>${report.offense_name}</h3>
                    <h3>${report.description}</h3>
                    <h3>${report.offender}</h3>
                    <h3>${report.title}</h3>
                    <h3>${report.created.toString().split(' ')[0]}</h3>
                    <div class="buttonChange">
                        <form action="/report" method="get">
                            <input type="hidden" name="type" value="comment">
                            <button class="btn btn-info" name="inspect" value="${report.id}">Inspect</button>
                        </form>
                        <form action="/delete" method="get">
                            <button class="btn btn-warning" name="deleteReport" value="${report.id}">Delete</button>
                        </form>
                        <input type="hidden" name="adId" value="${sessionScope.user.id}">
                    </div>
                </div>
        </c:forEach>
    </div>
    <div class="container">
        <h1>Here are all Reported Users!</h1>
        <div class="adminReports">
            <h3>Username |</h3>
            <h3>Offense |</h3>
            <h3>Description |</h3>
            <h3>Offender |</h3>
            <h3>Title |</h3>
            <h3>TimeStamp |</h3>
            <h3>Actions</h3>
        </div>
        <c:forEach  var="report" items="${userReports}" >
                <div class="adminReports">
                    <h3>${report.username}</h3>
                    <h3>${report.offense_name}</h3>
                    <h3>${report.description}</h3>
                    <h3>${report.offender}</h3>
                    <h3>${report.title}</h3>
                    <h3>${report.created.toString().split(' ')[0]}</h3>
                    <div class="buttonChange">
                        <form action="/report" method="get">
                            <input type="hidden" name="type" value="user">
                            <button class="btn btn-info" name="inspect" value="${report.id}">Inspect</button>
                        </form>
                        <form action="/delete" method="get">
                            <button class="btn btn-warning" name="deleteReport" value="${report.id}">Delete</button>
                        </form>
                        <input type="hidden" name="adId" value="${sessionScope.user.id}">
                    </div>
                </div>
        </c:forEach>
    </div>
    <c:if test="${sessionScope.admin.level == 3}">
    <div class="container">
        <h1>Here are all Admins</h1>
        <c:forEach  var="admin" items="${adminList}" >
            <div class="adminReports">
                <h3>${admin.master}</h3>
                <h3>${admin.username}</h3>
                <h3>${admin.level}</h3>
            </div>
        </c:forEach>
    </div>
    </c:if>
</c:if>
</body>
</html>
