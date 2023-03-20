<%-- 
    Document   : timetable
    Created on : Mar 10, 2023, 4:08:36 PM
    Author     : sonnt
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
        <p class="toolbar"><a href="../home" id="home">Home</a>    |    View Schedule</p>
        <table border="1px">
            <tr>
                <th>
                    Week
                    <form id="myForm" action="timetable" method="post">
                        <select id="mySelect" name="from">
                            <c:forEach items="${requestScope.mondays}" var="mon" varStatus="loop">
                                <option value="${mon}"
                                        <c:if test="${thismonday eq mon}"> selected="selected" </c:if>>
                                    ${mon} to ${sundays[loop.index]}
                                </option>
                            </c:forEach>
                        </select>
                    </form>
                    <script>
                        document.getElementById("mySelect").addEventListener("change", function () {
                            document.getElementById("myForm").submit();
                        });
                    </script>
                </th>
                <c:forEach items="${requestScope.dates}" var="d">
                    <th>${d} <br/>
                        <fmt:formatDate value="${d}" pattern="EEEE" />

                    </th>
                </c:forEach>
            <tr/>
            <c:forEach items="${requestScope.slots}" var="s">
                <tr>
                    <td>${s.description}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.sessions}" var="ses">
                                <c:if test="${ses.slot.id eq s.id and ses.date eq d}">
                                    ${ses.group.name}-${ses.group.course.name} <br/>
                                    ${ses.room.name} 
                                    <c:if test="${ses.status}">
                                        -(attended) <a href="takeattend?id=${ses.id}">Edit</a>
                                    </c:if>
                                    <c:if test="${!ses.status}">-
                                        <a href="takeattend?id=${ses.id}">Take attend</a>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                <tr/> 
            </c:forEach>
        </table>
</html>
