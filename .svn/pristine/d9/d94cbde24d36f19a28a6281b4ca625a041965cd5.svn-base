package servlets;

import common.Application;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * Servlet for constructing and printing the full tenure application document.
 *
 * @author Riley Hughes (2019)
 */
@WebServlet(name = "PrintAllPagesServlet", urlPatterns = {"/PrintAllPagesServlet"})
public class PrintAllPagesServlet extends HttpServlet {

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
        // Get the current application
        Application a = (Application) request.getSession().getAttribute("currentapp");
        
        // Use the separate POI classes to fill the document with tenure
        // formatted content
        XWPFDocument d = utilities.POICoverPage.generateDoc(a, null, false);
        d = utilities.POIFirstPage.generateDoc(a, d, false);
        d = utilities.POIEduCred.generateDoc(a, d, false);
        d = utilities.POITeachingExp.generateDoc(a, d, false);
        d = utilities.POIWorkExp.generateDoc(a, d, false);
        d = utilities.POITeachingFulfillment.generateDoc(a, d, false);
        d = utilities.POITeachingHistory.generateDoc(a, d, false);
        d = utilities.POIProfessionalFulfillment.generateDoc(a, d, false);
        d = utilities.POIScholarlyGrowth.generateDoc(a, d, false);
        d = utilities.POIUniComm.generateDoc(a, d, true);
        
        // Set Download info
        String relativePath = getServletContext().getRealPath("");
        ServletContext context = getServletContext();
        String mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        response.setContentType(mimeType);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "Application.docx");
        response.setHeader(headerKey, headerValue);

        // Send the download!
        OutputStream outStream = response.getOutputStream();
        d.write(outStream);

        // Finish out.
        outStream.close();
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
