<%-- 
    Document   : attendReport
    Created on : Mar 15, 2023, 7:35:13 PM
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
        <style type="text/css">
            <%@include file="Teacher.css"%>
        </style>
    </head>
    <body>
        <h1>FPT University Academic Portal</h1>
        <p class="toolbar"><a href="../home" id="home">Home</a> |    View Attendance Report</p>

        <div id="menu">
            <ul>
                <c:forEach items="${groups}" var="g">
                    <li <c:if test="${gid eq g.id}">style="background: #3e8e41" </c:if>><a href="attendReport?gid=${g.id}">${g.course.name}(${g.course.shortname})</a></li>
                    </c:forEach>
            </ul>
        </div>
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
            <c:set var="absent" value="${0}"/>
            <c:if test="${atts[0] eq null}">Hello</c:if>
            <c:forEach items="${requestScope.atts}" var="a">
                <tr>
                    <td>${a.session.noSession}</td>
                    <td class="date">${a.session.date}</td>
                    <td class="slotattend">${a.session.slot.id} ${a.session.slot.description}</td>
                    <td>${a.session.room.name}</td>
                    <td>${a.session.lecturer.user.username}</td>
                    <td>${a.session.group.name}</td>

                    <c:if test="${a.status}">
                        <td style="color: chartreuse;">Present</td>
                    </c:if>
                    <c:if test="${!a.status}">
                        <c:set var="absent" value="${absent+1}"/>
                        <td class="absent">Absent</td>
                    </c:if>

                    <td>${a.description}</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="8">
                    <b>ABSENT: <fmt:formatNumber type="percent" value="${absent/20}"/> ABSENT SO FAR (${absent} ABSENT ON 20 TOTAL )</b>
                </td>
            </tr>
        </table>
    </body>
</html>
