<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--<c:forEach var="child" items="${comment.getChildren()}">--%>
        <%--<p>${child.username}</p>--%>
        <%--<!-- Print comment -->--%>
        <%--<c:set var="child" value="${child}}" scope="request"/>--%>
        <%--&lt;%&ndash;<jsp:include page="comments.jsp"/>&ndash;%&gt;--%>
    <%--</c:forEach>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul>
    <c:forEach var="child" items="${comment.getChildren()}">
        <li>
            <p>${child.username}</p>
            <p>${child.comment}</p>
            <p>Last Edited: ${child.posted.toString().split('T')[0]}</p>
            <c:set var="comment" value="${child}" scope="request"/>
            <jsp:include page="../partials/comments.jsp"/>
        </li>
    </c:forEach>
</ul>