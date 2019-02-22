<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Welcome to my site!" />
    </jsp:include>
    <link href="/static/css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <h1>Welcome to the<img id="ghostLogo" src="static/img/ghost.ico" alt="ghost" style="height: 1em; width: 1em; margin-right: -5px;margin-bottom: 9px;">GhostPost!</h1>
    </div>
</body>
</html>

