package servlets;

import common.UserRole;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.WebErrorLogger;

/**
 * <code>ControlServlet</code> is the main servlet that processes most 
 * navigation requests. This servlet will redirect to other servlets depending 
 * on the attributes passed and page directed from.
 * UPDATE: LoginServlet now handles all login processing. ControlServlet is
 * now exclusively for redirection.
 * @author Joseph Picataggio
 */
@WebServlet(name = "ControlServlet", urlPatterns = {"/ControlServlet"})
public class ControlServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(true);//Create a new session if one does not exists
        final Object lock = session.getId().intern();//To synchronize the session variable
        database.UserManager um = database.Database.getDatabaseManagement().getUserManager();
        common.User user = (common.User) session.getAttribute("user");
        String action = request.getParameter("control");
        
        log("action is "+action) ;  //testing  message    
        //This case is for a successful login
        //We need to update the user table and see if it was their first login
        if(action.trim().equalsIgnoreCase("login")){
          // Update the information in the user table for this user  
            boolean firstLogin = user.getLoginCount() == 0;
            user.setLoginCount(user.getLoginCount()+1);
            LocalDateTime now = LocalDateTime.now();
            user.setLastLoginTime(now);
            user.setAttemptedLoginCount(0);
            user.setLastAttemptedLoginTime(now);
            um.updateUser(user);
        
            // Always lock a session variable to be thread safe.
            synchronized(lock){
                session.setAttribute("user", user);//update information in the session attribute for this user
            }

            /*if (firstLogin) {//Force the user to reset their password
                response.sendRedirect(request.getContextPath() + "/html/ResetPassword.html");
                return; //return statement is needed
            } */

            //request.getServletContext()
            //    .getRequestDispatcher("/LogoutServlet") //page we want after successful login. /html/indexdirect.html
            //        .getRequestDispatcher("/PrintUser")
            //    .forward(request, response);
            
            // Send a logged-in user to the intro page
            if(user.getUserRole() == UserRole.SystemAdmin)
            {
                String url = "/AdminServlet";
                request.getRequestDispatcher(url).forward(request, response);
               
                return;
            }
            else if(user.getUserRole() == UserRole.TenureCommitteeAdministrator)
            {
                response.sendRedirect(request.getContextPath() + "/html/tcAdminInitial.html");
                return;
            }
            else if(user.getUserRole() == UserRole.TenureCommitteeMember)
            {
                response.sendRedirect(request.getContextPath() + "/html/tcInitial.html");
                return;
            }
            else if(user.getUserRole() == UserRole.Professor)
            {
                // Send a professor user to view their applications and choose one
                String url = "/GetApplicationsServlet";
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            else if(user.getUserRole() == UserRole.Chair){
            
                String url = "/AdminServlet";
                request.getRequestDispatcher(url).forward(request, response);
               
                return;
            }
        }
             //end of code for login action
        
            // The next code we will write is for the resetpassword action
            if(action.trim().equalsIgnoreCase("resetpassword")){
                user =um.getUserByID(Integer.parseInt(request.getParameter("UID")));
                synchronized(lock){
                    session.setAttribute("user", user);//update information in the session attribute
                }
                if(user.getUserPassword() != request.getParameter("token")){
                    //We have a problem, the url does not have the correct token, reject the attempt
                     //We should contact an admin to state what happened
                   log(user.getLoginName() +" tried to reset a password using the wrong token in the url");
                   log("user id was "+ request.getParameter("UID") );
                   WebErrorLogger.log(Level.SEVERE,user.getLoginName() +" tried to reset a password using the wrong token in the url. "+
                           "user id was "+ request.getParameter("UID"));
                   
                   response.sendRedirect(request.getContextPath() + "/jsp/loginScreen.jsp");
            
                }
                else {
                    response.sendRedirect(request.getContextPath() + "/html/ResetPassword.html"); 
                }
                return;    // return is needed becvause it is a redirect
                          //The difference between a redrect and a forward is important
                        //Look at the URL in the browswer bar and notice a redirect changes it
             }
            
            //Our next action to process is add
             if(action.trim().equalsIgnoreCase("add")){
                //response.sendRedirect(request.getContextPath() + "/html/javascriptDisabled.html");
                request.getServletContext()
                .getRequestDispatcher("/html/javascriptDisabled.html") 
                .forward(request, response);
                return;    // return is needed

             }

            //Other actions go here, this can get long if everything goes to the  control servlet
             
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "ControlServlet is the main Control object for this web application";
    }// </editor-fold>
}
