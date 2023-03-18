<%-- 
    Document   : list
    Created on : Feb 10, 2023, 4:46:06 PM
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
                var a = confirm("are you sure?");
                if(a)
                {
                    window.location.href='delete?id='+id;
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
                    <td><input type="button" onclick="window.location.href='update?id=${s.id}'" value="Update"/></td>
                <td><input type="button" onclick="deleteStudent(${s.id});" value="Delete"/></td>
                </tr>
                
            </c:forEach>
        </table>
        <a href="add">Create new Student</a>
    </body>
</html>
