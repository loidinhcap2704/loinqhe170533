<%-- 
    Document   : home
    Created on : Mar 15, 2023, 10:43:23 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${requestScope.user.roles}" var="role">
            <c:if test="${role.name ne null}">
                <a href="/Assignment2${role.url}">
                    ${role.name}
                </a><br>
            </c:if>
        </c:forEach>
    </body>
</html>
