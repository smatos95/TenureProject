<%-- 
    Document   : sysAdminInitial
    Created on : (To be entered at a later time)
    Author     : Riley Hughes (2019)
    Author     : Steven Matos(2019)
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
        <div class="sidetabs">
            <img src ="images/bloomsburg-logo.png" height = 190px width = 230px style="padding-left: 12%;">
            <form id="redirect_page" action="UserServlet" method="POST">
                <button class="tabbuttons" name="appselect"><i class ="fa fa-briefcase"></i> My Applications</button>
            </form>
            <!-- Tab buttons direct to appropriate forms -->
            <form id='redirct_page' action='RedirectServlet' method='POST'>
                <button class="tabbuttons" name="overview"><i class="fa fa-home"></i> Overview</button>
                <button class="tabbuttons" name="cover"><i class ="fa fa-folder-open"></i> Cover Page</button>
                <button class="tabbuttons" name="first"><i class="fa fa-address-card"></i> First Page</button>               
            </form>
            <!-- Dropdown button which displays submenus -->
            <button class="dropdown-btn" name="dropbutton" id="backgroundInformationDropdown"><i class="fa fa-list-alt"></i> Background Information
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-container">
                <form id='redirct_page' action='RedirectServlet' method='POST'>
                    <button class="inDropdown" name="eduCred">Educational Credentials</button>
                    <button class="inDropdown" name="teachFac">Teaching/Faculty</button>
                    <button class="inDropdown" name="workExp">Other Work Experience</button>
                    <button class="inDropdown" name="transcripts">Official Transcripts</button>
                </form>
            </div>
            <button class="dropdown-btn" name="dropbutton" id="teachingAndFulfillmentDropdown"><i class="fa fa-users"></i> Teaching and Fulfillment
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-container">
                <form id='redirct_page' action='RedirectServlet' method='POST'>
                    <button class="inDropdown" name="teachingNarrative">Professional Responsibilities</button>
                    <button class="inDropdown" name="teachingHistory">Teaching History</button>
                    <button class="inDropdown" name="studentEvaluations">Summary Tables of Student Evaluations</button>
                    <button class="inDropdown" name="fulfillmentProfessional">Fulfillment of Professional Responsibilities</button>
                    <button class="inDropdown" name="timeActivities">Assigned Time Activities</button>
                    <button class="inDropdown" name="Innovations">Teaching Related Innovations</button>
                </form>
            </div>
            <button class="dropdown-btn" name="dropbutton" id="scholarlyGrowthDropdown"><i class="fa fa-line-chart"></i> Scholarly Growth
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-container">
                <form id='redirct_page' action='RedirectServlet' method='POST'>
                    <button class="inDropdown" name="scholar"></i>Narrative on Continuing Scholarly Growth</button>
                    <button class="inDropdown" name="Activities"></i>Activities</button>
                </form>
            </div>
            <button class="dropdown-btn" name="dropbutton" id="universityCommunityDropdown"><i class="fa fa-leanpub"></i> University/Community
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-container">
                <form id='redirct_page' action='RedirectServlet' method='POST'>
                    <button class="inDropdown" name="uniCommNarrative">Narrative on Contributions to University and Community</button>
                    <button class="inDropdown" name="committeeService">Committee Service</button>
                    <button class="inDropdown" name="studentOrgAdvisement">Advisement to Student Organizations</button>
                    <button class="inDropdown" name="otherContributions">Other Contributions to the University</button>
                    <button class="inDropdown" name="communityContributions">Community Contributions</button>
                </form>
            </div>
            <button class="dropdown-btn" name="dropbutton" id="signaturePagesDropdown"><i class="fa fa-pencil-square-o"></i> Signature Pages
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-container">
                <form id='redirct_page' action='RedirectServlet' method='POST'>
                    <button class="inDropdown" name="tenureSigPage">Tenure Application Signature Page</button>
                    <button class="inDropdown" name="deptNarrativeSigPage">Department Committee Narrative and Signature Page</button>
                    <button class="inDropdown" name="chairNarrativeSigPage">Chairperson Narrative and Signature Page</button>
                    <button class="inDropdown" name="specialNote">Special Note</button>
                </form>
            </div>
            
            <!--visible horizontal line break for the side tab -->
            <hr>
            <!-- About us page -->
            <form id="redirect_page" action="RedirectServlet" method="POST">
                <button class="tabbuttons" name="about"><i class ="fa fa-bookmark"></i> About Us</button>
            </form>
            <!--log out buttons-->            
            <form id="redirect_page" action="LogoutServlet" method="POST">
            <button class="tabbuttons" name="logout"><i class ="fa fa-power-off"></i> Logout</button>
            </form>   
            <hr>
           </div>
        
        <!--Script to set the active tab to show in the application-->        
        <script>
            /**
             * Activates the current working tab and highlights it so users can easily identify
             * the current tab they are working in. Differentiates the tabs into 2 types, 
             * the active dropdown tab and the tabs in the dropdown. 
             * 
             * @param activeTabs Tabs that are in the tabbuttons and active class.
             * @param activeDropdown Tabs that are in the inDropdown and active class.
             */
            var activeTabs = document.getElementsByClassName("tabbuttons active");
            var activeDropdown = document.getElementsByClassName("inDropdown active");
            if (!(activeTabs === undefined || activeTabs.length === 0))
            {
                for (var i = 0; i < activeTabs.length; i++) {
                    activeTabs[i].className = "tabbuttons";
                }
            }
            if (!(activeDropdown === undefined || activeDropdown.length === 0))
            {
                for (var i = 0; i < activeTabs.length; i++) {
                    activeDropdown[i].className = "inDropdown";
                }
            }
           
        </script>

        <!--
        Possible to send name of clicked tab via a session or request attribute?
        -->