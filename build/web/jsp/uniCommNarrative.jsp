<%-- 
    Document   : uniCommNarrative
    Created on : Mar 17, 2019, 5:10:34 PM
               : Shane Panagakos (2019)
    Author     : Jake Gordon (2019)
--%>

<%@page import="mysql.NarrativeManager"%>
<%@page import="java.util.List"%>
<%@page import="common.Narrative"%>
<%@page import="java.util.Iterator"%>
<%@page import="common.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.BuUser"%>
<!DOCTYPE html>
<html>
    <head>
        <title>University & Community Narratives</title>
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
         <% HttpSession session1 = request.getSession(true);
                    common.BuUser use = (common.BuUser) session1.getAttribute("user");
                    NarrativeManager narrm = new NarrativeManager();
                    List<Narrative> narrs = narrm.getNarrativesByApplication(
                            ((Application) request.getSession().getAttribute("currentapp")).getApplicationID());
                    Narrative narr = null;
                    Iterator<Narrative> itann = null;
                    String narrativeText = "";
                    if (narrs != null && narrs.isEmpty() == false) {
                        itann = narrs.iterator();

                        while (itann.hasNext()) {
                            narr = itann.next();

                            if (narr.getType().equals("UC"))
                                break;
                            else
                                narr = null;
                        }
                    }
                    
                    if (narr != null) {
                        narrativeText = narr.getNarrativeText();
                    }
                    %>
        <jsp:include page="sideTabs.jsp"/>
        <script>
            document.getElementsByName("dropbutton")[3].className = "dropdown-btn active";
            document.getElementsByName("uniCommNarrative")[0].className = "inDropdown active";
            
            //keeps the dropdown open when element is clicked
            window.onload = function(){
                document.getElementById("universityCommunityDropdown").click();
            };
        </script>
        <div id="uniCommNarrative" class="tabcontent">
            <div class="paper">
               <div class="paperTop">
                <div class="smallPageTitle">Narrative on Contributions to University and Community</div>

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
                <p class="description">
                    The narrative should specifically describe your original contributions in that area.
                    It should <b>NOT</b> be simply a list of achievements in sentence form.
                </p>
                <div>
                    <div id="uniCommnarrativeWordCount" style="float: left; margin-left: 1%;">
                       <span id="uniCommnarrativeCurrent"><%=narrativeText.length()%></span>
                       <span id="uniCommnarrativeMaximum">/ 4050</span>
                    </div>
                    <br>
                <form id='redirct_page' action='UpdateUniCommServlet' method='POST'>
                    <textarea id="uniCommnarrativeTextarea" oninput="updateUniCommNarrativeCharCount()" class="narrative" rows="20" maxlength="4050" name="UniCommNarr"><%=narrativeText%></textarea>    
                </div>
            </div>
            <div class="footerButtons">
                <hr>
                   <input type="submit" hidden="hidden" id="save_hidden_button">
                    <input type="submit" name="Activities" value="Back"/>
                    <input type="submit" formaction="PrintUniCommServlet" value="Export"/>
                    <input type="submit" name="committeeService" value="Continue"/>
                                
                </form>
                <form id='redirct_page' action='RedirectServlet' method='POST'>
                    <!--skip button-->                    
                    <input type="submit" name="committeeService" value="Skip"/>
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
