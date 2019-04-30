/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import common.Application;
import common.Narrative;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysql.NarrativeManager;

/**
 *
 * @author rsh50944
 */
@WebServlet(name = "UpdateScholarlyGrowthServlet", urlPatterns = {"/UpdateScholarlyGrowthServlet"})
public class UpdateScholarlyGrowthServlet extends HttpServlet {

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
        
        // Create Narrative Manager, get current application, and get this application's
        // narratives
        NarrativeManager narrm = new NarrativeManager();
        Application app = (Application) request.getSession().getAttribute("currentapp");
        List<Narrative> narrs = narrm.getNarrativesByApplication(app.getApplicationID());
        
        // Temp variable to hold current narrative (iterative) with iterator
        Narrative narr = null;
        Iterator<Narrative> itann = null;
        
        // If there are narratives for this app, go through them to see if there
        // is a professional responsibilities narrative. If there is one, narr
        // will become it, and if not, narr is null.
        if (narrs != null && narrs.isEmpty() == false) {
            itann = narrs.iterator();
            
            while (itann.hasNext()) {
                narr = itann.next();
                
                if (narr.getType().equals("SG"))
                    break;
                else
                    narr = null;
            }
        }
        
        // If we found a narrative, modify it with the user's new text and update.
        if (narr != null) {
            narr.setNarrativeText(request.getParameter("ScholGrowthNar"));
            narrm.updateNarrative(narr);
            
         // If there is no PR narrative, make a new one, put the user's info in,
         // and insert the narrative.
        } else {
            narr = new Narrative(0);
            narr.setApplication(app.getApplicationID());
            narr.setNarrativeText(request.getParameter("ScholGrowthNar"));
            narr.setType("SG");
            narr.setNarrativeMedia(null);
            narrm.insertNarrative(narr);
        }
        
        // Send the user to the next page
        if (request.getParameter("Activities") != null)
        {
            request.getRequestDispatcher("jsp/Activities.jsp").forward(request, response);
        }
//        else if (request.getParameter("print") != null) Not implemented yet
//        {
//            request.getRequestDispatcher("/PrintCoverPageServlet").forward(request, response);
//        }
        // Send the user back a page
        else if (request.getParameter("Innovations") != null)
        {
            request.getRequestDispatcher("jsp/Innovations.jsp").forward(request, response);
        }
        // Default, keep the user on this page
        else
            request.getRequestDispatcher("jsp/scholarlygrowth.jsp").forward(request, response);
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
