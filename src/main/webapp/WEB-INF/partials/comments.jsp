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
        <div class="borderBox4">
            <div id="likeMachine">
                <div class="votes">
                    <span>${child.upvote}</span>
                    <span>${child.downvote}</span>
                </div>
                <div class="votes">
                    <form action="/upvote?id=${child.id}" method="POST">
                        <input type="hidden" name="up" value="up" />
                        <input type="hidden" name="redirect" value="/adIndividual?id=${ad.id}" />
                        <a href="#" onclick="this.parentNode.submit()"><span class="glyphicon glyphicon-chevron-up"></span></a>
                    </form>
                    <form action="/downvote?id=${child.id}" method="POST">
                        <input type="hidden" name="down" value="down" />
                        <input type="hidden" name="redirect" value="/adIndividual?id=${ad.id}" />
                        <a href="#" onclick="this.parentNode.submit()"><span class="glyphicon glyphicon-chevron-down"></span></a>
                    </form>
                </div>
            </div>
            <p>${child.username}</p>
            <p>${child.comment}</p>
            <input id="parent" type="hidden" name="parent"/>
            <span>Last Edited: ${child.posted.toString().split('T')[0]}</span>
            <button class="buttonRestyle" value="${child.getId()}" onclick="updateComment()"><span class="glyphicon glyphicon-comment"></span></button>
        </div>
            <c:set var="comment" value="${child}" scope="request"/>
            <jsp:include page="../partials/comments.jsp"/>
    </c:forEach>
</ul>

