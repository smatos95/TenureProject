package servlets;

import common.Application;
import common.BuUser;
import common.WorkExp;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mysql.ApplicationManager;
import mysql.WorkExpManager;

/**
 * @author Steven Matos (2019)
 */
@WebServlet(name = "UpdateOtherWorkExperienceServlet", urlPatterns = {"/UpdateOtherWorkExperienceServlet"})
public class UpdateOtherWorkExperienceServlet extends HttpServlet {

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
        
        // Get BU User's details
        BuUser user;
        
        ApplicationManager appManager = new ApplicationManager();
        WorkExpManager workExpManager = new WorkExpManager();
        
        sess.removeAttribute("isPopup");
        
        
        List<WorkExp> workExps = null;
        WorkExp workExp;
        
        // Set an application with the details the user entered. Since no data
        // stored in an application object is set in this page, only basic info
        // is set.
        Application app;
        String apptype;
        Integer workNumber;
        Integer appCreated = null;
        
        //get session variables
        synchronized (lock) {
            user = (BuUser) sess.getAttribute("user");
            app = (Application) sess.getAttribute("currentapp");
            apptype = (String) sess.getAttribute("apptype");
            workNumber = (Integer) sess.getAttribute("currentwork");
        }
        
        // Get user's apps
        Collection<Application> applications = appManager.getApplicationsByUserID(user.getUserNumber());
        
        
        if (app == null) {
            app = new Application(0);
            app.setCreationDate(LocalDateTime.now());
        } else {
            workExps = workExpManager.getWorkExpsByApplication(app.getApplicationID());
            if (app.getCreationDate() == null)
                app.setCreationDate(LocalDateTime.now());
        }
        app.setApplicantUser(user.getUserNumber());
        
        if (apptype.equalsIgnoreCase("Tenure"))
            app.setApplicationType("tenure");
        else if (apptype.equalsIgnoreCase("Promotion"))
            app.setApplicationType("promotion");
        
        if (request.getParameter("deleteWork") != null)
            {
                request.getRequestDispatcher("/OtherWorkExperienceDeleteServlet").forward(request, response);
                return;
            }
        if (request.getParameter("workExp") != null)
            {
                request.getRequestDispatcher("/OtherWorkExperienceAutoFillServlet").forward(request, response);
      
            }
        
        // check whether to update or insert, if either
        if (request.getParameter("insert") != null) {
            // add new credential if the new credential button was clicked
            workExp = new WorkExp(0);
        } else if (workNumber != null && workNumber >= 0) {
            // get the record requested and check for valid access
            workExp = workExpManager.getWorkExp(workNumber);           
            if (!(workExp.getApplication() == app.getApplicationID() &&
                app.getApplicantUser() == user.getUserNumber())) {
                // add a new credential instead of allowing an invalid access
                workExp = new WorkExp(0);
            }
        } else {
            if (request.getParameter("transcripts") != null)// forwards the page to the next one
            {
                request.getRequestDispatcher("jsp/officialTranscripts.jsp").forward(request, response);
            }
            else if (request.getParameter("print") != null)
            {
                request.getRequestDispatcher("/PrintOtherWorkExperienceServlet").forward(request, response);
            }
            else if (request.getParameter("teachFac") != null) // sends the page to the previous one
            {
                request.getRequestDispatcher("jsp/teachingFaculty.jsp").forward(request, response);
            }
            else if(request.getParameter("cancel") != null)
            {
                request.getRequestDispatcher("jsp/otherWorkExperience.jsp").forward(request, response);
            }
            else
                request.getRequestDispatcher("jsp/otherWorkExperience.jsp").forward(request, response);
            return;
        }
                
        // Set work experience info
        workExp.setInstitution(request.getParameter("otherWorkLocation"));
        workExp.setTitle("jobTitle");
        workExp.setExperienceDates(request.getParameter("otherJobFrom") + "%" + request.getParameter("otherJobTo"));
        workExp.setYearsOfService(Integer.parseInt(request.getParameter("otherJobYears")));
        if(request.getParameter("otherJobStatus").equals("Full-time"))
            workExp.setIsFullTime(true);
        else if (request.getParameter("otherJobStatus").equals("Part-time"))
            workExp.setIsFullTime(false);
        
        
        // If the user has no apps, make a new one. If they have apps, then
        // update their current one.
        if (applications == null || applications.isEmpty()) {
            appCreated = appManager.insertApplication(app);
        } else {
            if (request.getSession().getAttribute("currentapp") != null)
                appManager.updateApplication(app);
            else {
                appCreated = appManager.insertApplication(app);
            }
        }
        
        synchronized (lock) {
            request.getSession().setAttribute("currentapp", app);
        }
        
        /*
        // If the user created a new teachingexp, insert it. If they are updating
        // a current one, then do so.
        if (teachExp.getTeachingID() == 0) {
            teachExp.setApplication(app.getApplicationID());
            teachingExpManager.insertTeachingExp(teachExp);
        } else
            teachingExpManager.updateTeachingExp(teachExp);*/
        
        // Inserts or updates a new work experience
        if(workExp.getWorkID() == 0){
            workExp.setApplication(app.getApplicationID());
            workExpManager.insertWorkExp(workExp);
        }else
            workExpManager.insertWorkExp(workExp);
        
        synchronized (lock) {
            sess.removeAttribute("currentwork");
        }
        
        
        // Send the user to the transcripts page
        if (request.getParameter("transcripts") != null)// forwards the page to the next one
        {
            request.getRequestDispatcher("jsp/officialTranscripts.jsp").forward(request, response);
        }
        else if (request.getParameter("print") != null)
        {
            request.getRequestDispatcher("/PrintTeachingFacultyServlet").forward(request, response);
        }
        else if (request.getParameter("teachFac") != null)
        {
            request.getRequestDispatcher("jsp/teachingFaculty.jsp").forward(request, response);
        }
        else
        request.getRequestDispatcher("jsp/otherWorkExperience.jsp").forward(request, response);
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
