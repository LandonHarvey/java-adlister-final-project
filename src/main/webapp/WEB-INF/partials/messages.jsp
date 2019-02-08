<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: landonharvey
  Date: 2/7/19
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<c:if test="${error != null}">
    <div class="alert alert-danger" role="alert"><h4>${error}</h4></div>
    <hr>
</c:if>