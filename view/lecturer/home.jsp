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
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                text-align: center;
            }

            a {
                display: inline-block;
                padding: 10px 20px;
                margin: 10px 0;
                background-color: #4CAF50;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
            }

            a:hover {
                background-color: #3e8e41;
            }
        </style>
    </head>
    <body>
        <h1>FPT University Academic Portal</h1>
        <h2>Hello ${user.username}</h2>
        <c:forEach items="${requestScope.user.roles}" var="role">
            <c:if test="${role.name ne null}">
                <a href="/Assignment2${role.url}">
                    ${role.name}
                </a><br>
            </c:if>
        </c:forEach>
    </body>
</html>
