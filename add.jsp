<%-- 
    Document   : add
    Created on : Feb 13, 2023, 10:42:47 AM
    Author     : sonnt
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
        <form action="add" method="POST"> 
            Name: <input type="text" name="sname"/> <br/>
            Gender: <input type="radio" name="gender" value="male"/> Male
            <input type="radio" name="gender" value="female"/> Female <br/>
            Dob : <input type="date" name="dob"/> <br/>
            Department :
            <select name="did">
                <c:forEach items="${requestScope.depts}" var="d">
                    <option value="${d.did}">${d.dname}</option>
                </c:forEach>
            </select>
            <br/>
            Current Term:<input type="number" name="currentTerm"/><br>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
