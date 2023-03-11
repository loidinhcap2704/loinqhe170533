<%-- 
    Document   : AttendReport
    Created on : Feb 20, 2023, 11:00:47 PM
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
        <style>
            <%@include file="Student.css" %>
        </style>
    </head>
    <body>
        <h1>FPT University Academic Portal</h1>
        <p class="toolbar"><a style="" href="home" id="home">Home</a>|     <b>View Schedule</b></p>
        <table>
            <tr>
                <td style="vertical-align: text-top; width: 40%">
                    <table>
                        <tr>
                            <td style="vertical-align: text-top">
                                <table>
                                    <tr>
                                        <th>CAMPUS/PROGRAM</th>
                                    </tr>
                                    <tr>
                                        <td>FU-HL</td>
                                    </tr>
                                </table>
                            </td>
                            <td style="vertical-align: text-top">
                                <table>
                                    <tr>
                                        <th>TERM</th>
                                    </tr>
                                    <tr>
                                        <td>SPR2023</td>
                                    </tr>
                                </table>
                            </td>
                            <td style="vertical-align: text-top">
                                <table>
                                    <tr>
                                        <th>COURSE</th>
                                    </tr>
                                    <tr>
                                        <td>
                                            <c:forEach items="${courseList}" var="course">
                                                <a href="attendReportCourse?cid=${course.cl.group.course.cid}" >${course.cl.group.course.cname}(${course.cl.group.course.shortname})(${course.cl.group.gname})</a> 
                                            </c:forEach>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
                <c:set var="absent" value="${0}"/>
                <td style="vertical-align: text-top">
                    <table>
                        <tr>
                            <th>NO</th>
                            <th>DATE</th>
                            <th>SLOT</th>
                            <th>ROOM</th>
                            <th>LECTURER</th>
                            <th>GROUP NAME</th>
                            <th>ATTENDANCE STATUS</th>
                            <th>LECTURER'S COMMENT</th>
                        </tr>
                        <c:forEach items="${requestScope.list}" var="s">
                            <tr>
                                <td>${s.cl.slotOfCourse}</td>
                                <fmt:formatDate pattern="E dd-MM" value="${s.cl.date}" var="formattedDate" />
                                <fmt:formatDate pattern="yyyy-MM-dd" value="${s.cl.date}" var="compare"/>
                                <td class="date">${formattedDate}</td>
                                <td class="slotattend">${s.cl.slot.slot}_${s.cl.slot.description}</td>
                                <td>${s.cl.room.rname}</td>
                                <td>${s.cl.lecturer.user.username}</td>
                                <td>${s.cl.group.gname}</td>
                                <fmt:formatDate pattern="yyyy-MM-dd" value="${date}" var="now"/>
                                <c:if test="${now le compare}">
                                    <td>
                                        Future
                                    </td>
                                </c:if>
                                <c:if test="${now gt compare}">
                                    <c:if test="${s.attendanceStatus}">
                                        <td style="color: chartreuse;">Present</td>
                                    </c:if>
                                    <c:if test="${!s.attendanceStatus}">
                                        <c:set var="absent" value="${absent+1}"/>
                                        <td class="absent">Absent</td>
                                    </c:if>
                                </c:if>
                                <td>${s.lecturerComment}</td>
                                <td></td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            <tr>
                <td style="border: none"></td>
                <td>
                    <b>ABSENT: <fmt:formatNumber type="percent" value="${absent/20}"/> ABSENT SO FAR (${absent} ABSENT ON 20 TOTAL )</b>
                </td>
            </tr>
        </tr>
    </table>
</body>
</html>
