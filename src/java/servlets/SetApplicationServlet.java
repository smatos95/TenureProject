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
 * Trampoline servlet for handling application selection requests from <code>myApplication.jsp</code>.
 * 
 * @author Riley Hughes (2019)
 */
@WebServlet(name = "SetApplicationServlet", urlPatterns = {"/SetApplicationServlet"})
public class SetApplicationServlet extends HttpServlet {

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
        String url = "";
        
        if (request.getParameter("delete") != null)
        {
            url = "/deleteApplicationServlet";
            //current = am.getApplication(Integer.parseInt(request.getParameter("tappselect")));
        }   
        else if(request.getParameter("cancel") != null)
        {
            url = "jsp/myApplication.jsp";
        }
        else if (request.getParameter("submitTenure") != null) {
            url = "jsp/home.jsp";
            current = am.getApplication(Integer.parseInt(request.getParameter("tappselect")));
        } else if (request.getParameter("submitPromotion") != null) {
            url = "jsp/home.jsp";
            current = am.getApplication(Integer.parseInt(request.getParameter("pappselect")));
        }
        if (request.getParameter("delete") == null && current != null) {
            request.getSession().setAttribute("currentapp", current);
            request.getSession().setAttribute("apptype", current.getApplicationType());
            profile.setLastApplication(current.getApplicationID());
            pm.updateProfile(profile);
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
