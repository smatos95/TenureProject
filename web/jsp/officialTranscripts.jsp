<%-- 
    Document   : officialTranscripts
    Created on : Feb 24, 2019, 2:05:23 PM
               : Shane Panagakos (2019)
    Author     : Jake Gordon (2019)
--%>

<%@page import="utilities.FormattingUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="common.Degree"%>
<%@page import="java.util.Collection"%>
<%@page import="mysql.DegreeManager"%>
<%@page import="common.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Official Transcripts</title>
        <link rel ="icon" href="../images/husky.png">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/controlPage.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
    </head>
    <body bgcolor="#F4ECD9">
        <% HttpSession session1 = request.getSession(true);
            common.BuUser use = (common.BuUser) session1.getAttribute("user");
            Application app = (Application) request.getSession().getAttribute("currentapp");
        %>
        <jsp:include page="sideTabs.jsp"/>
        <script>
            document.getElementsByName("dropbutton")[0].className = "dropdown-btn active";
            document.getElementsByName("transcripts")[0].className = "inDropdown active";
            
            //keeps the dropdown open when element is clicked
            window.onload = function(){
                document.getElementById("backgroundInformationDropdown").click();
            };
        </script>

        <!--adds content to the official transcript tab-->
        <div id="transcripts" class="tabcontent">
            <div class="paper">
              <div class="paperTop">
                  
                  <!--save button-->
                        <input type="submit" onclick="clickSave()" value="Save"/>
                  
                <div class="pageTitle">Official Transcripts</div>
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
                <div>Attach copies of Official Transcripts of all GRADUATE work.  If any official transcript is in a language other than English, a translation should be provided.</div>
                <p class="description">Attach copies of Official Transcripts of all <b>GRADUATE</b> work.  If any official transcript is in a language other than English, a translation should be provided.</p>
                
                <%DegreeManager degm = new DegreeManager();
                  Collection<Degree> degs = degm.getDegreesByApplication(app.getApplicationID());
                  Degree temp;
                  String institution;
                  String credential;
                  String field;
                  String attendanceFrom;
                  String attendanceTo;
                  String degreeDate;
                  String transcSub = "No";
                  if (degs != null && degs.isEmpty() == false) {
                      Iterator<Degree> itan = degs.iterator();%>
                      Select the degree to which you would like to add a transcript:
                      <form id="add_transcript_form" action="SubmitTranscriptsServlet" method="POST" enctype="multipart/form-data">
                      <table style="border: 1px solid black;">
                          <tr>
                            <th>Institution</th>
                            <th>Credential</th>
                            <th>Field of Study</th>
                            <th>Attended From</th>
                            <th>Attended To</th>
                            <th>Degree Conferred</th>
                            <th>Transcript Submitted</th>
                          </tr>
                          <%while (itan.hasNext()) {
                              temp = itan.next();
                              institution = temp.getInstitution();
                              credential = temp.getCredential();
                              field = temp.getFieldOfStudy();
                              attendanceFrom = utilities.FormattingUtility.formatDate(utilities.FormattingUtility.dateRangeConvert(temp.getAttendancePeriods())[0]);
                              attendanceTo = utilities.FormattingUtility.formatDate(utilities.FormattingUtility.dateRangeConvert(temp.getAttendancePeriods())[1]);
                              degreeDate = utilities.FormattingUtility.formatDate(temp.getDegreeDate());
                              if (temp.getTranscript() > 0)
                                transcSub = "Yes";
                              else
                                transcSub = "No";
                              %>
                          <tr>
                              <td><%=institution%></td>
                              <td><%=credential%></td>
                              <td><%=field%></td>
                              <td><%=attendanceFrom%></td>
                              <td><%=attendanceTo%></td>
                              <td><%=degreeDate%></td>
                              <td><%=transcSub%></td>
                              <td><input type="radio" name="submittranscript" value=<%=temp.getDegreeID()%>></td>
                          </tr>
                          <%}%>
                      </table>
                      <input type="submit" value="Link This Transcript"/>
                      
                      <%} else {
                            %>No Degrees Submitted.
                          <%}%>
                
                <input type="file" id="transcript-hidden-button" name="transcript-hidden-button" hidden="hidden"/>
                <button type="button" id="transcript-upload-button">Select</button>
                <span id="transcript-file-text">No file selected.</span><br>
                <button type="button" id="transcript-submit-button">Submit</button>
                </form>
              </div>
              <div class="footerButtons">
                <hr>
                <input type="submit" hidden="hidden" id="save_hidden_button">
                <form id='redirct_page' action='RedirectServlet' method='POST'>
                    <!--back button-->
                        <span><input type="submit" name="workExp" value="Back"/>
                    <!--next button-->
                    <input type="submit" name="teachingNarrative" value="Continue"/></span>                    
                </form>
                <form id='redirct_page' action='RedirectServlet' method='POST'>                 
                    <!--skip button-->                    
                    <input type="submit" name="teachingNarrative" value="Skip"/>
                </form>
              </div>
            </div>
        </div>
        <script type="text/javascript">
            const transcriptHiddenButton = document.getElementById("transcript-hidden-button");
            const transcriptUploadButton = document.getElementById("transcript-upload-button");
            const transcriptFileText = document.getElementById("transcript-file-text");
            
            <!--when the customized upload button is clicked, the hidden button is clicked-->
            transcriptUploadButton.addEventListener("click", function()
            {
                transcriptHiddenButton.click();
            });
            
            <!--sets the text next to the button to display the selected file name-->
            transcriptHiddenButton.addEventListener("change",function()
            {
                if(transcriptHiddenButton.value)
                <!--extracts the file name from the file path and displays it next to the upload button-->
                    transcriptFileText.innerHTML=transcriptHiddenButton.value.match(/[\/\\]([\w\d\s\.\-\(\)]+)$/)[1];
                else
                    transcriptFileText.innerHTML="No file selected.";
               
            });
        </script>
        
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
