<%-- 
    Document   : otherContributions
    Created on : Mar 17, 2019, 6:56:46 PM
               : Shane Panagakos (2019)
    Author     : Jake Gordon (2019)
--%>

<%@page import="common.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.BuUser"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Other Contributions</title>
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
            document.getElementsByName("otherContributions")[0].className = "inDropdown active";
            
            //keeps the dropdown open when element is clicked
            window.onload = function(){
                document.getElementById("universityCommunityDropdown").click();
            };
        </script>
        <div id="otherContributions" class="tabcontent">
            <div class="paper">
               <div class="paperTop">
                <div class="smallPageTitle"> Other Contributions to the University</div>
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
                <div class="generalTextboxStyle">Other Contributions to the University</div>
                <div class="fieldLayout">New Course or Program which you Developed
                    <input id="newCourseorProg" type="text" class="generalTextboxStyle" class="generalTextboxStyle" name="orgName">
                </div>  
                <div class="fieldLayout">Date
                    <input type="date" name="courseDate" class="generalTextboxStyle">
                </div>
                <div class="fieldLayout">Description
                    <textarea class="narrative" cols="500" rows="8" maxlength="1000" name="courseDescription">
                       
                    </textarea>
                </div>  
                <hr>
                <div class="fieldLayout">Lecture Given
                    <input id="lectures" type="text" class="generalTextboxStyle" name="lectures">
                </div>
                <div class="fieldLayout">Campus Group
                    <input id="lectures" type="text" class="generalTextboxStyle" name="campusGroup">
                </div>
                <div class="fieldLayout">Date
                    <input type="date" name="lectureDate" class="generalTextboxStyle">
                </div>
                <div class="fieldLayout">Description
                    <textarea class="narrative" cols="500" rows="8" maxlength="1000" name="lectureDescription">
                       
                    </textarea>
                </div>  
                <hr>
                <div class="fieldLayout">Other Professionally Relevant Contributions to the University
                    <input id="otherRelevant" type="text" class="generalTextboxStyle" name="otherRelevant">
                </div>
                <div class="fieldLayout">Event
                    <input id="otherRelevant" type="text" class="generalTextboxStyle" name="otherRelevantEvent">
                </div>
                <div class="fieldLayout">Date
                    <input type="date" name="eventDate" class="generalTextboxStyle">
                </div>
                <div class="fieldLayout">Description
                    <textarea class="narrative" cols="500" rows="8" maxlength="1000" name="eventDescription">
                       
                    </textarea>
                </div>  
            </div>
            <div class="footerButtons">
                <hr>
                
                <form id='redirct_page' action='RedirectServlet' method='POST'>
                    <input type="submit" hidden="hidden" id="save_hidden_button">
                    <input type="submit" name="studentOrgAdvisement" value="Back"/>
                    <input type="submit" name="communityContributions" value="Continue"/>
                </form>
                <!--Skip button-->
                <form id='redirct_page' action='RedirectServlet' method='POST'>                    
                        <input type="submit" name="communityContributions" value="Skip"/>
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

