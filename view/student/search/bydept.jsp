<%-- 
    Document   : bydept
    Created on : Feb 23, 2023, 8:28:56 AM
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
        <form action="dept" method="GET">
            <c:forEach items="${requestScope.depts}" var="d">
                <input type="checkbox"
                       <c:forEach items="${requestScope.dids}" var="did">
                           <c:if test="${d.id eq did}">   
                               checked="checked"
                           </c:if>
                       </c:forEach>
                       name="did" value="${d.id}"/> ${d.name} 
            </c:forEach>
            <br/>
            <input type="submit" value="Search"/>
        </form>
        <c:if test="${requestScope.students ne null}">
            <table border="1px"> 
                <tr>
                    <td>Id</td>
                    <td>Name</td>
                    <td>Gender</td>
                    <td>Dob</td>
                    <td>Department</td>
                </tr>
                <c:forEach items="${requestScope.students}" var="s" varStatus="loop">
                    <tr 
                        <c:if test="${loop.index mod 2 ne 0}">style="background-color: gray;"</c:if>
                            >
                            <td>${s.id}</td>
                        <td>${s.name}</td>
                        <td><input type="checkbox" 
                                   <c:if test="${s.gender}">checked="checked"</c:if>
                                       /></td>
                            <td>
                        <fmt:formatDate type = "date" 
                                        value = "${s.dob}" />
                </td>
                <td>${s.dept.name}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>