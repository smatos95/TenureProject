<%-- 
    Document   : about
    Created on : Apr 1, 2019, 12:41:21 PM
    Author     : sm26511
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Us</title>
        <link rel="stylesheet" href="styles/controlPage.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
    </head>
    <body bgcolor="#F4ECD9">
        <jsp:include page="sideTabs.jsp"/>
            <div class="paper">
                <div class ="paperTop">
                    <div class="pageTitle">About Us</div>
                    <hr>                
                </div>
                <div class="paperContents">
                    <img src ="images/placeholder.png" height = 400px width = 500px style="margin-top: 5%; margin-right: 5%; vertical-align:middle; float:right;">
                    <p style="white-space: pre; font-size: 20px; margin-top: 5%;">
             Lorem ipsum dolor sit amet, albucius definitionem nec no, eam erroribus 
             consequuntur at. Sed ad modus simul intellegam. Nec modus splendide ei. 
             Pertinacia neglegentur in mea, ea autem apeirian assueverit mel. Fierent 
             officiis eum ex. Eam an facer inani vidisse. Te quem discere officiis cum. 
             Has in oblique tacimates expetendis, vis vitae officiis an, esse semper convenire 
             ei cum. Inani scripta graecis in est, ut quo inimicus assentior. Accusata 
             euripidis hendrerit no pri. Ad mundi quaestio temporibus vis, eu pri enim habeo 
             pertinax, timeam pericula ex per. Ne illud utinam voluptatum qui, facer utinam 
             mei eu. Nam altera eripuit vulputate eu. Cu amet invidunt convenire nam. His 
             vitae quando at, per alterum senserit corrumpit ei, sanctus vocibus has in. Cum 
             sumo audiam et. Cu alienum deseruisse eam. Doctus vulputate ex vis, sale 
             quando fuisset et pri.Mel at vocent cotidieque. Eu per option consequat, 
             at ponderum voluptaria mel. Dolore doming ne pri, sit id docendi perfecto.
             <form id="print_all_form" action="PrintAllPagesServlet" method="POST">
                    <input type="submit" value="Print Tenure App"/>
                </form>
                    </p>
             

                </div>
             
                
            </div>

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
            document.getElementsByName("about")[0].className = "tabbuttons active";
        </script>
    </body>
</html>
