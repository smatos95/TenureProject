package servlets;

import common.Application;
import common.BuUser;
import common.Profile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mysql.ApplicationManager;
import mysql.ProfileManager;

/**
 *
 * @author Riley Hughes (2019)
 */
@WebServlet(name = "UpdateCoverPageServlet", urlPatterns = {"/UpdateCoverPageServlet"})
public class UpdateCoverPageServlet extends HttpServlet {

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
        HttpSession sess = request.getSession(true);
        final Object lock = sess.getId().intern();
        
        Integer appCreated = null;
        
        // Get BU User's details
        BuUser user = (BuUser) request.getSession().getAttribute("user");
        
        ApplicationManager appManager = new ApplicationManager();
        ProfileManager profileManager = new ProfileManager();
        
        // Get user's apps and profile
        Collection<Application> applications = appManager.getApplicationsByUserID(user.getUserNumber());
        Profile profile = profileManager.getProfileByUserID(user.getUserNumber());
        
        // Set an application with the details the user entered
        Application app;
        
        if (request.getSession().getAttribute("currentapp") == null) {
            app = new Application(0);
            app.setCreationDate(LocalDateTime.now());
        } else {
            app = (Application) request.getSession().getAttribute("currentapp");
            if (app.getCreationDate() == null)
                app.setCreationDate(LocalDateTime.now());
        }
        app.setApplicantUser(user.getUserNumber());
        
        if (((String) request.getSession().getAttribute("apptype")).equalsIgnoreCase("Tenure"))
            app.setApplicationType("tenure");
        else if (((String) request.getSession().getAttribute("apptype")).equalsIgnoreCase("Promotion"))
            app.setApplicationType("promotion");
        
        app.setCustomFirstName(request.getParameter("customfirst"));
        app.setCustomLastName(request.getParameter("customlast"));
        app.setDepartment(request.getParameter("deptname"));
        
        // Set a profile with the details the user entered
        profile.setProfileUser(user.getUserNumber());
        profile.setRank(request.getParameter("rank"));
        profileManager.updateProfile(profile);
        
        // If the user has no apps, make a new one. If they have apps, then
        // update their current one.
        if (applications == null || applications.isEmpty()) {
            appCreated = appManager.insertApplication(app);
        }
        
        if (appCreated != null) synchronized (lock) {
            // Update session variable for autopopulation
            sess.setAttribute("currentapp", app);
        }
        
        // Send the user back to the first page
        if (request.getParameter("first") != null)
        {
            request.getRequestDispatcher("jsp/firstpage.jsp").forward(request, response);
        }
        else if (request.getParameter("print") != null)
        {
            request.getRequestDispatcher("/PrintCoverPageServlet").forward(request, response);
        }
        else if (request.getParameter("overview") != null)
        {
            request.getRequestDispatcher("jsp/home.jsp").forward(request, response);
        }
        else
        request.getRequestDispatcher("jsp/coverpage.jsp").forward(request, response);
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
