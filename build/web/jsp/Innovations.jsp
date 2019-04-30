<%-- 
    Document   : Innovations
    Created on : Mar 17, 2019, 3:30:36 PM
               : Shane Panagakos (2019)
    Author     : Jake Gordon (2019)
--%>

<%@page import="common.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.BuUser"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Innovations</title>
        <link rel ="icon" href="../images/husky.png">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/controlPage.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script type="text/javascript">          
            
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
    </head>
   
    <body bgcolor="#F4ECD9">
         <% HttpSession session1 = request.getSession(true);
                    common.BuUser use = (common.BuUser) session1.getAttribute("user");
                    %>
        <jsp:include page="sideTabs.jsp"/>
        <script>
            document.getElementsByName("dropbutton")[1].className = "dropdown-btn active";
            document.getElementsByName("Innovations")[0].className = "inDropdown active";
            
            //keeps the dropdown open when element is clicked
            window.onload = function(){
                document.getElementById("teachingAndFulfillmentDropdown").click();
            };
        </script>
        <div id="Innovations" class="tabcontent">
            <div class="paper">
                <div class="paperTop">
                <div class="pageTitle">Teaching Related Innovations</div>
                <center><div>
                        <div class ="current_apps_subtitle">Current Application:</div> 
                        <div class ="current_apps_displayBox">
                            <% if (request.getSession().getAttribute("currentapp") == null) {%>
                            No Application Selected
                            <%} else {
                                Application current = (Application) request.getSession().getAttribute("currentapp");
                            %>
                            <%=current.getFriendlyName()%>
                            <%}%>
                        </div>
                        </div></center>
                        <!--Save button-->
                        <input type="submit" onclick="clickSave()" value="Save"/>
                    <hr>
            </div>
            <div class="paperContent">
                <!-- This page needs better styling-->
                <p class="description">
                   List your Teaching Related Innovations, if any, indicating the extent of your personal contribution.
                   (<b>DO NOT</b> include in this section “New courses” to be listed in Section Four of this Application).
                </p>
            </div>
            <div class="footerButtons">
                <hr>
                <form id='redirct_page' action='RedirectServlet' method='POST'>
                        <input type="submit" hidden="hidden" id="save_hidden_button">
                    <input type="submit" name="timeActivities" value="Back"/>
                    <input type="submit" name="scholar" value="Continue"/>
                </form>
                <form id='redirct_page' action='RedirectServlet' method='POST'>                 
                    <!--skip button-->                    
                    <input type="submit" name="scholar" value="Skip"/>
                </form>
            </div>
          </div>
        </div> 
        <!-- Dropdown menu script -->
        <script>
            var dropdown = document.getElementsByClassName("dropdown-btn");
            var i;

            for (i = 0; i < dropdown.length; i++) {
                dropdown[i].addEventListener("click", function () {
                    
                    var dropdownContent = this.nextElementSibling;
                    if (dropdownContent.style.display === "block") {
                        dropdownContent.style.display = "none";
                    } else {
                        dropdownContent.style.display = "block";
                    }
                });
            }           
        </script>
        <script>
            // This function allows the visible button 'saved' to be clicked through a hidden button
            function clickSave()
            { 
                document.getElementById("save_hidden_button").click();
            }
         </script>
    </body>
</html>
