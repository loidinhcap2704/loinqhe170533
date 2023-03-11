<%-- 
    Document   : newjsp
    Created on : Feb 26, 2023, 12:50:22 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="login" method="POST">
            Username:<input type="text" name="username"/><br><!-- comment -->
            Password:<input type="password" name="password"/><br><!-- comment -->
            <input type="submit" name="Login"/>
        </form>
    </body>
</html>
