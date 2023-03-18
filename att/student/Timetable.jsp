<%-- 
    Document   : Timetable
    Created on : Mar 15, 2023, 12:07:21 PM
    Author     : nguye
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <td></td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>${d} <br/>
                        <fmt:formatDate value="${d}" pattern="EEEE" />

                    </td>
                </c:forEach>
            <tr/>
            <c:forEach items="${requestScope.slots}" var="s">
                <tr>
                    <td>${s.description}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.timetable}" var="att">
                                <c:if test="${att.session.slot.id eq s.id and att.session.date eq d}">
                                    ${att.session.group.name}-${att.session.group.course.name} <br/>
                                    ${att.session.room.name} 
                                    <c:if test="${att.status}">
                                        -(attended)
                                    </c:if>
                                    <c:if test="${!att.status}">
                                        - (absent)
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                <tr/> 
            </c:forEach>

        </table>
    </body>
</html>
