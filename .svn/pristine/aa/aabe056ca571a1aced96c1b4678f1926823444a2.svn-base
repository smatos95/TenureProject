package servlets;

import common.Application;
import common.BuUser;
import common.Profile;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysql.ApplicationManager;
import mysql.ProfileManager;

/**
 * THERE NEEDS TO BE AN APPLICATION WITH -1 AS APP ID IN ORDER TO SATISFY
 * FOREIGN KEY CONSTRAINTS FOR UPDATING PROFILE (POSSIBLY -1 USER IN THE BU USERS
 * TABLE AS WELL). IN THE PROFILE, -1 MARKS A USER WHOSE LAST APPLICATION ISN'T SET.
 * USERS WHO MAKE A NEW PROFILE HAVE THEIR LAST APP SET TO -1.
 * 
 * @author Jake Gordon (2019)
 * @author Riley Hughes (2019)
 */
@WebServlet(name = "deleteApplicationServlet", urlPatterns =
{
    "/deleteApplicationServlet"
})
public class deleteApplicationServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        // Get the selected application from the database and send it forward
        ApplicationManager am = new ApplicationManager();
        ProfileManager pm = new ProfileManager();
        Profile profile = pm.getProfileByUserID(((BuUser) request.getSession().getAttribute("user")).getUserNumber());
        Application current = null;
        String url = "jsp/myApplication.jsp";
        if (request.getParameter("tappselect") != null) {
            current = am.getApplication(Integer.parseInt(request.getParameter("tappselect")));

            if (profile.getLastApplication() == current.getApplicationID()) {
                profile.setLastApplication(-1);
                pm.updateProfile(profile);
            }

            am.deleteApplication(current);
            url = "jsp/myApplication.jsp";

            Application currentApp = (Application) request.getSession().getAttribute("currentapp");

            if(currentApp != null && current.getApplicationID() == currentApp.getApplicationID()) {
                request.getSession().setAttribute("currentapp", null);
                request.getSession().setAttribute("apptype", null);
            }
            request.getSession().setAttribute("apps", am.getApplicationsByUserID(current.getApplicantUser()));
        }
        
        request.getRequestDispatcher(url).forward(request, response);
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
        return "Short description";
    }// </editor-fold>

}
