<%-- 
    Document   : att
    Created on : Mar 14, 2023, 1:54:29 PM
    Author     : sonnt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            <%@include file="Teacher.css"%>
        </style>
    </head>
    <body>
        <h1>FPT University Academic Portal</h1>
        <p class="toolbar"><a href="../home" id="home">Home</a>    |   Take Attend</p>
        <form action="takeattend" method="POST">
            <table border="1px">
                <tr>
                    <td>No</td>
                    <td>Student Code</td>
                    <td>Name</td>
                    <td>Image</td>
                    <td>Absent/Present</td>
                    <td>Description</td>
                </tr>
                <c:forEach items="${requestScope.atts}" var="a" varStatus="loop">
                    <tr>
                        <td>${loop.index+1}</td>
                        <td>${a.student.id}
                            <input type="hidden" name="sid" value="${a.student.id}" />
                            <input type="hidden" name="aid${a.student.id}" value="${a.id}" />
                        </td>
                        <td>${a.student.name}</td>
                        <td></td>
                        <td><input type="radio"
                                   <c:if test="${!a.status}">
                                   checked="checked"
                                   </c:if>
                                   name="status${a.student.id}" value="absent"/>Absent
                            <input type="radio"
                                   <c:if test="${a.status}">
                                   checked="checked"
                                   </c:if>
                                   name="status${a.student.id}" value="present"/>Present
                        </td>
                        <td><input type="text" value="${a.description}" name="description${a.student.id}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <input type="hidden" name="sessionid" value="${param.id}"/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
