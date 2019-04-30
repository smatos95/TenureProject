package servlets;

import common.Application;
import common.BuUser;
import common.Profile;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import mysql.ApplicationManager;
import java.util.Iterator;
import mysql.ProfileManager;

/**
 * Basic servlet for routing a front-end "create application" request to the 
 * database.
 * 
 * @author Riley Huges (2019)
 * @author Gryphon Ayers (2019)
 */
@WebServlet(name = "CreateApplicationServlet", urlPatterns = {"/CreateApplicationServlet"})
public class CreateApplicationServlet extends HttpServlet {

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
        
        // If the cancel button was hit, go back to the applications page
        if( request.getParameter("hiddenName").isEmpty())
        {
            request.getRequestDispatcher("jsp/myApplication.jsp").forward(request, response);
            return;
        }
        
        // Create container for apps and a string for where to redirect back to
        Collection<Application> apps;
        String url = "jsp/myApplication.jsp";
                
        // Depending on which create app button was clicked, set the type
        // of application
        if (request.getParameter("tenure") != null)
        {
            request.getSession().setAttribute("apptype", "Tenure");
        } else if (request.getParameter("promotion") != null)
        {
            request.getSession().setAttribute("apptype", "Promotion");
        }
        
        // Get the user for the sake of attaching their user number to the app
        BuUser u = (BuUser) request.getSession().getAttribute("user");
        
        // Get the user's profile
        ProfileManager prom = new ProfileManager();
        Profile profile = prom.getProfileByUserID(u.getUserNumber());
        
        // Create the application and attach all information from the create
        // app page
        ApplicationManager appm = new ApplicationManager();
        Application a = new Application(0);
        a.setApplicantUser(u.getUserNumber());
        a.setApplicationType((String) request.getSession().getAttribute("apptype"));
        a.setCreationDate(LocalDateTime.now());
        a.setFriendlyName(request.getParameter("hiddenName"));
        a.setSubmissionDate(null);
        Integer temp = appm.insertApplication(a);
        
        // For the purposes of getting the app id from the database, get the
        // most recent application from the database by iteration.
        Application temp1 = null;
        apps = appm.getApplicationsByUserID(u.getUserNumber());
        Iterator<Application> itann = apps.iterator();

        while (itann.hasNext())
        {
            temp1 = itann.next();

            if (itann.hasNext() == false)
            {
                request.getSession().setAttribute("currentapp", temp1);
                request.getSession().setAttribute("apps", apps);
                profile.setLastApplication(temp1.getApplicationID());
                prom.updateProfile(profile);
            }
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
