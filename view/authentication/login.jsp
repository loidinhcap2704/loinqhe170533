<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>FPT Academic Portal - Login</title>
        <style>
            /* adjust the styles as needed */
            body {
                margin: 0;
                padding: 0;
            }

            #container {
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                align-items: center;
                padding: 10px; /* updated padding value */
                background-color: #f2f2f2;
            }

            #login-form {
                margin-right: 20px; /* updated margin-right value */
            }

            h1 {
                margin: 0;
                font-size: 24px;
            }
        </style>
    </head>
    <body>
        <div id="container">
            <div id="login-form">
                <h1>Login</h1>
                <form action="login" method="POST">
                    Username:<input type="text" name="username"/><br><!-- comment -->
                    Password:<input type="password" name="password"/><br><!-- comment -->
                    <input type="submit" name="Login"/>
                </form>
            </div>
            <div>
                <h1>FPT University Academic Portal</h1>
            </div>
        </div>
    </body>
</html>
