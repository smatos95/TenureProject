package servlets;

import common.Application;
import common.Narrative;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysql.NarrativeManager;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author Riley Hughes (2019)
 */
@WebServlet(name = "PrintScholarlyGrowthServlet", urlPatterns = {"/PrintScholarlyGrowthServlet"})
public class PrintScholarlyGrowthServlet extends HttpServlet {

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
        
        NarrativeManager narrm = new NarrativeManager();
        Application application = (Application) request.getSession().getAttribute("currentapp");
        List<Narrative> narrs = narrm.getNarrativesByApplication(application.getApplicationID());
        
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
        
        XWPFDocument document = new XWPFDocument();
        
        XWPFParagraph narrTitle = document.createParagraph();
        narrTitle.setPageBreak(true);
        narrTitle.setSpacingAfter(0);
        narrTitle.setSpacingBefore(0);
        narrTitle.setSpacingBetween(2);
        XWPFRun narrRun = narrTitle.createRun();
        narrRun.setText("A.   Narrative on Continuing Scholarly Growth");
        narrRun.setBold(true);
        
        String[] paras = narr.getNarrativeText().split("" + (char) 13);
        XWPFParagraph[] narrParas = new XWPFParagraph[paras.length];
        XWPFRun[] narrRuns = new XWPFRun[paras.length];
        for (int i = 0; i < narrParas.length; i++) {
            narrParas[i] = document.createParagraph();
            narrParas[i].setSpacingAfter(0);
            narrParas[i].setSpacingBefore(0);
            narrParas[i].setSpacingBetween(1);
            narrRuns[i] = narrParas[i].createRun();
            narrRuns[i].addTab();
            narrRuns[i].setText(paras[i]);
        }
        
        // Set Download info
        String relativePath = getServletContext().getRealPath("");
        ServletContext context = getServletContext();
        String mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        response.setContentType(mimeType);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "ScholarlyGrowthNarrative.docx");
        response.setHeader(headerKey, headerValue);

        // Send the download!
        OutputStream outStream = response.getOutputStream();
        document.write(outStream);

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
