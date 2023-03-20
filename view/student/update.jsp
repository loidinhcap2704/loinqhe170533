<%-- 
    Document   : update
    Created on : Feb 14, 2023, 2:09:51 PM
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
         <form action="update" method="POST">
             <input type="hidden" name="sid" value="${param.id}"/>
             Name: <input type="text" value="${requestScope.s.name}" name="name"/> <br/>
            Gender: <input type="radio" name="gender" 
                           <c:if test="${requestScope.s.gender}">
                           checked="checked" 
                           </c:if>
                           value="male"/> Male 
            <input type="radio" name="gender"
                           <c:if test="${!requestScope.s.gender}">
                           checked="checked" 
                           </c:if>
                   value="female"/> Female <br/>
            Dob: <input type="date" name="dob" value="${requestScope.s.dob}"/> <br/>
            Department: <select name="did">
                <c:forEach items="${requestScope.depts}" var="d">
                    <option 
                        <c:if test="${requestScope.s.dept.id eq d.id}">
                        selected="selected" 
                        </c:if>    
                            value="${d.id}">${d.name}</option>
                </c:forEach>
            </select> <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
