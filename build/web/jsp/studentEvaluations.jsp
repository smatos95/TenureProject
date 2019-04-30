<%-- 
    Document   : studentEvaluations
    Created on : Mar 17, 2019, 3:08:51 PM
               : Shane Panagakos (2019)
    Author     : Jake Gordon (2019)
--%>

<%@page import="common.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.BuUser"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Student Evaluations</title>
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
            document.getElementsByName("studentEvaluations")[0].className = "inDropdown active";
            
            //keeps the dropdown open when element is clicked
            window.onload = function(){
                document.getElementById("teachingAndFulfillmentDropdown").click();
            };
        </script>
        <div id="studentEvaluations" class="tabcontent">
            <div class="paper">
              <div class="paperTop">  
                  
                 <!--Save button-->
                 <input type="submit" onclick="clickSave()" value="Save"/>
                  
                <div class="pageTitle">Summary Tables of Student  Evaluations</div>
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
                        <hr>
              </div>
              <div class="paperContents">          
                <!-- This page needs better styling-->
                <p class="description">
                    If you have administered them, include one or more Summary Tables of Student  Evaluations; (place original data in Andruss Library).<br/><br/>		
                </p>
                <div class="generalHeaderFont">INSTRUCTIONS for creating Summary Tables of Student Evaluations:</div><ul class="description">
                    <li>Generate the table in a format which presents data clearly and summarizes the overall information.</li><br/>
                    <li>Summarize the data across all courses taught, or use other organizational schemas, such as: 
                        all sections of a single course; or lower-level versus upper-level courses.  
                        The format must be clearly organized and legible.  
                        A short note may explain the format.</li><br/>
                    <li>Be aware that reviewers of the application may verify the accuracy of these tables 
                        by cross-checking with the original data in the supporting materials.</li><br/>
                    <li>Each table must include the following information:
                        <ol><br/>
                            <li>course numbers and names of courses taught (full or abbreviated);</li><br/>
                            <li>number of students enrolled in the course, and number of those responding to questionnaires;</li><br/>
                            <li>number of responses to every questionnaire item on all five categories (a through e);</li><br/>
                            <li>response data given as frequencies (= actual numbers, rather than percentages).</li><br/>
                            <li>Grade distribution chart (request from Institutional Research with each student evaluation for the semester).</li><br/>
                        </ol></li>
            </ul>
              </div>  
             <div class="footerButtons">   
                <hr>
                
                <form id='redirct_page' action='RedirectServlet' method='POST'>
                    <input type="submit" hidden="hidden" id="save_hidden_button">
                    <input type="submit" name="teachingHistory" value="Back"/>
                    <input type="submit" name="fulfillmentProfessional" value="Continue"/>                            
                </form>
                <form id='redirct_page' action='RedirectServlet' method='POST'>                 
                    <!--skip button-->                    
                    <input type="submit" name="fulfillmentProfessional" value="Skip"/>
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
    </body>
</html>