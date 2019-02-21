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
                    <form action="/upvote" method="POST">
                        <input type="hidden" name="up" value="up" />
                        <input type="hidden" name="adid" value="${ad.id}"/>
                        <input type="hidden" name="redirect" value="/adIndividual?id=${ad.id}" />
                        <a href="#" onclick="this.parentNode.submit()"><span class="glyphicon glyphicon-chevron-up"></span></a>
                    </form>
                    <form action="/downvote" method="POST">
                        <input type="hidden" name="down" value="down" />
                        <input type="hidden" name="adid" value="${ad.id}" />
                        <input type="hidden" name="redirect" value="/adIndividual?id=${ad.id}" />
                        <a href="#" onclick="this.parentNode.submit()"><span class="glyphicon glyphicon-chevron-down"></span></a>
                    </form>
                </div>
            </div>
            <h2>${ad.title}</h2>
            <p>${ad.description}</p>
            <p>${ad.categories}</p>
            <span>Created By: <a href="/profile?id=${user.username}"><c:out value="${user.username}"> </c:out></a></span><br>
            <span> Last Edited: <c:out value="${ad.created.toString().split('T')[0]}"></c:out></span>
            <button class="buttonRestyle" onclick="updateComment(null)"><span class="glyphicon glyphicon-comment"></span></button>
            <button class="buttonRestyle" onclick="updateReport(${ad.id})"><span class="glyphicon glyphicon-flag"></span></button>
        </div>
    </div>
    <div class="col-md-6">
        <c:forEach var="comment" items="${comments}">
            <div class="commentContainer">
                <div class="commentCSS">
                    <div class="borderBox4">
                        <p><a href="/profile?id=${comment.username}">${comment.username}</a></p>
                    <p>${comment.comment}</p>
                    <div class="voteLine">
                        <span>Last Edited: ${comment.posted.toString().split('T')[0]}</span>
                        <button class="buttonRestyle" onclick="updateComment(${comment.id})"><span class="glyphicon glyphicon-comment"></span></button>
                        <button class="buttonRestyle" value="${child.getId()}" onclick="updateReport(${comment.id})"><span class="glyphicon glyphicon-flag"></span></button>
                        <span id="likeMachineC">
                            <div class="votesC">
                                <form action="/commentupvote?id=${comment.id}" method="POST">
                                    <input type="hidden" name="up" value="up" />
                                    <input type="hidden" name="redirect" value="/adIndividual?id=${ad.id}" />
                                    <a href="#" onclick="this.parentNode.submit()"><span id="glyphComment" class="glyphicon glyphicon-chevron-up"></span></a>
                                </form>
                                <div class="votesC">
                                    <span>${comment.upvote - comment.downvote}</span>
                                </div>
                                <form action="/commentdownvote?id=${comment.id}" method="POST">
                                    <input type="hidden" name="down" value="down" />
                                    <input type="hidden" name="redirect" value="/adIndividual?id=${ad.id}" />
                                    <a href="#" onclick="this.parentNode.submit()"><span class="glyphicon glyphicon-chevron-down"></span></a>
                                </form>
                            </div>
                        </span>
                    </div>
                    </div>
                <c:set var="comment" value="${comment}" scope="request"/>
                <jsp:include page="../partials/comments.jsp"/>
                </div>
            </div>
        </c:forEach>
        <div id="commentBox">
            <h4>Comment: </h4>
            <a href="#comment"></a>
            <form action="/comment?id=${ad.id}" method="post">
            <textarea name="comment" id="comment" cols="60" rows="3" placeholder="comment..."></textarea>
            <input type="hidden" name="redirect" value="/adIndividual?id=${ad.id}"/>
            <input id="parentComment" type="hidden" name="parentSent" value=""/>
            <button type="submit">Post</button>
            </form>
        </div>
        <jsp:include page="../partials/messages.jsp" />
        <div id="reportBox">
            <h4>Report: </h4>
            <a href="#report"></a>
            <form action="/report" method="post">
                <label for="offenses">Users Offense: </label>
                <select name="offenses" id="offenses">
                    <c:forEach var="offense" items="${offenses}">
                        <option value="${offense.getId()}"><c:out value="${offense.getOffense_name()}"></c:out></option>
                    </c:forEach>
                </select>
                <label for="type">Content: </label>
                <select name="type" id="type">
                    <option value="comment">Comment</option>
                    <option value="ad">Post</option>
                </select>
                <textarea name="report" id="report" cols="60" rows="3" placeholder="Report Description..."></textarea>
                <input type="hidden" name="redirect" value="/adIndividual?id=${ad.id}"/>
                <input id="changeVariable" type="hidden" name="changeVariable" value=""/>
                <button type="submit">Post</button>
            </form>
        </div>
    </div>
<%--</c:forEach>--%>
</div>
<script type="text/javascript" src="/static/js/ad.js"></script>
</body>
</html>
