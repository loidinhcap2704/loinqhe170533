<%-- 
    Document   : list
    Created on : Feb 9, 2023, 9:24:01 AM
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
        <script>
            function deleteStudent(id)
            {
                var a = confirm("Are you sure?");
                if (a)
                {
                    window.location.href = 'delete?id=' + id;
                }
            }

        </script>
    </head>
    <body>
        <table border="1px">
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Gender</td>
                <td>Dob</td>
                <td>Department</td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${requestScope.lecturers}" var="s">
                <tr>
                    <td>${s.lid}</td>
                    <td>${s.lname}</td>
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
                    <td><input type="button" onclick="window.location.href = 'update?id=${s.lid}'" value="Update"/></td>
                    <td><input type="button" onclick="deleteStudent(${s.lid});" value="Delete"/></td>
                </tr>
            </c:forEach>
        </table>
        <a href="add">Create new Lecturer</a>
    </body>
</html>
