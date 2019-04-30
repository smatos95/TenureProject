<%-- 
    Document   : communityContributions
    Created on : Mar 17, 2019, 7:21:07 PM
               : Shane Panagakos (2019)
    Author     : Jake Gordon (2019)
--%>

<%@page import="common.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.BuUser"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Community Contributions</title>
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
            document.getElementsByName("dropbutton")[3].className = "dropdown-btn active";
            document.getElementsByName("communityContributions")[0].className = "inDropdown active";
            
            //keeps the dropdown open when element is clicked
            window.onload = function(){
                document.getElementById("universityCommunityDropdown").click();
            };
        </script>
        <!--Very broad descriptions for input, might need to rename and change what is entered-->
        <div id="communityContributions" class="tabcontent">
            <div class="paper">
              <div class="paperTop">
                <div class="pageTitle">Community Contributions</div>
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
            <div class="paperContents">
                <div class="generalHeaderFont" class="generalTextboxStyle">Voluntary membership in Community Based Organizations</div>  
                
                <div class="fieldLayout" class="generalTextboxStyle">Organization
                    <input id="volunteer" type="text" name="volunteerOrganization" class="generalTextboxStyle">
                </div> 
                <div class="fieldLayout">Date
                    <input type="date" name="volunteerDate" class="generalTextboxStyle">
                </div>
                <div class="fieldLayout">Description
                    <textarea class="narrative" cols="500" rows="8" maxlength="1000" name="volunteerDescription">
                       
                    </textarea>
                </div>  
                <hr>
                <div class="generalHeaderFont" class="generalTextboxStyle">Lectures for Community Groups</div>
                
                <div class="fieldLayout" class="generalTextboxStyle">Event
                    <input id="volunteer" type="text" name="lectureEvent" class="generalTextboxStyle">
                </div> 
                <div class="fieldLayout">Date
                    <input type="date" name="lectureDate" class="generalTextboxStyle">
                </div>
                <div class="fieldLayout">Description
                    <textarea class="narrative" cols="500" rows="8" maxlength="1000" name="lectureDescription">
                       
                    </textarea>
                </div>
                <hr>
                <div class="generalHeaderFont" class="generalTextboxStyle">Consultations with Local and Area Agencies</div>
                
                <div class="fieldLayout" class="generalTextboxStyle">Agency
                    <input id="volunteer" type="text" name="agency" class="generalTextboxStyle">
                </div> 
                <div class="fieldLayout">Date
                    <input type="date" name="consultDate" class="generalTextboxStyle">
                </div>
                <div class="fieldLayout">Description
                    <textarea class="narrative" cols="500" rows="8" maxlength="1000" name="consultDescription">
                       
                    </textarea>
                </div>
            </div>
            <div class="footerButtons">
                <hr>
                
                <form id='redirct_page' action='RedirectServlet' method='POST'>
                    <input type="submit" hidden="hidden" id="save_hidden_button">
                    <input type="submit" name="otherContributions" value="Back"/>
                    <input type="submit" name="tenureSigPage" value="Continue"/>
                </form>
                <!--Skip button-->
                <form id='redirct_page' action='RedirectServlet' method='POST'> 
                        <input type="submit" name="tenureSigPage" value="Skip"/>
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
