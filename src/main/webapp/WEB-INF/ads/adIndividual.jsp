<%--
  Created by IntelliJ IDEA.
  User: landonharvey
  Date: 2/5/19
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
    <link href="/static/css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Here is your Ad!</h1>
<%--<c:forEach var="ad" items="${ads}">--%>
    <div class="col-md-6">
        <div class="borderBox3">
            <div id="likeMachine">
                <div class="votes">
                    <span>${ad.upvote}</span>
                    <span>${ad.downvote}</span>
                </div>
                <div class="votes">
                    <form action="/upvote?id=${ad.id}" method="POST">
                        <input type="hidden" name="up" value="up" />
                        <input type="hidden" name="redirect" value="/adIndividual?id=${ad.id}" />
                        <a href="#" onclick="this.parentNode.submit()"><span class="glyphicon glyphicon-chevron-up"></span></a>
                    </form>
                    <form action="/downvote?id=${ad.id}" method="POST">
                        <input type="hidden" name="down" value="down" />
                        <input type="hidden" name="redirect" value="/adIndividual?id=${ad.id}" />
                        <a href="#" onclick="this.parentNode.submit()"><span class="glyphicon glyphicon-chevron-down"></span></a>
                    </form>
                </div>
            </div>
            <h2>${ad.title}</h2>
            <p>${ad.description}</p>
            <p>${ad.categories}</p>
            <span>Created By: <c:out value="${user.username}"> </c:out></span><br>
            <span> Last Edited: <c:out value="${ad.created.toString().split('T')[0]}"></c:out></span>
        </div>
    </div>
    <div class="col-md-6">
        <c:forEach var="comment" items="${comments}">
            <div class="commentCSS">
                <p>${comment.username}</p>
                <p>${comment.comment}</p>
                <p>Last Edited: ${comment.posted.toString().split('T')[0]}</p>
            </div>
        </c:forEach>
        <h4>Comment: </h4>
        <form action="/comment?id=${ad.id}" method="post" id="commentBox">
        <textarea name="comment" id="comment" cols="60" rows="3" placeholder="comment..."></textarea>
        <input type="hidden" name="redirect" value="/adIndividual?id=${ad.id}" />
        <button type="submit">Post</button>
        </form>
    </div>
<%--</c:forEach>--%>
</div>
<script type="text/javascript" src="/static/js/ad.js"></script>
</body>
</html>
