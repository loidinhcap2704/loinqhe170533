<%-- 
    Document   : attendReport
    Created on : Mar 15, 2023, 7:35:13 PM
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
            <tr>
                <th>No</th>
                <th>Date</th>
                <th>Slot</th>
                <th>Room</th>
                <th>Lecturer</th>
                <th>Group Name</th>
                <th>Attendance Status</th>
                <th>Lecturer's comment</th>
            </tr>
            <c:if test="${atts[0] eq null}">Hello</c:if>
            <c:forEach items="${requestScope.atts}" var="a">
                <tr>
                    <td>${a.session.noSession}</td>
                    <td>${a.session.date}</td>
                    <td>${a.session.slot.id} ${a.session.slot.description}</td>
                    <td>${a.session.room.name}</td>
                    <td>${a.session.lecturer.user.username}</td>
                    <td>${a.session.group.name}</td>
                    <td>${a.status}</td>
                    <td>${a.description}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
