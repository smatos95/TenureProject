<%-- 
    Document   : sysAdminInitial
    Created on : Feb 18, 2019, 5:14:42 PM
    Author     : Riley Hughes
--%>
<!--Imports for all java utility and database objects-->
<%@page import="java.util.Iterator"%>
<%@page import="common.User"%>
<%@page import="common.BuUser"%>
<%@page import="common.ErrorLog"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tenure Prep Form</title>
        <link rel="stylesheet" href="styles/controlPage.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
        <!--Script to cycle through tabs based off of the button clicked-->
        <script type="text/javascript">
            function openTab(e, tabName) {
                var i, tabcontent, tabbuttons;
                
                // Get elements with tabcontent class and hide them
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                
                // Get elements w/ tabbuttons class and remove active class
                tabbuttons = document.getElementsByClassName("tabbuttons");
                for (i = 0; i < tabbuttons.length; i++) {
                    tabbuttons[i].className = tabbuttons[i].className.replace(" active", "");
                }
                
                // Show current tab, add active class to its button
                document.getElementById(tabName).style.display = "block";
                e.currentTarget.className += " active";
            }
        </script>
    </head>
    <body bgcolor="#F4ECD9" style="margin-left: 180px;">
        <!--Buttons to act as tabs on the admin page-->
        <div class="sidetabs">
            <img src ="images/bloomsburg-logo.png" height = 190px width = 230px style="padding-left: 12%;">
            <button class="tabbuttons" onclick="openTab(event, 'sysad1')" id="hometab" name = Properties>
               <i class=" fa fa-wrench"></i> Error Log</button>
            <button class="tabbuttons" onclick="openTab(event, 'startup')" name="Error Log">
                <i class="fa fa-gears"></i> Properties</button>
            <button class="tabbuttons" onclick="openTab(event, 'sysad2')" name = "Upgrade Roles">
            <i class="fa fa-level-up"></i> Upgrade Roles</button>
            <form id='redirct_page' action='RedirectServlet' method='POST'>
                <button class="tabbuttons" name="viewtenure"><i class="fa fa-home"></i> View Tenure Application</button>
                
            </form>
        </div>
        <!--Tab that holds a list of all user accounts 
        SHOULD BE SWAPPED OUT FOR THE PROFILE TABLE IN THE FUTURE-->
        <div id="startup" class="tabcontent paper">
            <div class="paperTop">
                    <div class="pageTitle">User Properties</div>
                    <hr>
            </div>
                
            <table border="1" style = "margin-top: 9%">
                <tr>
                    <th>User Number</th>
                    <th>Login Name</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email Address</th>
                    <th>User Role</th>
                </tr>
                <%
                    Collection<User> u = (Collection<User>) request.getAttribute("u"); // A collection of users from the user table
                    Iterator<User> iu = u.iterator(); // Iterator to count all users
                    User tempUser; // Temp user to fill up table
                    while (iu.hasNext())
                    {
                        tempUser = iu.next(); //Sets the iterator to the next user and fills in all their data on a table
                    %>
                    <tr>
                        <td>
                            <%=tempUser.getUserNumber()%>
                        </td>
                        <td>
                            <%=tempUser.getLoginName()%>
                        </td>
                        <td>
                            <%=tempUser.getFirstName()%>
                        </td>
                        <td>
                            <%=tempUser.getLastName()%>
                        </td>
                        <td>
                            <%=tempUser.getEmailAddress()%>
                        </td>
                        <td>
                            <%=tempUser.getUserRole()%>
                        </td>
                    </tr>
                    <%
                        }
                    %>
            </table>
        </div>
            </div>
        <!--Tab for error messages
        NEEDS FUNCTIONALITY TO DELETE AND CLEAR ALL-->
            <div id="sysad1" class="tabcontent paper">
                <div class="paperTop">
                    <div class="pageTitle">Error Logs</div>
                    <hr>
                    <div class ="clearFields">
                        <% if (request.getAttribute("e") != null && 
                                ((Collection<ErrorLog>) request.getAttribute("e")).isEmpty() == false) {%>
                    <form id="delete_log_form" action="ClearLogsServlet" method="POST">
                        <input type="submit" class = "error_log_buttons" name="deletesomelogs" value="Clear"/>
                        <input type="submit" class = "error_log_buttons" name="deletealllogs" value="Clear All"/>
                    </div>
                </div>
            
            <table border="1" style = "margin-top: 9%">
                <tr>
                    <th>EVENT_ID</th>
                    <th>EVENT_DATE</th>
                    <th>LEVEL</th>
                    <th>LOGGER</th>
                    <th>MESSAGE</th>
                    <th>THROWABLE</th>
                </tr>
                <%
                    Collection<ErrorLog> e = (Collection<ErrorLog>) request.getAttribute("e"); //Collection of errors from the error table
                    Iterator<ErrorLog> ie = e.iterator(); //Iterator to cycle through the error table
                    ErrorLog tempLog; //Temporary error to hold the error from the data table
                    while (ie.hasNext())
                    {
                        tempLog = ie.next(); //Gets all the errors from the database and sets them to the next one to fill the table
                    %>
                    <tr>
                        <td>
                            <%=tempLog.getEventID()%>
                        </td>
                        <td>
                            <%=tempLog.getEventDate()%>
                        </td>
                        <td>
                            <%=tempLog.getLevel()%>
                        </td>
                        <td>
                            <%=tempLog.getLogger()%>
                        </td>
                        <td>
                            <%=tempLog.getMessage()%>
                        </td>
                        <td>
                            <%=tempLog.getThrowable()%>
                        </td>
                        <td>
                            <input type="checkbox" name="deletelog" value=<%=tempLog.getEventID()%>>
                        </td>
                    </tr>
                    <%
                        }
                    %>
            </table>
            
            </form> <%} else {%>
            <h3>There are no Error Logs to display</h3>
            <%}%>
        </div>
        
        
         <!--Functionality for changing the roles of the users in the user table for future system admins and other roles to manage tenure committee-->   
        <div id="sysad2" class="tabcontent paper">
            <div class="paperTop">
                    <div class="pageTitle">Upgrade Users</div>
                    <hr>
            </div>
            <div style = "margin-top: 9%">
            <form id='update_users_form' action='RoleChangeServlet' method='POST'>
                <select name="userrecord">
            <%
                //Gets a list of users and sets them as a selctable option to change their role
                Iterator<User> iu2 = u.iterator();
                while (iu2.hasNext())
                {
                    tempUser = iu2.next();
                %>
                <option>
                    <%=tempUser.getUserNumber() + " " + tempUser.getFirstName() + " " + tempUser.getLastName()%>
                </option>
                <%}%>
                </select>
                <!--Gets all possible roles important to the tenure program
                MAY NEED TO ADD IN UNIVERSITY ROLES FOR THE FUTURE-->
                <select name="roles">
                    <option>SystemAdmin</option>
                    <option>TenureCommitteeAdministrator</option>
                    <option>TenureCommitteeMember</option>
                    <option>Professor</option>
                </select>
                <input type='submit' name='upusers' value='Change Role'/>
            </form>
            </div>
            </div>
      

        
        <script type="text/javascript">
            // Get elements with tabcontent class and hide them
            var tabContOuter, j;
            
            tabContOuter = document.getElementsByClassName("tabcontent");
            for (j = 0; j < tabContOuter.length; j++) {
                tabContOuter[j].style.display = "none";
            }
            
            // Display home tab
            document.getElementById("hometab").click();
        </script>
                  
    </body>
</html>
