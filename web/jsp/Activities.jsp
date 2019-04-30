<%-- 
    Document   : Activities
    Created on : Mar 17, 2019, 4:23:33 PM
               : Shane Panagakos (2019)
    Author     : Jake Gordon (2019)
--%>

<%@page import="common.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.BuUser"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Activities</title>
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
            document.getElementsByName("dropbutton")[2].className = "dropdown-btn active";
            document.getElementsByName("Activities")[0].className = "inDropdown active";
            
            //keeps the dropdown open when element is clicked
            window.onload = function(){
                document.getElementById("scholarlyGrowthDropdown").click();
            };
        </script>
        <div id="scholarly" class="tabcontent">
            <div class="paper">
              <div class="paperTop">
                <div class="pageTitle">Activities</div>
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
                <p>
                <p class="description">
                    List Activities in support of your claim of continuing scholarly growth, following
                    these guidelines:<br/>
                <ul class="description">
                    <li>Review of the list below and determine which best describes each of your works</li>
                    <li>Be sure to place copies of published articles and documentation of the status of your work
                    in library</li>
                    <li><b>DO NOT</b> list in this section materials resulting from a Bloomsburg University committee assignment</li>
                </ul>
                Contractually specified categories of scholarly growth include the following items:
                <br/>
                <br/>
                <input type="checkbox" name="activity1" value="activity1">Graduate course work completed in the discipline or related discipline.
                <br/>
                <input type="checkbox" name="activity2" value="activity2">Publications of juried scholarly articles, monographs, books, or presentations of original works.
                <br/>
                <input type="checkbox" name="activity3" value="activity3">Papers presented at professional societies.
                <br/>
                <input type="checkbox" name="activity4" value="activity4">Participation in juried, invitational or other shows.
                <br/>
                <input type="checkbox" name="activity5" value="activity5">Offices held in professional organizations.
                <br/>
                <input type="checkbox" name="activity6" value="activity6">Editorships of professional publications.
                <br/>
                <input type="checkbox" name="activity7" value="activity7">Professional awards and honors.
                <br/>
                <input type="checkbox" name="activity8" value="activity8">Participation in professional meetings, organizations, conferences, and workshops of professional societies.
                <br/>
                 <input type="checkbox" name="activity9" value="activity9">Applications for grants and grant acquisitions.
                <br/>
                 <input type="checkbox" name="activity10" value="activity10">Invitational lectures presented.
                <br/>
                <input type="checkbox" name="activity11" value="activity11">Musical or theatrical performances.
                <br/>
                <input type="checkbox" name="activity12" value="activity12">Refereeing of papers for presentation and/or publication/ book reviews.
                <br/>
                <input type="checkbox" name="activity13" value="activity13">Peer review of grant applications.
                <br/>
                <input type="checkbox" name="activity14" value="activity14">Development of experimental programs and program-related projects.
                <br/>
                <input type="checkbox" name="activity15" value="activity15">Collaborative research with students that result in a presentation or publication.
                <br/>
                <input type="checkbox" name="activity16" value="activity16">Collaboration with other professionals.
                <br/>
                <input type="checkbox" name="activity17" value="activity17">Consultantships that involve research and results in scholarly work.
                <br/>
                <input type="checkbox" name="activity18" value="activity18">Other:<input type="text" name="other">
                <br/>
                
                </p>
                <!--<div>
                    <textarea class="narrative" cols="500" rows="26" maxlength="3000">
                       
                    </textarea>
                </div>-->
            </div>
            <div class="footerButtons">
                <hr>
                
                <form id='redirct_page' action='RedirectServlet' method='POST'>
                    <input type="submit" hidden="hidden" id="save_hidden_button">
                    <input type="submit" name="scholar" value="Back"/>
                    <input type="submit" name="uniCommNarrative" value="Continue"/>
                </form>
                <form id='redirct_page' action='RedirectServlet' method='POST'>                 
                    <!--skip button-->                    
                    <input type="submit" name="uniCommNarrative" value="Skip"/>
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