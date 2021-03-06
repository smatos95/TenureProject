<%-- 
    Document   : home
    Created on : Feb 24, 2019, 4:49:59 PM
               : Shane Panagakos (2019)
    Author     : Jake Gordon (2019)
--%>

<%@page import="common.Application"%>
<%@page import="common.User"%>
<%@page import="common.BuUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Overview</title>
        <link rel ="icon" href="../images/husky.png">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/controlPage.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>JSP Page</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>

    </head>

    <body bgcolor="#F4ECD9">
        <% HttpSession session1 = request.getSession(true);
            common.BuUser use = (common.BuUser) session1.getAttribute("user");
            Collection<Application> appCollection = (Collection<Application>) request.getSession().getAttribute("apps");
            Application app = (Application) request.getSession().getAttribute("currentapp");
        %>
        <jsp:include page="sideTabs.jsp"/>
        <script>
            document.getElementsByName("overview")[0].className = "tabbuttons active";
        </script>

        <div class ="tabcontent">
            <div class ="paper">
                <div>
            <div class="paperTop">
                    <!-- Table of contents code-->    
                    <!--<div id="toc_container">-->
                    <div class="pageTitle">Table Of Contents</div>
                    <center><div>
                        <div class ="current_apps_subtitle">Current Application:</div> 
                        <div class ="current_apps_displayBox">
                            <%
                                /**
                                * This line checks to see if the user has no current app and warns them that they have no app currently selected.
                                */
                                if (request.getSession().getAttribute("currentapp") == null) {%>
                            No Application Selected
                            <%}
                                /**
                                * This line is invoked when a user has a current application. It sets the current <code>application</code> to the attribute of the current app.
                                * The <code>friendlyName</code> of the current user's application is then displayed to show the user the application they are working on.
                                * 
                                * @param current The application that is currently being editted.
                                */
                                else {
                                Application current = (Application) request.getSession().getAttribute("currentapp");
                            %>
                            <%=current.getFriendlyName()%>
                            <%}%>
                        </div>
                        </div></center>
                <hr>
            </div>
            <div class="paperContents">
                    <table id="TableOfContents">
                        <form id='redirct_page' action='RedirectServlet' method='POST'>
                            <tr>
                                <td style = "text-indent:20px;">Cover Page<input type="submit" class="linkButton" name = "cover" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:20px;">First Page<input type="submit" class="linkButton" name = "first" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:20px;">Background Information</td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Educational Credentials<input type="submit" class="linkButton" name = "eduCred" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Teaching/Faculty<input type="submit" class="linkButton" name = "teachFac" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Other Work Experience<input type="submit" class="linkButton" name = "workExp" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Official Transcripts<input type="submit" class="linkButton" name = "transcripts" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:20px;">Teaching and Fulfillment</td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Professional Responsibilities<input type="submit" class="linkButton" name = "teachingNarrative" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">BU Teaching History<input type="submit" class="linkButton" name = "teachingHistory" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Summary Tables of Student Evaluations<input type="submit" class="linkButton" name = "studentEvaluations" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Fulfillment of Professional Responsibilities<input type="submit" class="linkButton" name = "fulfillmentProfessional" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Assigned Time Activities<input type="submit" class="linkButton" name = "timeActivities" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Teaching Related Innovations<input type="submit" class="linkButton" name = "Innovations" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:20px;">Scholarly Growth</td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Narrative on Continuing Scholarly Growth<input type="submit" class="linkButton" name = "scholar" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Activities<input type="submit" class="linkButton" name = "Activities" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:20px;">University/Community</td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Narrative on Contributions to University and Community<input type="submit" class="linkButton" name = "uniCommNarrative" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Committee Service<input type="submit" class="linkButton" name = "committeeService" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Advisement to Student Organizations<input type="submit" class="linkButton" name = "studentOrgAdvisement" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Other Contributions to the University<input type="submit" class="linkButton" name = "otherContributions" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Community Contributions<input type="submit" class="linkButton" name = "communityContributions" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:20px;">Signature Pages</td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Tenure Application Signature Page<input type="submit" class="linkButton" name = "tenureSigPage" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Department Committee Narrative and Signature Page<input type="submit" class="linkButton" name = "deptNarrativeSigPage" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Chairperson Narrative and Signature Page<input type="submit" class="linkButton" name = "chairNarrativeSigPage" value="Go To"/></td>
                            </tr>

                            <tr>
                                <td style = "text-indent:50px;">Special Note<input type="submit" class="linkButton" name = "specialNote" value="Go To"/></td>
                            </tr>               
                        </div>

                        </form>
                    </table>
            </div>
            <div class="footerButtons">
                    <hr>               
                    </form>
                    <!--Continue Button-->
                    <form id='redirct_page' action='RedirectServlet' method='POST'>
                        <input type="submit" name="cover" value="Continue"/>

                    </form>  
                    <!-- Back Button -->
                    <form id='user_srvlt' action='UserServlet' method='POST'>
                        <input type="submit" name="myApplication" value="Back"/> 
                    </form>


            </div>

                </div>
            </div>
        </div>

        <script>
             /**
             * Activates and displays a vertical list of buttons which are set 
             * to the side navigation in order to implement the dropdown feature. 
             * 
             * @param dropdown The button that drops down all the other buttons.
             * @param i An interger used to iterate through all the children of the dropdown buttons.
             * @param dropdownContent Any child of the dropdown button.
             * 
             */
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
