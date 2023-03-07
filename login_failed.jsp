<%-- 
    Document   : login_failed
    Created on : Feb 20, 2023, 11:05:41 AM
    Author     : sonnt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <title>Login Failed</title>
            <script>
                // Function to countdown and redirect to login page
                function countdown() {
                    var seconds = 3;
                    setInterval(function () {
                        seconds--;
                        if (seconds <= 0) {
                            window.location.href = "../../login";
                        }
                        document.getElementById("countdown").textContent = seconds;
                    }, 1000);
                }
            </script>
        </head>
        <body onload="countdown()">
            <h1>Login Failed</h1>
            <p>Sorry, your login credentials were incorrect. You will be redirected back to the login page in <span id="countdown">3</span> seconds...</p>
        </body>
    </html>
</html>
