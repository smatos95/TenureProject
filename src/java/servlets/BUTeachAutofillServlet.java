package servlets;

import common.Application;
import common.BuUser;
import common.LocalExp;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mysql.LocalExpManager;

/**
 * Trampoline servlet for selecting a record on the page <code>BuTeachingHistory.jsp</code>.
 * @author Gryphon Ayers (2019)
 */


@WebServlet(name = "BUTeachAutofillServlet", urlPatterns = {"/BUTeachAutofillServlet"})
public class BUTeachAutofillServlet extends HttpServlet {

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
        LocalExpManager lxm = new LocalExpManager();
        Application app = (Application) sess.getAttribute("currentapp");
        BuUser user = (BuUser) sess.getAttribute("user");
        
        // check which degree we need to select
        if(request.getParameter("exp") != null) {
            Integer expNumber = Integer.parseInt(request.getParameter("exp"));
            if(expNumber == -1) {
                // "no record" was selected
                sess.removeAttribute("currentexp");
            } else {
                LocalExp e = lxm.getLocalExp(expNumber);
                if(e.getApplication() == app.getApplicationID() &&
                    app.getApplicantUser() == user.getUserNumber()) {
                    sess.setAttribute("currentexp", expNumber);
                }
            }
        }
        
        request.getRequestDispatcher("jsp/BuTeachingHistory.jsp").forward(request, response);
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
