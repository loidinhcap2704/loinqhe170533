<%-- 
    Document   : bydept
    Created on : Feb 24, 2023, 4:52:54 PM
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
                       <c:forEach items="${requestScope.dids}" var="p">
                           <c:if test="${p eq d.did}">
                               checked="checked"
                           </c:if>
                       </c:forEach>
                       name="did" value="${d.did}"/> ${d.dname}
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
                <c:forEach items="${requestScope.students}" var="s">
                    <tr>
                        <td>${s.sid}</td>
                        <td>${s.sname}</td>
                        <td>
                            <input type="checkbox" 
                                   <c:if test="${s.gender}">
                                       checked="checked"
                                   </c:if>    
                                   />
                        </td>
                        <td><fmt:formatDate type = "date" 
                                        value = "${s.dob}" /></td>
                <td>${s.dept.dname}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
