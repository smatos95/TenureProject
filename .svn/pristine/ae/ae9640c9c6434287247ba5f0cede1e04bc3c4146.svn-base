package servlets;

import common.ErrorLog;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysql.ErrorManager;

/**
 * A servlet that clears logs from the Error Logs table.
 * 
 * @author Riley Hughes (2019)
 */
@WebServlet(name = "ClearLogsServlet", urlPatterns = {"/ClearLogsServlet"})
public class ClearLogsServlet extends HttpServlet {

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
        
        // Initialize Error Manager, dummy variable to hold current log,
        // and collection of logs from the database
        ErrorManager errm = new ErrorManager();
        ErrorLog errlog;
        Collection<ErrorLog> errs = errm.getAllLogs();
        
        // Log Numbers for logs to be deleted, selected from the log page
        String[] deleteLogNums = request.getParameterValues("deletelog");
        
        // If the "only delete selected logs" button was hit
        if (request.getParameter("deletesomelogs") != null) {
            
            // Get the specified log from the database and delete it
            for (int i = 0; i < deleteLogNums.length; i++) {
                errlog = errm.getLogById(Integer.parseInt(deleteLogNums[i]));
                errm.deleteErr(errlog);
            }
            
        // If the "delete all logs" button was hit
        } else if (request.getParameter("deletealllogs") != null) {
            
            // If there are in fact logs to be deleted
            if (errs != null && errs.isEmpty() == false) {
                
                // Go through each log in the database and delete them
                Iterator<ErrorLog> itann = errs.iterator();

                while (itann.hasNext()) {
                    errlog = itann.next();
                    errm.deleteErr(errlog);
                }
            }
        }
        
        // Update the collection of logs for the site (session variable)
        errs = errm.getAllLogs();
        request.getSession().setAttribute("e", errs);
        
        // Go back to the servlet that prepares admin info
        request.getRequestDispatcher("/AdminServlet").forward(request, response);
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
