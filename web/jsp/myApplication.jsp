<%-- 
    Document   : myApplication
    Created on : Mar 24, 2019, 2:04:39 PM
    Author     : Jake Gordon (2019)

DISPLAY 'NO ____ APPS TO EDIT'.
Replacing selection menu with "You have no apps" needs to include the
Select tag as well as the options.
--%>

<%@page import="common.Profile"%>
<%@page import="mysql.ProfileManager"%>
<%@page import="mysql.ApplicationManager"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="common.Application"%>
<%@page import="common.BuUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/controlPage.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>My Applications</title>
    </head>
    <body bgcolor="#F4ECD9">
        <%
            /**
             * This java code is used to set all the database objects used in this page.
             * Sets the <code>BuUser</code> object with the current user of the Tenure application.
             * Creates a <code>Collection</code> of <code>applications</code> for the current user and populates it with apps from any prior use of the Tenure application.
             * Sets the app to be the current <code>application</code> the user is working on.
             * Creates an <code>ApplicationManager</code> to manage all the applications the user currently has.
             * Creates a <code>ProfileManager</code> to manage the current user's profile.
             * Sets the current user's <code>profile</code>.
             * Sets the user's most recently editted Tenure <code>application</code>.
             * 
             * @param user The user who is currently logged in and making a Tenure application.
             * @param appCollection The collection of the current user's apps 
             * @param app The current application the user is editting.
             * @param appManager Manages all the applications the current user has.
             * @param proManager Manages the current user's profile.
             * @param pro The profile of the current user.
             * @param mostRecent The application most recently editted.
             */
        common.BuUser user = (common.BuUser) request.getSession().getAttribute("user");
        Collection<Application> appCollection = (Collection<Application>) request.getSession().getAttribute("apps");
        Application app = (Application) request.getSession().getAttribute("currentapp");
        ApplicationManager appManager = new ApplicationManager();
        ProfileManager proManager = new ProfileManager();
        Profile pro = proManager.getProfileByUserID(user.getUserNumber());
        Application mostRecent;
        %>
                
        <div class="paper">
        <div class="pageTitle" style="margin-bottom: 2%; font-size: 3.5vw;">My Applications</div> 
        <div>
        <div style="display: inline-block; font-weight:bold;font-size: 2.25vw;">Current Application:</div> 
        <div style="display: inline-block; background-color: gainsboro; width: fit-content; padding:0.5%; border-radius:6px; font-size: 2.25vw;">
            <%
                /**
                 * This line checks to see if the user has no current app and warns them that they have no app currently selected.
                 */
                if (request.getSession().getAttribute("currentapp") == null) {%>
            No Application Selected
            <jsp:include page="disabledTabs.jsp"/>
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
            <jsp:include page="sideTabs.jsp"/>
            <%}%>
        </div>
        </div>
        <hr>
        
        <li style = "list-style: none;"><span style ="font-size: 1.5vw; font-weight: bold;">Edit an Existing Application: </span>
            <form id='app_selector_form' action='SetApplicationServlet' method='POST'>
                <div style="display: inline-block; font-size: 1.5vw; text-indent: 1.5vw;">Tenure Application:</div> <select style="width:fit-content; font-size: 1.5vw; margin-left: 3.5%; padding-left: 1.3vw; padding-right: 1.3vw;"class="generalTextboxStyle"id="display_app_appselect_dropdown" name='tappselect'>
                                <%
                                    /**
                                     * Checks to see that the user's <code>Applications</code> are there and sets the most recent application. It then displays all the user's
                                     * applications in a dropdown <code>select</code> with the most recent application showing up at the top.
                                     * 
                                     * @param appIterator An iterator for the collection of the user's applications.
                                     * @param temp An application that is set to the current application being iterated upon.
                                     */
                                    Iterator<Application> appIterator;
                                    Application temp;
                                    if (appCollection != null && appCollection.isEmpty() == false) {
                                        mostRecent = appManager.getApplication(pro.getLastApplication());
                                        if (mostRecent != null && mostRecent.getApplicationType().equalsIgnoreCase("Tenure")) {%>
                                            <option value=<%=mostRecent.getApplicationID()%>>
                                                <%=mostRecent.getFriendlyName()%>
                                            </option>
                                <%      }

                                        /**
                                         * Iterates through all the current user's Tenure <code>Applications</code> and sets them in an html <code>select</code> field.
                                         * If there is no most recent application it sets them as they appear in the database. if the user has no applications it
                                         * tells the user they have none.
                                         */
                                        appIterator = appCollection.iterator();
                                        while (appIterator.hasNext()) 
                                        {
                                            temp = appIterator.next();
                                            if(temp.getApplicationType().equalsIgnoreCase("tenure"))
                                            {
                                                if (mostRecent == null || temp.getApplicationID() != mostRecent.getApplicationID()) {
                                %>
                                                    <option value=<%=temp.getApplicationID()%>>
                                                        <%=temp.getFriendlyName()%>
                                                    </option>     
                                      <%        }
                                            }
                                        }
                                    } else {%>
                                    You have no Tenure applications
                                    <%}%>
                            </select>                               
                            <input type='submit' name='submitTenure' id="display_app_submit_choice_button" value="Edit" />  
                            <input type='submit' name='delete' id="delete_app_button" value="Delete" onClick="deleteApp()" /> 
                            </form>
                            <form id='app_selector_form' action='SetApplicationServlet' method='POST'>
                                <div style="display: inline-block; font-size: 1.5vw; text-indent: 1.5vw;">Promotion Application:</div> 
                                <select style="width:fit-content; font-size: 1.5vw; cursor: not-allowed" class="generalTextboxStyle" id="disablePromotion" name='pappselect'> <!-- Selection menu is currently disabled using a unique id"-->
                                <%
                                    if (appCollection != null && appCollection.isEmpty() == false) {
                                        mostRecent = appManager.getApplication(pro.getLastApplication());
                                        if (mostRecent != null && mostRecent.getApplicationType().equalsIgnoreCase("Promotion")) {%>
                                            <option value=<%=mostRecent.getApplicationID()%>>
                                                <%=mostRecent.getFriendlyName()%>
                                            </option>
                                <%      }

                                        /**
                                         * Iterates through all the current user's Promotion <code>Applications</code> and sets them in an html <code>select</code> field.
                                         * If there is no most recent application it sets them as they appear in the database. if the user has no applications it
                                         * tells the user they have none.
                                         */
                                        appIterator = appCollection.iterator();
                                        while (appIterator.hasNext()) 
                                        {
                                            temp = appIterator.next();
                                            if(temp.getApplicationType().equalsIgnoreCase("promotion"))
                                            {
                                                if (mostRecent == null || temp.getApplicationID() != mostRecent.getApplicationID()) {
                                %>
                                                    <option value=<%=temp.getApplicationID()%>>
                                                        <%=temp.getFriendlyName()%>
                                                    </option>
                                      <%        }
                                            }
                                        }
                                    } else {%>
                                    You have no Promotion applications
                                    <%}%>
                            </select>
                            <input type='submit' name='submitPromotion' id="disabledEdit" value="Edit" style="cursor: not-allowed; background-color: grey;" /> <!-- Edit button is currently disabled using a unique id"-->
                            </form>
                            </li>
                            <ul>
            <li style ="font-size: 2.5vw; margin-top: 3%; list-style: none">Start a new Application: 
            <form id="send_to_home_form" action="CreateApplicationServlet" method="POST">
                <input type="submit" id="display_app_new_tenure_button" value="Create New Tenure Application" name="tenure" onClick="nameApp()">
                <input id="hiddenName" type="text" style ="visibility:hidden;" name="hiddenName"><br>
                <input type="submit" id="display_app_new_promotion_button" value="Create New Promotion Application" name="promotion">
            </form></li> 
        </ul>
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
            // this document object allows for any element with the corresponding tag to be disabled
            document.getElementById("disablePromotion").disabled = true;
            document.getElementById("disabledEdit").disabled = true;
            document.getElementById("display_app_new_promotion_button").disabled = true;
            document.getElementsByName("appselect")[0].className = "tabbuttons active";
        </script>
        
        <script>
            /**
             * Lets the user name an application when they click on the create new tenure app.
             * It sets the name to a hidden value of an input and passes it along to a servlet
             * to set it to the <code>friendlyName</code> of the new app.
             *  
             *  @param appName The name of the application acquired from the prompt.
             *  @param hiddenName The input element that is being passed along to the servlet.
             */
            function nameApp()
            {
                var sampleName = "TENURE APP: CREATED: ";
                var d = new Date();
                var dateString = d.toDateString();
                var space = dateString.indexOf(" ");
                sampleName += dateString.substring(space);
                sampleName += " / DEADLINE: MM-YYYY";
                var appName = prompt("Please name your application", sampleName/*"TENURE APP: Created MM-DD-YYYY / Due MM-YYYY"*/);
                if(appName != null)
                {
                    var hiddenName = document.getElementById("hiddenName");
                    hiddenName.value = appName;
                }
            }
            </script>
            <script>
            /**
             * Prompts the user if they want to delete the app they have currently selected in the <code>select</code> dropdown.
             * If the user agrees to delete the app it will send to a servlet. If the user cancels it renames the delete button
             * to cancel so it won't go to the servlet.
             * 
             * @param deleteButton The outcome of the confirm for app deletion.
             * @param deleteName The delete button that triggers the method.
             */
            function deleteApp()
            {
                var deleteButton = confirm("Are you sure?");
                var deleteName = document.getElementById("delete_app_button");
                if(deleteButton == true)
                {
                    deleteName.name = "delete";
                }
                else
                {
                    deleteName.name = "cancel";
                }
            }
        </script>
    </body>
</html>



