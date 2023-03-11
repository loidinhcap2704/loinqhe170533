<%-- 
    Document   : Timetable
    Created on : Feb 19, 2023, 10:37:55 PM
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
            <%@include file="Student.css"%>
        </style>
    </head>
    <body>
        <h1>FPT University Academic Portal</h1>
        <p class="toolbar"><a href="home" id="home">Home</a>|           <b>View Schedule</b></p>
        <table>
            <tr>
                <th rowspan="2" class="slot">YEAR<select>
                        <option>2023</option>
                        <option>2022</option>
                        <option>2021</option>
                        <option>2020</option>
                    </select><br>
                    WEEK
                    <form id="myForm" action="timetable" method="post">
                        <select id="mySelect" name="date">
                            <c:forEach items="${requestScope.length}" var="length">
                                <fmt:formatDate pattern="dd-MM" value="${monday[length]}" var="monday1"/>
                                <fmt:formatDate pattern="yyyy-MM-dd" value="${monday[length]}" var="monday2"/>
                                <fmt:formatDate pattern="dd-MM" value="${sunday[length]}" var="sunday1"/>
                                <fmt:formatDate pattern="dd-MM" value="${get}" var="compare"/>
                                <option value="${monday2}"<c:if test="${compare eq monday1}">selected="selected"</c:if>>
                                    ${monday1} To ${sunday1}
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
                <th>
                    MON
                </th>
                <th>
                    TUE
                </th>
                <th>
                    WED
                </th>
                <th>
                    THU
                </th>
                <th>
                    FRI
                </th>
                <th>
                    SAR
                </th>
                <th>
                    SUN
                </th>
            </tr>
            <tr>
                <c:forEach items="${requestScope.weekday}" var="weekday1">
                    <th>
                        <fmt:formatDate pattern="dd-MM" value="${weekday1}" var="formattedDate" />
                        ${formattedDate}
                    </th>
                </c:forEach>
            </tr>
            <c:forEach items="${requestScope.slot}" var="sl">
                <tr>
                    <td>Slot ${sl.slot} <span style="background: chartreuse">${sl.description}</span></td>
                    <c:forEach items="${requestScope.week}" var="week">
                        <fmt:formatDate pattern="yyyy-MM-dd" value="${weekday[week-1]}" var="formattedDate1" />
                        <td><c:forEach items="${requestScope.timetable}" var="s">
                                <fmt:formatDate pattern="yyyy-MM-dd" value="${s.cl.date}" var="formattedDate2" />
                                <c:if test="${s.cl.slot.slot == sl.slot}">
                                    <c:if test="${formattedDate1 eq formattedDate2}">
                                        ${s.cl.group.course.shortname} at ${s.cl.room.rname}
                                    </c:if>
                                </c:if>
                            </c:forEach></td>
                        </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
