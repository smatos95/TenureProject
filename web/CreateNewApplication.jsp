<%-- 
    Document   : CreateNewApplication
    Created on : Feb 28, 2019, 5:01:23 PM
    Author     : rsh50944
--%>

<%@page import="common.BuUser"%>
<%@page import="common.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles/controlPage.css" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
    </head>
    <body>
                
        <%BuUser u = (BuUser) request.getSession().getAttribute("user");%>
        
        <form id='create_new_app_form' action='CreateApplicationServlet' method='POST'>
            <h3 class="fieldLayout">Let's make an application!</h3>
            <div class="fieldLayout"> First Name
            <input type='text' name='customfirst' value=<%=u.getFirstName()%>>
            </div>
            <div class="fieldLayout">Last Name
            <input type='text' name='customlast' value=<%=u.getLastName()%>>
            </div>
            <div class="fieldLayout">Select Application Type
            <select name='apptype'>
                <option>Tenure</option>
            </select>
            </div>
            <div class="fieldLayout">Department
            <input type='text' name='department' value=''/>
            </div>
             <div class="fieldLayout">Department Chair Name
            <input type='text' name='deptchair' value=''/>
             </div>
            <div class="fieldLayout">Department Chair Phone
            <input type='text' class="form-control" data-mask="(999) 999-9999" name='chairphone' value=''/>
            <input type='submit' name='createapp' value='Create Application'/>
            </div>
        </form>
            
    </body>
</html>
