package servlets;

import common.Application;
import common.TeachingExp;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysql.TeachingExpManager;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
/**
 * MULTIPLE DEGREE OBJECTS, USE A WHILE LOOP OR A FOR LOOP TO PROCESS AND ADD
 * TO XWPF DOCUMENT TO PRINT
 * Steven Matos (2019)
 */
@WebServlet(name = "PrintTeachingFacultyServlet", urlPatterns =
{
    "/PrintTeachingFacultyServlet"
})

public class PrintTeachingFacultyServlet extends HttpServlet{
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
            throws ServletException, IOException 
    {
        TeachingExpManager teachingExpManager = new TeachingExpManager();
        Application application = (Application) request.getSession().getAttribute("currentapp");      
        List<TeachingExp> tems = teachingExpManager.getTeachingExpsByApplication(application.getApplicationID());
        
        XWPFParagraph[][] texpPara;
        XWPFRun[][] texpRuns;
        String[] datesOfExp;
        TeachingExp texp = new TeachingExp(0);
        
        if (tems != null && tems.isEmpty() == false) {
            texpPara = new XWPFParagraph[tems.size()][5];
            texpRuns = new XWPFRun[tems.size()][10];
            texp = tems.get(0);
        } else {
            texpPara = new XWPFParagraph[1][5];
            texpRuns = new XWPFRun[1][10];
        }
        
        XWPFDocument document = new XWPFDocument();
        
        CTP ctp = CTP.Factory.newInstance();
        // this adds page number incremental
        CTR r = ctp.addNewR();
        if (application.getCustomFirstName() != null && application.getCustomLastName() != null)
            r.addNewT().setStringValue(application.getCustomFirstName() + " " + application.getCustomLastName() + " 1-");
        else
            r.addNewT().setStringValue("Your Name 1-");
        r.addNewPgNum();
        
        CTP ctp2 = CTP.Factory.newInstance();
        CTR r2 = ctp2.addNewR();
        r2.addNewT().setStringValue("Background Information");

        XWPFParagraph codePara = new XWPFParagraph(ctp, document);
        codePara.setSpacingAfter(0);
        codePara.setSpacingBefore(0);
        codePara.setSpacingBetween(1);
        XWPFParagraph codePara2 = new XWPFParagraph(ctp2, document);
        codePara2.setSpacingAfter(0);
        codePara2.setSpacingBefore(0);
        codePara2.setSpacingBetween(1);
        XWPFRun xrun = codePara2.getRun(r2);
        xrun.setBold(true);
        xrun.addCarriageReturn();
        XWPFParagraph[] paragraphs = new XWPFParagraph[2];
        paragraphs[0] = codePara;
        paragraphs[1] = codePara2;
        // position of number
        codePara.setAlignment(ParagraphAlignment.RIGHT);
        codePara2.setAlignment(ParagraphAlignment.RIGHT);

        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();

        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
        headerFooterPolicy.createHeader(STHdrFtr.DEFAULT, paragraphs);
        
        XWPFParagraph teachExpTitle = document.createParagraph();
        teachExpTitle.setSpacingAfter(0);
        teachExpTitle.setSpacingBefore(0);
        teachExpTitle.setSpacingBetween(2);
        XWPFRun teachExpRun = teachExpTitle.createRun();
        teachExpRun.setText("B.");
        XWPFRun teachExpRun2 = teachExpTitle.createRun();
        teachExpRun2.setBold(true);
        teachExpRun2.setText("   Teaching/Faculty Background");
        
        texpPara[0][0] = document.createParagraph();
        texpPara[0][0].setSpacingAfter(0);
        texpPara[0][0].setSpacingBefore(0);
        texpPara[0][0].setSpacingBetween(1);
        texpRuns[0][0] = texpPara[0][0].createRun();
        texpRuns[0][0].addTab();
        texpRuns[0][0].setBold(true);
        texpRuns[0][0].setText("Institution:");
        texpRuns[0][0].addTab();
        texpRuns[0][0].addTab();
        texpRuns[0][1] = texpPara[0][0].createRun();
        if (texp.getInstitution() != null)
            texpRuns[0][1].setText(texp.getInstitution());
        else
            texpRuns[0][1].setText("Your Institution Here");
        
        texpPara[0][1] = document.createParagraph();
        texpPara[0][1].setSpacingAfter(0);
        texpPara[0][1].setSpacingBefore(0);
        texpPara[0][1].setSpacingBetween(1);
        texpRuns[0][2] = texpPara[0][1].createRun();
        texpRuns[0][2].addTab();
        texpRuns[0][2].setBold(true);
        texpRuns[0][2].setText("Rank:");
        texpRuns[0][2].addTab();
        texpRuns[0][2].addTab();
        texpRuns[0][2].addTab();
        texpRuns[0][3] = texpPara[0][1].createRun();
        if (texp.getRank() != null && texp.getRank().equals("Select a Rank") == false)
            texpRuns[0][3].setText(texp.getRank());
        else
            texpRuns[0][3].setText("Your Rank Here");
        
        texpPara[0][2] = document.createParagraph();
        texpPara[0][2].setSpacingAfter(0);
        texpPara[0][2].setSpacingBefore(0);
        texpPara[0][2].setSpacingBetween(1);
        texpRuns[0][4] = texpPara[0][2].createRun();
        texpRuns[0][4].addTab();
        texpRuns[0][4].setBold(true);
        texpRuns[0][4].setText("Dates of Experience:");
        texpRuns[0][4].addTab();
        texpRuns[0][5] = texpPara[0][2].createRun();
        if (texp.getTeachingDates() != null) {
        datesOfExp = utilities.FormattingUtility.dateRangeConvert(texp.getTeachingDates());
        texpRuns[0][5].setText(utilities.FormattingUtility.formatMonthyear(datesOfExp[0]) 
                + " - " + utilities.FormattingUtility.formatMonthyear(datesOfExp[1]));
        } else {
            texpRuns[0][5].setText("Your Dates of Experience Here");
        }
        
        texpPara[0][3] = document.createParagraph();
        texpPara[0][3].setSpacingAfter(0);
        texpPara[0][3].setSpacingBefore(0);
        texpPara[0][3].setSpacingBetween(1);
        texpRuns[0][6] = texpPara[0][3].createRun();
        texpRuns[0][6].addTab();
        texpRuns[0][6].setBold(true);
        texpRuns[0][6].setText("Years:");
        texpRuns[0][6].addTab();
        texpRuns[0][6].addTab();
        texpRuns[0][6].addTab();
        texpRuns[0][7] = texpPara[0][3].createRun();
        if (texp.getYearsOfService() != null) {
            texpRuns[0][7].setText(texp.getYearsOfService() + " Years");
        } else
            texpRuns[0][7].setText("Your Years of Experience Here");
        
        texpPara[0][4] = document.createParagraph();
        texpPara[0][4].setSpacingAfter(0);
        texpPara[0][4].setSpacingBefore(0);
        texpPara[0][4].setSpacingBetween(1);
        texpRuns[0][8] = texpPara[0][4].createRun();
        texpRuns[0][8].addTab();
        texpRuns[0][8].setBold(true);
        texpRuns[0][8].setText("Full/Part Time Status:");
        texpRuns[0][8].addTab();
        texpRuns[0][9] = texpPara[0][4].createRun();
        if (texp.isFullTime() == true)
            texpRuns[0][9].setText("Full-time");
        else
            texpRuns[0][9].setText("Part-time");
        texpRuns[0][9].addCarriageReturn();
        
        for (int i = 1; i < texpPara.length; i++) {
            texp = tems.get(i);
            
            texpPara[i][0] = document.createParagraph();
            texpPara[i][0].setSpacingAfter(0);
            texpPara[i][0].setSpacingBefore(0);
            texpPara[i][0].setSpacingBetween(1);
            texpRuns[i][0] = texpPara[i][0].createRun();
            texpRuns[i][0].addTab();
            texpRuns[i][0].setBold(true);
            texpRuns[i][0].setText("Institution:");
            texpRuns[i][0].addTab();
            texpRuns[i][0].addTab();
            texpRuns[i][1] = texpPara[i][0].createRun();
            if (texp.getInstitution() != null)
                texpRuns[i][1].setText(texp.getInstitution());
            else
                texpRuns[i][1].setText("Your Institution Here");

            texpPara[i][1] = document.createParagraph();
            texpPara[i][1].setSpacingAfter(0);
            texpPara[i][1].setSpacingBefore(0);
            texpPara[i][1].setSpacingBetween(1);
            texpRuns[i][2] = texpPara[i][1].createRun();
            texpRuns[i][2].addTab();
            texpRuns[i][2].setBold(true);
            texpRuns[i][2].setText("Rank:");
            texpRuns[i][2].addTab();
            texpRuns[i][2].addTab();
            texpRuns[i][2].addTab();
            texpRuns[i][3] = texpPara[i][1].createRun();
            if (texp.getRank() != null && texp.getRank().equals("Select a Rank") == false)
                texpRuns[i][3].setText(texp.getRank());
            else
                texpRuns[i][3].setText("Your Rank Here");

            texpPara[i][2] = document.createParagraph();
            texpPara[i][2].setSpacingAfter(0);
            texpPara[i][2].setSpacingBefore(0);
            texpPara[i][2].setSpacingBetween(1);
            texpRuns[i][4] = texpPara[i][2].createRun();
            texpRuns[i][4].addTab();
            texpRuns[i][4].setBold(true);
            texpRuns[i][4].setText("Dates of Experience:");
            texpRuns[i][4].addTab();
            texpRuns[i][5] = texpPara[i][2].createRun();
            if (texp.getTeachingDates() != null) {
            datesOfExp = utilities.FormattingUtility.dateRangeConvert(texp.getTeachingDates());
            texpRuns[i][5].setText(utilities.FormattingUtility.formatMonthyear(datesOfExp[0]) 
                    + " - " + utilities.FormattingUtility.formatMonthyear(datesOfExp[1]));
            } else {
                texpRuns[i][5].setText("Your Dates of Experience Here");
            }

            texpPara[i][3] = document.createParagraph();
            texpPara[i][3].setSpacingAfter(0);
            texpPara[i][3].setSpacingBefore(0);
            texpPara[i][3].setSpacingBetween(1);
            texpRuns[i][6] = texpPara[i][3].createRun();
            texpRuns[i][6].addTab();
            texpRuns[i][6].setBold(true);
            texpRuns[i][6].setText("Years:");
            texpRuns[i][6].addTab();
            texpRuns[i][6].addTab();
            texpRuns[i][6].addTab();
            texpRuns[i][7] = texpPara[i][3].createRun();
            if (texp.getYearsOfService() != null) {
                texpRuns[i][7].setText(texp.getYearsOfService() + " Years");
            } else
                texpRuns[i][7].setText("Your Years of Experience Here");

            texpPara[i][4] = document.createParagraph();
            texpPara[i][4].setSpacingAfter(0);
            texpPara[i][4].setSpacingBefore(0);
            texpPara[i][4].setSpacingBetween(1);
            texpRuns[i][8] = texpPara[i][4].createRun();
            texpRuns[i][8].addTab();
            texpRuns[i][8].setBold(true);
            texpRuns[i][8].setText("Full/Part Time Status:");
            texpRuns[i][8].addTab();
            texpRuns[i][9] = texpPara[i][4].createRun();
            if (texp.isFullTime() == true)
                texpRuns[i][9].setText("Full-time");
            else
                texpRuns[i][9].setText("Part-time");
            texpRuns[i][9].addCarriageReturn();
        }
                        
        // Set Download info
        String relativePath = getServletContext().getRealPath("");
        ServletContext context = getServletContext();
        String mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        response.setContentType(mimeType);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "TeachingFaculty.docx");
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
    

