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

<c:choose>
    <c:when test="${tempUser == null}">
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
        <div class="container">
            <div class="titleProfile">
                <h1>Welcome, ${sessionScope.user.username}!</h1>
                <input id="handlez" type="hidden" value="${fileHandler.fileHandler}">
                <div id="content"></div>
                <h2>Level: <span id="level"></span></h2>
                <h2>Fortune: <span id="likes">${totalLikes}<span id="outof"></span></span></h2>
                <form action="/editProfile" method="get">
                    <button class="btn btn-success" name="editProfile" value="${sessionScope.user}">Edit Profile</button>
                </form>
            </div>
        </div>
        <div class="container">
            <h1>Here are all your posted ads!</h1>
            <c:forEach var="ad" items="${userAds}">
                <div class="col-md-6">
                    <div class="borderBox">
                        <a href="/adIndividual?id=${ad.id}"><h2>${ad.title}</h2></a>
                        <p>${ad.description}</p>
                        <p>${ad.categories}</p>
                        <div class="buttonChange">
                            <form action="/edit" method="get" class="spacing">
                                <button class="btn btn-success" name="edit" value="${ad.id}">Edit</button>
                            </form>
                            <form action="/delete" method="get">
                                <button class="btn btn-warning" name="delete" value="${ad.id}">Delete</button>
                            </form>
                            <input type="hidden" name="adId" value="${sessionScope.user.id}">
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    <div class="container">
    <h1>Liked Ads Last 24 Hours!</h1>
        <c:forEach var="ad" items="${userLiked}">
            <div class="col-md-6">
                <div class="borderBox">
                    <a href="/adIndividual?id=${ad.id}"><h2>${ad.title}</h2></a>
                    <p>${ad.description}</p>
                    <p>${ad.categories}</p>
                    <div class="buttonChange">
                        <form action="/remove" method="POST">
                            <button class="btn btn-info" name="remove" value="${ad.id}">Remove Like</button>
                        </form>
                        <input type="hidden" name="adId" value="${sessionScope.user.id}">
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    </c:when>

    <c:when test="${tempUser != null}">
        <jsp:include page="/WEB-INF/partials/navbar.jsp" />
        <div class="container">
            <div class="titleProfile">
                <h1>${tempUser.username} Profile!</h1>
                <input id="handlez" type="hidden" value="${fileHandler.fileHandler}">
                <div id="content"></div>
                <h2>Level: <span id="level"></span></h2>
                <h2>Fortune: <span id="likes">${totalLikes}<span id="outof"></span></span></h2>
                <button class="buttonRestyle" onclick="updateReport(${tempUser.id})"><span class="btn btn-danger">Report User</span></button>
            </div>
            <div id="userBox">
                <h4>Report: </h4>
                <a href="#user"></a>
                <form action="/report" method="post">
                <label for="offenses">Users Offense: </label>
                <select name="offenses" id="offenses">
                    <c:forEach var="offense" items="${offenses}">
                        <option value="${offense.getId()}"><c:out value="${offense.getOffense_name()}"></c:out></option>
                    </c:forEach>
                </select>
                    <textarea name="report" id="report" cols="60" rows="3" placeholder="Reason..."></textarea>
                    <input type="hidden" name="redirect" value="/profile?id=${tempUser.username}"/>
                    <input type="hidden" name="type" value="user"/>
                    <input id="changeVariable" type="hidden" name="changeVariable" value=""/>
                    <button type="submit">Post</button>
                </form>
            </div>
        </div>
        <div class="container">
            <h1>Here are all ${tempUser.username}'s posted ads!</h1>
            <c:forEach var="ad" items="${tempUserAds}">
                <div class="col-md-6">
                    <div class="borderBox">
                        <h2>${ad.title}</h2>
                        <p>${ad.description}</p>
                        <p>${ad.categories}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="container">
            <h1>Liked Ads Last 24 Hours!</h1>
            <c:forEach var="ad" items="${tempUserLiked}">
                <div class="col-md-6">
                    <div class="borderBox">
                        <h2>${ad.title}</h2>
                        <p>${ad.description}</p>
                        <p>${ad.categories}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:when>

</c:choose>
    <script type="text/javascript" src="/static/js/userLevel.js"></script>
    <script src="//static.filestackapi.com/filestack-js/1.x.x/filestack.min.js"></script>
    <script type="text/javascript" src="/static/js/profileShow.js"></script>
</body>
</html>
