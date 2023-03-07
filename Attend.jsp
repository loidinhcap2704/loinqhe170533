<%-- 
    Document   : Attend
    Created on : Feb 20, 2023, 3:01:34 PM
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
            <%@include file="Teacher.css" %>
        </style>
    </head>
    <body>
        <h1>Single activity Attendance</h1>
    <p>Attendance for <b>PRF192</b> with lecturer <b>SONNT5</b> at slot 1 on Wednesday 19/01/2022, Spring2022, in room BE-301 at FU-HL.</p>
    <table>
        <tr>
            <th>NO</th>
            <th>GROUP</th>
            <th>CODE</th>
            <TH>NAME</TH>
            <TH>IMAGE</TH>
            <TH>STATUS</TH>
            <TH>COMMENT</TH>
            <TH>TAKER</TH>
            <TH>RECORD TIME</TH>
        </tr>
        <c:forEach  items="${requestScope.students}" var="s" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>SE1738</td>
            <td>${s.sid}</td>
            <td>${s.sname}</td>
            <td></td>
            <td><attend class="attend">attened</attend></td>
            <TD></TD>
            <td>sonnt5</td>
            <td>1/19/2002 8:54:00 AM</td>
        </tr>
        </c:forEach>
    </table>
    </body>
</html>
