<%-- 
    Document   : update
    Created on : Feb 13, 2023, 11:02:02 AM
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
            <input type="hidden" name="sid" value="${param.sid}"/>
            Name: <input type="text" name="name" value="${student.sname}"/> <br/>
            Gender: <input type="radio" name="gender" value="male"
                           ${student.gender?"checked=\"checked\"":""}
                           /> Male
            <input type="radio" name="gender" value="female"
                            ${!student.gender?"checked=\"checked\"":""}
                   /> Female <br/>
            Dob : <input type="date" name="dob" value="${student.dob}"/> <br/>
            Department :
            <select name="did">
                <c:forEach items="${requestScope.depts}" var="d">
                    <option value="${d.did}" 
                            <c:if test="${requestScope.student.dept.did eq d.did}">selected="selected"</c:if>
                            >${d.dname}</option>
                </c:forEach>
            </select>
            <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
