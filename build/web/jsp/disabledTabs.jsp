<%-- 
    Document   : disabledTabs
    Created on : (To be entered at a later time)
    Author     : Steven Matos (2019)
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
        <div class="sidetabs">
            <img src ="images/bloomsburg-logo.png" height = 190px width = 230px style="padding-left: 12%;">
            <form id="redirect_page" action="UserServlet" method="POST">
                <button class="tabbuttons" name="appselect"><i class ="fa fa-briefcase"></i> My Applications</button>
            </form>   
            <!-- Tab buttons direct to appropriate forms -->
            <div class ="disabledButtons">
                <button class="disabledButtonsContainer" name="overview"><i class="fa fa-home"></i> Overview</button>
                <button class="disabledButtonsContainer" name="cover"><i class ="fa fa-folder-open"></i> Cover Page</button>
                <button class="disabledButtonsContainer" name="first"><i class="fa fa-address-card"></i> First Page</button>               

            <!-- Dropdown button which displays submenus -->
            <button class="disabledButtonsContainer" name="dropbutton"><i class="fa fa-list-alt"></i> Background Information
                <i class="fa fa-caret-down"></i>
            </button>
            <button class="disabledButtonsContainer" name="dropbutton"><i class="fa fa-users"></i> Teaching and Fulfillment
                <i class="fa fa-caret-down"></i>
            </button>
            <button class="disabledButtonsContainer" name="dropbutton"><i class="fa fa-line-chart"></i> Scholarly Growth
                <i class="fa fa-caret-down"></i>
            </button>
            <button class="disabledButtonsContainer" name="dropbutton"><i class="fa fa-leanpub"></i> University/Community
                <i class="fa fa-caret-down"></i>
            </button>
            <button class="disabledButtonsContainer" name="dropbutton"><i class="fa fa-pencil-square-o"></i> Signature Pages
                <i class="fa fa-caret-down"></i>
            </button>
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
             * the current tab they are working in.
             * 
             * @param activeTabs Tabs that are in the tabbuttons and active class.
             */
            var activeTabs = document.getElementsByClassName("tabbuttons active");
            if (!(activeTabs === undefined || activeTabs.length === 0))
            {
                for (var i = 0; i < activeTabs.length; i++) {
                    activeTabs[i].className = "tabbuttons";
                }
            }
        </script>

        <!--
        Possible to send name of clicked tab via a session or request attribute?
        -->