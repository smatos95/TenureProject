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
@WebServlet(name = "PrintProfessionalFulfillmentServlet", urlPatterns = {"/PrintProfessionalFulfillmentServlet"})
public class PrintProfessionalFulfillmentServlet extends HttpServlet {

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
                
                if (narr.getType().equals("SA"))
                    break;
                else
                    narr = null;
            }
        }
        
        XWPFDocument document = new XWPFDocument();
        
        XWPFParagraph narrTitle = document.createParagraph();
        narrTitle.setSpacingAfter(0);
        narrTitle.setSpacingBefore(0);
        narrTitle.setSpacingBetween(2);
        XWPFRun narrRun = narrTitle.createRun();
        narrRun.setText("D.   Fulfillment of Professional Responsibilities");
        narrRun.setBold(true);
        
        XWPFParagraph saTitle = document.createParagraph();
        saTitle.setSpacingAfter(0);
        saTitle.setSpacingBefore(0);
        saTitle.setSpacingBetween(2);
        XWPFRun saRun = saTitle.createRun();
        saRun.setText("       a.   Student Advisement");
        saRun.setBold(true);
        
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
        
        narrRuns[paras.length - 1].addCarriageReturn();
        narrRuns[paras.length - 1].addCarriageReturn();
        
        narr = null;
        itann = null;
        
        // If there are narratives for this app, go through them to see if there
        // is a professional responsibilities narrative. If there is one, narr
        // will become it, and if not, narr is null.
        if (narrs != null && narrs.isEmpty() == false) {
            itann = narrs.iterator();
            
            while (itann.hasNext()) {
                narr = itann.next();
                
                if (narr.getType().equals("AD"))
                    break;
                else
                    narr = null;
            }
        }
        
        XWPFParagraph adTitle = document.createParagraph();
        adTitle.setSpacingAfter(0);
        adTitle.setSpacingBefore(0);
        adTitle.setSpacingBetween(2);
        XWPFRun adRun = adTitle.createRun();
        adRun.setText("       b.   Acceptance of Departmental Assignments");
        adRun.setBold(true);
        
        String[] adParas = narr.getNarrativeText().split("" + (char) 13);
        XWPFParagraph[] adNarrParas = new XWPFParagraph[adParas.length];
        XWPFRun[] adNarrRuns = new XWPFRun[adParas.length];
        for (int i = 0; i < adNarrParas.length; i++) {
            adNarrParas[i] = document.createParagraph();
            adNarrParas[i].setSpacingAfter(0);
            adNarrParas[i].setSpacingBefore(0);
            adNarrParas[i].setSpacingBetween(1);
            adNarrRuns[i] = adNarrParas[i].createRun();
            adNarrRuns[i].addTab();
            adNarrRuns[i].setText(adParas[i]);
        }
        
        adNarrRuns[adParas.length - 1].addCarriageReturn();
        adNarrRuns[adParas.length - 1].addCarriageReturn();
        
        narr = null;
        itann = null;
        
        // If there are narratives for this app, go through them to see if there
        // is a professional responsibilities narrative. If there is one, narr
        // will become it, and if not, narr is null.
        if (narrs != null && narrs.isEmpty() == false) {
            itann = narrs.iterator();
            
            while (itann.hasNext()) {
                narr = itann.next();
                
                if (narr.getType().equals("TE"))
                    break;
                else
                    narr = null;
            }
        }
        
        XWPFParagraph teTitle = document.createParagraph();
        teTitle.setSpacingAfter(0);
        teTitle.setSpacingBefore(0);
        teTitle.setSpacingBetween(2);
        XWPFRun teRun = teTitle.createRun();
        teRun.setText("       c.   Timely Execution of Work");
        teRun.setBold(true);
        
        String[] teParas = narr.getNarrativeText().split("" + (char) 13);
        XWPFParagraph[] teNarrParas = new XWPFParagraph[teParas.length];
        XWPFRun[] teNarrRuns = new XWPFRun[teParas.length];
        for (int i = 0; i < teNarrParas.length; i++) {
            teNarrParas[i] = document.createParagraph();
            teNarrParas[i].setSpacingAfter(0);
            teNarrParas[i].setSpacingBefore(0);
            teNarrParas[i].setSpacingBetween(1);
            teNarrRuns[i] = teNarrParas[i].createRun();
            teNarrRuns[i].addTab();
            teNarrRuns[i].setText(teParas[i]);
        }
        
        teNarrRuns[teParas.length - 1].addCarriageReturn();
        teNarrRuns[teParas.length - 1].addCarriageReturn();
        
        // Set Download info
        String relativePath = getServletContext().getRealPath("");
        ServletContext context = getServletContext();
        String mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        response.setContentType(mimeType);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "ProfessionalFulfillmentNarrative.docx");
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
