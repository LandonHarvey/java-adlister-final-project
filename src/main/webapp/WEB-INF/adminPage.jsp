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
    <c:if test="${sessionScope.admin.level == 3}">
        <a href="/createAdmin">Create Admin</a>
    </c:if>
        <h1>Here are all reported Posts!</h1>
        <table class="table table-hover">
        <tr>
            <td>Username</td>
            <td>Offense</td>
            <td>Description</td>
            <td>Offender</td>
            <td>Title</td>
            <td>TimeStamp</td>
            <td>Actions</td>
        </tr>
        <c:forEach  var="report" items="${postsReports}" >
           <tr>
               <td>${report.username}</td>
               <td>${report.offense_name}</td>
               <td>${report.description}</td>
               <td>${report.offender}</td>
               <td>${report.title}</td>
               <td>${report.created.toString().split(' ')[0]}</td>
               <td class="buttonChange">
                   <form action="/report" method="get">
                       <input type="hidden" name="type" value="ad">
                       <button class="btn btn-info" name="inspect" value="${report.id}">Inspect</button>
                   </form>
                   <form action="/delete" method="get">
                       <button class="btn btn-warning" name="deleteReport" value="${report.id}">Delete</button>
                   </form>
                   <input type="hidden" name="adId" value="${sessionScope.user.id}">
               </td>
           </tr>
        </c:forEach>
        </table>
        <h1>Here are all reported Comments!</h1>
        <table class="table table-hover">
        <tr>
                <td>Username</td>
                <td>Offense</td>
                <td>Description</td>
                <td>Offender</td>
                <td>Title</td>
                <td>TimeStamp</td>
                <td>Actions</td>
        </tr>
        <c:forEach  var="report" items="${commentReports}" >
                    <tr>
                    <td>${report.username}</td>
                    <td>${report.offense_name}</td>
                    <td>${report.description}</td>
                    <td>${report.offender}</td>
                    <td>${report.title}</td>
                    <td>${report.created.toString().split(' ')[0]}</td>
                    <td class="buttonChange">
                        <form action="/report" method="get">
                            <input type="hidden" name="type" value="comment">
                            <button class="btn btn-info" name="inspect" value="${report.id}">Inspect</button>
                        </form>
                        <form action="/delete" method="get">
                            <button class="btn btn-warning" name="deleteReport" value="${report.id}">Delete</button>
                        </form>
                        <input type="hidden" name="adId" value="${sessionScope.user.id}">
                    </td>
                    </tr>
        </c:forEach>
        </table>
        <h1>Here are all Reported Users!</h1>
        <table class="table table-hover">
            <tr>
                <td>Username</td>
                <td>Offense</td>
                <td>Description</td>
                <td>Offender</td>
                <td>Title</td>
                <td>TimeStamp</td>
                <td>Actions</td>
            </tr>
            <c:forEach  var="report" items="${userReports}" >
                        <tr>
                        <td>${report.username}</td>
                        <td>${report.offense_name}</td>
                        <td>${report.description}</td>
                        <td>${report.offender}</td>
                        <td>${report.title}</td>
                        <td>${report.created.toString().split(' ')[0]}</td>
                        <td class="buttonChange">
                            <form action="/report" method="get">
                                <input type="hidden" name="type" value="user">
                                <button class="btn btn-info" name="inspect" value="${report.id}">Inspect</button>
                            </form>
                            <form action="/delete" method="get">
                                <button class="btn btn-warning" name="deleteReport" value="${report.id}">Delete</button>
                            </form>
                            <input type="hidden" name="adId" value="${sessionScope.user.id}">
                        </td>
                        </tr>
            </c:forEach>
        </table>
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
