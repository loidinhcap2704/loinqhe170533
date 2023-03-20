<%-- 
    Document   : user
    Created on : Mar 16, 2023, 4:31:08 PM
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
        <table border="1px">
            <c:forEach items="${requestScope.user.roles}" var="role">
                <tr>
                    <td>${user.userid}</td>
                    <td>${role.roleid}</td>
                    <td>${role.url}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
