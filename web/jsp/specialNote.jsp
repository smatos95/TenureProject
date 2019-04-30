<%-- 
    Document   : specialNote
    Created on : Mar 17, 2019, 8:09:03 PM
    Author     : Jake Gordon (2019)
--%>

<%@page import="common.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.BuUser"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Special Note</title>
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
            document.getElementsByName("dropbutton")[4].className = "dropdown-btn active";
            document.getElementsByName("specialNote")[0].className = "inDropdown active";
                    
            //keeps the dropdown open when element is clicked
            window.onload = function(){
                document.getElementById("signaturePagesDropdown").click();
            };
        </script>
        <div id="chairNarrativeSigPage" class="tabcontent">
            <div class="paper">
              <div class="paperTop">
                <div class="pageTitle">Special Notes</div>

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
                <p>
                    Special note about committee and chairperson narratives.  If these items are not received in time for<br/>
                    duplication and binding with the rest of the Application, they should be firmly attached to the end of<br/>
                    the document with staple, clips, or other binding materials.<br/>
                    <br/>
                    Special note about signatures:  We realize that, on rare occasions, evaluations and recommendations<br/>
                    may be written and signed by someone other than department faculty members or the department<br/>
                    chair.  (For example, this might occur when a conflict of interest exists because of a familial  or<br/>
                    domestic relationship with the applicant, or in a small department which cannot form a tenure<br/>
                    committee solely from its faculty.)  In such cases the University Tenure Committee requests prior<br/>

                <p class="description">
                    Special note about committee and chairperson narratives.  If these items are not received in time for
                    duplication and binding with the rest of the Application, they should be firmly attached to the end of
                    the document with staple, clips, or other binding materials.
                </p>
                <p class="description">
                    Special note about signatures:  We realize that, on rare occasions, evaluations and recommendations
                    may be written and signed by someone other than department faculty members or the department
                    chair.  (For example, this might occur when a conflict of interest exists because of a familial  or
                    domestic relationship with the applicant, or in a small department which cannot form a tenure
                    committee solely from its faculty.)  In such cases the University Tenure Committee requests prior

                    consultation if at all possible, plus an explanation to accompany such documents.
                </p>
                
                <!--<div>
                    <textarea class="narrative" cols="500" rows="26" maxlength="3000">
                       
                    </textarea>
                </div>-->
              </div>
              <div class="footerButtons">
                  <hr>
                <form id='redirct_page' action='RedirectServlet' method='POST'>
                        <input type="submit" name="chairNarrativeSigPage" value="Back"/>                    
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

