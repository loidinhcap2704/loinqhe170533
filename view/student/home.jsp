<%-- 
    Document   : home
    Created on : Mar 16, 2023, 6:29:24 PM
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
            <a href="/Assignment2${role.url}">${role.url}</a>
        </c:forEach>
    </body>
</html>
