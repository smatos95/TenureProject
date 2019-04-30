<!DOCTYPE html>
<%--LoginScreen.jsp--%>
<%--The first form that is loaded when the application is executed --%>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/generalStyles.css" type="text/css">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <noscript>
            <meta http-equiv="refresh" content="0; URL=/html/javascriptDisabled.html">
        </noscript>
    </head>
    <body>
        <header class="title_bar_container"> 
            <div id="HeaderText">Sample Web Application Login Page</div>
        </header>
        <img id="backPhoto" src="images/BackgroundPhoto.jpg">
        
        <%--Container for the login menu--%>
        <section class = "content_container" id = "small_container">

            <header class = "content_title_bar" id="login_header"> 
                <div class = "title" >
                    Login
                </div> 
            </header>

            <form id="login_form" action="LoginServlet" method = "POST">              
                <div>
                    <img id="huskyIcon" src="images/husky.png">
                    <div id="image_message_container">
                        ${errorMessage}
                    </div>
                </div>
                <div id = "login_text_container">
                    <input type="hidden" name="control" value="login">
                    Username: <input type = "text" name="username"  placeholder="Enter Username" value="${username}"/> <br>
                    Password: <input id="loginPassword" type="password" name="password"  placeholder="Enter Password"/><br>
                    <a href="ForgotPassword.jsp" id ="forgot_password_link">Forgot Password</a> <br>
                </div>

                <input type ="submit" name = "login" value="Login" class="submit_button"/>
                <br>
                <br>
            </form>
                    
        </section>      
    </body>
</html>
