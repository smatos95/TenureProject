package servlets;

import common.Application;
import common.Degree;
import common.TeachingExp;
import common.WorkExp;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysql.DegreeManager;
import mysql.TeachingExpManager;
import mysql.WorkExpManager;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;

/**
 * Servlet for constructing the "background info" section of the tenure application document.
 * 
 * @author Riley Hughes (2019)
 */
@WebServlet(name = "PrintBackgroundInfoServlet", urlPatterns = {"/PrintBackgroundInfoServlet"})
public class PrintBackgroundInfoServlet extends HttpServlet {

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
        DegreeManager degreeManager = new DegreeManager();
        Application application = (Application) request.getSession().getAttribute("currentapp");
        Collection<Degree> degrees = degreeManager.getDegreesByApplication(application.getApplicationID());
        
        TeachingExpManager teachingExpManager = new TeachingExpManager();
        List<TeachingExp> tems = teachingExpManager.getTeachingExpsByApplication(application.getApplicationID());
        
        WorkExpManager workManager = new WorkExpManager();
        List<WorkExp> workExps = workManager.getWorkExpsByApplication(application.getApplicationID());
        
        XWPFParagraph[][] degreePara;
        XWPFRun[][] degreeRuns;
        String[] attendanceDates;
        Iterator<Degree> itann = null;
        Degree degree = new Degree(0);
        
        XWPFParagraph[][] texpPara;
        XWPFRun[][] texpRuns;
        String[] datesOfExp;
        TeachingExp texp = new TeachingExp(0);
        
        XWPFParagraph[][] workExpPara = new XWPFParagraph[1][5];
        XWPFRun[][] workExpRuns = new XWPFRun[1][10];
        datesOfExp = new String[2];
        WorkExp workExp = new WorkExp(0);
        
