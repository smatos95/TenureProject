<%-- 
    Document   : BU Teaching History
    Created on : Feb 24, 2019, 2:25:10 PM
               : Shane Panagakos (2019)
    Author     : Jake Gordon (2019)
--%>

<%@page import="java.util.Iterator"%>
<%@page import="common.LocalExp"%>
<%@page import="java.util.List"%>
<%@page import="mysql.LocalExpManager"%>
<%@page import="common.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.BuUser"%>
<script src="scripts/wordCounterScript.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <title>BU Teaching History</title>
        <link rel ="icon" href="../images/husky.png">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/controlPage.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script type="text/javascript">          
            
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
        <script src="scripts/wordCounterScript.js"></script>
    </head>
   
    <body bgcolor="#F4ECD9">
         <% HttpSession sess = request.getSession(true);
            final Object lock = sess.getId().intern();
            LocalExpManager lem = new LocalExpManager();
            Application app;
            Integer currentexp;
            BuUser user;
            
            synchronized (lock) {
                    app = (Application) sess.getAttribute("currentapp");
                    currentexp = (Integer) sess.getAttribute("currentexp");
                    user = (BuUser) sess.getAttribute("user");
            }
            
            List<LocalExp> lec = null;
            boolean tecExist = false;
            LocalExp e = null;
            if (app != null)
                lec = lem.getLocalExpsByApplication(app.getApplicationID());
            tecExist = (lec != null && lec.size() > 0);
            
            // set default field values
            String semester = "";
            String year = "";
            String courseCode = "";
            String courseName = "";
            String sections = "";
            
            // get the record that corresponds to currentexp
            if (tecExist && currentexp != null) {
                    for(LocalExp exp : lec) {
                        if(exp.getLocalExpID() == currentexp) {
                            e = exp;
                            break;
                        }
                    }
                } else synchronized (lock) { // no recirds for application or currentexp not set
                    sess.setAttribute("currentexp", new Integer(-1));
                }
            
            // Check each field for nulls individually
            if (e != null) {
                if (e.getSemester() != null)
                    semester = e.getSemester();
                if (e.getYear() != null)
                    year = e.getYear();
                if (e.getCourseCode() != null)
                    courseCode = e.getCourseCode();
                if (e.getCourseName() != null)
                    courseName = e.getCourseName();
                if (e.getSections() != null)
                    sections = e.getSections();
            }
                    %>
        <jsp:include page="sideTabs.jsp"/>
        <script>
            document.getElementsByName("dropbutton")[1].className = "dropdown-btn active";
            document.getElementsByName("teachingHistory")[0].className = "inDropdown active";
            
            window.onload = function(){
                document.getElementById("teachingAndFulfillmentDropdown").click();
            };
        </script>
        <div id="teachingHistory" class="tabcontent">
            <div class="paper">
              <div class="paperTop">
                  
                  <!--Save button-->
                  <input type="submit" onclick="clickSave()" value="Save"/>
                  
                <div class="pageTitle">BU Teaching History</div>
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
                <div>Submitted Records</div>
                  <form id="degree_table" method="POST">
                    <%if (tecExist) {%>
                    <table>
                        <%  
                            // get the iterator for the degrees and print labels
                            Iterator<LocalExp> lecItr = lec.iterator();
                            LocalExp lex = lecItr.next();
                            out.print("<tr>");
                            lex.printSummaryLabels(out);
                            out.println("</tr>");
                            
                            // print the first line
                            out.print("<tr>");
                            lex.printSummaryRow(out);
                            out.println("<td><button type=\"submit\" formaction=\"BUTeachAutofillServlet\" name=\"exp\" value=\""+lex.getLocalExpID()+"\">Edit</button></td>");
                            out.println("<td><button type=\"submit\" formaction=\"BUTeachDeleteServlet\" name=\"exp\" value=\""+lex.getLocalExpID()+"\">Delete</button></td>");
                            out.println("</tr>");
                            // print the rest of the lines
                            while (lecItr.hasNext()) {
                                lex = lecItr.next();
                                out.print("<tr>");
                                lex.printSummaryRow(out);
                                out.println("<td><button type=\"submit\" formaction=\"BUTeachAutofillServlet\" name=\"exp\" value=\""+lex.getLocalExpID()+"\">Edit</button></td>");
                                out.println("<td><button type=\"submit\" formaction=\"BUTeachDeleteServlet\" name=\"exp\" value=\""+lex.getLocalExpID()+"\">Delete</button></td>");
                                out.println("</tr>");
                            }
                            out.println("</table>");%>
                    <%} else {%>No records have been added.<%}%>
                    </form>

                    <p class="description">List the following information for the Institution(s) you have taught at:</p>

                    <form id="update_teaching_history_form" action="UpdateBUTeachingServlet" method="POST">
                    <div class="fieldLayout">Semester
                    <select name="semester" class="generalTextboxStyle">
                        <option <%if (semester.equals("")){%> selected="selected"<%}%>>N/A</option>
                        <option <%if (semester.equals("Spring")){%> selected="selected"<%}%>>Spring</option>
                        <option <%if (semester.equals("Summer")){%> selected="selected"<%}%>>Summer</option>
                        <option <%if (semester.equals("Fall")){%> selected="selected"<%}%>>Fall</option>
                        <option <%if (semester.equals("Winter")){%> selected="selected"<%}%>>Winter</option>
                    </select>
                    </div>
                    <div class="fieldLayout">Year
                        <input type="number" name="year" placeholder="2011" class="generalTextboxStyle" value="<%=year%>"/>
                    </div>
                    <div class="fieldLayout">Short course code <span style="margin-left: 37.5%" id="courseCodeCurrent"><%=courseCode.length()%></span><span>/35</span>
                        <input id="courseCode" maxLength="35" oninput="updateCharCount('courseCodeCurrent', 'courseCode') "type="text" name="courseCode" class="generalTextboxStyle" value="<%=courseCode%>" placeholder="ENGLISH 101"/>
                    </div>
                    <div class="fieldLayout">Full course name <span style="margin-left: 38%" id="courseNameCurrent"><%=courseName.length()%></span><span>/35</span>
                        <input id="courseName" oninput="updateCharCount('courseNameCurrent', 'courseName')" maxLength="35" type="text" name="courseName" class="generalTextboxStyle" value="<%=courseName%>" placeholder="Foundations of Writing"/>
                    </div>
                    <div class="fieldLayout">Sections taught <span style="margin-left: 39%" id="sectionsCurrent"><%=sections.length()%></span><span>/35</span>
                        <input id="sections" oninput="updateCharCount('sectionsCurrent', 'sections')" maxLength="35" type="text" name="sections" class="generalTextboxStyle" value="<%=sections%>">
                    <input type="submit" name="insert" value="Add New Credential"/>
                  </div>
              <div class="footerButtons">
                <hr>
                
                <form id='redirct_page' action='UpdateBUTeachingServlet' method='POST'>
                     <input type="submit" hidden="hidden" id="save_hidden_button">
                     <input type="submit" name="teachingNarrative" value="Back"/>
                     <input type="submit" name="print" value="Export"/>
                    <input type="submit" name="studentEvaluations" value="Continue"/>
                </form>
                <form id='redirct_page' action='RedirectServlet' method='POST'>                 
                    <!--skip button-->                    
                    <input type="submit" name="studentEvaluations" value="Skip"/>
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
