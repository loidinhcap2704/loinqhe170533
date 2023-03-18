<%-- 
    Document   : login_failed.jsp
    Created on : Mar 16, 2023, 6:40:06 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login Failed</title>
        <script>
            function countdown() {
                var count = 3; // set initial count to 3
                var countdownElement = document.getElementById("countdown");

                var countdownInterval = setInterval(function () {
                    if (count > 0) {
                        count--;
                        countdownElement.innerHTML = count; // update countdown display
                    } else {
                        clearInterval(countdownInterval); // stop the countdown
                        window.location.href = "/Assignment2/login"; // redirect the user back to the login page
                    }
                }, 1000); // countdown every 1 second
            }
        </script>
    </head>
    <body onload="countdown()">
        <h1>Login Failed</h1>
        <p>Sorry, we were unable to log you in. Please check your username and password and try again.</p>
        <p>You will be redirected back to the login page in <span id="countdown">3</span> seconds.</p>
    </body>
</html>