        // Initialize paragraphs and degrees for appropriate amount of EduCreds
        if (degrees != null && degrees.isEmpty() == false) {
            degreePara = new XWPFParagraph[degrees.size()][5];
            degreeRuns = new XWPFRun[degrees.size()][10];
            itann = degrees.iterator();
            degree = itann.next();
        } else {
            degreePara = new XWPFParagraph[1][5];
            degreeRuns = new XWPFRun[1][10];
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
        CTSectPr sectPr2 = document.getDocument().getBody().getSectPr();
        sectPr2.addNewTitlePg();
        XWPFHeader firstHeader = new XWPFHeader();
        
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
        headerFooterPolicy.createHeader(STHdrFtr.DEFAULT, paragraphs);
        headerFooterPolicy.createHeader(STHdrFtr.FIRST);

        XWPFParagraph backgroundInfo = document.createParagraph();
        backgroundInfo.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun backgroundRun = backgroundInfo.createRun();
        backgroundRun.setUnderline(UnderlinePatterns.SINGLE);
        backgroundRun.setBold(true);
        backgroundRun.setText("Section 1");
        backgroundRun.setFontFamily("Calibri");
        backgroundRun.setFontSize(24);
        XWPFRun backgroundRun2 = backgroundInfo.createRun();
        backgroundRun2.setText(":");
        backgroundRun2.setBold(true);
        backgroundRun2.setFontFamily("Calibri");
        backgroundRun2.setFontSize(24);
        backgroundRun2.addCarriageReturn();
        backgroundRun2.addCarriageReturn();
        XWPFRun backgroundRun3 = backgroundInfo.createRun();
        backgroundRun3.setBold(true);
        backgroundRun3.setFontFamily("Calibri");
        backgroundRun3.setFontSize(24);
        
        XWPFParagraph eduCredTitle = document.createParagraph();
        eduCredTitle.setPageBreak(true);
        eduCredTitle.setSpacingAfter(0);
        eduCredTitle.setSpacingBefore(0);
        eduCredTitle.setSpacingBetween(2);
        XWPFRun eduCredRun = eduCredTitle.createRun();
        eduCredRun.setText("A.");
        XWPFRun eduCredRun2 = eduCredTitle.createRun();
        eduCredRun2.setBold(true);
        eduCredRun2.setText("   Education Credentials");
        
        // Institution Line
        degreePara[0][0] = document.createParagraph();
        degreePara[0][0].setSpacingAfter(0);
        degreePara[0][0].setSpacingBefore(0);
        degreePara[0][0].setSpacingBetween(1);
        degreeRuns[0][0] = degreePara[0][0].createRun();
        degreeRuns[0][0].addTab();
        degreeRuns[0][0].setBold(true);
        degreeRuns[0][0].setText("Institution:");
        degreeRuns[0][0].addTab();
        degreeRuns[0][0].addTab();
        degreeRuns[0][1] = degreePara[0][0].createRun();
        if (degree.getInstitution() != null)
            degreeRuns[0][1].setText(degree.getInstitution());
        else
            degreeRuns[0][1].setText("Your Institution Here");

        // Degree Line (Credential)
        degreePara[0][1] = document.createParagraph();
        degreePara[0][1].setSpacingAfter(0);
        degreePara[0][1].setSpacingBefore(0);
        degreePara[0][1].setSpacingBetween(1);
        degreeRuns[0][2] = degreePara[0][1].createRun();
        degreeRuns[0][2].addTab();
        degreeRuns[0][2].setBold(true);
        degreeRuns[0][2].setText("Degree:");
        degreeRuns[0][2].addTab();
        degreeRuns[0][2].addTab();
        degreeRuns[0][2].addTab();
        degreeRuns[0][3] = degreePara[0][1].createRun();
        if (degree.getCredential() != null)
            degreeRuns[0][3].setText(degree.getCredential());
        else
            degreeRuns[0][3].setText("Your Degree Credential Here");

        // Field of Study Line
        degreePara[0][2] = document.createParagraph();
        degreePara[0][2].setSpacingAfter(0);
        degreePara[0][2].setSpacingBefore(0);
        degreePara[0][2].setSpacingBetween(1);
        degreeRuns[0][4] = degreePara[0][2].createRun();
        degreeRuns[0][4].addTab();
        degreeRuns[0][4].setBold(true);
        degreeRuns[0][4].setText("Field of Study:");
        degreeRuns[0][4].addTab();
        degreeRuns[0][4].addTab();
        degreeRuns[0][5] = degreePara[0][2].createRun();
        if (degree.getFieldOfStudy() != null && degree.getFieldOfStudy().equals("^") == false)
            degreeRuns[0][5].setText(degree.getFieldOfStudy());
        else
            degreeRuns[0][5].setText("Your Field of Study Here");

        // Dates of Attendance Line
        degreePara[0][3] = document.createParagraph();
        degreePara[0][3].setSpacingAfter(0);
        degreePara[0][3].setSpacingBefore(0);
        degreePara[0][3].setSpacingBetween(1);
        degreeRuns[0][6] = degreePara[0][3].createRun();
        degreeRuns[0][6].addTab();
        degreeRuns[0][6].setBold(true);
        degreeRuns[0][6].setText("Dates of Attendance:");
        degreeRuns[0][6].addTab();
        degreeRuns[0][7] = degreePara[0][3].createRun();
        if (degree.getAttendancePeriods() != null) {
            attendanceDates = utilities.FormattingUtility.dateRangeConvert(degree.getAttendancePeriods());
            degreeRuns[0][7].setText(utilities.FormattingUtility.formatDate(attendanceDates[0]) + " - "
                   + utilities.FormattingUtility.formatDate(attendanceDates[1]));
        } else
            degreeRuns[0][7].setText("Your Attendance Period Here");

        // Date of Degree Conferral Line
        degreePara[0][4] = document.createParagraph();
        degreePara[0][4].setSpacingAfter(0);
        degreePara[0][4].setSpacingBefore(0);
        degreePara[0][4].setSpacingBetween(1);
        degreeRuns[0][8] = degreePara[0][4].createRun();
        degreeRuns[0][8].addTab();
        degreeRuns[0][8].setBold(true);
        degreeRuns[0][8].setText("Date of Degree:");
        degreeRuns[0][8].addTab();
        degreeRuns[0][8].addTab();
        degreeRuns[0][9] = degreePara[0][4].createRun();
        if (degree.getDegreeDate() != null)
            degreeRuns[0][9].setText(utilities.FormattingUtility.formatDate(degree.getDegreeDate()));
        else
            degreeRuns[0][9].setText("Your Date of Degree Conferral Here");
        degreeRuns[0][9].addCarriageReturn();

        if (degrees == null)
            degreeRuns[0][9].addCarriageReturn();
        
        // Loop to print out multiple eduCreds
        for (int i = 1; i < degreePara.length; i++) {
            if (itann != null) {
                degree = itann.next();
            }

            // Institution Line
            degreePara[i][0] = document.createParagraph();
            degreePara[i][0].setSpacingAfter(0);
            degreePara[i][0].setSpacingBefore(0);
            degreePara[i][0].setSpacingBetween(1);
            degreeRuns[i][0] = degreePara[i][0].createRun();
            degreeRuns[i][0].addTab();
            degreeRuns[i][0].setBold(true);
            degreeRuns[i][0].setText("Institution:");
            degreeRuns[i][0].addTab();
            degreeRuns[i][0].addTab();
            degreeRuns[i][1] = degreePara[i][0].createRun();
            if (degree.getInstitution() != null)
                degreeRuns[i][1].setText(degree.getInstitution());
            else
                degreeRuns[i][1].setText("Your Institution Here");

            // Degree Credential Line
            degreePara[i][1] = document.createParagraph();
            degreePara[i][1].setSpacingAfter(0);
            degreePara[i][1].setSpacingBefore(0);
            degreePara[i][1].setSpacingBetween(1);
            degreeRuns[i][2] = degreePara[i][1].createRun();
            degreeRuns[i][2].addTab();
            degreeRuns[i][2].setBold(true);
            degreeRuns[i][2].setText("Degree:");
            degreeRuns[i][2].addTab();
            degreeRuns[i][2].addTab();
            degreeRuns[i][2].addTab();
            degreeRuns[i][3] = degreePara[i][1].createRun();
            if (degree.getCredential() != null)
                degreeRuns[i][3].setText(degree.getCredential());
            else
                degreeRuns[i][3].setText("Your Degree Credential Here");

            // Field of Study Line
            degreePara[i][2] = document.createParagraph();
            degreePara[i][2].setSpacingAfter(0);
            degreePara[i][2].setSpacingBefore(0);
            degreePara[i][2].setSpacingBetween(1);
            degreeRuns[i][4] = degreePara[i][2].createRun();
            degreeRuns[i][4].addTab();
            degreeRuns[i][4].setBold(true);
            degreeRuns[i][4].setText("Field of Study:");
            degreeRuns[i][4].addTab();
            degreeRuns[i][4].addTab();
            degreeRuns[i][5] = degreePara[i][2].createRun();
            if (degree.getFieldOfStudy() != null && degree.getFieldOfStudy().equals("^") == false)
                degreeRuns[i][5].setText(degree.getFieldOfStudy());
            else
                degreeRuns[i][5].setText("Your Field of Study Here");

            // Dates of Attendance Line
            degreePara[i][3] = document.createParagraph();
            degreePara[i][3].setSpacingAfter(0);
            degreePara[i][3].setSpacingBefore(0);
            degreePara[i][3].setSpacingBetween(1);
            degreeRuns[i][6] = degreePara[i][3].createRun();
            degreeRuns[i][6].addTab();
            degreeRuns[i][6].setBold(true);
            degreeRuns[i][6].setText("Dates of Attendance:");
            degreeRuns[i][6].addTab();
            degreeRuns[i][7] = degreePara[i][3].createRun();
            if (degree.getAttendancePeriods() != null) {
                attendanceDates = utilities.FormattingUtility.dateRangeConvert(degree.getAttendancePeriods());
                degreeRuns[i][7].setText(utilities.FormattingUtility.formatDate(attendanceDates[0]) + " - "
                       + utilities.FormattingUtility.formatDate(attendanceDates[1]));
            } else
                degreeRuns[i][7].setText("Your Attendance Period Here");

            // Date of Degree Conferral Line
            degreePara[i][4] = document.createParagraph();
            degreePara[i][4].setSpacingAfter(0);
            degreePara[i][4].setSpacingBefore(0);
            degreePara[i][4].setSpacingBetween(1);
            degreeRuns[i][8] = degreePara[i][4].createRun();
            degreeRuns[i][8].addTab();
            degreeRuns[i][8].setBold(true);
            degreeRuns[i][8].setText("Date of Degree:");
            degreeRuns[i][8].addTab();
            degreeRuns[i][8].addTab();
            degreeRuns[i][9] = degreePara[i][4].createRun();
            if (degree.getDegreeDate() != null)
                degreeRuns[i][9].setText(utilities.FormattingUtility.formatDate(degree.getDegreeDate()));
            else
                degreeRuns[i][9].setText("Your Date of Degree Conferral Here");
            degreeRuns[i][9].addCarriageReturn();
        }
        
        degreeRuns[degreeRuns.length - 1][9].addCarriageReturn();
        
        if (tems != null && tems.isEmpty() == false) {
            texpPara = new XWPFParagraph[tems.size()][5];
            texpRuns = new XWPFRun[tems.size()][10];
            texp = tems.get(0);
        } else {
            texpPara = new XWPFParagraph[1][5];
            texpRuns = new XWPFRun[1][10];
        }
        
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
        
        if (tems == null)
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
        
        texpRuns[texpRuns.length - 1][9].addCarriageReturn();
        
        if (workExps != null && workExps.isEmpty() == false) {
            workExpPara = new XWPFParagraph[workExps.size()][5];
            workExpRuns = new XWPFRun[workExps.size()][10];
            workExp = workExps.get(0);
        }
        
        XWPFParagraph workExpTitle = document.createParagraph();
        workExpTitle.setSpacingAfter(0);
        workExpTitle.setSpacingBefore(0);
        workExpTitle.setSpacingBetween(1);
        XWPFRun workExpRun = workExpTitle.createRun();
        workExpRun.setText("C.");
        XWPFRun workExpRun2 = workExpTitle.createRun();
        workExpRun2.setBold(true);
        workExpRun2.setText("   Other Work Experience");
        
        if (workExps != null) {
            for (int i = 0; i < workExpPara.length; i++) {
                workExp = workExps.get(i);

                workExpPara[i][0] = document.createParagraph();
                workExpPara[i][0].setSpacingAfter(0);
                workExpPara[i][0].setSpacingBefore(0);
                workExpPara[i][0].setSpacingBetween(1);
                workExpRuns[i][0] = workExpPara[i][0].createRun();
                workExpRuns[i][0].addTab();
                workExpRuns[i][0].setBold(true);
                workExpRuns[i][0].setText("Institution:");
                workExpRuns[i][0].addTab();
                workExpRuns[i][0].addTab();
                workExpRuns[i][1] = workExpPara[i][0].createRun();
                if (workExp.getInstitution() != null)
                    workExpRuns[i][1].setText(workExp.getInstitution());
                else
                    workExpRuns[i][1].setText("Your Institution Here");

                workExpPara[i][1] = document.createParagraph();
                workExpPara[i][1].setSpacingAfter(0);
                workExpPara[i][1].setSpacingBefore(0);
                workExpPara[i][1].setSpacingBetween(1);
                workExpRuns[i][2] = workExpPara[i][1].createRun();
                workExpRuns[i][2].addTab();
                workExpRuns[i][2].setBold(true);
                workExpRuns[i][2].setText("Title:");
                workExpRuns[i][2].addTab();
                workExpRuns[i][2].addTab();
                workExpRuns[i][2].addTab();
                workExpRuns[i][3] = workExpPara[i][1].createRun();
                if (workExp.getTitle() != null)
                    workExpRuns[i][3].setText(workExp.getTitle());
                else
                    workExpRuns[i][3].setText("Your Title Here");

                workExpPara[i][2] = document.createParagraph();
                workExpPara[i][2].setSpacingAfter(0);
                workExpPara[i][2].setSpacingBefore(0);
                workExpPara[i][2].setSpacingBetween(1);
                workExpRuns[i][4] = workExpPara[i][2].createRun();
                workExpRuns[i][4].addTab();
                workExpRuns[i][4].setBold(true);
                workExpRuns[i][4].setText("Dates of Experience:");
                workExpRuns[i][4].addTab();
                workExpRuns[i][5] = workExpPara[i][2].createRun();
                if (workExp.getExperienceDates() != null) {
                datesOfExp = utilities.FormattingUtility.dateRangeConvert(workExp.getExperienceDates());
                workExpRuns[i][5].setText(utilities.FormattingUtility.formatMonthyear(datesOfExp[0]) 
                        + " - " + utilities.FormattingUtility.formatMonthyear(datesOfExp[1]));
                } else {
                    workExpRuns[i][5].setText("Your Dates of Experience Here");
                }

                workExpPara[i][3] = document.createParagraph();
                workExpPara[i][3].setSpacingAfter(0);
                workExpPara[i][3].setSpacingBefore(0);
                workExpPara[i][3].setSpacingBetween(1);
                workExpRuns[i][6] = workExpPara[i][3].createRun();
                workExpRuns[i][6].addTab();
                workExpRuns[i][6].setBold(true);
                workExpRuns[i][6].setText("Years:");
                workExpRuns[i][6].addTab();
                workExpRuns[i][6].addTab();
                workExpRuns[i][6].addTab();
                workExpRuns[i][7] = workExpPara[i][3].createRun();
                if (workExp.getYearsOfService() != 0) {
                    if (workExp.getYearsOfService() > 1)
                        workExpRuns[i][7].setText(workExp.getYearsOfService() + " Years");
                    else
                        workExpRuns[i][7].setText(workExp.getYearsOfService() + " Year");
                } else
                    workExpRuns[i][7].setText("Your Years of Experience Here");

                workExpPara[i][4] = document.createParagraph();
                workExpPara[i][4].setSpacingAfter(0);
                workExpPara[i][4].setSpacingBefore(0);
                workExpPara[i][4].setSpacingBetween(1);
                workExpRuns[i][8] = workExpPara[i][4].createRun();
                workExpRuns[i][8].addTab();
                workExpRuns[i][8].setBold(true);
                workExpRuns[i][8].setText("Full/Part Time Status:");
                workExpRuns[i][8].addTab();
                workExpRuns[i][9] = workExpPara[i][4].createRun();
                if (workExp.isFullTime() == true)
                    workExpRuns[i][9].setText("Full Time");
                else
                    workExpRuns[i][9].setText("Part Time");
                workExpRuns[i][9].addCarriageReturn();
            }
        } else {
            XWPFParagraph notAppl = document.createParagraph();
            notAppl.setSpacingAfter(0);
            notAppl.setSpacingBefore(0);
            notAppl.setSpacingBetween(1);
            XWPFRun run = notAppl.createRun();
            run.addTab();
            run.setItalic(true);
            run.setText("Not Applicable");
        }
        
        workExpRuns[workExpRuns.length - 1][9].addCarriageReturn();
        
        // Set Download info
        String relativePath = getServletContext().getRealPath("");
        ServletContext context = getServletContext();
        String mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        response.setContentType(mimeType);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "OtherWorkExperience.docx");
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
