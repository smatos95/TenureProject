<%-- 
    Document   : firstpage
    Created on : Feb 24, 2019, 2:13:03 PM
               : Shane Panagakos(2019)
    Author     : Jake Gordon (2019)
--%>

<%@page import="mysql.ProfileManager"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="common.Profile"%>
<%@page import="common.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.BuUser"%>
<!DOCTYPE html>
<html>
    <head>
        <title>First Page</title>
        <link rel ="icon" href="../images/husky.png">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/controlPage.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script type="text/javascript"></script>
        <script src="scripts/wordCounterScript.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
        
        <style>
            .collapsible {
              cursor: pointer;
              padding: 1vw;
              width: auto;
            }

            .collapsible:hover {
                text-shadow: 2px 2px rgba(0,0,0,0.19), 3px 3px rgba(0,0,0,0.19);
            }
            
            .collapsible:after {
                content: '\25C2';
                margin-left: 5px;
            }
            
            .open:after{
                content: "\25BE";
                margin-left: 5px;
            }
            
            .content {
              padding: 0 18px;
              display: none;
              overflow: hidden;
            }
        </style>
        
    </head>
    <body bgcolor="#F4ECD9">
        <%
                    /**
                     * This java code sets up the database objects used on this page.
                     * It sets a <code>ProfileManager</code> to manage the profile of the current user.
                     * Sets the <code>BuUser</code> object to the current user of the tenure application.
                     * Sets an <code>Application</code> object for the current application being used by the user
                     * Gets the <code>Profile</code> for the current user of the Tenure Application.
                     * It auto fills most fields that can be acquired from the user's database and then
                     * sets all other values for entry into the Tenure databases.
                     * 
                     * @param pm Manages the current user's profile
                     * @param use The user who is currently logged in and making a Tenure application
                     * @a The current application the user is editting
                     * @p The profile of the current user.
                     * 
                     * @param first A String to hold the user's first name. It may be autofilled from the user database.
                     * @param last A String to hold the user's last name. It may be autofilled from the user database.
                     * @param campusPhone A String to hold the user's campus phone number.
                     * @param dept A String to hold the user's department they are currently working in.
                     * @param appointment A String to hold the date the user was officially appointed to BU.
                     * @param years An integer value to hold the number of years the user has been employed and working as a teacher at BU.
                     * @param chairFirst A String to hold the first name of the user's department's chairperson.
                     * @param chairLast A String to hold the last name of the user's department's chairperson.
                     * @param chairPhone A String to hold the phone number of the user's department's chairperson.
                     * @param submission A String to hold the user's submission date for their Tenure application. By default it is set to the current date.
                     * 
                     */
                    ProfileManager pm = new ProfileManager();
                    common.BuUser use = (common.BuUser) request.getSession().getAttribute("user");
                    Application a = (Application) request.getSession().getAttribute("currentapp");
                    Profile p = pm.getProfileByUserID(use.getUserNumber());
                    
                    String first = use.getFirstName();
                    String last = use.getLastName();
                    String campusPhone = "";
                    String dept = "";
                    String appointment = LocalDateTime.now().toString().substring(0, LocalDateTime.now().toString().indexOf("T"));
                    int years = 0;
                    String chairFirst = "";
                    String chairLast = "";
                    String chairPhone = "";
                    String submission = LocalDateTime.now().toString().substring(0, LocalDateTime.now().toString().indexOf("T"));
                    
                    /**
                     * This java code checks to see if the user has an application. From there it will check to see
                     * if any of the fields in the application are null values and will autofill them with empty strings
                     * to avoid null pointer exceptions
                     */
                    if (a != null)
                    {
                        if (!((first = a.getCustomFirstName()) != null))
                            first = use.getFirstName();
                        if (!((last = a.getCustomLastName()) != null))
                            last = use.getLastName();
                        if (!((dept = a.getDepartment()) != null))
                            dept = "";
                        if (p.getAppointmentDate() != null)
                            appointment = p.getAppointmentDate().toString().substring(0, p.getAppointmentDate().toString().indexOf("T"));
                        if (!((chairFirst = a.getDepartmentChair()) != null)) {
                            chairFirst = "";
                            chairLast = "";
                        } else {
                            chairLast = chairFirst.substring(a.getDepartmentChair().indexOf(" ") + 1);
                            chairFirst = chairFirst.substring(0, a.getDepartmentChair().indexOf(" "));
                        }
                        if (!((chairPhone = a.getChairPhone()) != null))
                            chairPhone = "";
                    }

                    /**
                     * This java code is to check if the user has a profile. It will then check to see if date entries specific to the
                     * profile table are null values and will then autofill them with empty strings and default values to avoid 
                     * null pointer exceptions.
                     */
                    
                    if (p != null)
                    {
                        if (!((campusPhone = p.getCampusPhone()) != null))
                            campusPhone = "";
                        years = p.getYearsOfService();
                    }
                    %>
     <jsp:include page="sideTabs.jsp"/>
        <script>
            document.getElementsByName("first")[0].className = "tabbuttons active";
            //keeps the title fields open once the window is loaded
                window.onload = function(){
                    document.getElementById("firstPagePersonalInformation").click();
                    document.getElementById("firstDepartmentChairInformation").click();
                };  
        </script>
        
        

            <div class="paper">
              <div class="paperTop">
                <div class="pageTitle">First Page</div>
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
                <div id="firstPagePersonalInformation" class="generalHeaderFont collapsible">Personal Information</div>
                <div class="content">
                    <form id='app_and_profile_form' action='UpdateFirstPageServlet' method='POST' class="firstPageContent">
                    <div class="fieldLayout">First Name<span style="margin-left: 13%" id="firstNameCurrent"><%=first.length()%></span><span id="FirstPageFirstNameMaximum">/12</span> 
                        <input id="firstName" type="text" style="min-width: 20%" name="customfirst" maxLength="12" oninput="updateCharCount('firstNameCurrent', 'firstName')" class="generalTextboxStyle" value="<%=first%>" placeholder="John">
                    </div>
                    <div class="fieldLayout">Last Name<span style="margin-left: 21.5%" id="lastNameCurrent"><%=last.length()%></span><span id="FirstPageLasttNameMaximum">/18</span>
                        <input id="lastName" type="text" style="min-width: 30%" name="customlast" maxLength="18" oninput="updateCharCount('lastNameCurrent', 'lastName')" class="generalTextboxStyle" value="<%=last%>" placeholder="Doe">
                    </div>
                    <div class="fieldLayout">Campus Phone Number
                        <input type="text" class="form-control generalTextboxStyle" style="min-width: 20%" name="campusphone" data-mask="(999) 999-9999" value="<%=campusPhone%>" placeholder="(555) 555-5555">
                    </div>
                    <div class="fieldLayout">Name of Department<span style="margin-left: 46%" id="nameOfDepartmentCurrent"><%=dept.length()%></span><span id="FirstPageNameOfDepartmentMaximum">/70</span>
                        <input id="departmentName" type ="text" style="min-width: 60%" name="deptname" class="generalTextboxStyle" maxLength="70" oninput="updateCharCount('nameOfDepartmentCurrent', 'departmentName')" value="<%=dept%>" placeholder="Ex: Department of Mathematical and Digital Sciences">
                    </div>
                    <div class="fieldLayout">Date of Appointment
                        <input type="date" name="appointment" class="generalTextboxStyle"value="<%=appointment%>">
                    </div>
                    <div class="fieldLayout">Anticipated Years of Service 
                        <input style="min-width: 15%" type="number" name="years" class="generalTextboxStyle"
                               min="0" max="70" value="<%=years%>" placeholder="5">
                    </div>
                </div>
                <div id="firstDepartmentChairInformation" class="generalHeaderFont collapsible">Department Chair Information</div>
                <div class="content">
                    <div class="fieldLayout">First Name<span style="margin-left: 13%" id="chairFirstNameCurrent"><%=chairFirst.length()%></span><span id="DepartmentChairFirstNameMaximum">/12</span>
                        <input id="chairFirstName" type="text" style="min-width: 20%" name="chairfirst" class="generalTextboxStyle" maxLength="12" oninput="updateCharCount('chairFirstNameCurrent', 'chairFirstName')" value="<%=chairFirst%>" placeholder="John">
                    </div>
                    <div class="fieldLayout">Last Name<span style="margin-left: 21.5%" id="chairLastNameCurrent"><%=chairLast.length()%></span><span id="DepartmentChairLastNameMaximum">/18</span>
                        <input id="chairLastName" style="min-width: 30%" type="text" name="chairlast" class="generalTextboxStyle" maxLength="18" oninput="updateCharCount('chairLastNameCurrent', 'chairLastName')" value="<%=chairLast%>" placeholder="Doe">
                    </div>
                    <div class="fieldLayout">Phone Number
                        <input type="text" class="form-control generalTextboxStyle" style="min-width: 20%" name="chairphone" data-mask="(999) 999-9999" value="<%=chairPhone%>" placeholder="(555) 555-5555">
                    </div>

                    <div class="fieldLayout"><span style="font-weight:bold">Date of Application Submission</span>
                        <input type="date" style="min-width: 25%" name="appsubmission" class="generalTextboxStyle" value="<%=submission%>"></div>
                </div>
                
                </div>    
                <div class="footerButtons">
                    <hr>
  
                    <input type="submit" name="eduCred" value="Continue"/>
                    <input type="submit" name="print" value="Export"/>
                    <input type="submit" name="cover" value="Back"/>
                    </form>
                                                    
                    <!--Skip button-->
                    <form id='redirct_page' action='RedirectServlet' method='POST'>
                    <input type="submit" name="eduCred" value="Skip"/>
                </form>
                </div>
                </form>
            </div>  
                    
        <script>
            /**
             * This javascript code is used to collapse content on the page so it is less cluttered for the user to enter data. 
             * It gets a tag of the collapsible class and sets all the children of that tag to be displayed or hidden whenever
             * the collapsible class is clicked.
             * 
             * @param collapsible The tag with the collapsible class that is the parent to all the content to be hidden upon clicking.
             * @param i The integer to be incremented and to get through all the children of collapsible and set them to open or close
             * based upon the current status of collapsible.
             */
            var collapsible = document.getElementsByClassName("collapsible");
            var i;
            
            for (i = 0; i < collapsible.length; i++) {
              collapsible[i].addEventListener("click", function() {
                  this.classList.toggle("open");
                var content = this.nextElementSibling;
                if (content.style.display === "block") {
                  content.style.display = "none";
                } else {
                  content.style.display = "block";
                }
              });
            }
        </script>             
        
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
                    this.classList.toggle("active");
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
