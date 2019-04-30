package servlets;

import common.Application;
import common.BuUser;
import common.Degree;
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
import mysql.DegreeManager;

/**
 * Vision for the future: Multiple degrees in an application, then pull all of
 * them from database and put them onto the page with multiple slots. Upon
 * hitting save, all of them will be updated and/or inserted.
 *
 * @author Riley Hughes (2019)
 * @author Gryphon Ayers (2019)
 */
@WebServlet(name = "UpdateEduCredServlet", urlPatterns = {"/UpdateEduCredServlet"})
public class UpdateEduCredServlet extends HttpServlet {

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
        HttpSession sess = request.getSession(false);
        final Object lock = sess.getId().intern();

        Integer appCreated = null;

        ApplicationManager appManager = new ApplicationManager();
        DegreeManager degreeManager = new DegreeManager();
        
        sess.removeAttribute("isPopup");

        // Set an application with the details the user entered. Since no data
        // stored in an application object is set in this page, only basic info
        // is set.
        Application app;

        // get other session-based variables
        BuUser user;
        String apptype;
        Integer credNumber;

        synchronized (lock) {
            user = (BuUser) sess.getAttribute("user");
            apptype = (String) sess.getAttribute("apptype");
            credNumber = (Integer) sess.getAttribute("currentcred");
            app = (Application) sess.getAttribute("currentapp");
        }

        // Get user's apps
        Collection<Application> applications = appManager.getApplicationsByUserID(user.getUserNumber());

        if (app == null) {
            app = new Application(0);
            app.setCreationDate(LocalDateTime.now());
        } else {
            if (app.getCreationDate() == null) {
                app.setCreationDate(LocalDateTime.now());
            }
        }
        app.setApplicantUser(user.getUserNumber());

        if (apptype.equalsIgnoreCase("Tenure")) {
            app.setApplicationType("tenure");
        } else if (apptype.equalsIgnoreCase("Promotion")) {
            app.setApplicationType("promotion");
        }

        // get parameters from the page
        Degree degree;
        
        if (request.getParameter("deleteCred") != null) {
            request.getRequestDispatcher("/EduCredDeleteServlet").forward(request, response);
        }
        if (request.getParameter("updateCred") != null) {
                request.getRequestDispatcher("/EduCredAutofillServlet").forward(request, response);
            } 
        

        // check whether to update or insert, if either
        if (request.getParameter("insert") != null) {
            // add new credential if the new credential button was clicked
            degree = new Degree(0);
        } else if (credNumber != null && credNumber >= 0) {
            // get the degree requested and check for valid access
            degree = degreeManager.getDegree(credNumber);
            if (!(degree.getApplication() == app.getApplicationID()
                    && app.getApplicantUser() == user.getUserNumber())) {
                // add a new credential instead of allowing an invalid access
                degree = new Degree(0);
            }
        } else {
            // no degree to operate on, just forward instead
            if (request.getParameter("teachFac") != null) {
                request.getRequestDispatcher("jsp/teachingFaculty.jsp").forward(request, response);
            } else if (request.getParameter("print") != null) {
                request.getRequestDispatcher("/PrintEducationalCredServlet").forward(request, response);
            } else if (request.getParameter("first") != null) {
                request.getRequestDispatcher("jsp/firstpage.jsp").forward(request, response);
            } else if (request.getParameter("cancel") != null) {
                request.getRequestDispatcher("jsp/educationalCred.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("jsp/educationalCred.jsp").forward(request, response);
            }
            return;
        }

        // Set degree info
        degree.setAttendancePeriods(request.getParameter("degreeFrom") + "%" + request.getParameter("degreeTo"));
        degree.setCredential(request.getParameter("degree"));
        degree.setDegreeDate(request.getParameter("degreeConferralDate"));

        String fieldOfStudy = "";

        if (request.getParameter("field") == null) {
            fieldOfStudy += "^";
        } else {
            fieldOfStudy += request.getParameter("field");
        }
        degree.setFieldOfStudy(fieldOfStudy);
        degree.setInstitution(request.getParameter("Institution"));
        degree.setTranscript(-1);

        // If the user has no apps, make a new one. If they have apps, then
        // update their current one.
        if (applications == null || applications.isEmpty()) {
            appCreated = appManager.insertApplication(app);
        }

        if (appCreated != null) {
            synchronized (lock) {
                sess.setAttribute("currentapp", app);
            }
        }

        // If the user created a new degree, insert it. If they are updating
        // a current one, then do so.
        if (degree.getDegreeID() == 0) {
            degree.setApplication(app.getApplicationID());
            degreeManager.insertDegree(degree);
        } else {
            degreeManager.updateDegree(degree);
        }

        // Send the user back to the educred page
        if (request.getParameter("teachFac") != null) {
            request.getRequestDispatcher("jsp/teachingFaculty.jsp").forward(request, response);
        } else if (request.getParameter("print") != null)// print the current data in the educational credentials page
        {
            request.getRequestDispatcher("/PrintEducationalCredServlet").forward(request, response);
        } else if (request.getParameter("first") != null) // sends the user back to the first page
        {
            request.getRequestDispatcher("jsp/firstpage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("jsp/educationalCred.jsp").forward(request, response);
        }
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
