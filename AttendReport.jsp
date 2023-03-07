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
                            <th>CAMPUS/PROGRAM</th>
                            <th>TERM</th>
                            <th>COURSE</th>
                        </tr>
                        <tr>
                            <td>FU-HL</td>
                            <td><a href="#">SPR2022</a></td>
                            <td><a href="#">Statistics and Probability(MAS291)</a></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><a href="#">SUM2022</a></td>
                            <td><a href="#">Elementary Japanese 1-A1.2(JPD123)</a></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><a href="#">FALL2022</a></td>
                            <td><a href="#">Internet of Things(IOT102)</a></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><a href="#" class="choosen">SPR2023</a></td>
                            <td><a href="#" class="choosen">Java Web Application Development(PRJ301)</a></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td><a href="#">Introduction to Software Engineering(SWE201c)</a></td>
                        </tr>
                    </table>
                </td>
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
                                <td class="date">${formattedDate}</td>
                                <td class="slotattend">${s.cl.slot.slot}_${s.cl.slot.description}</td>
                                <td>${s.cl.room.rname}</td>
                                <td>${s.cl.lecturer.lname}</td>
                                <td>${s.cl.group.gname}</td>
                                <td class="absent" <c:if test="${s.attendanceStatus eq true}">style="color: chartreuse;"</c:if>>${s.attendanceStatus}</td>
                                <td>${s.lecturerComment}</td>
                            </tr>
                        </c:forEach>

                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>
